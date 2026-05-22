public class Snippet__7495736 {

    public static <T extends PointerBase> T pinArrayAndGetAddress(Object array) {
            PinnedObject pin = PinnedObject.create(array);
            pinnedObjectsListHead.set(new PinnedObjectListNode(pin, pinnedObjectsListHead.get()));
            return pin.addressOfArrayElement(0);
        }

}
