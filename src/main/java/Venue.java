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
}
