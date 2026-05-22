public class Snippet__6010673 {

    @Override
        @Value.Derived
        public Stacks stacks() {
            return new ReactorStacks(getConnectionContext(), getRootV2(), getTokenProvider());
        }

}
