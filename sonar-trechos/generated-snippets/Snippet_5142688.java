public class JGitInfoCommand
    extends AbstractCommand
    implements GitCommand
{
    @Override
    protected ScmResult executeCommand( ScmProviderRepository repository, ScmFileSet fileSet,
                                        CommandParameters parameters )
        throws ScmException
    {
        Git git = null;
        try
        {
            File basedir = fileSet.getBasedir();

            git = Git.open( basedir );

            ObjectId objectId = git.getRepository().resolve( "HEAD" );

            InfoItem infoItem = new InfoItem();
            infoItem.setRevision( StringUtils.trim( objectId.name() ) );
            infoItem.setURL( basedir.toPath().toUri().toASCIIString() );

            return new InfoScmResult( Collections.singletonList( infoItem ),
                                      new ScmResult( "JGit.resolve(HEAD)", "", objectId.toString(), true ) );
        }
        catch ( Exception e )
        {
            throw new ScmException( "JGit resolve failure!", e );
        }
        finally
        {
            JGitUtils.closeRepo( git );
        }
    }
}
