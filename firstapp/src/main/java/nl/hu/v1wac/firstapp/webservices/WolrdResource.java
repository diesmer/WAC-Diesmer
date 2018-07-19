package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.hu.v1wac.firstapp.webservices.Country;
import nl.hu.v1wac.firstapp.webservices.ServiceProvider;
import nl.hu.v1wac.firstapp.webservices.WorldService;

@Path("/countries")
public class WolrdResource {
	private WorldService service = ServiceProvider.getWorldService();
	
	@GET
	@Produces("application/json")
	public String getCountries() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArray countryArray = buildJsonCountryArray(service.getAllCountries());

		return countryArray.toString();
	}
	
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public Response addCountry( @Context SecurityContext sc,
								   @FormParam("countrycode") String code,
								   @FormParam("iso3") String iso3,
								   @FormParam("name") String name,
								   @FormParam("continent") String continent,
								   @FormParam("region") String region,
								   @FormParam("surface") double surface,
								   @FormParam("indepyear") int indepyear,
								   @FormParam("population") int population,
								   @FormParam("lifeexpectancy") int lifeexpectancy,
								   @FormParam("gnp") int gnp,
								   @FormParam("gnpoid") int gnpoid,
								   @FormParam("localname") String localname,
								   @FormParam("governmentform") String governmentform,
								   @FormParam("headofstate") String headofstate,
								   @FormParam("latitude") double latitude,
								   @FormParam("longitude") double longitude,
								   @FormParam("capital") String capital) throws SQLException {
		boolean role = sc.isUserInRole("user");
		System.out.println("HEEFT ROL USER: " + role + " (in countryresource @POST)");

		Map<String, String> messages = new HashMap<String, String>();
		messages.put("error", "ACCOUNT IS NIET GEMACHTIGD TAAK UIT TE VOEREN!");
		System.out.println("ACCOUNT IS NIET GEMACHTIGD TAAK UIT TE VOEREN!");
		return Response.status(409).entity(messages).build();
	}

	private JsonArray buildJsonCountryArray(List<Country> countries) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Country c : countries) {
			
			jsonArrayBuilder.add(buildJsonCountry(c));
		}

		return jsonArrayBuilder.build();
	}

	private JsonObjectBuilder buildJsonCountry(Country c) {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("capital", c.getCapital());
		job.add("code", c.getCode());
		job.add("continent", c.getContinent());
		job.add("government", c.getGovernment());
		job.add("iso3", c.getIso3());
		job.add("latitude", c.getLatitude());
		job.add("longitude", c.getLongitude());
		job.add("name", c.getName());
		job.add("population", c.getPopulation());
		job.add("region", c.getRegion());
		job.add("surface", c.getSurface());
		return job;
	}
	
	@GET
	@Path("{code}")
	@Produces("Application/json")
	public String getCountryInfo(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		Country world = service.getCountryByCode(code);
		JsonObjectBuilder job = null;
		if(world!=null) {
		job = buildJsonCountry(world);
		}
		return job != null ? job.build().toString(): Json.createObjectBuilder().add("error", "not there").build().toString();

	}


}
