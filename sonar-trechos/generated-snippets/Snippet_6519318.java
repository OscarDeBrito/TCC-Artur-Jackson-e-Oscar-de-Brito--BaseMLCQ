public class Snippet__6519318 {

    private Generator getGenerator(String serviceKey) {
        List<Generator> generators = this.registeredGeneratorsRepository.findByGeneratorKey(serviceKey);
        if (!generators.isEmpty()) {
          return generators.get(0);
        } else {
          return null;
        }
      }

}
