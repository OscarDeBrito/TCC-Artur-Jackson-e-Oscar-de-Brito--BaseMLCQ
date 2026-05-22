public class Procfile extends HashMap<String, String> {
	private static final long serialVersionUID = 1L;

	public static Procfile load(InputStream inputStream) throws IOException {

		Procfile procfile = new Procfile();
		BufferedReader reader = null;

		try {

			String line = null;
			reader = new BufferedReader(new InputStreamReader(inputStream));

			while ((line = reader.readLine()) != null) {

				if (line.isEmpty() || line.trim().isEmpty())
					continue;

				line = line.trim();
				String[] p = line.split(":", 2); //$NON-NLS-1$
				if (p.length == 2)
					procfile.put(p[0], p[1]);
			}

			return procfile;

		} finally {
			if (reader != null)
				reader.close();
		}
	}
}
