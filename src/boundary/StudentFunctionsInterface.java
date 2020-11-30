package boundary;

import controller.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.Console;

import static boundary.MyStarsInterface.RED;
import static boundary.MyStarsInterface.RESET;

/**
 * Boundary class that executes Student user functionalities
 *
 * @author anon
 */

public class StudentFunctionsInterface {

    /**
     * Main function that displays menu of student functions and input details or view details based on chosen function.
     * The operations are: Add, drop and print course, Check vacancies, Change and swap index numbers, Printing time table, course list, index list and request for overloading.
     *
     * @param args  null argument can be used to call the StudentFunction interface
     * @param actor User name of the current user of the system
     * @throws IOException throws IOException
     */

    public static void main(String[] args, String actor) throws IOException {
        RegistrationManager registrationManager = new RegistrationManager(new StudentRecordsMgr(), new CourseMgr());
        ObjectEntityController objectEntityController;
        Console console = System.console();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("+--------------------------------------------+");
            System.out.println("|              Select your task              |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1. Add a Course                            |");
            System.out.println("| 2. Drop a Course                           |");
            System.out.println("| 3. Print courses registered                |");
            System.out.println("| 4. Check vacancies available               |");
            System.out.println("| 5. Change index number of a course         |");
            System.out.println("| 6. Swap index numbers with another student |");
            System.out.println("| 7. Print Time Table                        |");
            System.out.println("| 8. View Course list                        |");
            System.out.println("| 9. View Index list of a course             |");
            System.out.println("| 10. Request for Overloading                |");
            System.out.println("|--------------------------------------------|");
            System.out.println("|             Press 0 to go back             |");
            System.out.println("+--------------------------------------------+");
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e);
                sc.nextLine();
                choice = -1;
            }

            String courseCode;
            String indexNumber;
            String friendName;
            String newIndexNumber;
            String password;

            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.print("Enter the course code of the course to add: ");
                    courseCode = sc.next();
                    System.out.print("Enter the index number of the course to add: ");
                    indexNumber = sc.next();
                    registrationManager.registerCourse(actor, courseCode, indexNumber);
                    break;
                case 2:
                    objectEntityController = new StudentRecordsMgr();
                    ((StudentRecordsMgr) objectEntityController).printCoursesRegistered(actor);
                    System.out.print("Enter the course code to remove: ");
                    courseCode = sc.next();
                    System.out.print("Enter the index to remove: ");
                    indexNumber = sc.next();
                    registrationManager.dropCourse(actor, courseCode, indexNumber, false);
                    break;
                case 3:
                    objectEntityController = new StudentRecordsMgr();
                    ((StudentRecordsMgr) objectEntityController).printCoursesRegistered(actor);
                    break;
                case 4:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.print("Enter the index number: ");
                    indexNumber = sc.next();
                    objectEntityController = new CourseMgr();
                    if (objectEntityController instanceof CourseMgr)
                        ((CourseMgr) objectEntityController).checkAvailabilityIndex(courseCode, indexNumber);
                    else System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                    break;
                case 5:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.print("Enter the current index number of that course: ");
                    indexNumber = sc.next();
                    System.out.print("Enter the new index number of that course: ");
                    newIndexNumber = sc.next();
                    registrationManager.changeIndex(actor, courseCode, indexNumber, newIndexNumber);
                    break;
                case 6:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    System.out.print("Enter the current index: ");
                    indexNumber = sc.next();
                    System.out.print("Enter the other student's user name: ");
                    friendName = sc.next();
                    try {
                        password = String.valueOf(console.readPassword("Enter the other student's password: "));
                    } catch (NullPointerException e) {
                        System.out.println(RED + "Could not mask password" + RESET);
                        System.out.print("Enter the other student's password: ");
                        password = sc.next();
                    }
                    System.out.print("Enter the other student's index number: ");
                    newIndexNumber = sc.next();
                    if (LoginMgr.loginCheck(friendName, password, "student"))
                        registrationManager.swapIndex(actor, friendName, courseCode, indexNumber, newIndexNumber);
                    else System.out.println(RED + "Invalid user name or password" + RESET);
                    break;

                case 7:
                    objectEntityController = new StudentRecordsMgr();
                    if (objectEntityController instanceof StudentRecordsMgr)
                        ((StudentRecordsMgr) objectEntityController).printTimeTable(actor);
                    else System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                    break;
                case 8:
                    objectEntityController = new CourseMgr();
                    objectEntityController.printObjects();
                    break;
                case 9:
                    System.out.print("Enter the course code: ");
                    courseCode = sc.next();
                    objectEntityController = new CourseMgr();
                    if (objectEntityController instanceof CourseMgr)
                        ((CourseMgr) objectEntityController).printIndexes(courseCode);
                    else System.out.println(RED + "Invalid controller class... Cannot perform this operation" + RESET);
                    break;

                case 10:
                    EmailAdminInterface.main(null, actor);
                    break;
                default:
                    System.out.println(RED + "Invalid choice" + RESET);
                    break;
            }

        } while (choice != 0);
    }
}
