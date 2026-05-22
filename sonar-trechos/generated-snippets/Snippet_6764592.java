public class Snippet__6764592 {

    public static PageInsightsAsyncExportRun fetchById(String id, APIContext context) throws APIException {
        return
          new APIRequestGet(id, context)
          .requestAllFields()
          .execute();
      }

}
