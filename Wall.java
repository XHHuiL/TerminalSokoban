class Wall extends FixedSquare{

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Wall(int x, int y){
        super(x, y);
    }

    /**
     * draw this wall
     */
    @Override
    void draw() {
        Monitor.print_str(Configuration.wallPattern);
    }

    @Override
    char getType() {
        return Configuration.wallTypeChar;
    }
}
