public class Snippet__4206958 {

    private long rollEpoch(long delta, long epoch, long nanos)
            {
                if (delta > 2 * INTERVAL)
                {
                    // this sample is more than twice our interval ahead, so just clear our counters completely
                    epoch = epoch(nanos);
                    sum = 0;
                    Arrays.fill(samples, 0);
                }
                else
                {
                    // ix(epoch - 1) => last index; this is our partial result bucket, so we add this to the sum
                    sum += samples[ix(epoch - 1)];
                    // then we roll forwards, clearing buckets, until our interval covers the new sample time
                    while (epoch + INTERVAL < nanos)
                    {
                        int index = ix(epoch);
                        sum -= samples[index];
                        samples[index] = 0;
                        epoch += BUCKET_INTERVAL;
                    }
                }
                // store the new epoch
                this.epoch = epoch;
                return epoch;
            }

}
