package edu.taco_cloud.services;

import edu.taco_cloud.models.TacoOrder;
import edu.taco_cloud.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final OrderRepository orderRepo;

    @Autowired
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Iterable<TacoOrder> findAll() {
        return orderRepo.findAll();
    }

    public TacoOrder findById(Long id) {
        return orderRepo.findById(id).orElseThrow();
    }

    public TacoOrder save(TacoOrder order) {
        return orderRepo.save(order);
    }
}
