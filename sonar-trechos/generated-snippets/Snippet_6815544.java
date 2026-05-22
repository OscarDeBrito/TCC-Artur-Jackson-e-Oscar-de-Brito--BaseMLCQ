private final class LocalVariables {
    private final BitSet availableSlots = new BitSet();
    private final Deque<Map<String, VarDefn>> currentScope = new ArrayDeque<>();
    private final BitSet slotsToRelease = new BitSet();

    /** Tracks the next unused slot to claim. */
    private int nextSlotToClaim = 0;

    /**
     * A counter that tracks when to release the {@link #slotsToRelease} set.
     *
     * <p>We add {@link #slotsToRelease} to {@link #availableSlots} only when exiting a scope if
     * this value == 0.
     */
    private int activeLazySlots = 0;

    /**
     * Enters a new scope. Variables {@link #define defined} will have a lifetime that extends until
     * a matching call to {@link #exitScope()}.
     */
    void enterScope() {
      currentScope.push(new LinkedHashMap<String, VarDefn>());
    }

    /**
     * Enters a new scope.
     *
     * <p>Variables defined in a lazy scope have a lifetime that extends to the matching {@link
     * #exitLazyScope()} call, but the variable slots reserved have their lifetimes extended until
     * the parent scope closes.
     */
    void enterLazyScope() {
      activeLazySlots++;
      enterScope();
    }

    /** Exits the current scope. */
    void exitLazyScope() {
      checkState(activeLazySlots > 0, "Exiting a lazy scope when we aren't in one");
      exitScope();
      activeLazySlots--;
    }

    /**
     * Exits the current lazy scope.
     *
     * <p>This releases all the variable indices associated with the variables defined in this frame
     * so that they can be reused.
     */
    void exitScope() {
      Map<String, VarDefn> variablesGoingOutOfScope = currentScope.pop();
      for (VarDefn var : variablesGoingOutOfScope.values()) {
        if (var instanceof LoopVar) {
          LoopVar loopVar = (LoopVar) var;
          slotsToRelease.set(loopVar.currentLoopIndexIndex());
          slotsToRelease.set(loopVar.isLastIteratorIndex());
        }
        slotsToRelease.set(var.localVariableIndex());
      }
      if (activeLazySlots == 0) {
        availableSlots.or(slotsToRelease);
        slotsToRelease.clear();
      }
    }

    /**
     * Returns the {@link VarDefn} associated with the given name by searching through the current
     * scope and all parent scopes.
     */
    VarDefn lookup(String name) {
      for (Map<String, VarDefn> scope : currentScope) {
        VarDefn defn = scope.get(name);
        if (defn != null) {
          return defn;
        }
      }
      return null;
    }

    /**
     * Defines a {@link LoopVar}. Unlike normal local variables and params loop variables get 2
     * extra implicit local variables for tracking the current index and whether or not we are at
     * the last index.
     */
    boolean define(LoopVar defn, SoyNode definingNode) {
      if (!define((VarDefn) defn, definingNode)) {
        return false;
      }
      // only allocate the extra slots if definition succeeded
      defn.setExtraLoopIndices(claimSlot(), claimSlot());
      return true;
    }

    /** Defines a variable. */
    boolean define(VarDefn defn, SoyNode definingNode) {
      // Search for the name to see if it is being redefined.
      VarDefn preexisting = lookup(defn.name());
      if (preexisting != null) {
        Optional<SourceLocation> sourceLocation = forVarDefn(preexisting);
        String location =
            sourceLocation.isPresent() ? " at line " + sourceLocation.get().getBeginLine() : "";
        errorReporter.report(
            definingNode.getSourceLocation(), VARIABLE_ALREADY_DEFINED, defn.name(), location);
        return false;
      }
      currentScope.peek().put(defn.name(), defn);
      defn.setLocalVariableIndex(claimSlot());
      return true;
    }

    /**
     * Returns the smallest available local variable slot or claims a new one if there is none
     * available.
     */
    private int claimSlot() {
      int nextSetBit = availableSlots.nextSetBit(0);
      int slotToUse;
      if (nextSetBit != -1) {
        slotToUse = nextSetBit;
        availableSlots.clear(nextSetBit);
      } else {
        slotToUse = nextSlotToClaim;
        nextSlotToClaim++;
      }
      return slotToUse;
    }

    void verify() {
      checkState(activeLazySlots == 0, "%s lazy scope(s) are still active", activeLazySlots);
      checkState(slotsToRelease.isEmpty(), "%s slots are waiting to be released", slotsToRelease);
      BitSet unavailableSlots = new BitSet(nextSlotToClaim);
      unavailableSlots.set(0, nextSlotToClaim);
      // now the only bits on will be the ones where available slots has '0'.
      unavailableSlots.xor(availableSlots);
      checkState(
          unavailableSlots.isEmpty(), "Expected all slots to be available: %s", unavailableSlots);
    }
  }
