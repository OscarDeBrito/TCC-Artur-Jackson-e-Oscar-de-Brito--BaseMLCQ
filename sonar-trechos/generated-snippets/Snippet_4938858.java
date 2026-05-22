@RequestFilters(AuthenticateRequest.class)
public interface LoadBalancerApi {
   /**
    * Create a new load balancer with the configuration defined by the request.
    * 
    * @return The object will contain a unique identifier and status of the request. Using the
    *         identifier, the caller can check on the progress of the operation by performing a
    *         {@link LoadBalancerApi#get}.
    */
   @Named("lb:create")
   @POST
   @ResponseParser(ParseLoadBalancer.class)
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(NullOnNotFoundOr404.class)
   @Path("/loadbalancers")
   LoadBalancer create(@WrapWith("loadBalancer") CreateLoadBalancer createLB);

   /**
    * Update the properties of a load balancer.
    * 
    * @return The object will contain a unique identifier and status of the request. Using the
    *         identifier, the caller can check on the progress of the operation by performing a
    *         {@link LoadBalancerApi#get}.
    */
   @Named("lb:update")
   @PUT
   @ResponseParser(ParseLoadBalancer.class)
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/loadbalancers/{id}")
   void update(@PathParam("id") int id, @WrapWith("loadBalancer") UpdateLoadBalancer updateLB);

   /**
    * List the load balancers.
    */
   @Named("lb:list")
   @GET
   @ResponseParser(ParseLoadBalancers.class)
   @Transform(ParseLoadBalancers.ToPagedIterable.class)
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/loadbalancers")
   @Fallback(EmptyPagedIterableOnNotFoundOr404.class)
   PagedIterable<LoadBalancer> list();
   
   /**
    * List the load balancers with full control of pagination.
    */
   @Named("lb:list")
   @GET
   @ResponseParser(ParseLoadBalancers.class)
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(EmptyPaginatedCollectionOnNotFoundOr404.class)
   @Path("/loadbalancers")
   IterableWithMarker<LoadBalancer> list(PaginationOptions options);

   /**
    * Get a load balancer.
    */
   @Named("lb:get")
   @GET
   @ResponseParser(ParseLoadBalancer.class)
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(NullOnNotFoundOr404.class)
   @Path("/loadbalancers/{id}")
   LoadBalancer get(@PathParam("id") int id);

   /**
    * Delete a load balancer.
    */
   @Named("lb:delete")
   @DELETE
   @Fallback(VoidOnNotFoundOr404.class)
   @Path("/loadbalancers/{id}")
   @Consumes("*/*")
   void delete(@PathParam("id") int id);
   
   /**
    * When a metadata item is added, it is assigned a unique identifier that can be used for mutating operations such
    * as changing the value attribute or removing it. Key and value must be 256 characters or less. 
    * All UTF-8 characters are valid.
    */
   @Named("lb:createmetadata")
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @ResponseParser(ParseMetadata.class)
   @Fallback(EmptyMapOnNotFoundOr404.class)
   @Path("/loadbalancers/{id}/metadata")
   Metadata createMetadata(@PathParam("id") int id, 
                           @BinderParam(BindMetadataToJsonPayload.class) Map<String, String> metadata);
    
   /**
    * List a load balancer's metadata.
    */
   @Named("lb:getmetadata")
   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @ResponseParser(ParseMetadata.class)
   @Fallback(EmptyMapOnNotFoundOr404.class)
   @Path("/loadbalancers/{id}/metadata")
   Metadata getMetadata(@PathParam("id") int id);
   
   /**
    * Update metadatum. Key and value must be 256 characters or less. All UTF-8 characters are valid.
    * 
    * @return true on a successful update, false if the metadatum was not found
    */
   @Named("lb:updatemetadatum")
   @PUT
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes("*/*")
   @Fallback(FalseOnNotFoundOr404.class)
   @Payload("%7B\"meta\":%7B\"value\":\"{value}\"%7D%7D")
   @Path("/loadbalancers/{id}/metadata/{metadatumId}")
   boolean updateMetadatum(@PathParam("id") int id, 
                           @PathParam("metadatumId") int metadatumId, 
                           @PayloadParam("value") String value);

   /**
    * Delete metadatum.
    * 
    * @see LoadBalancerApi#deleteMetadata(int, Iterable)
    * 
    * @return true on a successful removal, false if the metadatum was not found
    */
   @Named("lb:deletemetadatum")
   @DELETE
   @Fallback(FalseOnNotFoundOr404.class)
   @Consumes("*/*")
   @Path("/loadbalancers/{id}/metadata/{metadatumId}")
   boolean deleteMetadatum(@PathParam("id") int id, @PathParam("metadatumId") int metadatumId);

   
   /**
    * Batch delete metadata given the specified ids.
    * 
    * The current default limit is ten ids per request. Any and all configuration data is immediately purged and is 
    * not recoverable. If one or more of the items in the list cannot be removed due to its current status, an 
    * exception is thrown along with the ids of the ones the system identified as potential failures for this request.
    * 
    * @return true on a successful removal, false if the metadata was not found
    */
   @Named("lb:deletemetadata")
   @DELETE
   @Fallback(FalseOnNotFoundOr404.class)
   @Consumes("*/*")
   @Path("/loadbalancers/{id}/metadata")
   boolean deleteMetadata(@PathParam("id") int id, 
                          @QueryParam("id") Iterable<Integer> metadataIds);
}
