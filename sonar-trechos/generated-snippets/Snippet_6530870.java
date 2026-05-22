public class Snippet__6530870 {

    @Override
        public void setState(State state) {
            if (isAcceptedState(acceptedDataTypes, state)) {
                super.setState(state);
            } else {
                logSetTypeError(state);
            }
        }

}
