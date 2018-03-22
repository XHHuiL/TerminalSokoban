public class Player extends Square {

    /**
     * is this player in destination
     */
    private boolean inDes = false;

    /**
     * @param x coordinate
     * @param y coordinate
     */
    Player(int x, int y) {
        super(x, y);
    }

    /**
     * @param x     coordinate
     * @param y     coordinate
     * @param inDes is in destination
     */
    Player(int x, int y, boolean inDes) {
        this(x, y);
        this.inDes = inDes;
    }

    public void setInDes(boolean inDes) {
        this.inDes = inDes;
    }

    /**
     * draw this player
     */
    @Override
    void draw() {
        if (inDes)
            Monitor.print_str(Configuration.playerLightPattern);
        else
            Monitor.print_str(Configuration.playerNormalPattern);
    }

    @Override
    char getType() {
        return inDes ? Configuration.playerLightTypeChar :
                Configuration.playerNormalTypeChar[0];
    }

    /**
     * @param type the type os a square
     * @return whether this square a normal player
     */
    static boolean is_normal_player(char type) {
        for (char c : Configuration.playerNormalTypeChar
                ) {
            if (type == c)
                return true;
        }
        return false;
    }
}
