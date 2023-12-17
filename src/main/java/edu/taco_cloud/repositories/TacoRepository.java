package edu.taco_cloud.repositories;

import edu.taco_cloud.models.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository
        extends CrudRepository<Taco, Long> {

    Iterable<Taco> findAll(Pageable pageable);
}
