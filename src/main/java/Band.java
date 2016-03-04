import org.sql2o.*;
import java.util.List;

public class Band {
  private int id;
  private String band_name;

  public int getId() {
    return id;
  }

  public String getBandName() {
    return band_name;
  }

  public Band (String band_name) {
    this.band_name = band_name;
  }

  public static List<Band> all() {
    String sql = "SELECT id, band_name FROM bands ORDER BY band_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }
}
