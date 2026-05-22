class Foo {
    static void doit() throws Exception {
        System.out.println("*** About to invoke getThreadContextClassLoader().getResource()");

        URL r = Thread.currentThread().getContextClassLoader().getResource("/org/apache/aries/spifly/test/blah.txt");
        System.out.println("*** Found resource: " + r);
        System.out.println("*** First line of content: " + new BufferedReader(new InputStreamReader(r.openStream())).readLine());
    }
}
