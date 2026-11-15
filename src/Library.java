import java.io.*;
import java.util.ArrayList;


public class Library {
    private ArrayList<Material> materials;
    private final String FILE_NAME = "library.txt";

    public Library() {
        materials = new ArrayList<>();
        loadMaterialsFromFile();
    }

    public void addBook(String id, String title, String author, int publicationYear) {
        if (findMaterial(id) != null) {
            System.out.println("Book with this ID already exists.");
            return;
        }
        Book book = new Book(id,title,author,publicationYear);
        materials.add(book);
        System.out.println("Book" + title + "is added ");
        saveToFile();
    }

    public void addMagazine(String id, String title, int issueNumber, int publicationYear) {
        if (findMaterial(id) != null) {
            System.out.println("Book with this ID already exists.");
            return;
        }
        Magazine magazine = new Magazine(id,title,issueNumber,publicationYear) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        materials.add(magazine);
        System.out.println("Magazine" + title + "is added.");
        saveToFile();
    }

    public void addDVD(String id, String title, int publicationYear,String director, double duration) {
        if (findMaterial(id) != null) {
            System.out.println("Book with this ID already exists.");
            return;
        }
        DVD dvd = new DVD(id,title,publicationYear,director,duration);
        materials.add(dvd);
        System.out.println("DVD " + title + " is added.");
        saveToFile();
    }

    public Material findMaterial(String id) {
        for (Material m : materials) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public void listAllMaterials() {
        if (materials.isEmpty()) {
            System.out.println("There is no material.");
        } else {
            for (int i = 0; i < materials.size(); i++) {
                System.out.println((i + 1) + "." + materials.get(i));
            }
        }
    }


    public void listByType(String type) {
        type = type.toLowerCase();
        if (materials.isEmpty()) {
            System.out.println("No materials in library.");
        }
        int count = 0;
        for (Material m : materials) {
            if (type.equals("book") && m instanceof Book) {
                System.out.println(m.toString());
                count++;
            } else if (type.equals("magazine") && m instanceof Magazine) {
                System.out.println(m.toString());
                count++;
            } else if (type.equals("dvd") && m instanceof DVD) {
                System.out.println(m.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No materials of type ' " + type + " ' found. ");
        }
    }

    public void borrowMaterial(String id,String holder) {
        Material m = findMaterial(id);
        if (m == null) {
            System.out.println("Material not found");
        }
        if (m instanceof Borrowable) {
            Borrowable borrowableItem = (Borrowable) m;
            if (borrowableItem.isAvailableToBorrow()) {
                borrowableItem.borrow();
                System.out.println("You borrowed the " + m.getTitle());
                saveToFile();
            } else {
                System.out.println("The material is already borrowed. You cannot borrow.");
            }
        }
        if(m instanceof Reservable){
            Reservable reservableItem = (Reservable) m;
            if(reservableItem.isReserved() && !reservableItem.getReservationHolder().equals(holder)){
                System.out.println("This item is reserved by " + reservableItem.getReservationHolder());
            }
        }
    }

    public void returnMaterial(String id) {
        Material m = findMaterial(id);
        if (m == null) {
            System.out.println("Material not found.");
        }
        if (m instanceof Borrowable) {
            Borrowable borrowableItem = (Borrowable) m;
            if (borrowableItem.isAvailableToBorrow()) {
                System.out.println("Material is already avaliable.");
            } else {
                borrowableItem.returnItem();
                System.out.println("Returning material is successful." + m.getTitle());
                saveToFile();
            }
        }
    }

    public void reserveMaterial(String id, String holder) {
        Material m = findMaterial(id);
        if (m == null) {
            System.out.println("There is no material such like that.");
        }
        if (!(m instanceof Reservable)) {
            System.out.println("This item cannot be reserved.");
            return;
        }
        Reservable reservableItem = (Reservable) m;
        if (reservableItem.isReserved()) {
            System.out.println("Already resevred by " + reservableItem.getReservationHolder());
        } else {
            reservableItem.reserve(holder);
            System.out.println(m.getTitle() + " reserved for " + holder);
            saveToFile();
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Material m : materials) {
                bw.write(m.toFileFormat());
                bw.newLine();
            }
            System.out.println("Library saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("File Writer Error:" + e.getMessage());
        }
    }

    public void loadMaterialsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                String type = parts[0].trim().toUpperCase();
                if (type.equals("BOOK")) {
                    String id = parts[1].trim();
                    String title = parts[2].trim();
                    String author = parts[3].trim();
                    int publicationYear = Integer.parseInt(parts[4].trim());
                    boolean isBorrowed = Boolean.parseBoolean(parts[5]);
                    boolean isReserved = Boolean.parseBoolean(parts[6]);
                    String holder = parts[7].trim();
                    if(holder.equals("NULL")) holder = null;

                    Book b = new Book(id, title, author, publicationYear);
                    b.setIsBorrowed();
                    b.setIsReserved();
                    if(holder != null){
                        b.setReservationHolder(holder);
                    }
                    materials.add(b);
                } else if (type.equals("MAGAZINE")) {
                    String id = parts[1].trim();
                    String title = parts[2].trim();
                    int issueNumber = Integer.parseInt(parts[3].trim());
                    int publicationYear = Integer.parseInt(parts[4].trim());
                    Magazine m = new Magazine(id, title, publicationYear, issueNumber);
                    materials.add(m);
                } else if (type.equals("DVD")) {
                    String id = parts[1].trim();
                    String title = parts[2].trim();
                    String director = parts[3].trim();
                    int publicationYear = Integer.parseInt(parts[4].trim());
                    double duration = Double.parseDouble(parts[5].trim());
                    DVD d = new DVD(id, title, publicationYear, director, duration);
                    materials.add(d);
                }
            }
            System.out.println("Materials loaded successfully.");

        } catch (IOException e) {
            System.out.println("File reader error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Data error: " + e.getMessage());
        }
    }
}




