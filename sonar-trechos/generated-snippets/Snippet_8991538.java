public class Snippet__8991538 {

    private void refill() throws IOException {
        offset += usableLength;
        int leftover = length - usableLength;
        System.arraycopy(buffer, usableLength, buffer, 0, leftover);
        int requested = buffer.length - leftover;
        int returned = read(input, buffer, leftover, requested);
        length = returned + leftover;
        if (returned < requested) /* reader has been emptied, process the rest */
          usableLength = length;
        else { /* still more data to be read, find a safe-stopping place */
          usableLength = findSafeEnd();
          if (usableLength < 0)
            usableLength = length; /*
                                    * more than IOBUFFER of text without space,
                                    * gonna possibly truncate tokens
                                    */
        }

        breaker.setText(buffer, 0, Math.max(0, usableLength));
      }

}
