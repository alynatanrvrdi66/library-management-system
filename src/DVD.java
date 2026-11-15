public class DVD extends Material  {
  private String director;
  private double duration;

  public DVD(String id, String title, int publicationYear, String director, double duration){
      super(id,title,publicationYear);
      this.director = director;
      this.duration = duration;
  }
  public String getDirector() { return director; }
    public double getDuration() { return duration; }


    public void watch() {
        System.out.println("DVD is playing with named " + getTitle());
    }

    @Override
    public String toString() {
      return "DVD | " + "Id: " + getId() + " | " + "Title: " + getTitle() + " | " + "Director: " + getDirector()
              + " | " + "Publication Year: " + getPublicationYear() + " | " + "Duration: " + getDuration()
              + " | " + "Status: Avaliable (Can not be borrowed.)" ;
    }

    @Override
    public String toFileFormat() {
      return  "DVD , " + getId() + "," + getTitle() + "," + getPublicationYear() +
                "," + this.director + "," + this.duration ;
    }

}
