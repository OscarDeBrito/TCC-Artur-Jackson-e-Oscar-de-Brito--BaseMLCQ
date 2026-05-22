public class Snippet__9087427 {

    public Snippet__9087427(boolean functions, int maxNames, boolean unique) {
                this.functions = functions;
                this.maxNames = maxNames == -1 ? Integer.MAX_VALUE : maxNames;
                this.unique = unique ? new HashSet<>() : null;
                this.result = new ArrayList<>();
            }

}
