public class GlideAdapter extends ImageListAdapter {

  public GlideAdapter(
      Context context,
      PerfListener perfListener) {
    super(context, perfListener);
  }

  @Override
  public GlideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final InstrumentedImageView instrumentedImageView = new InstrumentedImageView(getContext());
    return new GlideHolder(getContext(), parent, instrumentedImageView, getPerfListener());
  }

  @Override
  public void shutDown() {
    GlideApp.get(getContext()).clearMemory();
  }
}
