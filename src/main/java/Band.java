import org.sql2o.*;
import java.util.List;

public class Band {
  private int id;
  private String band;

  public int getId() {
    return id;
  }

  public String getBand() {
    return band;
  }

  public Band (String band) {
    this.band = band;
  }

  public static List<Band> all() {
    String sql = "SELECT id, band FROM bands ORDER BY band";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand) {
    if(!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getBand().equals(newBand.getBand());
    }
  }

  public void save() {
    String sql = "INSERT INTO bands (band) VALUES (:band)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("band", band)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    String sql = "SELECT id, band FROM bands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Band band = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Band.class);
        return band;
    }
  }

  public void updateBand(String band) {
    String sql = "UPDATE bands SET band = :band WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("band", band)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM bands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM bands_venues WHERE band_id = :band_id";
      con.createQuery(joinDeleteQuery)
        .addParameter("band_id", this.getId())
        .executeUpdate();
    }
  }

  public void updateIdBandVenue(int id_bands_venues) {
    String sql = "UPDATE band SET id_bands_venues = :id_bands_venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id_bands_venues", id_bands_venues)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addVenue (Venue venue) {
    String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("band_id", this.getId())
        .addParameter("venue_id", venue.getId())
        .executeUpdate();
    }
  }

  public List<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT venues.* FROM bands " +
      "JOIN bands_venues ON (bands.id = bands_venues.band_id) " +
      "JOIN venues ON (bands_venues.venue_id = venues.id) " +
      "WHERE bands.id = :id";
      List<Venue> venues = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Venue.class);
      return venues;
    }
  }
}
