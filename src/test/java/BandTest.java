import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfBandNamesAreTheSame() {
    Band firstBand = new Band("Toto");
    Band secondBand = new Band("Toto");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_addsInstanceOfBandToDatabase() {
    Band newBand = new Band("Toto");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void save_assignsIdToObject() {
    Band newBand = new Band("Toto");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(newBand.getId(), savedBand.getId());
  }

  @Test
  public void find_locatesAllInstancesOfBandInDatabaseUsingID() {
    Band newBand = new Band("Toto");
    newBand.save();
    Band savedBand = Band.find(newBand.getId());
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void updateBand_updatesBandInDatabase() {
    Band newBand = new Band("Toto");
    newBand.save();
    newBand.updateBand("Abba");
    assertEquals(Band.all().get(0).getBand(), ("Abba"));
  }

  @Test
  public void delete_deletesBandObject() {
    Band newBand = new Band("Toto");
    newBand.save();
    newBand.delete();
    assertEquals(Band.all().size(), 0);
  }

  // @Test
  // public void addVenue_addsVenuesToBands() {
  //   Band newBand = new Band("Toto");
  //   newBand.save();
  //
  //   Venue newVenue = new Venue("The Social");
  //   newVenue.save();
  //
  //   newBand.addVenue(newVenue);
  //   Venue savedVenue = newBand.getVenues().get(0);
  //   assertTrue(newVenue.equal(savedVenue));
  // }
}
