public class FileSaveEventHandler implements ActionListener {

  private final MainFrame main;

  public FileSaveEventHandler(MainFrame frame) {
    super();
    this.main = frame;
  }

  public void actionPerformed(ActionEvent event) {
    this.main.saveFile();
    this.main.setStatusbarMessage("Text file " + this.main.getTextFile().getName() + " saved.");
  }

}
