package MiniJava.parser;

public abstract class Action {
    //if action = shift : number is state
    //if action = reduce : number is number of rule
    public int number;

    public Action(int number) {
        this.number = number;
    }

    public abstract void act(Parser parser);

    public abstract String toString();
}
