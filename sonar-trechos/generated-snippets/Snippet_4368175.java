public final class ForkOnceCodeGenerator {
    private ForkOnceCodeGenerator() {
        //utility
    }
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            int i = Integer.parseInt(line);
            if (i == -1) {
                reader.close();
                return;
            }
            String[] wargs = new String[i];
            for (int x = 0; x < i; x++) {
                wargs[x] = reader.readLine();
            }

            new WADLToJava(wargs).run(new ToolContext());

            line = reader.readLine();
        }
        reader.close();
    }
}
