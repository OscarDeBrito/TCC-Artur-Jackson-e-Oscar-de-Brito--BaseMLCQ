public class Snippet__7746473 {

    @Override
        @Deprecated
        public void reset(org.apache.dubbo.common.Parameters parameters) {
            reset(getUrl().addParameters(parameters.getParameters()));
        }

}
