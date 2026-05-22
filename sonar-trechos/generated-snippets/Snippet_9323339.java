public class Snippet__9323339 {

    private Frame senderForInterpreterFrame(X86RegisterMap map) {
        if (DEBUG) {
          System.out.println("senderForInterpreterFrame");
        }
        Address unextendedSP = addressOfStackSlot(INTERPRETER_FRAME_SENDER_SP_OFFSET).getAddressAt(0);
        Address sp = addressOfStackSlot(SENDER_SP_OFFSET);
        // We do not need to update the callee-save register mapping because above
        // us is either another interpreter frame or a converter-frame, but never
        // directly a compiled frame.
        // 11/24/04 SFG. With the removal of adapter frames this is no longer true.
        // However c2 no longer uses callee save register for java calls so there
        // are no callee register to find.

        if (map.getUpdateMap())
          updateMapWithSavedLink(map, addressOfStackSlot(LINK_OFFSET));

        return new X86Frame(sp, unextendedSP, getLink(), getSenderPC());
      }

}
