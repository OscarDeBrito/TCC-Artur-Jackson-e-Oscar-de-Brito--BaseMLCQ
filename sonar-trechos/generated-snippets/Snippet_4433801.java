public class Snippet__4433801 {

    public static void assertFailed(APIResult response) {
            Assert.assertNotEquals(response.getMessage(), "null",
                "response message should not be null");
            Assert.assertEquals(response.getStatus(), APIResult.Status.FAILED,
                    "Status should be FAILED. Message: " + response.getMessage());
        }

}
