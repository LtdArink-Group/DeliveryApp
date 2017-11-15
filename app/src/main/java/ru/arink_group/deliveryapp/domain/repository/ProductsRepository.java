package ru.arink_group.deliveryapp.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.ProductDTO;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductsRepository {

    Observable<List<ProductDTO>> productsList(int categoryId);

    Observable<ProductDTO> product(int productId);

}
