public class Snippet__8346160 {

    public static TypePattern read(VersionedDataInputStream s, ISourceContext c) throws IOException {
    		AnnotationTypePattern annPatt = AnnotationTypePattern.read(s, c);
    		AnyWithAnnotationTypePattern ret = new AnyWithAnnotationTypePattern(annPatt);
    		ret.readLocation(c, s);
    		return ret;
    	}

}
