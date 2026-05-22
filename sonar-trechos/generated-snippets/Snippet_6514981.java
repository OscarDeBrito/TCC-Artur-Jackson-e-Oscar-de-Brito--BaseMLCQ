public class Snippet__6514981 {

    @Override
            public void unitKept(IInstallableUnit unit) {
                super.unitKept(unit);
                logger.debug("  Keeping unit " + unit.getId() + "/" + unit.getVersion());
            }

}
