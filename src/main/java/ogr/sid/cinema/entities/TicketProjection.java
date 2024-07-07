package ogr.sid.cinema.entities;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.rest.core.config.Projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
@Projection(name="ticketProj", types=Ticket.class)
public interface TicketProjection {
	public Long getId();
	public String getNomClient();
	public double getPrix();
	public Integer getCodePayement();
	public Integer getReserve();
	public Integer getPlace();
}
