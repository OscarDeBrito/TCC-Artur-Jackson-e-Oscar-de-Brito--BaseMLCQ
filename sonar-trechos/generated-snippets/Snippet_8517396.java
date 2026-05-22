public class CheckHealthAnswer extends Answer {

    public CheckHealthAnswer() {
    }

    public CheckHealthAnswer(CheckHealthCommand cmd, boolean alive) {
        super(cmd, alive, "resource is " + (alive ? "alive" : "not alive"));
    }
}
