class Destination extends FixedSquare {

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Destination(int x, int y){
        super(x, y);
    }

    /**
     * draw this destination
     */
    @Override
    void draw() {
        Monitor.print_str(Configuration.destinationPattern);
    }

    @Override
    char getType() {
        return Configuration.destinationTypeChar;
    }
}
