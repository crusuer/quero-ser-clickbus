package br.com.manager.service;

import br.com.manager.dto.PlaceDTO;
import br.com.manager.entity.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {
    Place create(PlaceDTO p);

    Optional<Place> findByName(String name);

    List<Place> findAllByName(String name);

    List<Place> findAll();

    Place save(PlaceDTO p);

    void delete(String name);
}
