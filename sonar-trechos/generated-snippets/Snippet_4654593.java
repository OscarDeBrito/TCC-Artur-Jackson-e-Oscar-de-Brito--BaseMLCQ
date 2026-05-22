public class Snippet__4654593 {

    @Override
    	public void write(DataOutputView out) throws IOException {
    		out.writeInt(position);

    		for (int i = 0; i < position; i++) {
    			out.writeDouble(data[i]);
    		}
    	}

}
