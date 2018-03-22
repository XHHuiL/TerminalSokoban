public class Configuration {
    /*
    * The first part:
    * Some prompt messages when the program execute in a abnormal way,
    * the user programmer use class in a undesirable way,
    * or some other messages
    * When blow messages are printed on the terminal,
    * it means the program has met a error, warning or prompt message!
    * */

    // error reason
    static final String notSupportedUsageError = "Usage: java Sokoban <path>! Status: 1";

    static final String pathNotExistsError = "Error: this path is not exists! Status: 2";

    static final String notFileError = "Error: this path not represent a file! Status: 3";

    static final String notReadableError = "Error: this file is not readable! Status: 4";

    static final String closeReaderFailError = "Error: close reader fail! Status: 5";

    static final String readLineFailError = "Error: read line from map fail! Status: 6";

    static final String typeNotExistsError = "Error: this type of square is not exists! Status: 7";

    static final String overIndexRangeError = "Error: over index range! Status 8";

    // error result
    static final String loadMapFail = "Load map fail!";

    static final String exitImmediately = "Exit immediately!";

    static final String generateSquareFail = "Generate square fail!";

    // warning message
    static final String canNotSetFixedSquareX = "Warning: can not set x coordinate of fixed square!";

    static final String canNotSetFixedSquareY = "Warning: can not set y coordinate of fixed square!";

    static final String canNotSetFixedSquarePosition = "Warning: can not set position of fixed square!";

    static final String notSupportedInstruction = "Warning: not supported instruction!";

    static final String requireInputInstruction = "Warning: please input instruction!";

    // other message
    static final String sokobanHeaderStr = "---------- Sokoban ----------";

    static final String promptForInput = "move: w, a, s, d\nrestart: r\nundo: u\nquit: q";

    static final String sokobanFooterStr = "-----------------------------";

    static final String celebrateVictory = "Good! You have won!";

    /*
    * The second part:
    * This part is for the status code
    *       status      description
    *       0           this program exit normally
    *       1           not supported usage
    *       2           map path is not exists
    *       3           map path not represent a file
    *       4           map file is not readable
    *       5           close buffer reader fail
    *       6           read line from map fail
    *       7           type of square is not exists
    *       8           over index range
    * */

    /*
    * The third part:
    * This part is for all the patterns used
    *       pattern     description
    *       #           pattern used to split the format map string
    *       _           pattern used to draw the square
    *       ¡ö          pattern used to draw the wall
    *       '¡¡'        pattern used to draw the ground
    *       ¡ò          pattern used to draw the destination
    *       '¡¡'        pattern used to draw the floor
    *       ¡õ          pattern used to draw the normal box
    *       ¡Ñ          pattern used to draw the light box
    *       ¡î          pattern used to draw the normal player
    *       ¡ï          pattern used to draw the light player
    *       ' '         pattern used to draw the space between two squares
     * */
    static final String splitPattern = "#";
    static final String squarePattern = "_";
    static final String wallPattern = "¡ö";
    static final String groundPattern = "¡¡";
    static final String destinationPattern = "¡ò";
    static final String floorPattern = "¡¡";
    static final String boxNormalPattern = "¡õ";
    static final String boxLightPattern = "¡Ñ";
    static final String playerNormalPattern = "¡î";
    static final String playerLightPattern = "¡ï";
    static final String spacePattern = " ";

    /*
    * The fourth part
    * This part for all chars used to represent type of square
    * and all instructions
    * */
    static final char squareTypeChar = '-';
    static final char groundTypeChar = '0';
    static final char wallTypeChar = '1';
    static final char floorTypeChar = '2';
    static final char boxNormalTypeChar = '3';
    static final char destinationTypeChar = '4';
    static final char playerNormalTypeChar[] = new char[]{'5', '6', '7', '8'};
    static final char boxLightTypeChar = '9';
    static final char playerLightTypeChar = '*';
    static final char noInstr = 'n';
    static final char quit = 'q';
    static final char up = 'w';
    static final char left = 'a';
    static final char down = 's';
    static final char right = 'd';
    static final char undo = 'u';
    static final char restart = 'r';
}
