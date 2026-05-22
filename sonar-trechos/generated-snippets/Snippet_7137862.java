private static class UserHeaderClickListener implements View.OnClickListener {
        private final Fragment fragment;

        public UserHeaderClickListener(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void onClick(View view) {
            UserCompactView user = (UserCompactView) view.getTag(R.id.es_keyUser);
            EventBus.post(new OpenUserProfileEvent(fragment, user));
        }
    }
