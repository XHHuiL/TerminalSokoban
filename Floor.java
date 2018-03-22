class Floor extends FixedSquare {

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Floor(int x, int y){
        super(x, y);
    }

    /**
     * draw this floor
     */
    @Override
    void draw() {
        Monitor.print_str(Configuration.floorPattern);
    }

    @Override
    char getType() {
        return Configuration.floorTypeChar;
    }
}
