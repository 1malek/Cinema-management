package ogr.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Categorie implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;
	@Column(length=75)
private String name ;

@OneToMany(mappedBy="categorie")
@JsonProperty(access=Access.WRITE_ONLY)
private Collection<Film>films;
}
