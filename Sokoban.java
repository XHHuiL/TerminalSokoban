import java.util.Scanner;

public class Sokoban {

    public static void main(String[] args) {
        if (args.length == 1) {
            Controller controller = new Controller(args[0]);

            char instruction = Configuration.noInstr;
            String instructions[];
            Scanner input = new Scanner(System.in);

            /*
            * drw map
            * prompt input
            * and execute instructions
            * */
            while (instruction != Configuration.quit) {
                controller.draw();
                Monitor.println(Configuration.promptForInput);
                instructions = input.nextLine().trim().toLowerCase().split("");
                for (String instr : instructions
                        ) {
                    if (instr.length() > 0) {
                        instruction = instr.charAt(0);
                        controller.execute_instruction(instruction);
                    } else
                        Monitor.print_warning_mess(Configuration.requireInputInstruction);
                }
            }

            System.exit(0);
        } else {
            Monitor.print_error_mess(Configuration.notSupportedUsageError,
                    Configuration.exitImmediately, 1);
            System.exit(1);
        }
    }
}
