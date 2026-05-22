@Immutable
final class AttributesDeletedStrategy extends AbstractEventStrategy<AttributesDeleted> {

    @Override
    protected ThingBuilder.FromCopy applyEvent(final AttributesDeleted event,
            final ThingBuilder.FromCopy thingBuilder) {

        return thingBuilder.removeAllAttributes();
    }

}
