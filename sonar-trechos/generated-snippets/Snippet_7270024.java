public class Snippet__7270024 {

    public void paintItem(ChartItem item, List<ItemSelection> highlighted,
                              List<ItemSelection> selected, Graphics2D g,
                              Rectangle dirtyArea, ChartContext context) {
    //        if (!(item instanceof ProfilerXYItem))
    //            throw new UnsupportedOperationException("Unsupported item: " + item); // NOI18N
    //        if (!(context instanceof ProfilerXYChartComponent.Context))
    //            throw new UnsupportedOperationException("Unsupported context: " + context);
        
            paint((SynchronousXYItem)item, highlighted, selected, g, dirtyArea,
                  (SynchronousXYChartContext)context);
        }

}
