public class Snippet__4222807 {

    public MerkleTrees deserialize(DataInputPlus in, int version) throws IOException
            {
                IPartitioner partitioner = null;
                int nTrees = in.readInt();
                Collection<MerkleTree> trees = new ArrayList<>(nTrees);
                if (nTrees > 0)
                {
                    for (int i = 0; i < nTrees; i++)
                    {
                        MerkleTree tree = MerkleTree.serializer.deserialize(in, version);
                        trees.add(tree);

                        if (partitioner == null)
                            partitioner = tree.partitioner();
                        else
                            assert tree.partitioner() == partitioner;
                    }
                }

                return new MerkleTrees(partitioner, trees);
            }

}
