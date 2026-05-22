public class AQueryAdapter extends ImageListAdapter {

  private AQuery mAQuery;

  public AQueryAdapter(
      Context context,
      PerfListener perfListener) {
    super(context, perfListener);
    mAQuery = new AQuery(context);
  }

  @Override
  public AQueryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final InstrumentedImageView instrImageView = new InstrumentedImageView(getContext());
    return new AQueryHolder(getContext(), mAQuery, parent, instrImageView, getPerfListener());
  }

  @Override
  public void shutDown() {
    for (int i = 0; i < getItemCount(); i++) {
      String uri = getItem(i);
      mAQuery.invalidate(uri);
    }
    super.clear();
  }
}
