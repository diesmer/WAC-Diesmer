package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.List;

//import domain.Account;
import nl.hu.v1wac.firstapp.persistence.CountryDao;
import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;
//import persistence.UserDao;
//import persistence.UserPostgresDaoImpl;

public class WorldService {
	
	private CountryDao countryDAO = new CountryPostgresDaoImpl();
	//private UserDao userDAO = new UserPostgresDaoImpl();

	
	public List<Country> getAllCountries() {
		return countryDAO.findAll();
	}
	
	public List<Country> get10LargestPopulations() {
		return countryDAO.find10LargestPopulations();
	}
	
	public List<Country> find10LargestSurfaces() {
		return countryDAO.find10LargestSurfaces();
	}

	public Country getCountryByCode(String code) {
		Country result = null;
		
		for (Country c : countryDAO.findAll()) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}
		
		return result;
	}

	public Country updateCountry(String code, String name, String capital, String region, double surface, int population) throws SQLException {
		Country c = countryDAO.findByCode(code);
			c.setName(name);
			c.setCapital(capital);
			c.setRegion(region);
			c.setSurface(surface);
			c.setPopulation(population);
			if(countryDAO.update(c)) {
				return countryDAO.findByCode(code);
			}
			
		
		return c;
	}
	
	
	public Country addCountry(String code, String iso3, String name, String continent, String region, 
			double surface, int indepyear, int population, int lifeexpectancy, 
			int gnp, int gnpoid, String localname, String governmentform,
			String headofstate, double latitude, double longitude, String capital) throws SQLException {
		Country c = new Country(code, iso3, name, continent, region, surface, indepyear, population, 
								lifeexpectancy, gnp, gnpoid, localname, governmentform, 
								headofstate, latitude, longitude, capital);
		c.setCode(code);
		c.setIso3(iso3);
		c.setName(name);
		c.setContinent(continent);
		c.setRegion(region);
		c.setSurface(surface);
		c.setIndepyear(indepyear);
		c.setPopulation(population);
		c.setLifeexpectancy(lifeexpectancy);
		c.setGnp(gnpoid);
		c.setGnpoid(gnpoid);
		c.setLocalname(localname);
		c.setGovernment(governmentform);
		c.setHeadofstate(headofstate);
		c.setLatitude(latitude);
		c.setLongitude(longitude);
		c.setCapital(capital);
		
		if(countryDAO.create(c)) {
			return c;
		}
		return c;
	}
	
	
	public boolean deleteCountry(String code) {
		boolean resultaat = false;
		Country c = countryDAO.findByCode(code);
		if (c != null) {
			resultaat = countryDAO.delete(c);
		} else {
			throw new IllegalArgumentException("Code bestaat niet!");
		}
		return resultaat;
	}
	

	public Country createCountry(String nm, String em) {
		// TODO Auto-generated method stub
		return null;
	}
}
