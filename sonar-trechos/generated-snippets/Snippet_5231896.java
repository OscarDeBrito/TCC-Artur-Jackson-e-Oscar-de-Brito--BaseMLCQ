public final class RunDirJobSubmitter {

    /* our log stream */
    private static final Logger LOG = Logger.getLogger(RunDirJobSubmitter.class
            .getName());

    /* our res mgr client */
    private XmlRpcResourceManagerClient client = null;

    public RunDirJobSubmitter(URL rUrl) {
        client = new XmlRpcResourceManagerClient(rUrl);
    }

    public void submitRunDirJobFile(String jobFname, String inputFname)
            throws JobExecutionException {

        // -------------------------------------------------------------
        // check validity of given job filename and input filename
        // -------------------------------------------------------------

        File jobFile = new File(jobFname);
        if (!jobFile.exists()) {
            // file doesn't exist
            throw new JobExecutionException("RunDirJobSubmitter: input file "
                    + jobFname + " does not exist.");
        } else if (!jobFile.isFile()) {
            // file is a directory
            throw new JobExecutionException("RunDirJobSubmitter: input file "
                    + jobFname + " is not a file.");
        }

        File f = new File(inputFname);
        if (!f.exists()) {
            // file doesn't exist
            throw new JobExecutionException("RunDirJobSubmitter: input file "
                    + inputFname + " does not exist.");
        } else if (!f.isFile()) {
            // file is a directory
            throw new JobExecutionException("RunDirJobSubmitter: input file "
                    + inputFname + " is not a file.");
        }

        // ----------------------------------------------------------------
        // create a default JobSpec
        // ----------------------------------------------------------------
        JobSpec spec = JobBuilder.buildJobSpec(jobFile.getAbsolutePath());
        Job job = spec.getJob();
        NameValueJobInput jobInput = (NameValueJobInput) spec.getIn();

        // ----------------------------------------------------------------
        // open the file to read. traverse through the list of directories
        // name given & override the default Job's runDirName value with the
        // directory name. then submit the Job.
        // ----------------------------------------------------------------

        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFname));
            if (!in.ready()) {
                throw new IOException();
            }

            String line;
            String jobId;
            while ((line = in.readLine()) != null) {

                // overwrite the runDirName
                jobInput.setNameValuePair("runDirName", line);

                jobId = submitJob(job, jobInput);
                LOG.log(Level.INFO, "Job Submitted: id: [" + jobId + "]");
            }

            in.close();
        } catch (IOException e) {
            throw new JobExecutionException("RunDirJobSubmitter: " + e);
        }

    }

    public static void main(String[] args) throws JobExecutionException, MalformedURLException {
        String resMgrUrlStr = null;
        String jobFileName = null;
        String runDirFileName = null;

        String usage = "RunDirJobSubmitter --rUrl <resource mgr url> "
                + "--jobFile <input job file> "
                + "--runDirFile <input running directories file> \n";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--rUrl")) {
                resMgrUrlStr = args[++i];
            } else if (args[i].equals("--jobFile")) {
                jobFileName = args[++i];
            } else if (args[i].equals("--runDirFile")) {
                runDirFileName = args[++i];
            }
        }

        if (resMgrUrlStr == null || jobFileName == null
                || runDirFileName == null) {
            System.err.println(usage);
            System.exit(1);
        }

        RunDirJobSubmitter submitter = new RunDirJobSubmitter(new URL(
                resMgrUrlStr));
        submitter.submitRunDirJobFile(jobFileName, runDirFileName);
    }

    private String submitJob(Job job, JobInput jobInput)
            throws JobExecutionException {
        return client.submitJob(job, jobInput);
    }

}
