package pl.ppyrczak.carsApp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.ppyrczak.carsApp.model.Car;
import pl.ppyrczak.carsApp.model.SqlCarRepository;

import java.util.List;

@RepositoryRestController
public class CarController {
    private final SqlCarRepository carRepository;
    public static final Logger logger = LoggerFactory.getLogger(CarController.class);

    public CarController(SqlCarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "/cars", params = {"!sort", "!size", "!page"})
    ResponseEntity<?> showAllCars() {
        logger.warn("showing all the cars");
        return ResponseEntity.ok(carRepository.findAll());
    }

    @GetMapping("users")
    ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    ResponseEntity<Car>
}
