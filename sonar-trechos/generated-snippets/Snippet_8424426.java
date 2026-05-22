interface ExchangeCreatedEvent extends ExchangeEvent {
        default Type getType() {
            return Type.ExchangeCreated;
        }
    }
