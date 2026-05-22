public interface SharedLibraryInterfaceParams {

  Iterable<BuildTarget> getParseTimeDeps();

  Kind getKind();

  /** @return additional flags to pass to the linker when linking interfaces. */
  ImmutableList<String> getLdflags();

  /** The configured mode for shared library interfaces. */
  enum Type {

    /** Do not use shared library interfaces. */
    DISABLED,

    /** Use shared library interfaces for linking. */
    ENABLED,

    /** Strip undefined symbols from shared library interfaces, and use them for linking */
    DEFINED_ONLY,
  }

  enum Kind {
    ELF
  }
}
