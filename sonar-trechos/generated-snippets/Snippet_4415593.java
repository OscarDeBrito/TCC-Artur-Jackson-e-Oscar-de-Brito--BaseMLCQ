public class Snippet__4415593 {

    @SuppressWarnings("unchecked")
            @Override
            public void write(T data) throws IOException {
                Class<?> actualCls = entityCls != Object.class ? entityCls : data.getClass();
                if (writer == null) {
                    writer = (MessageBodyWriter<T>)providers.getMessageBodyWriter(actualCls, actualCls, anns, mt);
                    if (writer == null) {
                        throw new InternalServerErrorException();
                    }
                }
                writer.writeTo(data, actualCls, actualCls, anns, mt, headers, os);
            }

}
