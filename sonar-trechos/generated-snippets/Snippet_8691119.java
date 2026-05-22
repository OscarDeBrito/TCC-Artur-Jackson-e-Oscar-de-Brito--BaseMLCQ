public static class ContainerField extends FieldInfo {

        private ContainerField(ContainerField original, ModelFormField modelFormField) {
            super(original.getFieldSource(), original.getFieldType(), modelFormField);
        }

        public ContainerField(Element element, ModelFormField modelFormField) {
            super(element, modelFormField);
        }

        public ContainerField(int fieldSource, int fieldType, ModelFormField modelFormField) {
            super(fieldSource, fieldType, modelFormField);
        }

        @Override
        public void accept(ModelFieldVisitor visitor) throws Exception {
            visitor.visit(this);
        }

        @Override
        public FieldInfo copy(ModelFormField modelFormField) {
            return new ContainerField(this, modelFormField);
        }

        @Override
        public void renderFieldString(Appendable writer, Map<String, Object> context, FormStringRenderer formStringRenderer)
                throws IOException {
            formStringRenderer.renderContainerFindField(writer, context, this);
        }
    }
