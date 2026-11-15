public interface Reservable {
    public void reserve(String holder);
    public void cancelReservation();
    public boolean isReserved();
    public String getReservationHolder();
}
