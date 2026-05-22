public static class Response extends BasicResponse {

        public Response(HttpResponse response) {
            super(response);
        }

        public String getAppId() throws IOException {
            return JsonPath.read(getString(), "$.application-id");
        }
    }
