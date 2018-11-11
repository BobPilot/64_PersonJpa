package telran.person.dao;

import telran.person.domain.Person;
import telran.person.dto.CompanyEmployees;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public Iterable<Person> getEmployeesBiggestCompany() {

        String company = (String) em.createQuery("select e.company from Employee e group by e.company order by count(e) desc")
                .setMaxResults(1)
                .getSingleResult();
        return em.createQuery("select e from Employee e where e.company = :company")
                .setParameter("company", company)
                .getResultList();
    }
}
