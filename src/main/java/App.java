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

    post("/band", (request, response) -> { //POSTS BANDS TO VENUES PAGE
      HashMap model = new HashMap();
      String band = request.queryParams("newBand");
      Band newBand = new Band(band);
      newBand.save();
      response.redirect("/concerts");
      return null;
    });

    post("/venue", (request, response) -> { //POSTS VENUES TO BANDS PAGE
      HashMap model = new HashMap();
      String venue = request.queryParams("newVenue");
      Venue newVenue = new Venue(venue);
      newVenue.save();
      response.redirect("/concerts");
      return null;
    });

    get("/band/:id", (request, response) -> {
      HashMap model = new HashMap();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("band", band);
      model.put("allVenues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band/:id", (request, response) -> {
      HashMap model = new HashMap();
      int bandId = Integer.parseInt(request.queryParams("bandId"));
      int venueId = Integer.parseInt(request.queryParams("venueId"));
      Venue venue = Venue.find(venueId);
      Band band = Band.find(bandId);
      band.addVenue(venue);
      response.redirect("/band/" + bandId);
      return null;
    });

    get("/venue/:id", (request, response) -> {
      HashMap model = new HashMap();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      model.put("venue", venue);
      model.put("allBands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venue/:id", (request, response) -> {
      HashMap model = new HashMap();
      int venueId = Integer.parseInt(request.queryParams("venueId"));
      int bandId = Integer.parseInt(request.queryParams("bandId"));
      Band band = Band.find(bandId);
      Venue venue = Venue.find(venueId);
      venue.addBand(band);
      response.redirect("/venue/" + venueId);
      return null;
    });
  }
}
