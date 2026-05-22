public class FXGeometryAttachmentTester {

	/**
	 * Checks that the attachment handles updates correctly.
	 */
	@Test
	public void checkUpdates() {

		// Create an attachment
		FXGeometryAttachment attachment = new FXGeometryAttachment(
				new FXGeometryAttachmentManager());

		// Create a sphere shape
		Sphere sphere = GeometryFactory.eINSTANCE.createSphere();
		sphere.setRadius(1);

		Geometry geometry = GeometryFactory.eINSTANCE.createGeometry();
		geometry.addNode(sphere);

		// Set the shape to the attachment
		attachment.addGeometry(geometry);

		// The attachment's JavaFX node should now contain the shape's JavaFX
		// representation
		((Group) attachment.getFxNode().getChildren().get(0)).getChildren()
				.contains(attachment.getRender(sphere).getMesh());
	}
}
