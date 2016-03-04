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

  @Test
  public void save_addsInstanceOfVenueToDatabase() {
    Venue newVenue = new Venue("The Social");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue newVenue = new Venue("The Social");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(newVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_locatesAllInstancesOfVenueInDatabaseUsingID() {
    Venue newVenue = new Venue ("AKA Lounge");
    newVenue.save();
    Venue savedVenue = Venue.find(newVenue.getId());
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void updateVenue_updatesVenueInDatabase() {
    Venue newVenue = new Venue("AKA Lounge");
    newVenue.save();
    newVenue.updateVenue("Club Firestone");
    assertEquals(Venue.all().get(0).getVenue(), ("Club Firestone"));
  }

  @Test
  public void deleteVenue_deleteVenueObject() {
    Venue newVenue = new Venue("Backbooth");
    newVenue.save();
    newVenue.delete();
    assertEquals(Venue.all().size(), 0);
  }
  //
  // @Test
  // public void addBand_addsBandToVenue() {
  //   Venue newVenue = new Venue("The Social");
  //   newVenue.save();
  //
  //   Band newBand = new Band("Eastern Youth");
  //   newBand.save();
  //
  //   newVenue.addBand(newBand);
  //   Band savedBand = newVenue.getBands().get(0);
  //   assertTrue(newBand.equals(savedBand));
  // }
}
