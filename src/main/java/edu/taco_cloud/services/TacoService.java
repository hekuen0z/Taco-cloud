package edu.taco_cloud.services;

import edu.taco_cloud.models.Taco;
import edu.taco_cloud.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TacoService {

    private TacoRepository tacoRepo;

    @Autowired
    public TacoService(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    public Iterable<Taco> findAllByPage(Pageable pageable) {
        return tacoRepo.findAll(pageable);
    }
}
