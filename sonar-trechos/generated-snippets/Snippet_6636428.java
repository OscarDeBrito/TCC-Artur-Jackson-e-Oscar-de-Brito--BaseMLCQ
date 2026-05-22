public abstract class ShareMessengerActionButton implements ShareModel {

    private final String title;

    protected ShareMessengerActionButton(final Builder builder) {
        this.title = builder.title;
    }

    ShareMessengerActionButton(final Parcel in) {
        this.title = in.readString();
    }

    /**
     * The title displayed to the user for the Messenger action button.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    /**
     * Abstract builder for {@link com.facebook.share.model.ShareMessengerActionButton}
     */
    public static abstract class Builder<M extends ShareMessengerActionButton, B extends Builder>
            implements ShareModelBuilder<M, B> {
        private String title;

        /**
         * Sets the title for the Messenger action button.
         */
        public B setTitle(@Nullable final String title) {
            this.title = title;
            return (B) this;
        }

        @Override
        public B readFrom(final M model) {
            if (model == null) {
                return (B) this;
            }
            return this.setTitle(model.getTitle());
        }
    }
}
