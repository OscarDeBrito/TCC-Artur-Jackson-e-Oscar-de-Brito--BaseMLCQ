public class Snippet__6228656 {

    @Test
    	public void test_contains_Point_BottomConcavePentagonTangentPoints() {
    		assertFalse("This point is outside the pentagon",
    				CONCAVE_PENTAGON.contains(new Point(-1, 4)));
    		assertTrue("This point is inside the pentagon",
    				CONCAVE_PENTAGON.contains(new Point(1, 4)));
    		assertTrue("This point is inside the pentagon",
    				CONCAVE_PENTAGON.contains(new Point(5, 4)));
    		assertFalse("This point is outside the pentagon",
    				CONCAVE_PENTAGON.contains(new Point(9, 4)));
    	}

}
