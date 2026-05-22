class MqttReconnectActionListener implements IMqttActionListener {

		final String methodName;

		MqttReconnectActionListener(String methodName) {
			this.methodName = methodName;
		}

		public void onSuccess(IMqttToken asyncActionToken) {
			// @Trace 501=Automatic Reconnect Successful: {0}
			log.fine(CLASS_NAME, methodName, "501", new Object[] { asyncActionToken.getClient().getClientId() });
			comms.setRestingState(false);
			stopReconnectCycle();
		}

		public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
			// @Trace 502=Automatic Reconnect failed, rescheduling: {0}
			log.fine(CLASS_NAME, methodName, "502", new Object[] { asyncActionToken.getClient().getClientId() });
			if (reconnectDelay < connOpts.getMaxReconnectDelay()) {
				reconnectDelay = reconnectDelay * 2;
			}
			rescheduleReconnectCycle(reconnectDelay);
		}

		private void rescheduleReconnectCycle(int delay) {
			String reschedulemethodName = methodName + ":rescheduleReconnectCycle";
			// @Trace 505=Rescheduling reconnect timer for client: {0}, delay:
			// {1}
			log.fine(CLASS_NAME, reschedulemethodName, "505",
					new Object[] { MqttAsyncClient.this.clientId, String.valueOf(reconnectDelay) });
			synchronized (clientLock) {
				if (MqttAsyncClient.this.connOpts.isAutomaticReconnect()) {
					if (reconnectTimer != null) {
						reconnectTimer.schedule(new ReconnectTask(), delay);
					} else {
						// The previous reconnect timer was cancelled
						reconnectDelay = delay;
						startReconnectCycle();
					}
				}
			}
		}

	}
