public class Snippet__4737119 {

    public boolean iterate(DoubleWritable xi) throws HiveException {
                if (xi == null) {// skip
                    return true;
                }
                if (partial == null) {
                    this.partial = new PartialResult();
                }
                partial.iterate(xi.get());
                return true;
            }

}
