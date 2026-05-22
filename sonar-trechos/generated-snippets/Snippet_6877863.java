public class Snippet__6877863 {

    private void informBaseTypesNotification(final TypesNotificationContainer container,
          final SQLProvider provider) throws CouldntLoadDataException {

        final TypeManager typeManager = provider.findModule(container.getModuleId()).getTypeManager();
        if (container.getDatabaseOperation().equals("INSERT")) {
          typeManager.loadAndInitializeBaseType(container.getBaseTypeId().get());
        } else if (container.getDatabaseOperation().equals("UPDATE")) {
          typeManager.loadAndUpdateBaseType(container.getBaseTypeId().get());
        } else if (container.getDatabaseOperation().equals("DELETE")) {
          typeManager.removeBaseTypeInstance(container.getBaseTypeId().get());
        }
      }

}
