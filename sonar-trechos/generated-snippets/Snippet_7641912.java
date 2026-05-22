public class Snippet__7641912 {

    public void testSynchronizationGlobal() throws Exception {
    		SecurityContextHolder.clearContext();
    		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
    		loadStartAndWaitForThreads(true, "Main_", NUM_THREADS, true, false);
    		assertThat(errors).as("Thread errors detected; review log output for details").isZero();
    	}

}
