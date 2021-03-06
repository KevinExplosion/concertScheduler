import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Concert Scheduler");
  }

  @Test
  public void fillBandFormTest() {
      goTo("http://localhost:4567/concerts");
      fill("#newBand").with("Men at Work");
      submit("#submit");
      assertThat(pageSource()).contains("Men at Work");
  }

  @Test
  public void fillVenueFormTest() {
      goTo("http://localhost:4567/concerts");
      fill("#newVenue").with("Will's Pub");
      submit("#submit");
      assertThat(pageSource()).contains("Will's Pub");
  }
}
