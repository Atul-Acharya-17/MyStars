package boundary;

import controller.CourseMgr;
import controller.UpdateManager;

import java.util.Scanner;

import static boundary.MyStarsInterface.RED;
import static boundary.MyStarsInterface.RESET;

/**
 * Boundary class that implements Index Update interface
 *
 * @author anon
 */

public class IndexUpdateInterface {

    /**
     * Main function where admin can update index details based on chosen attribute (Index number, Vacancy)
     *
     * @param args       null argument can be used to call the IndexUpdate interface
     * @param actor      User name of the current user of the system
     * @param courseCode String specifies the course of which index needs to be updated
     */

    public static void main(String[] args, String actor, String courseCode) {
        CourseMgr courseMgr = new CourseMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            String indexNumber;
            System.out.println("+--------------------------------------------------------+");
            System.out.println("|                    Update Index Menu                   |");
            System.out.println("+--------------------------------------------------------+");
            System.out.print("| Enter the Index number to update (Enter 0 to go back): |  ");
            indexNumber = sc.next();
            System.out.println("+--------------------------------------------------------+");
            if (indexNumber.equals("0"))
                break;
            if (courseMgr instanceof CourseMgr && !((CourseMgr) courseMgr).checkIndex(courseCode, indexNumber)) {
                System.out.println(RED + "This index number doesn't exist" + RESET);
                break;
            } else {
                String newIndexNumber;
                int vacancy = 10;
                System.out.println("+----------------------------------------+");
                System.out.println("|     Choose the attribute to update     |");
                System.out.println("|----------------------------------------|");
                System.out.println("| 1: Index number                        |");
                System.out.println("| 2: Vacancy                             |");
                System.out.println("+----------------------------------------+");

                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(e);
                    sc.nextLine();
                    choice = -1;
                }

                boolean goBack = false;
                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        System.out.print("Enter the new index number : ");
                        newIndexNumber = sc.next();
                        updateManager.updateIndexNumber(courseCode, indexNumber, newIndexNumber);
                        goBack = true;
                        break;
                    case 2:
                        System.out.print("Enter the new vacancy : ");
                        try {
                            vacancy = sc.nextInt();
                            if (vacancy < 0) {
                                System.out.println(RED + "Vacancy must be a positive integer" + RESET);
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(RED + "Vacancy must be a positive integer" + RESET);
                        }

                        updateManager.updateIndexVacancy(courseCode, indexNumber, vacancy);
                        goBack = true;
                        break;
                    default:
                        System.out.println(RED + "Invalid choice" + RESET);
                }
                if (goBack)
                    break;
            }
        } while (choice != 0);
    }
}
