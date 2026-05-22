@RequestFilters(AuthenticationFilter.class)
@QueryParams(keys = "response", values = "json")
public interface AddressApi {

   /**
    * Lists IPAddresses
    * 
    * @param options
    *           if present, how to constrain the list.
    * @return IPAddresses matching query, or empty set, if no IPAddresses are
    *         found
    */
   @Named("listPublicIpAddresses")
   @GET
   @QueryParams(keys = { "command", "listAll" }, values = { "listPublicIpAddresses", "true" })
   @SelectJson("publicipaddress")
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(EmptySetOnNotFoundOr404.class)
   Set<PublicIPAddress> listPublicIPAddresses(ListPublicIPAddressesOptions... options);

   /**
    * get a specific IPAddress by id
    * 
    * @param id
    *           IPAddress to get
    * @return IPAddress or null if not found
    */
   @Named("listPublicIpAddresses")
   @GET
   @QueryParams(keys = { "command", "listAll" }, values = { "listPublicIpAddresses", "true" })
   @SelectJson("publicipaddress")
   @OnlyElement
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(NullOnNotFoundOr404.class)
   PublicIPAddress getPublicIPAddress(@QueryParam("id") String id);

   /**
    * Acquires and associates a public IP to an account.
    * 
    * @param zoneId
    *           the ID of the availability zone you want to acquire an public IP
    *           address from
    * @return IPAddress
    */
   @Named("associateIpAddress")
   @GET
   @QueryParams(keys = "command", values = "associateIpAddress")
   @Unwrap
   @Consumes(MediaType.APPLICATION_JSON)
   AsyncCreateResponse associateIPAddressInZone(@QueryParam("zoneid") String zoneId,
         AssociateIPAddressOptions... options);

   /**
    * Disassociates an ip address from the account.
    * 
    * @param id
    *           the id of the public ip address to disassociate
    */
   @Named("disassociateIpAddress")
   @GET
   @QueryParams(keys = "command", values = "disassociateIpAddress")
   @Fallback(VoidOnNotFoundOr404OrUnableToFindAccountOwner.class)
   void disassociateIPAddress(@QueryParam("id") String id);
   
}
