public class Snippet__8660303 {

    @Override public void restartNodes(Collection<UUID> ids) throws IgniteException {
            // synthetic constructor call removed

            try {
                ctx.grid().compute(forNodeIds(ids)).execute(IgniteKillTask.class, true);
            }
            finally {
                unguard();
            }
        }

}
