public class Snippet__6920454 {

    String computeAndDisplayPin(String user, int position, boolean computeHotp) {
        OtpType type = AccountDb.getType(user);
        String secret = getSecret(user);
        PinInfo currentPin;
        if (mUsers[position] != null) {
          currentPin = mUsers[position]; // existing PinInfo, so we'll update it
        } else {
          currentPin = new PinInfo();
          currentPin.mPin = sResources.getString(EMPTY_PIN);
        }
    
        currentPin.mUser = user;
        if (type == OtpType.TOTP) {
          currentPin.mPin = computePin(secret, null);
        } else if (type == OtpType.HOTP) {
          currentPin.mIsHotp = true;
          if (computeHotp) {
            AccountDb.incrementCounter(user);
            Integer counter = AccountDb.getCounter(user);
            currentPin.mPin = computePin(secret, new Long(counter.longValue()));
          }
        }
        mUsers[position] = currentPin;
        return currentPin.mPin;
      }

}
