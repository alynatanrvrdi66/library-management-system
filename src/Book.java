import java.time.LocalDate;

public class Book extends Material implements Borrowable, Reservable {
    private String author;
    private boolean isBorrowed;
    private boolean isReserved;
    private String reservationHolder;
    private int borrowDay;
    private final int BORROW_PERIOD_DAYS = 14;

    //constructor
    public Book(String id, String title, String author, int publicationYear) {
        super(id, title, publicationYear);
        this.author = author;
        this.isBorrowed = false;
        this.isReserved = false;
        this.reservationHolder = null;
        this.borrowDay = -1;
    }
    //getter setter methods

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed() {
        this.isBorrowed = !(this.isBorrowed);
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved() {
        this.isReserved = !(this.isReserved);
    }

    public String getReservationHolder() {
        return reservationHolder;
    }
    public void setReservationHolder(String reservationHolder){
        this.reservationHolder = reservationHolder;
    }

    @Override
    public void borrow() {
        if (isBorrowed) {
            System.out.println(getTitle() + "is already borrowed.");
        } else if (isReserved && reservationHolder != null) {
            System.out.println(getTitle() + "is reserved by " + reservationHolder + "cannot be borrowed. ");
        } else {
            this.isBorrowed = true;
            borrowDay = LocalDate.now().getDayOfYear();
            System.out.println(getTitle() + "is borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (!isBorrowed) {
            System.out.println(getTitle() + "was not borrowed.");
        } else {
            this.isBorrowed = false;
            borrowDay = -1;
            System.out.println(getTitle() + "is returned.");
        }
    }

    @Override
    public boolean isAvailableToBorrow() {

        if (!isBorrowed && !isReserved) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getRemainingDays() {
        if(!isBorrowed || borrowDay == -1){
            System.out.println(getTitle() + "has not been borrowed.");
         return 0;
        }
        int today = LocalDate.now().getDayOfYear();
        int passedDays = today - borrowDay;

        int remainingDay = BORROW_PERIOD_DAYS - passedDays;
                if(remainingDay < 0){
                    remainingDay = 0;
                }
                return remainingDay;
    }


    @Override
    public void reserve(String holder) {
        if (isReserved) {
            System.out.println(getTitle() + "is already reserved by " + reservationHolder);
        } else {
            this.isReserved = true;
            this.reservationHolder = holder;
            System.out.println(getTitle() + " reserved successfully for " + holder);
        }
    }

    @Override
    public void cancelReservation() {
        if (!isReserved) {
            System.out.println(getTitle() + "is not reserved. ");
        } else {
            System.out.println("Reservation for " + getTitle() + "by" + reservationHolder + "cancelled. ");
            this.isReserved = false;
            this.reservationHolder = null;
        }
    }
    @Override
    public boolean isReserved(){
        return isReserved;
    }

    @Override
    public String toString() {
        String status;
        if (isBorrowed) {
            status = "Borrowed.";
        } else if (isReserved) {
            status = "Reserved.";
        } else {
            status = "Avaliable";
        }
        return ("Book |" + "ID:" + getId() + "|" + "Title:" + getTitle() + "|" + "Author:" + getAuthor()
                + "|" + "Year:" + getPublicationYear() + "|" + "Status:" + status);
    }

    public String toFileFormat() {
        String holder = (reservationHolder != null) ? this.reservationHolder : "NULL";
        return "BOOK , " + getId() + "," + getTitle() + "," + author + "," + getPublicationYear() + ","
                + isBorrowed + "," + isReserved + "," + holder;
    }
}





