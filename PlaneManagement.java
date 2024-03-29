import java.util.*;

public class PlaneManagement {
    public static int [][] seat_layout = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    public static int[][] seatPrice ={{200,200,200,200,200,150,150,150,150,180,180,180,180,180},
            {200,200,200,200,200,150,150,150,150,180,180,180},
            {200,200,200,200,200,150,150,150,150,180,180,180},
            {200,200,200,200,200,150,150,150,150,180,180,180,180,180}
    };
    public static Ticket[] soldTickets =new Ticket[14+12+12+14];

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        System.out.println();
        System.out.println("*****************************************");
        System.out.println("*               MENU OPTION             *");
        System.out.println("*****************************************");
        int number;
        while (true) {
            try {
                do {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1. Buy a seat \n2. Cancel a seat \n3. Find first available seat \n4. Show seating plan \n5. Print tickets information and total sales \n6. Search ticket \n0. Quit");
                    System.out.println();
                    System.out.print("⚪ Please select an option : ");
                    number = scanner.nextInt();
                    System.out.println();
                    switch (number) {
                        case 0:
                            System.out.println("*** Thank you for using the Plan Management application ***");
                            return;
                        case 1:
                            buy_seat();
                            break;
                        case 2:
                            cancel_seat();
                            break;
                        case 3:
                            find_first_available();
                            break;
                        case 4:
                            show_seating_plan();
                            break;
                        case 5:
                            print_tickets_info();
                            break;
                        case 6:
                            search_ticket();
                            break;
                        default:
                            System.out.println("❌ Invalid option ❌\n»Please try again « ");
                            System.out.println();
                    }
                }
                while (number <= 6 && number >0);
            }
            catch (Exception e) {
                System.out.println();
                System.out.println("❌ Invalid option ❌\n»Please try again « ");
                System.out.println();
            }
        }
    }

    private static void buy_seat() {
        while (true){
            try {
                System.out.println();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a row letter 'A' or 'B' or 'C' or 'D': ");
                String row_let = scanner.next();
                String row_letter;
                row_letter = row_let.toUpperCase();

                System.out.println();
                System.out.println("◯ seat number 1 to 14 for row letter 'A' and 'D'");
                System.out.println("◯ seat number 1 to 12 for row letter 'B' and 'C'");
                System.out.println();
                System.out.print("Enter a seat number : ");
                int seat_number = scanner.nextInt();
                System.out.println();
                if (!row_letter.equals("A") && !row_letter.equals("B") && !row_letter.equals("C") && !row_letter.equals("D")) {
                    System.out.println("⚠ Your row letter is incorrect ⚠\n»Please try again «");
                    buy_seat();
                    return;
                }
                if ((seat_number <= 0) || (seat_number >= 15)) {
                    System.out.println("⚠ Your seat number is incorrect ⚠\n»Please try again «");
                    buy_seat();
                    return;
                }
                else{
                    int num = switch (row_letter) {
                        case "A" -> 0;
                        case "B" -> 1;
                        case "C" -> 2;
                        default -> 3;
                    };

                    if (seat_layout[num][seat_number-1] == 0) {
                        seat_layout[num][seat_number-1] = 1;
                        System.out.println("✅ "+row_letter + seat_number + " seat is available !");
                        System.out.println("✅ The seat booked for you!");

                        System.out.println();
                        System.out.print("Enter your name: ");
                        String name=scanner.next();
                        System.out.print("Enter your surname: ");
                        String surname=scanner.next();
                        System.out.print("Enter your email: ");
                        String email=scanner.next();

                        Person person=new Person(name,surname,email);

                        double price = seatPrice[num][seat_number-1];
                        Ticket ticket = new Ticket(row_letter,seat_number,price,person);

                        System.out.println();
                        ticket.save();
                        System.out.println();

                        for (int T = 0; T< soldTickets.length; T++){
                            if (soldTickets[T]==null){
                                soldTickets[T]=ticket;
                                break;
                            }
                        }
                        System.out.println("👏 Seat purchased Successfully ! 👏");
                        System.out.println("___Thank you!___");
                        System.out.println();
                        System.out.println();
                        return;
                    }
                    else {
                        System.out.println();
                        System.out.println("⏩ "+row_letter + seat_number + " seat is already booked");
                        System.out.println();
                        System.out.println("» Please try again «");
                        System.out.println();
                        buy_seat();
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("⚠ Your row letter or seat number is incorrect ⚠\n»Please try again «");
                System.out.println();
                buy_seat();
                return;
            }
        }
    }

    private static void cancel_seat(){
        while(true){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a row letter 'A' or 'B' or 'C' or 'D': ");
                String row_let = scanner.next();
                String row_letter;
                row_letter = row_let.toUpperCase();
                System.out.println();
                System.out.println("◯ seat number 1 to 14 for row letter 'A' and 'D'*");
                System.out.println("◯ seat number 1 to 12 for row letter 'B' and 'C'*");
                System.out.println();
                System.out.print("Enter a seat number : ");
                int seat_number = scanner.nextInt();
                System.out.println();
                if (!row_letter.equals("A") && !row_letter.equals("B") && !row_letter.equals("C") && !row_letter.equals("D")) {
                    System.out.println("⚠ Your row letter or seat number is incorrect ⚠\n    »Please try again «");
                    System.out.println();
                    cancel_seat();
                    return;
                }
                else if ((seat_number <= 0) || (seat_number > 14)) {
                    System.out.println("⚠ Your row letter or seat number is incorrect ⚠\n    »Please try again «");
                    System.out.println();
                    cancel_seat();
                    return;
                }
                int num = switch (row_letter) {
                    case "A" -> 0;
                    case "B" -> 1;
                    case "C" -> 2;
                    default -> 3;
                };
                if (seat_layout[num][seat_number-1] == 1) {
                    seat_layout[num][seat_number-1] = 0;
                    System.out.println();
                    System.out.println("◉"+ row_letter + seat_number + " seat has booked");
                    System.out.println();

                    for (int T = 0; T< soldTickets.length; T++){
                        Ticket ticket= soldTickets[T];
                        if(ticket!=null && ticket.getRow().equals(row_letter) && ticket.getSeat()==seat_number){
                            soldTickets[T]=null;
                            break;
                        }
                    }
                    System.out.println();
                    System.out.println("✅ Ticket canceled Successfully ! ");
                    System.out.println("✅ The seat booking canceled !");
                    System.out.println("___Thank you!___");
                    System.out.println();
                    return;
                }
                else {
                    System.out.println();
                    System.out.println("🚫 Wrong seat!\n>>>"+row_letter + seat_number + " seat has not booked. Nothing to cancel 🚫");
                    System.out.println();
                    System.out.println("»Please try again «");
                    System.out.println();
                    cancel_seat();
                    return;
                }
            }
            catch (Exception e){
                System.out.println();
                System.out.println("⚠ Your row letter or seat number is incorrect ⚠\n»Please try again «");
                System.out.println();
                cancel_seat();
                return;
            }
        }
    }

    private static void find_first_available(){
        boolean found=false;

        for (int a = 0; a< seat_layout.length; a++) {
            for (int b = 0; b < seat_layout[a].length; b++) {
                if (seat_layout[a][b] == 0) {

                    String row_letter = switch (a) {
                        case 0 -> "A";
                        case 1 -> "B";
                        case 2 -> "C";
                        default -> "D";
                    };
                    System.out.println("⏩ Row letter " + row_letter + " and seat number " + (b + 1) + " is available for you!");
                    System.out.println();
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if(!found){
            System.out.println("❗ Not available ❗");
        }
    }

    private static void  show_seating_plan() {
        for (int i = 0; i <= (seat_layout.length - 1); i++) {
            System.out.println();
            for (int j = 0; j <= (seat_layout[i].length - 1); j++) {
                if (seat_layout[i][j]==1){
                    System.out.print("X");
                }
                else{
                    System.out.print("O");
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    private static void  print_tickets_info(){
        double total_price = 0;
        System.out.println();
        System.out.println("<<<< Information of all tickets that sold out during the session >>>>");
        for (Ticket ticket: soldTickets){
            if(ticket!=null){
                ticket.Ticket_details();
                total_price+=ticket.getPrice();
                System.out.println();
            }
        }
        System.out.println("🔆 Total price of all sold tickets £ "+total_price);
        System.out.println();
    }
    private static void search_ticket(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the row letter: ");
            String row_let = scanner.next();
            String row_letter;
            row_letter = row_let.toUpperCase();
            System.out.print("Enter seat number: ");
            int seat_num = scanner.nextInt();
            System.out.println();

            if (!row_letter.equals("A") && !row_letter.equals("B") && !row_letter.equals("C") && !row_letter.equals("D")) {
                System.out.println("❌ Invalid row letter ❌\n»Please try again « ");
                System.out.println();
                search_ticket();
                return;
            }
            else if ((seat_num <= 0) || (seat_num > 14)) {
                System.out.println("❌ Invalid seat number ❌ \n»Please try again « ");
                System.out.println();
                search_ticket();
                return;
            }
            for (Ticket ticket : soldTickets) {
                if (ticket != null && ticket.getSeat() == seat_num && ticket.getRow().equals(row_letter)) {
                    ticket.Ticket_details();
                    System.out.println();
                    break;
                } else {
                    System.out.println();
                    System.out.println("⏩ This seat is available ⏪");
                    System.out.println();
                    return;
                }
            }
        }catch(Exception e){
            System.out.println();
            System.out.println("❌ Invalid input ❌ \n»Please try again « ");
            System.out.println();
            search_ticket();
        }
    }
}