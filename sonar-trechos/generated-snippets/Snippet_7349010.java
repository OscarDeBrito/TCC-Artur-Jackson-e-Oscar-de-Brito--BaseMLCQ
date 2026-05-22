public class Snippet__7349010 {

    @Override
        public String getErrorMessage(final NTLMState state) {
            Check.notNull(state, "state"); //$NON-NLS-1$
            Check.isTrue(state instanceof NativeNTLMState, "state instanceof NativeNTLMState"); //$NON-NLS-1$

            try {
                return NativeAuth.authGetErrorMessage(((NativeNTLMState) state).id);
            } catch (final Exception e) {
                return MessageFormat.format(
                    Messages.getString("NativeNTLM.CouldNotCallNativeNTLMFormat"), //$NON-NLS-1$
                    e.getLocalizedMessage());
            }
        }

}
