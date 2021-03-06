package boundary;

import controller.*;

import static boundary.MyStarsInterface.RED;
import static boundary.MyStarsInterface.RESET;

import java.util.Scanner;

/**
 * Boundary class that executes the function to send an Email to admin
 *
 * @author anon
 */

public class EmailAdminInterface {

    /**
     * Main function where student can send email to admin requesting for overloading
     *
     * @param args  null argument can be used to call the EmailAdmin interface
     * @param actor User name of the current user of the system
     */

    public static void main(String[] args, String actor) {
        AdminMgr adminMgr = new AdminMgr();
        String adminUsername;
        Scanner sc = new Scanner(System.in);
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|        Name         |           Username         |                 Email              |");
        System.out.println("+---------------------------------------------------------------------------------------+");
        adminMgr.printObjects();
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("| Enter the admin username to send the email to (Press 0 to go back)  :        |  ");
        adminUsername = sc.next();
        System.out.println("+------------------------------------------------------------------------------------+");

        if (adminUsername.equals("0"))
            return;
        if (!adminMgr.checkObjectExists(adminUsername)) {
            System.out.println(RED + "No such admin exists" + RESET);
        } else {
            CommunicationController communicationController = new CommunicationController();
            int academicUnits;
            String message;
            System.out.print("How many AUs do you require? : ");
            academicUnits = sc.nextInt();
            System.out.print("State your reason : ");
            sc.nextLine();
            message = sc.nextLine();
            message = "I want to overload courses this semester. I would like to take " + academicUnits
                    + " AUs because " + message + ".";
            String subject = "Permission to Overload this semester for " + actor;
            Notifier notifier = new EmailMgr();
            communicationController.communicateToAdmin(adminUsername, subject, message, notifier);
        }
    }
}
