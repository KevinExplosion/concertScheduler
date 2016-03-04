import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/band_venues_test", null, null);
  }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteBandsQuery = "DELETE FROM bands *;";
      String deleteVenuesQuery = "DELETE FROM venues *;";
      String deleteBandsVenuesQuery = "DELETE FROM bands_venues *;";
      con.createQuery(deleteBandsQuery).executeUpdate();
      con.createQuery(deleteVenuesQuery).executeUpdate();
      con.createQuery(deleteBandsVenuesQuery).executeUpdate();
      //Was getting an error on line 17. Remember: 14 and 17 are tied to the
      //Table bands_venues, NOT the DATABASE band_venues. So band is singular
    }
  }
}
