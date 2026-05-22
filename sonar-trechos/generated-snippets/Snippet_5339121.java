public class Snippet__5339121 {

    private String getOwnerPartition(final File swapFile) {
            final String filename = swapFile.getName();
            final int indexOfDot = filename.indexOf(".");
            if (indexOfDot < 1) {
                return null;
            }

            final int lastIndexOfDot = filename.lastIndexOf(".");
            if (lastIndexOfDot == indexOfDot) {
                return null;
            }

            return filename.substring(indexOfDot + 1, lastIndexOfDot);
        }

}
