public class Snippet__5904841 {

    public BranchInstruction GE(boolean tozero) {
    	return tozero ? (BranchInstruction) new IFGE(null) : 
    	    (BranchInstruction) new IF_ICMPGE(null);
        }

}
