package telran.person.service;

import telran.person.domain.Address;
import telran.person.domain.Person;
import telran.person.dto.ChildDto;
import telran.person.dto.CityPopulation;
import telran.person.dto.CompanySalary;

public interface PersonService {
	
	void addPerson(Person person);
	
	Person getPerson(int id);
	
	boolean removePerson(int id);
	
	boolean updateAddress(int id, Address address);
	
	Iterable<Person> getPersonByAge(int minAge, int maxAge);
	
	Iterable<Person> getPersonByCity(String city);
	
	Iterable<Person> getEmployeeBySalary(int minSalary, int maxSalary);

	Iterable<ChildDto> getChildren();
	
	Iterable<CityPopulation> getCityPopulations();

	Iterable<Person> getEmployeesCompany(String company);

	Iterable<Person> getEmployeesBiggestCompany();

	Iterable<CompanySalary> getCompaniesSalaries();

}
