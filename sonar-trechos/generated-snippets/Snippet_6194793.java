public class Snippet__6194793 {

    @Override
      public void actionPerformed(ActionEvent e) {
        final Resource[] resources = appContext.getResources();

        checkState(resources != null && resources.length == 1 && resources[0] instanceof Container);

        presenter.showDialog((Container) resources[0]);
      }

}
