package MiniJava.parser;

public class AcceptAction extends Action{
    public AcceptAction(int number) {
        super(number);
    }

    @Override
    public void act(Parser parser) {
        parser.setFinish(true);
    }

    @Override
    public String toString() {
        return "acc";
    }
}
