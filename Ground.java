class Ground extends FixedSquare {

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Ground(int x, int y){
        super(x, y);
    }

    /**
     * draw this ground
     */
    @Override
    void draw() {
        Monitor.print_str(Configuration.groundPattern);
    }

    @Override
    char getType() {
        return Configuration.groundTypeChar;
    }
}
