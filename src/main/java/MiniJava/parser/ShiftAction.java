package MiniJava.parser;

public class ShiftAction extends Action{

    public ShiftAction(int number) {
        super(number);
    }

    @Override
    public void act(Parser parser) {
        parser.getParsStack().push(this.number);
        parser.setLookAhead(parser.getLexicalAnalyzer().getNextToken());
    }

    @Override
    public String toString() {
        return "s" + number;
    }
}
