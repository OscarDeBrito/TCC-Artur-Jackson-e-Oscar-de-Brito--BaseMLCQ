private static class State {

        static State initial() {
            return new State(ImmutableSet.of());
        }

        final ImmutableSet<DLPConfigurationItem> rules;

        private State(ImmutableSet<DLPConfigurationItem> rules) {
            this.rules = rules;
        }

        State add(List<DLPConfigurationItem> toAdd) {
            ImmutableSet<DLPConfigurationItem> union = Stream.concat(this.rules.stream(), toAdd.stream()).collect(Guavate.toImmutableSet());
            return new State(union);
        }

        State remove(List<DLPConfigurationItem> toRemove) {
            ImmutableSet<DLPConfigurationItem> filtered = rules.stream().filter(rule -> !toRemove.contains(rule)).collect(Guavate.toImmutableSet());
            return new State(filtered);
        }
    }
