public class Snippet__7228404 {

    @Override
            public Flux<?> flux() {
                sender = sender(senderOptions());
                Flux<Person> srcFlux = source().flux();
                return sender
                        .sendTransactionally(srcFlux.map(p -> records(p)))
                        .concatMap(r -> r)
                        .doOnNext(r -> log.info("Sent record successfully {}", r))
                        .doOnError(e -> log.error("Send failed, terminating.", e))
                        .doOnCancel(() -> close());
            }

}
