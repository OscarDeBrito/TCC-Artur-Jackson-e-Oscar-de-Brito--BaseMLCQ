public class FXScaleOptionTester {

	/**
	 * Check that the decorator will set the object's scale correctly.
	 */
	@Test @Ignore
	public void checkMesh() {

		// Create a render object
		Shape shape = GeometryFactory.eINSTANCE.createShape();
		FXRenderObject object = new FXRenderObject(shape, new FXMeshCache());

		// Create a scale decorator for it
		FXScaleOption decorator = new FXScaleOption(object);

		// Set the scale
		object.setProperty(ScaleOptionImpl.PROPERTY_NAME_SCALE, 0.5);

		// The child's scale in all directions should have been changed
		assertEquals(0.5, object.getMesh().getScaleX(), .01d);
		assertEquals(0.5, object.getMesh().getScaleY(), .01d);
		assertEquals(0.5, object.getMesh().getScaleZ(), .01d);

		// Sending an update from the shape should also set the scale
		shape.changeDecoratorProperty(ScaleOptionImpl.PROPERTY_NAME_SCALE, 10d);
		assertEquals(10, object.getMesh().getScaleX(), .01d);
		assertEquals(10, object.getMesh().getScaleY(), .01d);
		assertEquals(10, object.getMesh().getScaleZ(), .01d);

		// Deactivating the option should leave the object with a 1:1 scale
		decorator.setActive(false);
		assertEquals(1, object.getMesh().getScaleX(), .01d);
		assertEquals(1, object.getMesh().getScaleY(), .01d);
		assertEquals(1, object.getMesh().getScaleZ(), .01d);
	}
}
