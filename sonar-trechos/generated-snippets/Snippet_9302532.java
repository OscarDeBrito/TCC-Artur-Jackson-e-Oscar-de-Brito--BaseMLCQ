class StandardDocFile extends DocFile {
        private final Path file;

        /** Create a StandardDocFile for a given file. */
        private StandardDocFile(Path file) {
            this.file = file;
        }

        /** Create a StandardDocFile for a given location and relative path. */
        private StandardDocFile(Location location, DocPath path) {
            super(location, path);
            Assert.check(location == DocumentationTool.Location.DOCUMENTATION_OUTPUT);
            this.file = newFile(getDestDir(), path.getPath());
        }

        @Override
        public FileObject getFileObject()  {
            return getJavaFileObjectForInput(file);
        }

        /**
         * Open an input stream for the file.
         *
         * @throws DocFileIOException if there is a problem while opening stream
         */
        @Override
        public InputStream openInputStream() throws DocFileIOException {
            try {
                JavaFileObject fo = getJavaFileObjectForInput(file);
                return new BufferedInputStream(fo.openInputStream());
            } catch (IOException e) {
                throw new DocFileIOException(this, DocFileIOException.Mode.READ, e);
            }
        }

        /**
         * Open an output stream for the file.
         * The file must have been created with a location of
         * {@link DocumentationTool.Location#DOCUMENTATION_OUTPUT} and a corresponding relative path.
         *
         * @throws DocFileIOException if there is a problem while opening stream
         */
        @Override
        public OutputStream openOutputStream() throws DocFileIOException {
            if (location != DocumentationTool.Location.DOCUMENTATION_OUTPUT)
                throw new IllegalStateException();

            try {
                OutputStream out = getFileObjectForOutput(path).openOutputStream();
                return new BufferedOutputStream(out);
            } catch (IOException e) {
                throw new DocFileIOException(this, DocFileIOException.Mode.WRITE, e);
            }
        }

        /**
         * Open an writer for the file, using the encoding (if any) given in the
         * doclet configuration.
         * The file must have been created with a location of
         * {@link DocumentationTool.Location#DOCUMENTATION_OUTPUT} and a corresponding relative path.
         *
         * @throws DocFileIOException if there is a problem while opening stream
         * @throws UnsupportedEncodingException if the configured encoding is not supported
         */
        @Override
        public Writer openWriter() throws DocFileIOException, UnsupportedEncodingException {
            if (location != DocumentationTool.Location.DOCUMENTATION_OUTPUT)
                throw new IllegalStateException();

            try {
                OutputStream out = getFileObjectForOutput(path).openOutputStream();
                return new BufferedWriter(new OutputStreamWriter(out, configuration.docencoding));
            } catch (IOException e) {
                throw new DocFileIOException(this, DocFileIOException.Mode.WRITE, e);
            }
        }

        /** Return true if the file can be read. */
        @Override
        public boolean canRead() {
            return Files.isReadable(file);
        }

        /** Return true if the file can be written. */
        @Override
        public boolean canWrite() {
            return Files.isWritable(file);
        }

        /** Return true if the file exists. */
        @Override
        public boolean exists() {
            return Files.exists(file);
        }

        /** Return the base name (last component) of the file name. */
        @Override
        public String getName() {
            return file.getFileName().toString();
        }

        /** Return the file system path for this file. */
        @Override
        public String getPath() {
            return file.toString();
        }

        /** Return true is file has an absolute path name. */
        @Override
        public boolean isAbsolute() {
            return file.isAbsolute();
        }

        /** Return true is file identifies a directory. */
        @Override
        public boolean isDirectory() {
            return Files.isDirectory(file);
        }

        /** Return true is file identifies a file. */
        @Override
        public boolean isFile() {
            return Files.isRegularFile(file);
        }

        /** Return true if this file is the same as another. */
        @Override
        public boolean isSameFile(DocFile other) {
            if (!(other instanceof StandardDocFile))
                return false;

            try {
                return Files.isSameFile(file, ((StandardDocFile) other).file);
            } catch (IOException e) {
                return false;
            }
        }

        /** If the file is a directory, list its contents. */
        @Override
        public Iterable<DocFile> list() throws DocFileIOException {
            List<DocFile> files = new ArrayList<>();
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(file)) {
                for (Path f: ds) {
                    files.add(new StandardDocFile(f));
                }
            } catch (IOException e) {
                throw new DocFileIOException(this, DocFileIOException.Mode.READ, e);
            }
            return files;
        }

        /** Create the file as a directory, including any parent directories. */
        @Override
        public boolean mkdirs() {
            try {
                Files.createDirectories(file);
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        /**
         * Derive a new file by resolving a relative path against this file.
         * The new file will inherit the configuration and location of this file
         * If this file has a path set, the new file will have a corresponding
         * new path.
         */
        @Override
        public DocFile resolve(DocPath p) {
            return resolve(p.getPath());
        }

        /**
         * Derive a new file by resolving a relative path against this file.
         * The new file will inherit the configuration and location of this file
         * If this file has a path set, the new file will have a corresponding
         * new path.
         */
        @Override
        public DocFile resolve(String p) {
            if (location == null && path == null) {
                return new StandardDocFile(file.resolve(p));
            } else {
                return new StandardDocFile(location, path.resolve(p));
            }
        }

        /**
         * Resolve a relative file against the given output location.
         * @param locn Currently, only
         * {@link DocumentationTool.Location.DOCUMENTATION_OUTPUT} is supported.
         */
        @Override
        public DocFile resolveAgainst(Location locn) {
            if (locn != DocumentationTool.Location.DOCUMENTATION_OUTPUT)
                throw new IllegalArgumentException();
            return new StandardDocFile(getDestDir().resolve(file));
        }

        /** Return a string to identify the contents of this object,
         * for debugging purposes.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("StandardDocFile[");
            if (location != null)
                sb.append("locn:").append(location).append(",");
            if (path != null)
                sb.append("path:").append(path.getPath()).append(",");
            sb.append("file:").append(file);
            sb.append("]");
            return sb.toString();
        }

        private JavaFileObject getJavaFileObjectForInput(Path file) {
            return fileManager.getJavaFileObjects(file).iterator().next();
        }

        private FileObject getFileObjectForOutput(DocPath path) throws IOException {
            // break the path into a package-part and the rest, by finding
            // the position of the last '/' before an invalid character for a
            // package name, such as the "." before an extension or the "-"
            // in filenames like package-summary.html, doc-files or src-html.
            String p = path.getPath();
            int lastSep = -1;
            for (int i = 0; i < p.length(); i++) {
                char ch = p.charAt(i);
                if (ch == '/') {
                    lastSep = i;
                } else if (i == lastSep + 1 && !Character.isJavaIdentifierStart(ch)
                        || !Character.isJavaIdentifierPart(ch)) {
                    break;
                }
            }
            String pkg = (lastSep == -1) ? "" : p.substring(0, lastSep);
            String rest = p.substring(lastSep + 1);
            return fileManager.getFileForOutput(location, pkg, rest, null);
        }
    }
