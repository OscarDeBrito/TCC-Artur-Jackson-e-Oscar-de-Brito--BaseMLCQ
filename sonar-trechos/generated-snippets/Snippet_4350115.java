public class Snippet__4350115 {

    public static	ExecIndexRow getIndexRowFromHeapRow(IndexRowGenerator irg,
    														RowLocation rl,
    														ExecRow heapRow)
    		throws StandardException
    	{
    		ExecIndexRow		indexRow;

    		indexRow = irg.getIndexRowTemplate();
    		// Get an index row based on the base row
    		irg.getIndexRow(heapRow, rl, indexRow, (FormatableBitSet) null);

    		return indexRow;
    	}

}
