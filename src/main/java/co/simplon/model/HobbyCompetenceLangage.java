package co.simplon.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="hobby")
public class HobbyCompetenceLangage {
	@Id
	private String nom;
	private String typeHobby;

}
