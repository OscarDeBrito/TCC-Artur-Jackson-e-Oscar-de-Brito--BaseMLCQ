public class Snippet__5328561 {

    @Override
            public void process(OutputStream out) throws IOException {
                IOUtils.write("<docs>", out, StandardCharsets.UTF_8);
                for (SolrDocument doc : response.getResults()) {
                    final String xml = ClientUtils.toXML(toSolrInputDocument(doc));
                    IOUtils.write(xml, out, StandardCharsets.UTF_8);
                }
                IOUtils.write("</docs>", out, StandardCharsets.UTF_8);
            }

}
