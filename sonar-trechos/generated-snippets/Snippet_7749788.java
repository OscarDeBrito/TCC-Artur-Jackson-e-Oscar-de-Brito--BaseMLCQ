public class Snippet__7749788 {

    public static int execSingleProcess(List<String> cmdWords, Map<String,?> env, File directory, OutputStream out, OutputStream err, Object contextForLogging) {
            StreamGobbler errgobbler = null;
            StreamGobbler outgobbler = null;
        
            ProcessBuilder pb = new ProcessBuilder(cmdWords);
            if (env!=null) {
                for (Map.Entry<String,?> kv: env.entrySet()) pb.environment().put(kv.getKey(), String.valueOf(kv.getValue())); 
            }
            if (directory != null) {
                pb.directory(directory);
            }
        
            try {
                Process p = pb.start();
            
                if (out != null) {
                    InputStream outstream = p.getInputStream();
                    outgobbler = new StreamGobbler(outstream, out, (Logger) null);
                    outgobbler.start();
                }
                if (err != null) {
                    InputStream errstream = p.getErrorStream();
                    errgobbler = new StreamGobbler(errstream, err, (Logger) null);
                    errgobbler.start();
                }
            
                int result = p.waitFor();
            
                if (outgobbler != null) outgobbler.blockUntilFinished();
                if (errgobbler != null) errgobbler.blockUntilFinished();
            
                if (result==255)
                    // this is not definitive, but tests (and code?) expects throw exception if can't connect;
                    // only return exit code when it is exit code from underlying process;
                    // we have no way to distinguish 255 from ssh failure from 255 from the command run through ssh ...
                    // but probably 255 is from CLI ssh
                    throw new SshException("exit code 255 from CLI ssh; probably failed to connect");
            
                return result;
            } catch (InterruptedException e) {
                throw Exceptions.propagate(e);
            } catch (IOException e) {
                throw Exceptions.propagate(e);
            } finally {
                closeWhispering(outgobbler, contextForLogging, "execProcess");
                closeWhispering(errgobbler, contextForLogging, "execProcess");
            }
        }

}
