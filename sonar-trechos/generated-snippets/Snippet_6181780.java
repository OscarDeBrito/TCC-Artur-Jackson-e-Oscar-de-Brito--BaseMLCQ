@DTO
public interface FileStateUpdateDto {
  FileWatcherEventType getType();

  FileStateUpdateDto withType(FileWatcherEventType type);

  String getPath();

  FileStateUpdateDto withPath(String path);

  String getHashCode();

  FileStateUpdateDto withHashCode(String hashCode);
}
