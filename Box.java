class Box extends Square {

    /**
     * is this box in destination
     */
    private boolean inDes = false;

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Box(int x, int y) {
        super(x, y);
    }

    /**
     * @param x     coordinate
     * @param y     coordinate
     * @param inDes is in destination
     */
    Box(int x, int y, boolean inDes) {
        this(x, y);
        this.inDes = inDes;
    }

    // getter and setter
    boolean isInDes() {
        return inDes;
    }

    void setInDes(boolean inDes) {
        this.inDes = inDes;
    }

    /**
     * draw this box
     */
    @Override
    void draw() {
        if (inDes)
            Monitor.print_str(Configuration.boxLightPattern);
        else
            Monitor.print_str(Configuration.boxNormalPattern);
    }

    @Override
    char getType() {
        return inDes ? Configuration.boxLightTypeChar :
                Configuration.boxNormalTypeChar;
    }

    // as same as clone
    Box copy() {
        return new Box(getX(), getY(), isInDes());
    }
}
