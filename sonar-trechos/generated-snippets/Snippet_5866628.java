public final class WideLeftAnnotationSideAction extends BaseSelectionListenerAction {
  
  public static final String ID = "WideLeftAnnotationSide";
  
  private ICasEditor editor;

  /**
   * Initializes a new instance.
   *
   * @param editor
   */
  public WideLeftAnnotationSideAction(ICasEditor editor) {
    super("WideLeftAnnotationSside");

    this.editor = editor;

    setEnabled(false);
  }

  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    AnnotationSelection annotation = new AnnotationSelection(selection);

    return annotation.size() == 1;
  }

  /**
   * Widens the annotation and sends and sends an update notification
   * to the provided document.
   * 
   * @param document
   * @param annotation
   */
  public static void wideLeftAnnotationSide(ICasDocument document, AnnotationFS annotation) {
    Type annotationType = annotation.getType();
    Feature beginFeature = annotationType.getFeatureByBaseName("begin");

    if (annotation.getBegin() > 0) {
      annotation.setIntValue(beginFeature, annotation.getBegin() - 1);
    }

    document.update(annotation);
  }
  
  /**
   * Decreases the begin index of an annotation by one.
   */
  @Override
  public void run() {
    AnnotationSelection annotations = new AnnotationSelection(getStructuredSelection());

    AnnotationFS annotation = annotations.getFirst();
    
    wideLeftAnnotationSide(editor.getDocument(), annotation);
  }
}
