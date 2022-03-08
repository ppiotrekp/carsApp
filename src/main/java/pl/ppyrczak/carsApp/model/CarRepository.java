package pl.ppyrczak.carsApp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> findAll();

    Page<Car> findAll(Pageable page);

    Optional<Car> findById(Integer id);

    boolean existsById(Integer id);

    Car save(Car entity);
}
