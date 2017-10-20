package ru.arink_group.deliveryapp.data.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.arink_group.deliveryapp.domain.dto.ProductDTO;

/**
 * Created by kirillvs on 20.10.17.
 */

public class ProductsObject {

    @SerializedName("products")
    @Expose
    private List<ProductDTO> products = null;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}



