public class Snippet__4288257 {

    private boolean checkSecurityToken(FedizResponse response) {
            if (response == null) {
                return false;
            }
            long currentTime = System.currentTimeMillis();
            return response.getTokenExpires().getTime() > currentTime;
        }

}
