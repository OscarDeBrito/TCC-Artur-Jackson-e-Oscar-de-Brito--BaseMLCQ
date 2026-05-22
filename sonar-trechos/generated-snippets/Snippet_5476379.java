public class Snippet__5476379 {

    @Override
        public UUID[] remove(final ConfiguredObjectRecord... objects)
        {
            assertState(State.OPEN);
            List<UUID> removed = new ArrayList<UUID>();
            for (ConfiguredObjectRecord record : objects)
            {
                if (_configuredObjectRecords.remove(record.getId()) != null)
                {
                    removed.add(record.getId());
                }
            }
            return removed.toArray(new UUID[removed.size()]);
        }

}
