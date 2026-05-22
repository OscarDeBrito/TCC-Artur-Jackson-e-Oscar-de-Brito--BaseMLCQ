public class Snippet__9449118 {

    @Override
            public Void visitAnnotation(Annotation_element_value ev, Boolean resolveIndices) {
                print((char) ev.tag);
                AnnotationWriter.this.write(ev.annotation_value, resolveIndices);
                return null;
            }

}
