package telran.person.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.person.dao.PersonRepository;
import telran.person.domain.Address;
import telran.person.domain.Person;
import telran.person.dto.ChildDto;
import telran.person.dto.CityPopulation;
import telran.person.dto.CompanySalary;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository repository;

	@Override
	public void addPerson(Person person) {
		repository.save(person);

	}

	@Override
	public Person getPerson(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public boolean removePerson(int id) {
		Person person = repository.findById(id).orElse(null);
		if (person == null) {
			return false;
		}
		repository.delete(person);
		return true;
	}

	@Override
	@Transactional
	public boolean updateAddress(int id, Address address) {
		Person person = repository.findById(id).orElse(null);
		if (person == null) {
			return false;
		}
		person.setAddress(address);
		//repository.save(person);
		return true;
	}

	@Override
	public Iterable<Person> getPersonByAge(int minAge, int maxAge) {
		LocalDate fromDate = LocalDate.now().minusYears(maxAge);
		LocalDate toDate = LocalDate.now().minusYears(minAge);
		return repository.findByBirthDateBetween(fromDate, toDate);
	}

	@Override
	public Iterable<Person> getPersonByCity(String city) {
		return repository.findByAddressCity(city);
	}

	@Override
	public Iterable<Person> getEmployeeBySalary(int minSalary, int maxSalary) {
		return repository.findBySalaryBetween(minSalary, maxSalary);
	}

	@Override
	public Iterable<ChildDto> getChildren() {
		return repository.findChildren();
	}

	@Override
	public Iterable<CityPopulation> getCityPopulations() {
		return repository.getCityPopulations();
	}

	@Override
	public Iterable<Person> getEmployeesCompany(String company) {
		return repository.getEmployeesCompany(company);
	}

	@Override
	public Iterable<Person> getEmployeesBiggestCompany() {
		return repository.getEmployeesBiggestCompany();
	}

	@Override
	public Iterable<CompanySalary> getCompaniesSalaries() {
		return repository.getCompaniesSalaries();
	}

}
