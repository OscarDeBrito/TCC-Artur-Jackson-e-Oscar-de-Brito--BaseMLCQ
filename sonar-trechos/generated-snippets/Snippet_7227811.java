public class Snippet__7227811 {

    static final class SwitchOnFirstConditionalInnerSubscriber<T> implements InnerConsumer<T>,
                                                                                 Fuseable.ConditionalSubscriber<T> {

            final AbstractSwitchOnFirstInner<?, ? super T>  parent;
            final Fuseable.ConditionalSubscriber<? super T> inner;

            SwitchOnFirstConditionalInnerSubscriber(
                    AbstractSwitchOnFirstInner<?, ? super T> parent,
                    Fuseable.ConditionalSubscriber<? super T> inner) {
                this.parent = parent;
                this.inner = inner;
            }

            @Override
            public Context currentContext() {
                return inner.currentContext();
            }

            @Override
            public void onSubscribe(Subscription s) {
                inner.onSubscribe(s);
            }

            @Override
            public void onNext(T t) {
                inner.onNext(t);
            }

            @Override
            public boolean tryOnNext(T t) {
                return inner.tryOnNext(t);
            }

            @Override
            public void onError(Throwable throwable) {
                if (!parent.done) {
                    parent.cancel();
                }

                inner.onError(throwable);
            }

            @Override
            public void onComplete() {
                if (!parent.done) {
                    parent.cancel();
                }

                inner.onComplete();
            }

            @Override
            public Object scanUnsafe(Attr key) {
                if (key == Attr.PARENT) return parent;
                if (key == Attr.ACTUAL) return inner;

                return null;
            }
        }

}
