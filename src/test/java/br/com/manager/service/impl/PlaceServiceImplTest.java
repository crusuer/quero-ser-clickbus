package br.com.manager.service.impl;

import br.com.manager.App;
import br.com.manager.dto.PlaceDTO;
import br.com.manager.entity.Place;
import br.com.manager.service.PlaceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PlaceServiceImplTest {
    @Autowired
    private PlaceService placeService;
    private PlaceDTO place = new PlaceDTO("Home");

    @Before
    public void setUp() {
        place.setCity("Los Angeles");
        place.setSlug("LA");
        place.setState("California");

        placeService.create(place);
    }

    @After
    public void tearDown() {
        placeService.delete(place.getName());
    }

    @Test
    public void testCreatePlace() {
        placeService.delete(place.getName());

        Place validate = placeService.create(place);
        assertEquals("Home", validate.getName());
        assertEquals("Los Angeles", validate.getCity());
        assertEquals("LA", validate.getSlug());
        assertEquals("California", validate.getState());
        assertNotNull(validate.getCreatedAt());
        assertNotNull(validate.getUpdatedAt());
        assertEquals(validate.getCreatedAt(), validate.getUpdatedAt());
    }

    @Test
    public void testCreateExistingPlace() {
        Place validate = placeService.create(place);
        assertNull(validate);
    }

    @Test
    public void testFindExistingPlaceByName() {
        Optional<Place> validate = placeService.findByName("Home");

        assertTrue(validate.isPresent());
        assertEquals("Home", validate.get().getName());
        assertEquals("Los Angeles", validate.get().getCity());
        assertEquals("LA", validate.get().getSlug());
        assertEquals("California", validate.get().getState());
        assertNotNull(validate.get().getCreatedAt());
        assertNotNull(validate.get().getUpdatedAt());
    }

    @Test
    public void testFindIneExistingPlaceByName() {
        Optional<Place> validate = placeService.findByName("");

        assertFalse(validate.isPresent());
        assertEquals(Optional.empty(), validate);
    }

    @Test
    public void testFindAllPlacesByName() {
        List<Place> validate = placeService.findAllByName("Ho");

        assertEquals(1, validate.size());
        assertEquals("Home", validate.get(0).getName());
        assertEquals("Los Angeles", validate.get(0).getCity());
        assertEquals("LA", validate.get(0).getSlug());
        assertEquals("California", validate.get(0).getState());
        assertNotNull(validate.get(0).getCreatedAt());
        assertNotNull(validate.get(0).getUpdatedAt());
    }

    @Test
    public void testFindAllPlaces() {
        PlaceDTO incrediblePlace = new PlaceDTO("Dream");
        incrediblePlace.setSlug("none");
        incrediblePlace.setCity("Budapest");
        incrediblePlace.setState("Hungary");
        placeService.create(incrediblePlace);

        List<Place> validate = placeService.findAll();
        assertEquals(2, validate.size());
        assertEquals("Home", validate.get(0).getName());
        assertEquals("Los Angeles", validate.get(0).getCity());
        assertEquals("Dream", validate.get(1).getName());
        assertEquals("Budapest", validate.get(1).getCity());

        placeService.delete(incrediblePlace.getName());
    }


    @Test
    public void testUpdatePlace() {
        place.setCity("Sacramento");

        Place validate = placeService.save(place);
        assertEquals("Home", validate.getName());
        assertEquals("Sacramento", validate.getCity());
        assertEquals("LA", validate.getSlug());
        assertEquals("California", validate.getState());
        assertNotNull(validate.getCreatedAt());
        assertNotNull(validate.getUpdatedAt());
        assertNotEquals(validate.getCreatedAt(), validate.getUpdatedAt());
    }

    @Test
    public void testDeletePlace() {
        placeService.delete("Home");
        Optional<Place> validate = placeService.findByName("Home");

        assertFalse(validate.isPresent());
        assertEquals(Optional.empty(), validate);
    }
}