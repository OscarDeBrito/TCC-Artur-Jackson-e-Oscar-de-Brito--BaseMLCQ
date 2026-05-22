public interface ShareProductWritePlatformService {

    CommandProcessingResult createShareProduct(JsonCommand jsonCommand);

    CommandProcessingResult updateProduct(Long productId, JsonCommand command);

    CommandProcessingResult createShareProductDividend(Long productId, JsonCommand jsonCommand);

    CommandProcessingResult approveShareProductDividend(Long PayOutDetailId);

    CommandProcessingResult deleteShareProductDividend(Long PayOutDetailId);
}
