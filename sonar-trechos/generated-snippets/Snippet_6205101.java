public interface IDiagramLayoutConnector {

    /**
     * Build a KGraph instance for the given diagram. The resulting layout graph should reflect
     * the structure of the original diagram. All graph elements must have
     * {@link org.eclipse.elk.core.klayoutdata.KShapeLayout KShapeLayouts} or
     * {@link org.eclipse.elk.core.klayoutdata.KEdgeLayout KEdgeLayouts} attached,
     * and their modification flags must be set to {@code false}.
     * 
     * <p>At least one of the two parameters must be non-null.</p>
     * 
     * <p>This method is usually called from the UI thread.</p>
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed, or {@code null} if there
     *            is no workbench part for the diagram
     * @param diagramPart
     *            the parent object for which layout is performed, or {@code null} if the
     *            whole diagram shall be layouted
     * @return a layout graph mapping, or {@code null} if the given workbench part or diagram part
     *            is not supported
     */
    LayoutMapping buildLayoutGraph(IWorkbenchPart workbenchPart, Object diagramPart);

    /**
     * Apply the computed layout back to the diagram. Graph elements whose modification flag
     * was not set during layout should be ignored.
     * 
     * <p>This method is usually called from the UI thread.</p>
     * 
     * @param mapping a layout mapping that was created by this layout connector
     * @param settings general settings for applying layout, e.g. whether to use animation
     */
    void applyLayout(LayoutMapping mapping, IPropertyHolder settings);
    
}
