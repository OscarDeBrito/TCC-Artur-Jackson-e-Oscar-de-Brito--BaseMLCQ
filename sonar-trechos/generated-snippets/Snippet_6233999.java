public class Snippet__6233999 {

    @Override
    	public boolean equals(Object other) {
    		if (other instanceof Vector3D) {
    			Vector3D o = (Vector3D) other;
    			Point tmp = this.toPoint();
    			if (tmp == null) {
    				return o.toPoint() == null;
    			}
    			return tmp.equals(o.toPoint());
    		}
    		return false;
    	}

}
