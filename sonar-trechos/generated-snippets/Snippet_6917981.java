public class Snippet__6917981 {

    private DiffList computeDiffEntries(
            Repository repo, GitilesView view, RevWalk walk, RevCommit commit) throws IOException {
          DiffList result = new DiffList();
          result.revision =
              view.getRevision().matches(commit)
                  ? view.getRevision()
                  : Revision.peeled(commit.name(), commit);

          AbstractTreeIterator oldTree;
          switch (commit.getParentCount()) {
            case 0:
              result.oldRevision = Revision.NULL;
              oldTree = new EmptyTreeIterator();
              break;
            case 1:
              result.oldRevision =
                  Revision.peeled(result.revision.getName() + "^", commit.getParent(0));
              oldTree = getTreeIterator(walk, commit.getParent(0));
              break;
            default:
              // TODO(dborowitz): handle merges
              return result;
          }
          AbstractTreeIterator newTree = getTreeIterator(walk, commit);

          try (DiffFormatter diff = new DiffFormatter(NullOutputStream.INSTANCE)) {
            diff.setRepository(repo);
            diff.setDetectRenames(true);
            result.entries = diff.scan(oldTree, newTree);
            return result;
          }
        }

}
