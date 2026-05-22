public class SetAccessControlEntriesInfo {

    private ArrayList<AccessControlEntry> accessControlEntries;
    private boolean merge;
    private String token;

    public ArrayList<AccessControlEntry> getAccessControlEntries() {
        return accessControlEntries;
    }

    public void setAccessControlEntries(final ArrayList<AccessControlEntry> accessControlEntries) {
        this.accessControlEntries = accessControlEntries;
    }

    public boolean getMerge() {
        return merge;
    }

    public void setMerge(final boolean merge) {
        this.merge = merge;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
