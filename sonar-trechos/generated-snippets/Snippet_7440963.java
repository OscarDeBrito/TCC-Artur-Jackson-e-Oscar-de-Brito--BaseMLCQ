public class Snippet__7440963 {

    @TruffleBoundary
        private LLVMManagedPointer getApplicationArguments() {
            String[] result;
            if (mainArguments == null) {
                result = new String[]{""};
            } else {
                result = new String[mainArguments.length + 1];
                // we don't have an application path at this point in time. it will be overwritten when
                // _start is called
                result[0] = "";
                for (int i = 1; i < result.length; i++) {
                    result[i] = mainArguments[i - 1].toString();
                }
            }
            return toTruffleObjects(result);
        }

}
