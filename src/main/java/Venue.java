import org.sql2o.*;
import java.util.List;

public class Venue {
  private int id;
  private String venue;

  public int getId() {
    return id;
  }

  public String getVenue() {
    return venue;
  }

  public Venue (String venue) {
    this.venue = venue;
  }

  public static List<Venue> all() {
    String sql = "SELECT id, venue FROM venues ORDER BY venue";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue) {
    if(!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getVenue().equals(newVenue.getVenue());
    }
  }

  public void save() {
    String sql = "INSERT INTO venues (venue) VALUES (:venue)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("venue", venue)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    String sql = "SELECT id, venue FROM venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Venue venue = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
        return venue;
    }
  }

 public void updateVenue(String venue) {
   String sql = "UPDATE venues SET venue = :venue WHERE id = :id";
   try(Connection con = DB.sql2o.open()) {
     con.createQuery(sql)
      .addParameter("venue", venue)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addBand (Band band) {
    String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("band_id", band.getId())
        .addParameter("venue_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Band> getBand() {
    try(Connection con = DB.sql2o.open()) {

      String sql = "SELECT band.* FROM venues " +
      "JOIN bands_venues ON (venue.id = bands_venues.venue_id) " +
      "Join band ON (bands_venues.band_id = band.id) " +
      "WHERE venue.id = :id";
      List<Band> bands = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Band.class);
      return bands;
    }
  }
}
