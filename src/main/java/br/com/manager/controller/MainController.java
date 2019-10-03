package br.com.manager.controller;

import br.com.manager.dto.PlaceDTO;
import br.com.manager.entity.Place;
import br.com.manager.service.PlaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    @Autowired
    private PlaceService placeService;

    @GetMapping("/findByName")
    public Optional<Place> findByName(@RequestParam("name") String name) {
        LOGGER.debug("Place searched: {}", name);
        return placeService.findByName(name);
    }

    @GetMapping("/findAllByName")
    public List<Place> findAllByName(@RequestParam("name") String name) {
        LOGGER.debug("Name searched: {}", name);
        return placeService.findAllByName(name);
    }

    @GetMapping("/findAll")
    public List<Place> findAll() {
        return placeService.findAll();
    }

    @PostMapping("/create")
    public Place create(@Valid PlaceDTO p) {
        return placeService.create(p);
    }

    @PutMapping("/update")
    public Place update(@Valid PlaceDTO p) {
        return placeService.save(p);
    }
}
