public class Snippet__6488574 {

    public Snippet__6488574(String pid, String commandLine, Properties environment, ImageThread currentThread, Iterator threads, ImageModule executable, Iterator libraries, int pointerSize)
    	{
    		_id = pid;
    		_commandLine = commandLine;
    		_environment = environment;
    		setCurrentThread(currentThread);
    		setThreads(threads);
    		_executable = executable;
    		while (libraries.hasNext()) {
    			_libraries.add(libraries.next());
    		}
    		_pointerSize = pointerSize;
    	}

}
