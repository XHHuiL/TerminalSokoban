import java.io.*;

public class Loader {

    /**
     * @param path path of map
     * @return a format string stand the map
     */
    static String load_map(String path) {
        // readable ?
        File f = can_read(path);

        // read this file
        return read_map_file(f);
    }

    /**
     * @param path path of map
     * @return a File object that can be read
     */
    private static File can_read(String path) {
        File f = new File(path);

        // judge whether this file is readable
        if (!f.exists()) {
            Monitor.print_error_mess(Configuration.pathNotExistsError,
                    Configuration.loadMapFail, 2);
        }
        if (!f.isFile()) {
            Monitor.print_error_mess(Configuration.notFileError,
                    Configuration.loadMapFail, 3);
        }
        if (!f.canRead()) {
            Monitor.print_error_mess(Configuration.notReadableError,
                    Configuration.loadMapFail, 4);
        }

        return f;
    }

    /**
     * @param f File object used to read the col, the row and the map string
     */
    private static String read_map_file(File f) {
        BufferedReader reader = null;
        StringBuilder formatStr = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(f));
            String s;
            int count = 0;

            // read line
            while ((s = reader.readLine()) != null) {
                if (count == 0) {
                    formatStr.append(read_col_and_row(s));
                }
                else {
                    formatStr.append(read_squares(s));
                }

                count++;
            }
        }
        // file may not found
        catch (FileNotFoundException e) {
            Monitor.print_error_mess(Configuration.pathNotExistsError,
                    Configuration.loadMapFail, 2);
        }
        // read line fail
        catch (IOException ioE) {
            Monitor.print_error_mess(Configuration.readLineFailError,
                    Configuration.loadMapFail, 6);
        }
        // make sure the reader will be closed
        finally {
            close_reader(reader);
        }

        // remove the last char
        String fStr = formatStr.toString();
        return fStr.substring(0, fStr.length() - 1);
    }

    /**
     * @param s the first line
     * @return the col and row of the map
     */
    private static String read_col_and_row(String s) {
        String temp[] = s.split("\\s+");
        return temp[0] + Configuration.splitPattern + temp[1] + Configuration.splitPattern;
    }

    /**
     * @param s other line not the first
     * @return squares of the map
     */
    private static String read_squares(String s) {
        String[] squares = s.split("");
        StringBuilder formatStr = new StringBuilder();
        for (int i = 0; i < squares.length; i++) {
            squares[i] += Configuration.splitPattern;
            formatStr.append(squares[i]);
        }
        return formatStr.toString();
    }

    /**
     * @param reader the buffer reader need to be closed
     */
    private static void close_reader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            }
            // close reader fail
            catch (IOException e) {
                Monitor.print_error_mess(Configuration.closeReaderFailError,
                        Configuration.loadMapFail, 5);
            }
        }
    }
}
