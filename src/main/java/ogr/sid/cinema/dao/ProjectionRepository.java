package ogr.sid.cinema.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ogr.sid.cinema.entities.Cinema;
import ogr.sid.cinema.entities.Projection;
@RepositoryRestResource
@CrossOrigin("*")
public interface ProjectionRepository extends JpaRepository<Projection,Long>{

}
