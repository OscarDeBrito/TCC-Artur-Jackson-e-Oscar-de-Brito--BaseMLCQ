public class CrossProductInputSplit extends FileSplit {
	//
	// private long length = 0;
	// private String[] hosts;
	private List<Path> inputPortDirectories;
	private Path workingDirectory;

	public CrossProductInputSplit() {
		super(null,0,0,null);
		inputPortDirectories = new ArrayList<Path>();
		System.out.println("Calling default constructor for cross product split");
	}

	public CrossProductInputSplit(Path workingDirectory, List<Path> inputPortDirectories) {
		// this.length = length;
		// this.hosts = hosts;
		super(workingDirectory, 0, 0, new String[0]);
		this.workingDirectory = workingDirectory;
		this.inputPortDirectories = inputPortDirectories;
		System.out.println("Calling non-default constructor for cross product split");
	}

	public void addInputPortDirectory(Path path) {
		inputPortDirectories.add(path);
	}

	public List<Path> getInputPortDirectories() {
		return inputPortDirectories;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		super.write(out);
		Text.writeString(out, workingDirectory.toString());
		out.writeInt(inputPortDirectories.size());
		for (Path path : inputPortDirectories) {
			Text.writeString(out, path.toString());
		}
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		super.readFields(in);
		workingDirectory = new Path(Text.readString(in));
		int length = in.readInt();
		for (int i = 0; i < length; i++) {
			inputPortDirectories.add(new Path(Text.readString(in)));
		}
	}

}
