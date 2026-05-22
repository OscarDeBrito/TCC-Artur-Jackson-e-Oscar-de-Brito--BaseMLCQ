abstract class ReferenceLookupHelper extends LookupHelper {

        /** The member reference tree */
        JCMemberReference referenceTree;

        ReferenceLookupHelper(JCMemberReference referenceTree, Name name, Type site,
                List<Type> argtypes, List<Type> typeargtypes, MethodResolutionPhase maxPhase) {
            super(name, site, argtypes, typeargtypes, maxPhase);
            this.referenceTree = referenceTree;
        }

        /**
         * Returns an unbound version of this lookup helper. By default, this
         * method returns an dummy lookup helper.
         */
        ReferenceLookupHelper unboundLookup(InferenceContext inferenceContext) {
            return null;
        }

        /**
         * Get the kind of the member reference
         */
        abstract JCMemberReference.ReferenceKind referenceKind(Symbol sym);

        Symbol access(Env<AttrContext> env, DiagnosticPosition pos, Symbol location, Symbol sym) {
            if (sym.kind == AMBIGUOUS) {
                AmbiguityError a_err = (AmbiguityError)sym.baseSymbol();
                sym = a_err.mergeAbstracts(site);
            }
            //skip error reporting
            return sym;
        }
    }
