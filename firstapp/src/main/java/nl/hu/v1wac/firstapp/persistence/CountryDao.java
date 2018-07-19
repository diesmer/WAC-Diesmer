package nl.hu.v1wac.firstapp.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.v1wac.firstapp.webservices.Country;

public interface CountryDao {

	public List<Country> findAll();

	public Country findByCode(String code);

	public List<Country> find10LargestPopulations();

	public List<Country> find10LargestSurfaces();

	public boolean update(Country country) throws SQLException;

	public boolean delete(Country country);

	public boolean create(Country country) throws SQLException;

}
