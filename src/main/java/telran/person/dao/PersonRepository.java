package telran.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.person.domain.Person;
import telran.person.dto.ChildDto;
import telran.person.dto.CityPopulation;
import telran.person.dto.CompanySalary;

public interface PersonRepository extends PersonRepositoryCustom, JpaRepository<Person, Integer> {
	List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	List<Person> findByAddressCity(String city);
	
	@Query("select p from Person p where p.salary between :from and :to")
	List<Person> findBySalaryBetween(@Param("from") int fromSalary, @Param("to") int toSalary);

	@Query("select new telran.person.dto.ChildDto(p.name, p.address.city, p.kindergarten) from Person p where p.kindergarten is not null")
	Iterable<ChildDto> findChildren();
	
	@Query("select new telran.person.dto.CityPopulation(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	Iterable<CityPopulation> getCityPopulations();


	// HomeWork

	@Query("select e from Employee e where e.company = :company")
	Iterable<Person> getEmployeesCompany(@Param("company") String company);

//	@Query("select e from Employee e where e.company = (" +
//			"select e.company from Employee e group by e.company limit 1)")
//	@Query("select e from Employee e where e.company = (select e.company from Employee e where max(count(e.company)))")
//	@Query("select e from Employee e where e.company = (max((select new telran.person.dto.CompanyEmployees(e.company, count(e)) form Employee group by e.company).countEmployees)).company")
//  Iterable<Person> getEmployeesBiggestCompany();


	@Query("select new telran.person.dto.CompanySalary(e.company, avg(e.salary)) from Employee e group by e.company order by avg(e.salary) desc")
	Iterable<CompanySalary> getCompaniesSalaries();


	/*
	* 1. Update PersonService interface adding the following methods
	* 1.1 Iterable<Person> getEmployeesCompany(String company); returns employeesworking for a specified company
	* 1.1 Iterable<Person> getEmployeesBigestCompany(); returns employees working for the biggest company
	* 1.2 Iterable<CompanySalary> getCompaniesSalaries(); returns the company names
	* 		and average salary of each company sorted by average salary in the descending order.
	* 2. Add the implementations of the above methods in the class PersonService
	* 3. Add the proper methods in the controller for processing the following GET requests
	* 3.1 /employees/{company}  ­ getting employees working for a specified company
	* 3.2 /employees ­ getting employees working for the biggest company
	* 3.3 /companies ­ getting all companies with the average salary for each company
	*
	* */

}
