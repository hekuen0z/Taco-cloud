package edu.taco_cloud.repositories;

import edu.taco_cloud.models.TacoOrder;
import edu.taco_cloud.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository
        extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> findAllByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    /**
     * Метод, делающий выборку по двум предикатам (условиям)
     * @param deliveryZip
     * @param startDate
     * @param endDate
     * @return - список объектов из БД, удовлетворяющих условиям
     * Можно использовать также слово `get` == `find` == `read`.
     */
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip,
            Date startDate,
            Date endDate
    );

    //@Query("Order o where o.deliveryCity='Seattle'")
    //List<TacoOrder> findOrdersDeliveredInSeattle();

}
