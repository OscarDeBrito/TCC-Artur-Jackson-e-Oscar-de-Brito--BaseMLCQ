public class TrieDictionaryForestBuilder<T> {

    public static int DEFAULT_MAX_TRIE_TREE_SIZE_MB = 500;

    private static final Logger logger = LoggerFactory.getLogger(TrieDictionaryForestBuilder.class);

    private BytesConverter<T> bytesConverter;

    private int curTreeSize = 0;

    private TrieDictionaryBuilder<T> trieBuilder;

    private ArrayList<TrieDictionary<T>> trees = new ArrayList<>();

    private ArrayList<ByteArray> valueDivide = new ArrayList<>(); //find tree

    private ArrayList<Integer> accuOffset = new ArrayList<>(); //find tree

    private ByteArray previousValue = null; //value use for remove duplicate

    private int baseId;

    private int curOffset;

    private int maxTrieTreeSize;

    private boolean isOrdered = true;

    public TrieDictionaryForestBuilder(BytesConverter<T> bytesConverter) {
        this(bytesConverter, 0);
    }

    public TrieDictionaryForestBuilder(BytesConverter<T> bytesConverter, int baseId) {
        this(bytesConverter, baseId, getMaxTrieSizeInMB());
    }

    public TrieDictionaryForestBuilder(BytesConverter<T> bytesConverter, int baseId, int maxTrieTreeSizeMB) {
        this.bytesConverter = bytesConverter;
        this.trieBuilder = new TrieDictionaryBuilder<T>(bytesConverter);
        this.baseId = baseId;
        this.curOffset = 0;
        this.maxTrieTreeSize = maxTrieTreeSizeMB * 1024 * 1024;
    }

    public void addValue(T value) {
        if (value == null)
            return;
        byte[] valueBytes = bytesConverter.convertToBytes(value);
        addValue(valueBytes);
    }

    private void addValue(byte[] valueBytes) {
        ByteArray valueByteArray = new ByteArray(valueBytes);
        if (previousValue != null && isOrdered) {
            int comp = previousValue.compareTo(valueByteArray);
            if (comp == 0) {
                return; //duplicate value
            }
            if (comp > 0) {
                logger.info("values not in ascending order, previous '{}', current '{}'", previousValue, valueByteArray);
                isOrdered = false;
                if (trees.size() > 0) {
                    throw new IllegalStateException("Invalid input data. Unordered data cannot be split into multi trees");
                }
            }
        }
        previousValue = valueByteArray;
        trieBuilder.addValue(valueBytes);
        curTreeSize += valueBytes.length;

        if (curTreeSize >= maxTrieTreeSize && isOrdered) {
            TrieDictionary<T> tree = trieBuilder.build(0);
            addTree(tree);
            reset();
        }
    }

    public TrieDictionaryForest<T> build() {
        if (trieBuilder.isHasValue()) { //last tree
            TrieDictionary<T> tree = trieBuilder.build(0);
            addTree(tree);
            reset();
        }
        TrieDictionaryForest<T> forest = new TrieDictionaryForest<T>(this.trees, this.valueDivide, this.accuOffset, this.bytesConverter, baseId);
        // if input values are not in ascending order and tree num>1,TrieDictionaryForest can not work correctly.
        if (forest.getTrees().size() > 1 && !isOrdered) {
            throw new IllegalStateException("Invalid input data. Unordered data can not be split into multi trees");
        }
        return forest;
    }

    public int getMaxTrieTreeSize() {
        return maxTrieTreeSize;
    }

    void setMaxTrieTreeSize(int maxTrieTreeSize) {
        this.maxTrieTreeSize = maxTrieTreeSize;
        logger.info("maxTrieSize is set to:" + maxTrieTreeSize + "B");
    }

    private void addTree(TrieDictionary<T> tree) {
        trees.add(tree);
        int minId = tree.getMinId();
        accuOffset.add(curOffset);
        byte[] valueBytes = tree.getValueBytesFromIdWithoutCache(minId);
        valueDivide.add(new ByteArray(valueBytes, 0, valueBytes.length));
        curOffset += (tree.getMaxId() + 1);
        
        checkDictSize();
    }

    private void checkDictSize() {
        // due to the limitation of resource store, no dictionary beyond 2GB is allowed
        long size = 0;
        for (TrieDictionary trie : trees) {
            size += trie.getStorageSizeInBytes();
        }
        if (size > TrieDictionaryBuilder._2GB)
            throw new TooBigDictionaryException("Too big dictionary, dictionary cannot be bigger than 2GB");
    }

    private void reset() {
        curTreeSize = 0;
        trieBuilder = new TrieDictionaryBuilder<T>(bytesConverter);
    }

    public static int getMaxTrieSizeInMB() {
        KylinConfig config = null;
        try {
            config = KylinConfig.getInstanceFromEnv();
        } catch (RuntimeException e) {
            logger.info("cannot get KylinConfig from env.Use default setting:" + DEFAULT_MAX_TRIE_TREE_SIZE_MB + "MB");
        }
        int maxTrieTreeSizeMB;
        if (config != null) {
            maxTrieTreeSizeMB = config.getTrieDictionaryForestMaxTrieSizeMB();
        } else {
            maxTrieTreeSizeMB = DEFAULT_MAX_TRIE_TREE_SIZE_MB;
        }
        return maxTrieTreeSizeMB;
    }

}
