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
}
