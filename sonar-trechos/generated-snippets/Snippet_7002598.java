static class MustHaveSignedMarksInCurrentPhaseException extends CommandUseErrorException {
    public MustHaveSignedMarksInCurrentPhaseException() {
      super("The current registry phase requires a signed mark for registrations");
    }
  }
