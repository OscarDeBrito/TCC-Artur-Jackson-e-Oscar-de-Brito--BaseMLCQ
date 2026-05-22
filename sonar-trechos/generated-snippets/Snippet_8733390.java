@InterfaceAudience.Public
@InterfaceStability.Stable
public class TaskAttemptID extends org.apache.hadoop.mapreduce.TaskAttemptID {
  
  /**
   * Constructs a TaskAttemptID object from given {@link TaskID}.  
   * @param taskId TaskID that this task belongs to  
   * @param id the task attempt number
   */
  public TaskAttemptID(TaskID taskId, int id) {
    super(taskId, id);
  }
  
  /**
   * Constructs a TaskId object from given parts.
   * @param jtIdentifier jobTracker identifier
   * @param jobId job number 
   * @param isMap whether the tip is a map 
   * @param taskId taskId number
   * @param id the task attempt number
   * @deprecated Use {@link #TaskAttemptID(String, int, TaskType, int, int)}.
   */
  @Deprecated
  public TaskAttemptID(String jtIdentifier, int jobId, boolean isMap, 
      int taskId, int id) {
    this(jtIdentifier, jobId, isMap ? TaskType.MAP : TaskType.REDUCE, taskId,
	id);
  }
  
  /**
   * Constructs a TaskId object from given parts.
   * @param jtIdentifier jobTracker identifier
   * @param jobId job number 
   * @param type the TaskType 
   * @param taskId taskId number
   * @param id the task attempt number
   */
  public TaskAttemptID(String jtIdentifier, int jobId, TaskType type, 
                       int taskId, int id) {
    this(new TaskID(jtIdentifier, jobId, type, taskId), id);
  }
  
  public TaskAttemptID() { 
    super(new TaskID(), 0);
  }

  /**
   * Downgrade a new TaskAttemptID to an old one
   * @param old the new id
   * @return either old or a new TaskAttemptID constructed to match old
   */
  public static 
  TaskAttemptID downgrade(org.apache.hadoop.mapreduce.TaskAttemptID old) {
    if (old instanceof TaskAttemptID) {
      return (TaskAttemptID) old;
    } else {
      return new TaskAttemptID(TaskID.downgrade(old.getTaskID()), old.getId());
    }
  }

  public TaskID getTaskID() {
    return (TaskID) super.getTaskID();
  }

  public JobID getJobID() {
    return (JobID) super.getJobID();
  }

  @Deprecated
  public static TaskAttemptID read(DataInput in) throws IOException {
    TaskAttemptID taskId = new TaskAttemptID();
    taskId.readFields(in);
    return taskId;
  }
  
  /** Construct a TaskAttemptID object from given string 
   * @return constructed TaskAttemptID object or null if the given String is null
   * @throws IllegalArgumentException if the given string is malformed
   */
  public static TaskAttemptID forName(String str
                                      ) throws IllegalArgumentException {
    return (TaskAttemptID) 
             org.apache.hadoop.mapreduce.TaskAttemptID.forName(str);
  }
  
  /** 
   * Returns a regex pattern which matches task attempt IDs. Arguments can 
   * be given null, in which case that part of the regex will be generic.  
   * For example to obtain a regex matching <i>all task attempt IDs</i> 
   * of <i>any jobtracker</i>, in <i>any job</i>, of the <i>first 
   * map task</i>, we would use :
   * <pre> 
   * TaskAttemptID.getTaskAttemptIDsPattern(null, null, true, 1, null);
   * </pre>
   * which will return :
   * <pre> "attempt_[^_]*_[0-9]*_m_000001_[0-9]*" </pre> 
   * @param jtIdentifier jobTracker identifier, or null
   * @param jobId job number, or null
   * @param isMap whether the tip is a map, or null 
   * @param taskId taskId number, or null
   * @param attemptId the task attempt number, or null
   * @return a regex pattern matching TaskAttemptIDs
   */
  @Deprecated
  public static String getTaskAttemptIDsPattern(String jtIdentifier,
      Integer jobId, Boolean isMap, Integer taskId, Integer attemptId) {
    return getTaskAttemptIDsPattern(jtIdentifier, jobId,
	isMap ? TaskType.MAP : TaskType.REDUCE, taskId, attemptId);
  }
  
  /** 
   * Returns a regex pattern which matches task attempt IDs. Arguments can 
   * be given null, in which case that part of the regex will be generic.  
   * For example to obtain a regex matching <i>all task attempt IDs</i> 
   * of <i>any jobtracker</i>, in <i>any job</i>, of the <i>first 
   * map task</i>, we would use :
   * <pre> 
   * TaskAttemptID.getTaskAttemptIDsPattern(null, null, TaskType.MAP, 1, null);
   * </pre>
   * which will return :
   * <pre> "attempt_[^_]*_[0-9]*_m_000001_[0-9]*" </pre> 
   * @param jtIdentifier jobTracker identifier, or null
   * @param jobId job number, or null
   * @param type the {@link TaskType} 
   * @param taskId taskId number, or null
   * @param attemptId the task attempt number, or null
   * @return a regex pattern matching TaskAttemptIDs
   */
  @Deprecated
  public static String getTaskAttemptIDsPattern(String jtIdentifier,
      Integer jobId, TaskType type, Integer taskId, Integer attemptId) {
    StringBuilder builder = new StringBuilder(ATTEMPT).append(SEPARATOR);
    builder.append(getTaskAttemptIDsPatternWOPrefix(jtIdentifier, jobId,
        type, taskId, attemptId));
    return builder.toString();
  }
  
  @Deprecated
  static StringBuilder getTaskAttemptIDsPatternWOPrefix(String jtIdentifier
      , Integer jobId, TaskType type, Integer taskId, Integer attemptId) {
    StringBuilder builder = new StringBuilder();
    builder.append(TaskID.getTaskIDsPatternWOPrefix(jtIdentifier
        , jobId, type, taskId))
        .append(SEPARATOR)
        .append(attemptId != null ? attemptId : "[0-9]*");
    return builder;
  }
}
