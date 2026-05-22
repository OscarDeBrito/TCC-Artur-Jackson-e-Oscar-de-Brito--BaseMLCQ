public interface PlaceInfoRequestListener {

        /**
         * Invoked when the place info response is received.
         *
         * @param place The place instance parsed from the response,
         *              or contains null if the request fails.
         * @param response The Places Graph response.
         */
        void onPlaceInfoResult(@Nullable Place place, GraphResponse response);
    }
