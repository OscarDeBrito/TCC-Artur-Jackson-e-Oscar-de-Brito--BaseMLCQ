private static class TestId {

    final String testClass;
    final String id;

    public TestId(String testClass, String id) {
      this.testClass = testClass;
      this.id = id;
    }

    @Override
    public int hashCode() {
      return Objects.hash(testClass, id);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;

      if (obj instanceof TestId) {
        TestId other = (TestId) obj;

        return id.equals(other.id) && testClass.equals(other.testClass);
      }

      return false;
    }
  }
