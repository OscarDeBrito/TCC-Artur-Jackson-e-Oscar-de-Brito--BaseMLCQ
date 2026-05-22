public class Snippet__3725378 {

    @Override
            protected List<ClassStructure> initialValue() {
                final List<ClassStructure> annotationTypeClassStructures = new ArrayList<ClassStructure>();
                accept(new ClassVisitor(ASM7) {

                    @Override
                    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                        if (visible) {
                            final ClassStructure annotationTypeClassStructure = newInstance(Type.getType(desc).getClassName());
                            if (null != annotationTypeClassStructure) {
                                annotationTypeClassStructures.add(annotationTypeClassStructure);
                            }
                        }
                        return super.visitAnnotation(desc, visible);
                    }

                });
                return annotationTypeClassStructures;
            }

}
