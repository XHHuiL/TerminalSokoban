abstract class FixedSquare extends Square{

    /**
     * @param x coordinate
     * @param y coordinate
     */
    FixedSquare(int x, int y){
        super(x, y);
    }

    /**
     * below methods is not allowed to call
     */
    @Override
    void setX(int x) {
        Monitor.print_warning_mess(Configuration.canNotSetFixedSquareX);
    }

    @Override
    void setY(int y) {
        Monitor.print_warning_mess(Configuration.canNotSetFixedSquareY);
    }

    @Override
    void set_position(int x, int y) {
        Monitor.print_warning_mess(Configuration.canNotSetFixedSquarePosition);
    }
}
