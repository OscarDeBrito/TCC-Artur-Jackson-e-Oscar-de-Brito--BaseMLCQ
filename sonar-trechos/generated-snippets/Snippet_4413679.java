public class Snippet__4413679 {

    public void onClose(CachedOutputStream cos) {
                // bytes were already copied after flush
                try {
                    byte[] bytes = cos.getBytes();
                    synchronized (outbound) {
                        outbound.add(bytes);
                    }
                } catch (Exception e) {
                    LOG.fine("Can't record message from output stream class: "
                             + cos.getOut().getClass().getName());
                }
            }

}
