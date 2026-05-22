public final class LithoViewSubComponentDeepExtractor
    implements Extractor<LithoView, List<InspectableComponent>> {

  private LithoViewSubComponentDeepExtractor() {}

  @Override
  public List<InspectableComponent> extract(LithoView lithoView) {
    final List<InspectableComponent> res = new LinkedList<>();
    final Stack<InspectableComponent> stack = new Stack<>();

    final InspectableComponent rootInstance = InspectableComponent.getRootInstance(lithoView);
    Preconditions.checkNotNull(
        rootInstance,
        "Could not obtain DebugComponent. "
            + "Please ensure that ComponentsConfiguration.IS_INTERNAL_BUILD is enabled.");
    stack.add(rootInstance);

    while (!stack.isEmpty()) {
      final InspectableComponent inspectableComponent = stack.pop();
      res.add(inspectableComponent);

      for (InspectableComponent child : inspectableComponent.getChildComponents()) {
        stack.push(child);
      }
    }

    return res;
  }

  public static LithoViewSubComponentDeepExtractor subComponentsDeeply() {
    return new LithoViewSubComponentDeepExtractor();
  }

  public static Condition<LithoView> deepSubComponentWith(
      final Condition<InspectableComponent> inner) {
    return new Condition<LithoView>() {
      @Override
      public boolean matches(LithoView lithoView) {
        as("deep sub component with <%s>", inner);
        for (InspectableComponent component : subComponentsDeeply().extract(lithoView)) {
          if (inner.matches(component)) {
            return true;
          }
        }

        return false;
      }
    };
  }
}
