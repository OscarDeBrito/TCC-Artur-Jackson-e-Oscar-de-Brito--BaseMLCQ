public class Snippet__5464749 {

    public void onDischargeSuccess() {
                cleanup();

                // If the declare already returned a result we can proceed otherwise
                // we need to wait for it finish in order to get the correct outcome.
                if (declare == null) {
                    request.onSuccess();
                } else if (declare.isComplete()) {
                    if (declare.getFailureCause() == null) {
                        request.onSuccess();
                    } else {
                        request.onFailure(declare.getFailureCause());
                    }
                }
            }

}
