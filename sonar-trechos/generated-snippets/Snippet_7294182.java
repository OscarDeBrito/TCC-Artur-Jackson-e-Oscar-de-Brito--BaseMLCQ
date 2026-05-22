public class Snippet__7294182 {

    private String gspFolderName() {
            String name = unit.getElementName();
            int dotIndex = name.indexOf(".");
            if (dotIndex > 0) {
                name = Character.toLowerCase(name.charAt(0)) + name.substring(1, dotIndex);
                return name;
            } else {
                return name;
            }
        }

}
