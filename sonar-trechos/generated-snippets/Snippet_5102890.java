public class Snippet__5102890 {

    protected Item parseItem(Element rssRoot, Element eItem) {
            Item item = super.parseItem(rssRoot,eItem);
            Element e = eItem.getChild("description", getRSSNamespace());
            if (e!=null) {
                item.setDescription(parseItemDescription(rssRoot,e));
            }
            Element ce = eItem.getChild("encoded", getContentNamespace());
            if (ce != null) {
                Content content = new Content();
                content.setType(Content.HTML);
                content.setValue(ce.getText());
                item.setContent(content);
            }
            return item;
        }

}
