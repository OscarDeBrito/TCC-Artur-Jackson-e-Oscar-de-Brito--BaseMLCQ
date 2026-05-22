@FunctionalInterface
public interface TypedZPath<T>
{
    /**
     * Resolve into a ZPath using the given parameter
     *
     * @param p1 the parameter
     * @return ZPath
     */
    ZPath resolved(T p1);

    /**
     * Return a TypedZPath using {@link org.apache.curator.x.async.modeled.ZPath#parseWithIds}
     *
     * @param pathWithIds path to pass to {@link org.apache.curator.x.async.modeled.ZPath#parseWithIds}
     * @return TypedZPath
     */
    static <T> TypedZPath<T> from(String pathWithIds)
    {
        return from(ZPath.parseWithIds(pathWithIds));
    }

    /**
     * Return a TypedZPath
     *
     * @param path path to use
     * @return TypedZPath
     */
    static <T> TypedZPath<T> from(ZPath path)
    {
        return path::resolved;
    }
}
