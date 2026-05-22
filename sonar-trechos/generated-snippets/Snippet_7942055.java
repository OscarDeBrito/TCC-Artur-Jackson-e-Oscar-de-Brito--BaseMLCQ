public class ViewerErrorScanner implements Runnable {

    // the process to monitor
    private Process process;

    public ViewerErrorScanner(Process process) {
        this.process = process;
    }
    
    /**
     * Wait for the program to exit and read the status.
     */
    public void run() {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        
        ArrayList buffer = new ArrayList();
        String tmp = null;
        try {
            
            // read the error output stream lines before the program exits
            // the stream is not available anymore after the program has exit
            while ((tmp = in.readLine()) != null) {
                buffer.add(tmp);
            }
            in.close();
            
        } catch (IOException e) {
        }
        
        int exitCode = 0;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
        }

        // if there was an error, the viewer exited with non-zero status
        if (exitCode != 0) {
            // print the error messages
            for (int i = 0; i < buffer.size(); i++) {
                tmp = (String) buffer.get(i);
                BuilderRegistry.printToConsole("viewer> " + tmp);
            }
        }
    }
}
