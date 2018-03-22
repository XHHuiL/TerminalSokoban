class Square {

    // the position (x, y)
    private int x;
    private int y;

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // getter and setter
    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    void set_position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * draw this square
     */
    void draw() {
        Monitor.print_str(Configuration.squarePattern);
    }

    char getType(){
        return Configuration.squareTypeChar;
    }

    /**
     * @param type the square type
     * @return a square object
     */
    static Square generate_square(int x, int y, char type) {
        switch (type) {
            case Configuration.groundTypeChar:
                return new Ground(x, y);
            case Configuration.wallTypeChar:
                return new Wall(x, y);
            case Configuration.floorTypeChar:
                return new ContainerSquare(new Floor(x, y));
            case Configuration.boxNormalTypeChar:
                return new ContainerSquare(new Floor(x, y), new Box(x, y));
            case Configuration.boxLightTypeChar:
                return new ContainerSquare(new Destination(x, y), new Box(x, y, true));
            case Configuration.destinationTypeChar:
                return new ContainerSquare(new Destination(x, y));
            case Configuration.playerLightTypeChar:
                return new ContainerSquare(new Destination(x, y), new Player(x, y, true));
            default:
                if (Player.is_normal_player(type)) {
                    return new ContainerSquare(new Floor(x, y), new Player(x, y));
                } else {
                    Monitor.print_error_mess(Configuration.typeNotExistsError,
                            Configuration.generateSquareFail, 7);
                    return null;
                }
        }
    }
}
