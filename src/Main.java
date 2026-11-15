import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        boolean exit = false;

        while(!exit){
            System.out.println("\n ---LIBRARY MANAGEMENT SYSTEM---");
            System.out.println("1.Add Book");
            System.out.println("2.Add Magazine");
            System.out.println("3.Add DVD");
            System.out.println("4.List All Materials");
            System.out.println("5.List by Type");
            System.out.println("6.Borrow Material");
            System.out.println("7.Return Material");
            System.out.println("8.Reserve Material");
            System.out.println("0. Exit");
            int choice= -1;

            try{
                choice = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Invalid input ! Please enter a number.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            System.out.println();

            switch (choice){
                case 1:
                    System.out.println("Enter Id: ");
                    String id = scanner.nextLine();

                    System.out.println("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.println("Enter Author: ");
                    String author = scanner.nextLine();

                    int publicationYear = 0;
                    while (true) {
                        System.out.println("Enter Publication Year: ");
                        try{
                            publicationYear = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (Exception e){
                            System.out.println("Invalid year! Please enter a number.");
                            scanner.nextLine();
                        }
                    }
                    library.addBook(id,title,author,publicationYear);
                    break;

                case 2:
                    System.out.print("Enter Id: ");
                    String magId = scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String magTitle = scanner.nextLine();

                    int issueNumber = 0;
                    while (true) {
                        System.out.print("Enter Issue Number: ");
                        try {
                            issueNumber = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid issue number! Please enter a number.");
                            scanner.nextLine();
                        }
                    }
                    int pubYear = 0;
                    while (true) {
                        System.out.print("Enter Publication Year: ");
                        try {
                            pubYear = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid year! Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    library.addMagazine(magId, magTitle, issueNumber, pubYear);
                    break;

                case 3:
                    System.out.print("Enter Id: ");
                    String dvdId = scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String dvdTitle = scanner.nextLine();

                    System.out.print("Enter Director: ");
                    String director = scanner.nextLine();

                    int dvdYear = 0;
                    while (true) {
                        System.out.print("Enter Publication Year: ");
                        try {
                            dvdYear = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid year! Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    double duration = 0;
                    while (true) {
                        System.out.print("Enter Duration (minutes): ");
                        try {
                            duration = scanner.nextDouble();
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid duration! Please enter a number.");
                            scanner.nextLine();
                        }
                    }

                    library.addDVD(dvdId,dvdTitle,dvdYear, director, duration);
                    break;

                case 4:
                    library.listAllMaterials();
                    break;

                case 5:
                    System.out.print("Enter type (book/magazine/dvd): ");
                    String type = scanner.nextLine().toLowerCase();
                    library.listByType(type);
                    break;

                case 6:
                    System.out.print("Enter material ID to borrow: ");
                    String borrowId = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String borrower = scanner.nextLine();
                    library.borrowMaterial(borrowId, borrower);
                    break;

                case 7:
                    System.out.print("Enter material ID to return: ");
                    String returnId = scanner.nextLine();
                    library.returnMaterial(returnId);
                    break;
                case 8:
                    System.out.print("Enter material ID to reserve: ");
                    String reserveId = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String holder = scanner.nextLine();
                    library.reserveMaterial(reserveId, holder);
                    break;

                case 9:
                    System.out.print("Enter material ID to cancel reservation: ");
                    String cancelId = scanner.nextLine();
                    System.out.print("Enter your name: ");
                    String cancelHolder = scanner.nextLine();
                    library.cancelReservation(cancelId, cancelHolder);
                    break;

                case 0:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again. \n");
            }

        }
        scanner.close();


    }
}