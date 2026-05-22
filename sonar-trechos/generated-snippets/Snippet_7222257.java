public class Snippet__7222257 {

    @Override
    		public void onSubscribe(Subscription s) {
    			if (Operators.validate(this.s, s)) {
    				this.s = s;

    				actual.onSubscribe(this);

    				s.request(Long.MAX_VALUE);
    			}
    		}

}
