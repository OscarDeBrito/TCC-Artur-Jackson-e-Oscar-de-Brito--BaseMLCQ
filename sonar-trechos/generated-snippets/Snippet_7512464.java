public class Snippet__7512464 {

    static final class Key {

            final Object content;
            final URI uri;
            final URL url;
            final String name;
            final String mimeType;
            final String language;
            final String path;
            final boolean internal;
            final boolean interactive;
            final boolean cached;
            // TODO remove legacy field with deprecated Source builders.
            final boolean legacy;

            Key(Object content, String mimeType, String languageId, URL url, URI uri, String name, String path, boolean internal, boolean interactive, boolean cached, boolean legacy) {
                this.content = content;
                this.mimeType = mimeType;
                this.language = languageId;
                this.name = name;
                this.path = path;
                this.internal = internal;
                this.interactive = interactive;
                this.cached = cached;
                this.url = url;
                this.uri = uri;
                this.legacy = legacy;
            }

            @Override
            public int hashCode() {
                int result = 31 * 1 + ((content == null) ? 0 : content.hashCode());
                result = 31 * result + (interactive ? 1231 : 1237);
                result = 31 * result + (internal ? 1231 : 1237);
                result = 31 * result + (cached ? 1231 : 1237);
                result = 31 * result + ((language == null) ? 0 : language.hashCode());
                result = 31 * result + ((mimeType == null) ? 0 : mimeType.hashCode());
                result = 31 * result + ((name == null) ? 0 : name.hashCode());
                result = 31 * result + ((path == null) ? 0 : path.hashCode());
                result = 31 * result + ((uri == null) ? 0 : uri.hashCode());
                result = 31 * result + ((url == null) ? 0 : url.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                } else if (!(obj instanceof Key)) {
                    return false;
                }
                Key other = (Key) obj;
                /*
                 * Compare characters last as it is likely the most expensive comparison in the worst
                 * case.
                 */
                return Objects.equals(language, other.language) && //
                                Objects.equals(mimeType, other.mimeType) && //
                                Objects.equals(name, other.name) && //
                                Objects.equals(path, other.path) && //
                                Objects.equals(uri, other.uri) && //
                                Objects.equals(url, other.url) && //
                                interactive == other.interactive && //
                                internal == other.internal &&
                                cached == other.cached &&
                                compareContent(other);
            }

            private boolean compareContent(Key other) {
                Object otherContent = other.content;
                if (content == other.content) {
                    return true;
                } else if (content instanceof CharSequence && otherContent instanceof CharSequence) {
                    return compareCharacters((CharSequence) content, (CharSequence) otherContent);
                } else if (content instanceof ByteSequence && otherContent instanceof ByteSequence) {
                    return compareBytes((ByteSequence) content, (ByteSequence) otherContent);
                } else {
                    return false;
                }
            }

            private static boolean compareBytes(ByteSequence bytes, ByteSequence other) {
                if (bytes == null || bytes.length() != other.length()) {
                    return false;
                } else {
                    // trusted class
                    return bytes.equals(other);
                }
            }

            private static boolean compareCharacters(CharSequence characters, CharSequence other) {
                if (characters == null || characters.length() != other.length()) {
                    return false;
                } else {
                    return Objects.equals(characters.toString(), other.toString());
                }
            }

            SourceImpl toSourceInterned() {
                assert cached;
                return new SourceImpl(this);
            }

            SourceImpl toSourceNotInterned() {
                assert !cached;
                return new SourceImpl(this, this);
            }

        }

}
