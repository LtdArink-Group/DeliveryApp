package ru.arink_group.deliveryapp.data.repository.datasource;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.OrderDTO;

/**
 * Created by kirillvs on 17.11.17.
 */

public interface OrderDataStore {
    Observable<Object> sendOrderToServer(OrderDTO orderDTO);
}
