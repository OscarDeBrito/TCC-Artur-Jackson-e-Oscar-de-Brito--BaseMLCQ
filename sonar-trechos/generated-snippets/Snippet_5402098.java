public class Snippet__5402098 {

    @Override
        public long spill() {
            // Make sure we have something to spill.  Don't create empty
            // files, as that will make a mess.
            if (mContents.size() == 0) return 0;

            // Lock the container before I spill, so that iterators aren't
            // trying to read while I'm mucking with the container.
            long spilled = 0;
            synchronized (mContents) {
                DataOutputStream out = null;
                try {
                    out = getSpillFile();
                }  catch (IOException ioe) {
                    // Do not remove last file from spilled array. It was not
                    // added as File.createTmpFile threw an IOException
                    warn(
                        "Unable to create tmp file to spill to disk", PigWarning.UNABLE_TO_CREATE_FILE_TO_SPILL, ioe);
                    return 0;
                }
                try {
                    Iterator<Tuple> i = mContents.iterator();
                    while (i.hasNext()) {
                        SEDES.writeDatum(out, i.next(), DataType.TUPLE);
                        spilled++;
                        // This will spill every 16383 records.
                        if ((spilled & 0x3fff) == 0) reportProgress();
                    }
                    out.flush();
                    out.close();
                    out = null;
                    mContents.clear();
                } catch (Throwable e) {
                    // Remove the last file from the spilled array, since we failed to
                    // write to it.
                    mSpillFiles.remove(mSpillFiles.size() - 1);
                    warn(
                        "Unable to spill contents to disk", PigWarning.UNABLE_TO_SPILL, e);
                    return 0;
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            warn("Error closing spill", PigWarning.UNABLE_TO_CLOSE_SPILL_FILE, e);
                        }
                    }
                }
            }
            // Increment the spill count
            incSpillCount(PigCounters.SPILLABLE_MEMORY_MANAGER_SPILL_COUNT);
            return spilled;
        }

}
