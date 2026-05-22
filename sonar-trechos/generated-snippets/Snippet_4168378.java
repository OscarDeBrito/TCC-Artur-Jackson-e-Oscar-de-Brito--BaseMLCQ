public class FSMIntermedEntryPartitioner
	implements Partitioner<ChukwaRecordKey, FSMIntermedEntry>
{

	public int getPartition
		(ChukwaRecordKey key, FSMIntermedEntry val, int numPartitions)
	{
		return (Math.abs(key.hashCode() % numPartitions));		
	}
	
	public void configure(JobConf job) {
		// do nothing
	}

}
