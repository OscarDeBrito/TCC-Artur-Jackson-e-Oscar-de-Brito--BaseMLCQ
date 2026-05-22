class IntIterator4bag<T extends FeatureStructure> extends FSIntIteratorImplBase<T> {

  private int itPos;

  final private FSBagIndex<T> fsBagIndex; // just an optimization, is == to fsLeafIndexImpl from super class, allows dispatch w/o casting
    

  IntIterator4bag(FSBagIndex<T> fsBagIndex, int[] detectIllegalIndexUpdates) {
    super(fsBagIndex, detectIllegalIndexUpdates);
    this.fsBagIndex = fsBagIndex;
    moveToFirst();
  }

  @Override
  public boolean isValid() {
    return fsBagIndex.isValid(this.itPos);
  }

  /**
   * If empty, make position -1 (invalid)
   */
  @Override
  public void moveToFirst() {
    resetConcurrentModification();
    this.itPos = fsBagIndex.moveToFirst();
  }

  /**
   * If empty, make position -1 (invalid)
   */
  @Override
  public void moveToLast() {
    resetConcurrentModification();
    this.itPos = fsBagIndex.moveToLast();
  }

  @Override
  public void moveToNext() {
    checkConcurrentModification(); 
    this.itPos = fsBagIndex.moveToNext(itPos);
  }

  @Override
  public void moveToPrevious() {
    checkConcurrentModification(); 
    this.itPos = fsBagIndex.moveToPrevious(itPos);
  }

  @Override
  public int get() {
    if (!isValid()) {
      throw new NoSuchElementException();
    }
    checkConcurrentModification(); 
    return fsBagIndex.get(itPos);
  }

  /**
   * @see org.apache.uima.internal.util.IntPointerIterator#copy()
   */
  @Override
  public Object copy() {
    IntIterator4bag<T> copy = new IntIterator4bag<T>(this.fsBagIndex, this.detectIllegalIndexUpdates);
    copy.itPos = this.itPos;
    return copy;
  }

  /**
   * @see org.apache.uima.internal.util.IntPointerIterator#moveTo(int)
   */
  @Override
  public void moveTo(int i) {
    resetConcurrentModification();
    this.itPos = fsBagIndex.findLeftmost(i);
  }

  @Override
  public int ll_indexSize() {
    return fsBagIndex.size();
  }

}
