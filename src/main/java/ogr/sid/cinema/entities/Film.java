package ogr.sid.cinema.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

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
public class Film implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;
private String titre ;
private double duree ;
private String realisateur ;
private String description;
private String photo;
private Date dateSortie ;
@ManyToOne
@JsonProperty
private Categorie categorie;
@OneToMany(mappedBy="film")
@JsonProperty(access=Access.WRITE_ONLY)
private Collection<Projection>projections;
}
