public class Snippet__4197031 {

    public File getLocationForDisk(DataDirectory dataDirectory)
        {
            if (dataDirectory != null)
                for (File dir : dataPaths)
                    if (dir.getAbsolutePath().startsWith(dataDirectory.location.getAbsolutePath()))
                        return dir;
            return null;
        }

}
