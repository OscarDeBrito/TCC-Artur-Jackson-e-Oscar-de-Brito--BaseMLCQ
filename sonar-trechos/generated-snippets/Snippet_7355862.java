public class Snippet__7355862 {

    @Override
        public Object clone() throws CloneNotSupportedException {
            final HttpHost copy = (HttpHost) super.clone();
            copy.init(this);
            return copy;
        }

}
