public class Snippet__6244944 {

    @Override
        public void get(
                final String tenantId,
                final String type,
                final String authId,
                final JsonObject clientContext,
                final Span span,
                final Handler<AsyncResult<CredentialsResult<JsonObject>>> resultHandler) {

            Objects.requireNonNull(tenantId);
            Objects.requireNonNull(type);
            Objects.requireNonNull(authId);
            Objects.requireNonNull(resultHandler);

            final JsonObject data = getSingleCredentials(tenantId, authId, type, clientContext, span);
            if (data == null) {
                resultHandler.handle(Future.succeededFuture(CredentialsResult.from(HttpURLConnection.HTTP_NOT_FOUND)));
            } else {
                resultHandler.handle(Future.succeededFuture(
                        CredentialsResult.from(HttpURLConnection.HTTP_OK, data.copy(), CacheDirective.noCacheDirective())));
            }
        }

}
