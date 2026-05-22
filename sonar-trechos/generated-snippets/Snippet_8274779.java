public final class ContainerName {
    public static ContainerName of(String value) {
        return new ContainerName(value);
    }

    private final String container;

    private ContainerName(String value) {
        this.container = value;
    }

    public String value() {
        return container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainerName that = (ContainerName) o;
        return Objects.equal(container, that.container);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(container);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("container", container)
            .toString();
    }
}
