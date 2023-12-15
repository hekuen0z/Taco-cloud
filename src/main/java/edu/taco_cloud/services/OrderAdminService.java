package edu.taco_cloud.services;

import edu.taco_cloud.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService {

    private final OrderRepository orderRepo;

    @Autowired
    public OrderAdminService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * PreAuthorize примиает выражение SpEL
     * если выражение возвращает false - метод не вызывается
     * также сгенерирует исключение AccessDeniedException
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepo.deleteAll();
    }
}
