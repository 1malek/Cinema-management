package ogr.sid.cinema.entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Projection implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;
private Date dateProjection ;
private double prix;
@ManyToOne
@JsonProperty(access=Access.WRITE_ONLY)
private Salle salle;
@ManyToOne
private Film film;
@OneToMany(mappedBy="projection")
private Collection<Ticket>tickets;
@ManyToOne
private Seance seance;
}
