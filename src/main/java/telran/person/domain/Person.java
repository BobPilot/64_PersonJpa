package telran.person.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY
, property="type")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@Entity(name = "Person")
@Table(name = "person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Person {
	@Id
	int id;
	String name;
	LocalDate birthDate;
	Address address;

}
