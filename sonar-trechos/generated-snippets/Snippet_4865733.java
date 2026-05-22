@JsonIgnoreProperties(ignoreUnknown = true)
public class RebalanceResult {
  private Map<String, Map<String, String>> idealStateMapping;
  private PartitionAssignment partitionAssignment;
  private String status;

  public RebalanceResult() {

  }

  public RebalanceResult(@JsonProperty("idealState") Map<String, Map<String, String>> idealStateMapping,
      @JsonProperty("partitionAssignment") PartitionAssignment partitionAssignment, String status) {
    this.idealStateMapping = idealStateMapping;
    this.partitionAssignment = partitionAssignment;
    this.status = status;
  }

  public Map<String, Map<String, String>> getIdealStateMapping() {
    return idealStateMapping;
  }

  public void setIdealStateMapping(Map<String, Map<String, String>> idealStateMapping) {
    this.idealStateMapping = idealStateMapping;
  }

  public PartitionAssignment getPartitionAssignment() {
    return partitionAssignment;
  }

  public void setPartitionAssignment(PartitionAssignment partitionAssignment) {
    this.partitionAssignment = partitionAssignment;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
