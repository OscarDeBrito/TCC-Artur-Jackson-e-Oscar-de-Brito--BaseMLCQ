public class Snippet__4258444 {

    public FTPFile[] mlistDir(String pathname, FTPFileFilter filter) throws IOException
        {
            FTPListParseEngine engine = initiateMListParsing( pathname);
            return engine.getFiles(filter);
        }

}
