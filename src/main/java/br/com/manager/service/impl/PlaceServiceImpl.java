package br.com.manager.service.impl;

import br.com.manager.dto.PlaceDTO;
import br.com.manager.entity.Place;
import br.com.manager.repository.PlaceRepository;
import br.com.manager.service.PlaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private static final Logger LOGGER = LogManager.getLogger(PlaceServiceImpl.class);
    @Autowired
    private PlaceRepository placeRepository;

    /**
     * Function to create a new Place
     * Important: the return value is null when the Place already exists
     *
     * @param p
     * @return Place
     */
    @Override
    public Place create(PlaceDTO p) {
        Optional<Place> github = findByName(p.getName());
        if (github.isPresent()) {
            LOGGER.info("Place {} already exists", p.getName());
            return null;
        }
        Place place = new Place();
        BeanUtils.copyProperties(p, place);
        Date creationTimestamp = Calendar.getInstance().getTime();
        place.setCreatedAt(creationTimestamp);
        place.setUpdatedAt(creationTimestamp);
        return placeRepository.save(place);
    }

    /**
     * Function to find a Place record by name
     * Important: the return value is a empty Optional, if the record doesn't exists
     *
     * @param name
     * @return Optional
     */
    @Override
    public Optional<Place> findByName(String name) {
        return placeRepository.findByName(name);
    }

    /**
     * Function to find all existing Places by name
     *
     * @return List
     */
    @Override
    public List<Place> findAllByName(String name) {
        return placeRepository.findAll().stream().filter(place -> place.getName().startsWith(name)).collect(Collectors.toList());
    }

    /**
     * Function to find all existing Places
     *
     * @return List
     */
    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    /**
     * Function to update a existing Place record
     * Important: a new Place record is created, if doesn't exist yet
     *
     * @param p
     * @return Place
     */
    @Override
    public Place save(PlaceDTO p) {
        Optional<Place> present = findByName(p.getName());
        if (present.isPresent()) {
            BeanUtils.copyProperties(p, present.get());
            present.get().setUpdatedAt(Calendar.getInstance().getTime());
            return placeRepository.save(present.get());
        }
        LOGGER.info("Place {} doesn't exist, create it before update", p.getName());
        return null;
    }

    /**
     * Function to delete a existing Place record
     *
     * @param name
     */
    @Override
    public void delete(String name) {
        if (findByName(name).isPresent()) {
            placeRepository.deleteById(name);
        } else {
            LOGGER.info("Place {} doesn't exists", name);
        }
    }
}
