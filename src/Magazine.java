import java.time.LocalDate;

public class Magazine extends Material implements Borrowable {
    private boolean isBorrowed;
    private int issueNumber;
    private int borrowDay;
    private final int BORROW_PERIOD_DAYS = 7;

    public Magazine(String id, String title, int publicationYear, int issueNumber) {
        super(id, title,publicationYear);
        this.issueNumber = issueNumber;
        this.isBorrowed = false;
        this.borrowDay = -1;
    }
    @Override
    public String toString() {
        return ("Magazine |" + "ID:"+ getId() +" | " + "Title:" + getTitle() + " | " +
                 " | " + "Year:" + getPublicationYear() + " | " + " Issue Number: " + issueNumber );
    }
    @Override
    public String toFileFormat() {
        return "MAGAZINE , " + getId() + "," + getTitle() + "," + issueNumber + "," + getPublicationYear();
    }
    @Override
    public void borrow(){
        if (isBorrowed) {
            System.out.println(getTitle() + "is already borrowed.");
        }
         else {
            this.isBorrowed = true;
            borrowDay = LocalDate.now().getDayOfYear();
            System.out.println(getTitle() + "(Issue: " + issueNumber + ") has been borrowed on day" + borrowDay);
        }
    }

    @Override
    public void returnItem(){
        if (!isBorrowed) {
            System.out.println(getTitle() + " was not borrowed.");
            return;
        }
        isBorrowed = false;
        borrowDay = -1;
        System.out.println(getTitle() + " (Issue: " + issueNumber + ") has been returned.");

    }
    @Override
    public boolean isAvailableToBorrow(){
        return !this.isBorrowed;
    }

    @Override
    public int getRemainingDays(){
    if(!isBorrowed || borrowDay == -1){
        System.out.println(getTitle() + "has not been borrowed.");
        return 0;
    }
    int today = LocalDate.now().getDayOfYear();
    int passedDays = today - borrowDay;
    int remainingDay = BORROW_PERIOD_DAYS - passedDays;

    if(remainingDay < 0){
        remainingDay = 0 ;
    }
    return remainingDay;
    }
    public int getIssueNumber(){ return issueNumber; }
}