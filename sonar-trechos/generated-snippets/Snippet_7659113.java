private class LocalPersistStateChangeListener implements PersistStateChangeListener {

		@Override
		public void onPersist(State<String, String> state, Message<String> message,
				Transition<String, String> transition, StateMachine<String, String> stateMachine) {
			if (message != null && message.getHeaders().containsKey("order")) {
				Integer order = message.getHeaders().get("order", Integer.class);
				jdbcTemplate.update("update orders set state = ? where id = ?", state.getId(), order);
			}
		}
	}
