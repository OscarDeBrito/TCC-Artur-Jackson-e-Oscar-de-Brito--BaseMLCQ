public class CMSHeap extends GenCollectedHeap {

  public CMSHeap(Address addr) {
    super(addr);
  }

  public CollectedHeapName kind() {
    return CollectedHeapName.CMS;
  }
}
