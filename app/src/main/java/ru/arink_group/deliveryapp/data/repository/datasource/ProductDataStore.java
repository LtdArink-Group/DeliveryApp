package ru.arink_group.deliveryapp.data.repository.datasource;

import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.domain.dto.ProductDTO;

/**
 * Created by kirillvs on 03.10.17.
 */

public interface ProductDataStore {

    Observable<List<ProductDTO>> productsDataList(int categoryId);

    Observable<ProductDTO> productData(int productId);
}
