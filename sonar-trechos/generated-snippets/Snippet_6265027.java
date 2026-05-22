public class Snippet__6265027 {

    @Test
    	public void checkEquality() {

    		// Construct a component to test against.
    		HeatExchanger component = new HeatExchanger();
    		component.setInnerRadius(2.0);
    		component.setLength(3.0);
    		component.setNumElements(4);

    		// Construct a component equal to the first.
    		HeatExchanger equalComponent = new HeatExchanger();
    		equalComponent.setInnerRadius(2.0);
    		equalComponent.setLength(3.0);
    		equalComponent.setNumElements(4);

    		// Construct a component equal to the first for checking transitivity
    		HeatExchanger transComponent = new HeatExchanger();
    		transComponent.setInnerRadius(2.0);
    		transComponent.setLength(3.0);
    		transComponent.setNumElements(4);

    		// Construct a component not equal to the first.
    		HeatExchanger unequalComponent = new HeatExchanger();

    		// Check that component and unequalComponet are not the same.
    		assertFalse(component.equals(unequalComponent));
    		assertFalse(unequalComponent.equals(component));

    		// Check that equality also fails with illegal values
    		assertFalse(component == null);
    		assertFalse(component.equals(11));
    		assertFalse("House Lannister".equals(component));

    		// Check is equals() is reflexive and symmetric.
    		assertTrue(component.equals(component));
    		assertTrue(component.equals(equalComponent)
    				&& equalComponent.equals(component));

    		// Check equals() is transitive.
    		if (component.equals(transComponent)
    				&& transComponent.equals(equalComponent)) {
    			assertTrue(component.equals(equalComponent));
    		} else {
    			fail();
    		}
    		// Check the hashCode values.
    		assertEquals(component.hashCode(), component.hashCode());
    		assertEquals(component.hashCode(), equalComponent.hashCode());
    		assertFalse(component.hashCode() == unequalComponent.hashCode());

    		return;

    	}

}
