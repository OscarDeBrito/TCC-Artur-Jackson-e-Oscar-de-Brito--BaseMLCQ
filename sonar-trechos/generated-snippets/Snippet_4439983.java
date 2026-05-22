public class Snippet__4439983 {

    @Permittable(value = AcceptedTokenType.TENANT, groupId = PermittableGroupIds.IDENTIFICATIONS)
      @RequestMapping(
              value = "/customers/{identifier}/identifications",
              method = RequestMethod.POST,
              produces = MediaType.APPLICATION_JSON_VALUE,
              consumes = MediaType.APPLICATION_JSON_VALUE
      )
      public
      @ResponseBody
      ResponseEntity<Void> createIdentificationCard(@PathVariable("identifier") final String identifier,
                                    @RequestBody @Valid final IdentificationCard identificationCard) {
        if (this.customerService.customerExists(identifier)) {
          if (this.customerService.identificationCardExists(identificationCard.getNumber())) {
            throw ServiceException.conflict("IdentificationCard {0} already exists.", identificationCard.getNumber());
          }

          this.commandGateway.process(new CreateIdentificationCardCommand(identifier, identificationCard));
        } else {
          throw ServiceException.notFound("Customer {0} not found.", identifier);
        }

        return ResponseEntity.accepted().build();
      }

}
