@RequestFilters(AuthenticateRequest.class)
public interface SessionPersistenceApi {
   /**
    * Get the current session persistence.
    * 
    * @see SessionPersistence
    */
   @Named("sessionpersistence:get")
   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @ResponseParser(ParseSessionPersistence.class)
   @Fallback(NullOnNotFoundOr404.class)
   @Path("/sessionpersistence")
   SessionPersistence get();
   
   /**
    * Create session persistence.
    * 
    * @see SessionPersistence
    */
   @Named("sessionpersistence:create")
   @PUT
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(VoidOnNotFoundOr404.class)
   @Payload("%7B\"sessionPersistence\":%7B\"persistenceType\":\"{sessionPersistence}\"%7D%7D")
   @Path("/sessionpersistence")
   void create(@PayloadParam("sessionPersistence") SessionPersistence sessionPersistence);
   
   /**
    * Delete session persistence.
    * 
    * @see SessionPersistence
    */
   @Named("sessionpersistence:delete")
   @DELETE
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(VoidOnNotFoundOr404.class)
   @Path("/sessionpersistence")
   void delete();
}
