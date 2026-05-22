public class Snippet__4041399 {

    @Override
      public PCollection<OutputT> expand(PCollection<InputT> input) {
        PCollection<KV<Long, InputT>> in;
        in = input.apply(getName("wrap"), ParDo.of(new Wrap<>()));
        if (input.getTypeDescriptor() != null) {
          in =
              in.setTypeDescriptor(
                  TypeDescriptors.kvs(TypeDescriptors.longs(), input.getTypeDescriptor()));
        }
        return timestampedTransform.apply(in);
      }

}
