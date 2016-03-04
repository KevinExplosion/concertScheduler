import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenuesAreTheSame() {
    Venue firstVenue = new Venue("The Social");
    Venue secondVenue = new Venue("The Social");
    assertTrue(firstVenue.equals(secondVenue));
  }
}
