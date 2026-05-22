public interface DataSchemaResolver
{
  /**
   * Return a map of names to {@link NamedDataSchema} bindings that have been resolved.
   *
   * @return map of names to {@link NamedDataSchema} bindings that have been resolved.
   */
  Map<String, NamedDataSchema> bindings();

  /**
   * Return a map of names to {@link DataSchemaLocation}s that have been
   * resolved through this resolver.
   *
   * @return a map of names to {@link DataSchemaLocation}s that have been
   * resolved through this resolver.
   */
  Map<String, DataSchemaLocation> nameToDataSchemaLocations();

  /**
   * Find a {@link NamedDataSchema} for the specified name.
   *
   * If a {@link NamedDataSchema} with the specified name is not found, the resolver
   * will should try its best to find and instantiate a {@link NamedDataSchema} with
   * the specified name.
   *
   * @param name of the schema to find.
   * @param errorMessageBuilder to append error messages to.
   * @return the {@link NamedDataSchema} if it can be located, else return null.
   */
  NamedDataSchema findDataSchema(String name, StringBuilder errorMessageBuilder);

  /**
   * Bind name to the provided {@link NamedDataSchema} and {@link DataSchemaLocation}.
   *
   * @param name to bind to.
   * @param schema provides the {@link NamedDataSchema}
   * @param location provides the {@link DataSchemaLocation}
   * @throws IllegalStateException if name is already bound.
   */
  void bindNameToSchema(Name name, NamedDataSchema schema, DataSchemaLocation location);

  /**
   * Lookup existing {@link NamedDataSchema} with the specified name.
   *
   * This is a pure lookup operation. If a {@link NamedDataSchema} with the specified
   * name does not already exist, then this method will return null, else it
   * returns the existing {@link NamedDataSchema}.
   *
   * @param name of the schema to find.
   * @return the {@link NamedDataSchema} if it already exists, else return null.
   */
  NamedDataSchema existingDataSchema(String name);

  /**
   * Return whether the specified {@link DataSchemaLocation} has been associated with a name.
   *
   * @param location provides the {@link DataSchemaLocation} to check.
   * @return true if the specified {@link DataSchemaLocation} has been associated with a name.
   */
  boolean locationResolved(DataSchemaLocation location);

  /**
   * Add a record that is currently being parsed to the pending schema list. This is used to detect and disallow
   * circular references involving includes.
   * @param name Full name of the record.
   */
  void addPendingSchema(String name);

  /**
   * Update a pending schema to indicate the status of parsing includes for that schema.
   * @param name Schema name
   * @param isParsingInclude status of parsing include. Set to true before parsing includes and cleared after include
   *                         list is processed.
   */
  void updatePendingSchema(String name, Boolean isParsingInclude);

  /**
   * Remove a record from the pending list.
   * @param name Full name of the record.
   */
  void removePendingSchema(String name);

  /**
   * Return the list of records currently in the state of parsing.
   */
  LinkedHashMap<String, Boolean> getPendingSchemas();
}
