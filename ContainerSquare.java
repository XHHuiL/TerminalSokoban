import java.util.Stack;

class ContainerSquare extends FixedSquare{

    private Stack<Square> squares;

    private ContainerSquare(int x, int y){
        super(x, y);
        squares = new Stack<>();
    }

    /*
    * variable length of parameters make
    * creating container square easy
    * */
    ContainerSquare(Square fSquare,Square...iSquares){
        this(fSquare.getX(), fSquare.getY());
        push(fSquare);
        for (Square s:iSquares
             ) {
            push(s);
        }
    }

    void push(Square square){
        squares.push(square);
    }

    Square pop(){
        return squares.pop();
    }

    Square peek(){
        return squares.peek();
    }

    @Override
    void draw() {
        squares.peek().draw();
    }

    @Override
    char getType() {
        return squares.peek().getType();
    }
}
