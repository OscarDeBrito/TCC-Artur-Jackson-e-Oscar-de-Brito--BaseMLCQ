class LinkFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    LinkFrame next;
  Map<OLink, LinkInfo> links = new HashMap<OLink, LinkInfo>();

  LinkFrame(LinkFrame next) {
    this.next = next;
  }

  LinkInfo resolve(OLink link) {
    LinkInfo li = links.get(link);
    if (li == null && next != null)
      return next.resolve(link);
    return li;
  }

}
