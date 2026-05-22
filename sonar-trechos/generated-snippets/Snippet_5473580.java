public class Snippet__5473580 {

    private Collection<LinkDefinition<Source, Target>> performUpdate(final LinkStoreUpdater updater,
                                                                         Collection<LinkDefinition<Source, Target>> linkDefinitions,
                                                                         final ModelVersion storedVersion,
                                                                         final ModelVersion currentVersion)
                throws SQLException
        {
            linkDefinitions = updater.update(storedVersion.toString(), linkDefinitions);
            Connection connection = getConnection();
            try
            {
                connection.setAutoCommit(false);

                try (Statement statement = connection.createStatement())
                {
                    statement.execute("DELETE FROM " + getLinksTableName());
                }

                for (LinkDefinition<? extends BaseSource, ? extends BaseTarget> linkDefinition : linkDefinitions)
                {
                    insert(connection, generateLinkKey(linkDefinition), linkDefinition);
                }
                updateVersion(connection, currentVersion);
                connection.commit();
            }
            catch (SQLException e)
            {
                try
                {
                    connection.rollback();
                }
                catch (SQLException re)
                {
                    LOGGER.debug("Cannot rollback transaction", re);
                }
                throw e;
            }
            finally
            {
                JdbcUtils.closeConnection(connection, LOGGER);
            }
            return linkDefinitions;
        }

}
