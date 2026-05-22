protected class Users
        implements TransientComposite
    {
        public void signup( Registration registration )
        {
            // Check if user with this name already exists
            events.signedup( registration );
        }
    }
