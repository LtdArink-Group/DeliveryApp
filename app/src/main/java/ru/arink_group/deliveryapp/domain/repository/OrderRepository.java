package ru.arink_group.deliveryapp.domain.repository;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.OrderDTO;

/**
 * Created by kirillvs on 17.11.17.
 */

public interface OrderRepository {
    Observable<Object> sendOrder(OrderDTO orderDTO);
}
