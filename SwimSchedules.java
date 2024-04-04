import java.util.Scanner;
public class SwimSchedules {
    //this is a constant for the number of swim days
    private static final int swimDays = 4;
    //this is a constant for the number of swim slots
    private static final int swimSlots = 4;
    //this is a constant for the days assigned to jeff
    private static final int JeffDays = 0;
    //this is a constant for the days assigned to anna
    private static final int AnnaDays = 1;

    //this is the method that displays the menu that prints that shows the options available
    public static void Menu() {
        System.out.println("Enter one of the following commands: ");
        System.out.println("p - Print schedules");
        System.out.println("s - Schedule a slot");
        System.out.println("f - Free a slot");
        System.out.println("i - Show slots avaiable for individual lessons");
        System.out.println("g - Show slots available for group lessons");
        System.out.println("q - Quit");
    }
    //this is a 3D array that represents the swim schedules for instructors and days
    //in the 3-dimensional array each element is within a 2D array
    //each element in swimSchedule is a 2D array for JeffDays and AnnaDays
    private static char[][][] swimSchedule = {
            {
                    {'X', '_', '_', 'X'},
                    {'X', 'X', 'X', 'X'},
                    {'_', 'X', 'X', 'X'},
                    {'_', 'X', '_', '_'}
            },
            {
                    {'X', '_', 'X', 'X'},
                    {'X', 'X', 'X', '_'},
                    {'_', '_', '_', 'X'},
                    {'X', 'X', '_', 'X'}

            }
    };

    //this method is the choose an instructor either Jeff or Anna
    public static int chooseInstructor(Scanner userInput) {
        System.out.print("Select Instructor (1 - Jeff, 2 - Anna): ");
        int userChoice;
        do {
            userChoice = userInput.nextInt();
            userInput.nextLine();
        } while (userChoice != 1 && userChoice != 2);
        return userChoice - 1;
    }

    //this method is to choose a day either Monday, Tuesday, Wednesday or Thursday
    public static int chooseDay(Scanner userInput) {
        System.out.print("Select Day (1 - Mon, 2 - Tue, 3 - Wed, 4 - Thu): ");
        int userChoice;

        do {
            userChoice = userInput.nextInt();
            userInput.nextLine();
        } while (userChoice < 1 || userChoice > 4);
        return userChoice - 1;
    }

    //this method is to choose a slot that's available
    public static int chooseSlot(Scanner userInput) {
        System.out.print("Select Slot (1 - 11-12, 2 - 12-1, 3 - 1-2, 4 - 2-3):");
        int userChoice;
        do {
            userChoice = userInput.nextInt();
            userInput.nextLine();
        } while (userChoice < 1 || userChoice > 4);
        return userChoice - 1;
    }

    //this method is to display the available schedules for both instructor
    public static void availableSchedule() {
        System.out.println("Jeff:");
        availableSchedules(JeffDays);
        System.out.println("Anna:");
        availableSchedules(AnnaDays);
    }

    //this method shows the available schedules for a specific instructor
    public static void availableSchedules(int instructor) {
        String[] days = {"Mon", "Tue", "Wed", "Thu"};
        String[] slots = {"11-12", "12-1", "1-2", "2-3"};
        int maxSlotLength = 5;//this is to help with formatting

        System.out.printf(String.format("%-" + maxSlotLength + "s", "") + " ");
        for (String day : days) {
            System.out.printf("%-7s", day);
        }
        System.out.println();

        for (int i = 0; i < swimSlots; i++) {
            System.out.printf(String.format("%-" + maxSlotLength + "s", slots[i]));
            for (int j = 0; j < swimDays; j++) {
                System.out.printf(" " + "%-6s", swimSchedule[instructor][j][i] + " ");
            }
            System.out.println("  ");
        }
        System.out.println(" ");
    }

    //this method is to free a slot
    public static void availableSlot(Scanner userInput) {
        int instructor = chooseInstructor(userInput);
        int day = chooseDay(userInput);
        int slot = chooseSlot(userInput);

        swimSchedule[instructor][day][slot] = '_';
        System.out.println("  ");
    }

    //this method is to schedule a slot
    public static void scheduleSlot(Scanner userInput) {
        int instructor = chooseInstructor(userInput);
        int day = chooseDay(userInput);
        int slot = chooseSlot(userInput);

        // this will check if the slot is already reserved
        if (swimSchedule[instructor][day][slot] == 'X') {
            System.out.println("That day is already reserved. Choose");
        } else {
            // if the day is not yet reserved then it will be scheduled
            swimSchedule[instructor][day][slot] = 'X';
            System.out.println("  ");
        }
        System.out.println();
    }

    //this method shows the available slots for individual lessons
    public static void InstructorAvailableSlots() {
        System.out.println("Slots marked with an 'I' are available for individual lessons:");
        System.out.println(" ");
        String[] days = {"Mon", "Tue", "Wed", "Thu"};
        String[] slots = {"11-12", "12-1", "1-2", "2-3"};
        int maxSlotLength = 5;

        System.out.printf(String.format("%-" + maxSlotLength + "s", "") + " ");
        for (String day : days) {
            System.out.printf("%-7s", day);
        }
        System.out.println();

        for (int s = 0; s < swimSlots; s++) {
            System.out.printf(String.format("%-" + maxSlotLength + "s", slots[s]));
            for (int d = 0; d < swimDays; d++) {
                if (swimSchedule[JeffDays][d][s] == '_' || swimSchedule[AnnaDays][d][s] == '_') {
                    System.out.printf(" " + "%-6s","I ");
                } else {
                    System.out.printf(" " + "%-6s","_ ");
                }
            }
            System.out.println();
        }
        System.out.println(" ");
    }
    //this method to show the avaliable slots for the group lessons
    public static void AvailableGroupSlots() {
        System.out.println("Slots marked with a 'G' are available for group lessons:");
        System.out.println(" ");
        String[] days = {"Mon", "Tue", "Wed", "Thu"};
        String[] slots = {"11-12", "12-1", "1-2", "2-3"};
        int maxSlotLength = 5;

        System.out.printf(String.format("%-" + maxSlotLength + "s", "") + " ");
        for (String day : days) {
            System.out.printf("%-7s", day);
        }
        System.out.println();


        for (int s = 0; s < swimSlots; s++) {
            System.out.printf(String.format("%-" + maxSlotLength + "s", slots[s]));
            for (int d = 0; d < swimDays; d++) {
                if (swimSchedule[JeffDays][d][s] == '_' && swimSchedule[AnnaDays][d][s] == '_') {
                    System.out.printf(" " + "%-6s","G ");
                } else {
                    System.out.printf(" " + "%-6s","_ ");
                }
            }
            System.out.println();
        }
        System.out.println(" ");
    }

    //this is the main method
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        char userCommand;

        do {
            Menu();
            System.out.print("Command: ");
            userCommand = userInput.nextLine().charAt(0);
            System.out.println(" ");

            switch (userCommand) {
                case 'p':
                    availableSchedule();
                    break;
                case 's':
                    scheduleSlot(userInput);
                    break;
                case 'f':
                    availableSlot(userInput);
                    break;
                case 'i':
                    InstructorAvailableSlots();
                    break;
                case 'g':
                    AvailableGroupSlots();
                    break;
                case 'q':
                    System.out.println("Thanks for using this program!");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (userCommand != 'q');

        userInput.close();
    }
}

