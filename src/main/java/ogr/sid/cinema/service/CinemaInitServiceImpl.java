package ogr.sid.cinema.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ogr.sid.cinema.dao.CategorieRepository;
import ogr.sid.cinema.dao.CinemaRepository;
import ogr.sid.cinema.dao.FilmRepository;
import ogr.sid.cinema.dao.PlaceRepository;
import ogr.sid.cinema.dao.ProjectionRepository;
import ogr.sid.cinema.dao.SalleRepository;
import ogr.sid.cinema.dao.SeanceRepository;
import ogr.sid.cinema.dao.TicketRepository;
import ogr.sid.cinema.dao.VilleRepository;
import ogr.sid.cinema.entities.Categorie;
import ogr.sid.cinema.entities.Cinema;
import ogr.sid.cinema.entities.Film;
import ogr.sid.cinema.entities.Ville;
import ogr.sid.cinema.entities.Salle;
import ogr.sid.cinema.entities.Seance;
import ogr.sid.cinema.entities.Ticket;
import ogr.sid.cinema.entities.Place;
import ogr.sid.cinema.entities.Projection;
@Service
@Transactional
public class CinemaInitServiceImpl  implements ICinemaInitService{
@Autowired
	private VilleRepository villeRepository;
@Autowired
private CinemaRepository cinemaRepository;
@Autowired
private SalleRepository salleRepository;
@Autowired
private PlaceRepository placeRepository;
@Autowired
private ProjectionRepository projectionRepository;
@Autowired
private TicketRepository ticketRepository;
@Autowired
private CategorieRepository categorieRepository;
@Autowired
private SeanceRepository seanceRepository;
@Autowired
private FilmRepository filmRepository;
	@Override
	public void initVilles() {
		Stream.of("Tunis","Sousse","Hammamet","Sfax").forEach(nameVille->{
			Ville ville=new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);
		});
		
	}
	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v -> {
			Stream.of("pathé","l'étoile","logistique","le colisé")
			.forEach(nameCinema->{
				Cinema cinema=new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
		});
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				Salle salle = new Salle();
				salle.setName("salle"+(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(15+(int)(Math.random()*20));
				salleRepository.save(salle);
			}
		});
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
		
	}

	@Override
	public void initSeances() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","17:00","19:00","21:00")
		.forEach(s->{
			Seance seance =new Seance();
			
			try {
				seance.setHeureDebut(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initCategories() {
		Stream.of("Histoire","Action","Fiction","Drama").forEach(cat->{
			Categorie categorie=new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);});
		}
		
	

	@Override
	public void initFilms() {
		double[] durees= new double [] {1,1,5,2,2.5,3};
		List<Categorie> categories = categorieRepository.findAll();
		
		Stream.of("game of thrones","five feet a part","divergent","spidermen","call","after").forEach(
				titreFilm->{
					Film film=new Film();
					film.setTitre(titreFilm);
					film.setDuree(durees[new Random().nextInt(durees.length)]);
					film.setPhoto(titreFilm.replaceAll("","")+ ".jpg");
					film.setCategorie(categories.get(new Random().nextInt(categories.size())));
					filmRepository.save(film);
				});
				
		
	}

	@Override
	public void initProjections() {
		double[] prices=new double [] {30,50,60,70,90,100};
		List<Film>films =filmRepository.findAll();
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					int index =new Random().nextInt(films.size());
					Film film =films.get(index);
					//filmRepository.findAll().forEach(film->{
						seanceRepository.findAll().forEach(seance->{
							Projection projection =new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setPrix(prices[new Random().nextInt(prices.length)]);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionRepository.save(projection);
						});
					});
				});
			});
		
		
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
			});
		});
		
	}

}
