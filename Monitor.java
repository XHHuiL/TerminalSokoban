class Monitor {

    /**
     * a new line
     */
    static void println() {
        System.out.println();
    }

    /**
     * @param s string need to println
     */
    static void println(String s) {
        System.out.println(s);
    }

    /**
     * @param s string need to print
     */
    static void print_str(String s) {
        System.out.print(s);
    }

    /**
     * @param errorReasonMes reason of this error
     * @param errorResultMes result of this error
     * @param status         status code
     */
    static void print_error_mess(String errorReasonMes, String errorResultMes, int status) {
        println(errorReasonMes);
        println(errorResultMes);
        System.exit(status);
    }

    /**
     * @param warningReasonMes reason of this warning
     */
    static void print_warning_mess(String warningReasonMes) {
        println(warningReasonMes);
    }

}
