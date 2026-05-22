public class Snippet__5186909 {

    public static String getMessageHash(JSONObject message, Collection<String> hashFields) {
        List<String> hashElements = hashFields.stream().map(errorField ->
                String.format("%s-%s", errorField, message.get(errorField))).collect(Collectors.toList());
        return DigestUtils.sha256Hex(String.join("|", hashElements).getBytes(UTF_8));
      }

}
