public class Snippet__7754600 {

    @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            ApiError that = ApiError.class.cast(other);
            return Objects.equal(this.message, that.message) &&
                    Objects.equal(this.details, that.details) &&
                    Objects.equal(this.error, that.error);
        }

}
