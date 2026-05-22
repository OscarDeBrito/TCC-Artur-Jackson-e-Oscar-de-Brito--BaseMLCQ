public class Snippet__7124150 {

    @RestLiAssociation(name = "typerefCustomDoubleAssociationKeyResource",
                       namespace = "com.linkedin.restli.examples.greetings.client",
                       assocKeys = {@Key(name = "src", type = CustomDouble.class, typeref = CustomDoubleRef.class),
                           @Key(name = "dest", type = URI.class, typeref = UriRef.class)})
    public class TyperefCustomDoubleAssociationKeyResource extends AssociationResourceTemplate<Message>
    {
      @RestMethod.Get
      public Message get(final CompoundKey key, @QueryParam(value = "array",
                                                            typeref = CustomStringRef.class) final CustomString[] stringArray)
      {
        return new Message().setId(((CustomDouble) key.getPart("src")).toDouble() + "->" + ((URI) key.getPart(
            "dest")).getHost())
                            .setMessage(String.format("I need some $20. Array contents %s.", Arrays.asList(stringArray)))
                            .setTone(Tone.SINCERE);
      }
    }

}
