public static class Listeners extends ListenerList<RollupStateListener> implements RollupStateListener {
        @Override
        public Vote previewExpandedChange(Rollup rollup) {
            VoteResult result = new VoteResult();

            forEach(listener -> result.tally(listener.previewExpandedChange(rollup)));

            return result.get();
        }

        @Override
        public void expandedChangeVetoed(Rollup rollup, Vote reason) {
            forEach(listener -> listener.expandedChangeVetoed(rollup, reason));
        }

        @Override
        public void expandedChanged(Rollup rollup) {
            forEach(listener -> listener.expandedChanged(rollup));
        }
    }
