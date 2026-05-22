public class Snippet__5907719 {

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
    	if (argumentCount() > 0) {
    	    argument().typeCheck(stable);
    	}
    	return _type = Type.Real;
        }

}
