package ogr.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Ticket implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;
	@Column(length=75)
private String nomClient ;
private double prix;
@Column(unique=false,nullable=true)
private Integer codePayement ;
private boolean reservee;
@ManyToOne
private Place place;
@ManyToOne
//@JsonProperty(access=Access.WRITE_ONLY)
private Projection projection  ;
}
