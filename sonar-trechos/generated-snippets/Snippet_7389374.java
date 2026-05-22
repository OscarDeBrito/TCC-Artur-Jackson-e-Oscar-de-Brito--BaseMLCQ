public class Snippet__7389374 {

    @Bean
    		public ClassPathFileSystemWatcher classPathFileSystemWatcher(
    				FileSystemWatcherFactory fileSystemWatcherFactory,
    				ClassPathRestartStrategy classPathRestartStrategy) {
    			DefaultRestartInitializer restartInitializer = new DefaultRestartInitializer();
    			URL[] urls = restartInitializer.getInitialUrls(Thread.currentThread());
    			if (urls == null) {
    				urls = new URL[0];
    			}
    			return new ClassPathFileSystemWatcher(fileSystemWatcherFactory,
    					classPathRestartStrategy, urls);
    		}

}
