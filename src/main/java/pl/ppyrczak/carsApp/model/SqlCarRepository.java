package pl.ppyrczak.carsApp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SqlCarRepository extends CarRepository, JpaRepository<Car, Integer> {

}