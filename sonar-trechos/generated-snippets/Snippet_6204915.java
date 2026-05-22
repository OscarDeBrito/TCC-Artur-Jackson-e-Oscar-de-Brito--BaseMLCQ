public interface NewProjectConfig extends ProjectConfig {
  /** Sets project name */
  void setName(String name);

  /** Sets project path */
  void setPath(String path);

  /** Sets project description */
  void setDescription(String description);

  /** Sets primary project type */
  void setType(String type);

  /** Sets mixin project types */
  void setMixins(List<String> mixins);

  /** Sets project attributes */
  void setAttributes(Map<String, List<String>> attributes);

  /** Sets options for generator to create project */
  void setOptions(Map<String, String> options);

  /** Returns options for generator to create project */
  Map<String, String> getOptions();
}
