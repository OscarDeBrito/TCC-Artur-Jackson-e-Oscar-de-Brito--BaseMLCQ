public class Snippet__5718043 {

    @Override
    		public Iterator<Tuple2<ValueIndexPair,Double>> call(Tuple2<MatrixIndexes,MatrixBlock> arg0) 
    			throws Exception 
    		{
    			ArrayList<Tuple2<ValueIndexPair,Double>> ret = new ArrayList<>(); 
    			MatrixIndexes ix = arg0._1();
    			MatrixBlock mb = arg0._2();
			
    			long ixoffset = (ix.getRowIndex()-1)*_brlen;
    			for( int i=0; i<mb.getNumRows(); i++) {
    				double val = mb.quickGetValue(i, 0);
    				ret.add(new Tuple2<>(new ValueIndexPair(val,ixoffset+i+1), val));
    			}
			
    			return ret.iterator();
    		}

}
