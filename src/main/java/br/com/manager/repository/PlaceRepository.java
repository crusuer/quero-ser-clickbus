package br.com.manager.repository;

import br.com.manager.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {
    Optional<Place> findByName(String name);
}
