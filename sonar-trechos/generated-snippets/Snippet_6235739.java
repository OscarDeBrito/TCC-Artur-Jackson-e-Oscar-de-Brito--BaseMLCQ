public class Snippet__6235739 {

    @Test
    	public void test_getSize() {
    		forRectangles(new IAction() {
    			@Override
    			public void action(Rectangle rect, Point tl, Point br) {
    				assertEquals(new Dimension(br.x - tl.x, br.y - tl.y),
    						rect.getSize());
    			}
    		});
    	}

}
