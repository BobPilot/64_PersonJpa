package telran.person.dao;

import telran.person.domain.Person;

public interface PersonRepositoryCustom {

    Iterable<Person> getEmployeesBiggestCompany();

}
