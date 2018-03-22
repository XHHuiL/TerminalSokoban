import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Controller {

    // the col of the map
    private int col;

    // the row of the map
    private int row;

    // the array used to stand the map
    private Square[][] map;

    // the string used to store the map
    private Stack<String> mapStrings;

    private Player player;

    private Queue<Box> boxes;

    /*
     * accept the map file to construct the controller
     * load the map file
     * */
    Controller(String path) {
        mapStrings = new Stack<>();
        mapStrings.push(Loader.load_map(path));
        extract_map_str(mapStrings.peek());
    }

    /**
     * @param mapStr map string that need to extract
     */
    private void extract_map_str(String mapStr) {
        // split map string
        String[] temp = mapStr.split(Configuration.splitPattern);

        // empty the boxes
        boxes = new LinkedList<>();

        // extract the col and row
        col = Integer.parseInt(temp[0]);

        row = Integer.parseInt(temp[1]);

        Square s;
        // extract squares
        map = new Square[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                s = Square.generate_square(i, j, temp[i * col + j + 2].charAt(0));
                map[i][j] = s;
                // update the boxes and player
                if (s instanceof ContainerSquare) {
                    if (((ContainerSquare) s).peek() instanceof Box)
                        boxes.add((Box) ((ContainerSquare) s).peek());
                    else if (((ContainerSquare) s).peek() instanceof Player)
                        player = (Player) ((ContainerSquare) s).peek();
                }
            }
        }
    }

    /**
     * form a new map string
     * the new string will be used when the player undo
     */
    private void form_new_map_string() {
        StringBuilder mapStr = new StringBuilder();
        mapStr.append(col).append(Configuration.splitPattern);
        mapStr.append(row).append(Configuration.splitPattern);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mapStr.append(map[i][j].getType()).
                        append(Configuration.splitPattern);
            }
        }

        mapStrings.push(mapStr.toString().substring(0, mapStr.length() - 1));
    }

    /**
     * draw the map
     */
    void draw() {
        Monitor.println(Configuration.sokobanHeaderStr);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j].draw();
                Monitor.print_str(Configuration.spacePattern);
            }
            Monitor.println();
        }
        Monitor.println(Configuration.sokobanFooterStr);
    }

    /**
     * @param instr the instruction need to execute
     */
    void execute_instruction(char instr) {
        if (instr == Configuration.up ||
                instr == Configuration.left ||
                instr == Configuration.down ||
                instr == Configuration.right) {
            if (move(instr))
                form_new_map_string();
            if (whether_win()) {
                draw();
                Monitor.println(Configuration.celebrateVictory);
                System.exit(0);
            }
        } else if (instr == Configuration.undo)
            undo();
        else if (instr == Configuration.restart)
            restart();
        else if (instr == Configuration.quit)
            System.exit(0);
        else
            Monitor.print_warning_mess(Configuration.notSupportedInstruction);
    }

    /*
     * if move success then return true, otherwise return false
     * */
    private boolean move(char instr) {
        Square nextSqr = get_next_square(player, instr);
        if (nextSqr == null) {
            Monitor.print_error_mess(Configuration.overIndexRangeError,
                    Configuration.exitImmediately, 8);
            return false;
        }
        if (nextSqr instanceof Floor) {
            move_square(nextSqr, player, false);
            return true;
        } else if (nextSqr instanceof Destination) {
            move_square(nextSqr, player, true);
            return true;
        } else
            return nextSqr instanceof Box && move_player_and_box((Box) nextSqr, instr);
    }

    /*
     * is move success then return true, otherwise return false
     * */
    private boolean move_player_and_box(Box box, char instr) {
        Square afterBoxSqr = get_next_square(box, instr);
        Box oldBox = box.copy();
        if (afterBoxSqr == null) {
            Monitor.print_error_mess(Configuration.overIndexRangeError,
                    Configuration.exitImmediately, 8);
            return false;
        }
        if (afterBoxSqr instanceof Floor) {
            move_square(afterBoxSqr, box, false);
            move_square(oldBox, player, oldBox.isInDes());
            return true;
        } else if (afterBoxSqr instanceof Destination) {
            move_square(afterBoxSqr, box, true);
            move_square(oldBox, player, oldBox.isInDes());
            return true;
        } else
            return false;
    }

    /**
     * @param currSquare current square that may be move
     * @param instr      instruction
     * @return next square around current square
     */
    private Square get_next_square(Square currSquare, char instr) {
        int currX, nextX, currY, nextY;
        switch (instr) {
            case Configuration.up:
                currX = currSquare.getX();
                nextX = currX - 1;
                if (whether_over_index_range(nextX, true))
                    return null;
                return get_upper_square(nextX, currSquare.getY());
            case Configuration.left:
                currY = currSquare.getY();
                nextY = currY - 1;
                if (whether_over_index_range(nextY, false))
                    return null;
                return get_upper_square(currSquare.getX(), nextY);
            case Configuration.down:
                currX = currSquare.getX();
                nextX = currX + 1;
                if (whether_over_index_range(nextX, true))
                    return null;
                return get_upper_square(nextX, currSquare.getY());
            default:
                currY = currSquare.getY();
                nextY = currY + 1;
                if (whether_over_index_range(nextY, false))
                    return null;
                return get_upper_square(currSquare.getX(), nextY);
        }
    }

    private boolean whether_over_index_range(int index, boolean isVertical) {
        return index < 0 || isVertical ? (index >= row) : (index >= col);
    }

    /*
     * get the upper square of a container square
     * */
    private Square get_upper_square(int x, int y) {
        Square s = map[x][y];
        if (s instanceof ContainerSquare)
            return ((ContainerSquare) s).peek();
        return s;
    }

    /*
     * move the player or box
     * */
    private void move_square(Square ds, Square ss, boolean toDes) {
        int x = ds.getX();
        int y = ds.getY();
        ContainerSquare cs = (ContainerSquare) map[x][y];
        ContainerSquare pcs = (ContainerSquare) map[ss.getX()][ss.getY()];
        cs.push(pcs.pop());
        ss.set_position(x, y);
        if (ss instanceof Player)
            ((Player) ss).setInDes(toDes);
        else if (ss instanceof Box)
            ((Box) ss).setInDes(toDes);
    }

    private boolean whether_win() {
        for (Box box : boxes
                ) {
            if (!box.isInDes())
                return false;
        }
        return true;
    }

    /*
     * support game player undo
     * */
    private void undo() {
        if (mapStrings.size() > 1) {
            mapStrings.pop();
            extract_map_str(mapStrings.peek());
        }
    }

    /*
     * support game player restart
     * */
    private void restart() {
        while (mapStrings.size() > 1)
            mapStrings.pop();
        extract_map_str(mapStrings.peek());
    }
}
