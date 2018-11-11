package telran.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.person.domain.Address;
import telran.person.domain.Person;
import telran.person.dto.ChildDto;
import telran.person.dto.CityPopulation;
import telran.person.dto.CompanySalary;
import telran.person.service.PersonService;

@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@PostMapping("/person/")
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@GetMapping("/person/{id}")
	public Person getPerson(@PathVariable int id) {
		return personService.getPerson(id);
	}
	
	@DeleteMapping("/person/{id}")
	public boolean removePerson(@PathVariable int id) {
		return personService.removePerson(id);
	}
	
	@GetMapping("/persons/age/{min}/{max}")
	public Iterable<Person> getPersonsByAge(@PathVariable int min, @PathVariable int max) {
		return personService.getPersonByAge(min, max);
	}
	
	@GetMapping("/persons/address/{city}")
	public Iterable<Person> getPersonsByCity(@PathVariable String city) {
		return personService.getPersonByCity(city);
	}
	
	@GetMapping("/persons/salary/{min}/{max}")
	public Iterable<Person> getEmployeesBySalary(@PathVariable int min, @PathVariable int max){
		return personService.getEmployeeBySalary(min, max);
	}
	
	@PutMapping("/person/{id}/address/")
	public boolean updateAddress(@PathVariable int id, @RequestBody Address address) {
		return personService.updateAddress(id, address);
	}
	
	@GetMapping("/persons/children/")
	public Iterable<ChildDto> getChildren(){
		return personService.getChildren();
	}
	
	@GetMapping("/persons/populations/")
	public Iterable<CityPopulation> getCityPopulations(){
		return personService.getCityPopulations();
	}

	@GetMapping("/employees/{company}")
	Iterable<Person> getEmployeesCompany(@PathVariable String company){
		return personService.getEmployeesCompany(company);
	}

	@GetMapping("/employees")
	Iterable<Person> getEmployeesBiggestCompany(){
		return personService.getEmployeesBiggestCompany();
	}

	@GetMapping("/companies")
	Iterable<CompanySalary> getCompaniesSalaries(){
		return personService.getCompaniesSalaries();
	}

}
