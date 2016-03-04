import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/concerts", (request, response) -> {
      HashMap model = new HashMap();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/concerts.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band", (request, response) -> { //POSTS TITLES TO BOOKS PAGE
      HashMap model = new HashMap();
      String band = request.queryParams("newBand");
      Band newBand = new Band(band);
      newBand.save();
      response.redirect("/concerts");
      return null;
    });

    post("/venue", (request, response) -> { //POSTS AUTHORS TO BOOKS PAGE
      HashMap model = new HashMap();
      String venue = request.queryParams("newVenue");
      Venue newVenue = new Venue(venue);
      newVenue.save();
      response.redirect("/concerts");
      return null;
    });
  }
}
