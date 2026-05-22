protected class LeaderListener extends LeaderSelectorListenerAdapter {

		@Override
		public void takeLeadership(CuratorFramework framework) {
			try {
				LeaderInitiator.this.candidate.onGranted(LeaderInitiator.this.context);
				if (LeaderInitiator.this.leaderEventPublisher != null) {
					try {
						LeaderInitiator.this.leaderEventPublisher.publishOnGranted(LeaderInitiator.this,
								LeaderInitiator.this.context, LeaderInitiator.this.candidate.getRole());
					}
					catch (Exception e) {
						logger.warn("Error publishing OnGranted event.", e);
					}
				}

				// when this method exits, the leadership will be revoked;
				// therefore this thread needs to be held up until the
				// candidate is no longer leader
				Thread.sleep(Long.MAX_VALUE);
			}
			catch (@SuppressWarnings("unused") InterruptedException e) {
				// InterruptedException, like any other runtime exception,
				// is handled by the finally block below. No need to
				// reset the interrupt flag as the interrupt is handled.
			}
			finally {
				LeaderInitiator.this.candidate.onRevoked(LeaderInitiator.this.context);
				if (LeaderInitiator.this.leaderEventPublisher != null) {
					try {
						LeaderInitiator.this.leaderEventPublisher.publishOnRevoked(LeaderInitiator.this,
								LeaderInitiator.this.context, LeaderInitiator.this.candidate.getRole());
					}
					catch (Exception e) {
						logger.warn("Error publishing OnRevoked event.", e);
					}
				}
			}
		}
	}
