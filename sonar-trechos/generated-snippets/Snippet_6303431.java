public class Snippet__6303431 {

    @Override
        public String toString()
        {
            return String.format("%s[requested=\"%s\", negotiated=\"%s\"]",
                    getClass().getSimpleName(),
                    configRequested.getParameterizedName(),
                    configNegotiated.getParameterizedName());
        }

}
