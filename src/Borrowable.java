public interface Borrowable {
    public void borrow();
    public void returnItem();
    public boolean isAvailableToBorrow();
    public int getRemainingDays();

}
