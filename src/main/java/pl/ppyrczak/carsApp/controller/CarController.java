package pl.ppyrczak.carsApp.controller;


import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ppyrczak.carsApp.model.Car;
import pl.ppyrczak.carsApp.model.CarRepository;
import pl.ppyrczak.carsApp.model.SqlCarRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RepositoryRestController
public class CarController {
    private final CarRepository carRepository;
    public static final Logger logger = LoggerFactory.getLogger(CarController.class);

    public CarController(SqlCarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "/cars", params = {"!sort", "!size", "!page"})
    ResponseEntity<?> showAllCars() {
        logger.warn("showing all the cars");
        return ResponseEntity.ok(carRepository.findAll());
    }

    @GetMapping("cars")
    ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    ResponseEntity<Car> getCarById(@PathVariable int id) {
        return carRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cars")
    ResponseEntity<Car> createCar(@RequestBody @Valid Car carToCreate) {
        Car car = carRepository.save(carToCreate);
        return ResponseEntity.created(URI.create("/" + car.getId())).body(car);
    }

    @PutMapping("/tasks/{id}")
    ResponseEntity<?> updateCar(@PathVariable int id, @RequestBody Car carToUpdate) {
        if(!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        carToUpdate.setId(id);
        carRepository.save(carToUpdate);
        return ResponseEntity.noContent().build();
    }




}
