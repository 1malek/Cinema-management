package ogr.sid.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import ogr.sid.cinema.entities.Film;
import ogr.sid.cinema.entities.Salle;
import ogr.sid.cinema.service.ICinemaInitService;

@SpringBootApplication
public class CinemaApplication 
	implements CommandLineRunner{
	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		restConfiguration.exposeIdsFor(Film.class,Salle.class);
		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategories();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
		cinemaInitService.initVilles();
		cinemaInitService.initVilles();
	}

}
