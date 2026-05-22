public class Snippet__4413486 {

    private void setupHeaders(BindingOperationInfo op,
                                  BindingMessageInfo bMsg,
                                  BindingMessageInfo unwrappedBMsg,
                                  MessageInfo msg,
                                  SoapBindingConfiguration config) {
            List<MessagePartInfo> parts = new ArrayList<>();
            for (MessagePartInfo part : msg.getMessageParts()) {
                if (config.isHeader(op, part)) {
                    SoapHeaderInfo headerInfo = new SoapHeaderInfo();
                    headerInfo.setPart(part);
                    headerInfo.setUse(config.getUse());

                    bMsg.addExtensor(headerInfo);
                } else {
                    parts.add(part);
                }
            }
            unwrappedBMsg.setMessageParts(parts);
        }

}
