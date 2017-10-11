package ru.arink_group.deliveryapp.domain.transform;

import java.util.ArrayList;
import java.util.List;

import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.domain.SelectedIngredient;
import ru.arink_group.deliveryapp.domain.SelectedPortion;
import ru.arink_group.deliveryapp.domain.SelectedProduct;

/**
 * Created by kirillvs on 11.10.17.
 */

public class ProductToSelectedProductTransform {
    public static SelectedProduct execute(Product product) {
        SelectedProduct selectedProduct = new SelectedProduct(product);

        SelectedPortion selectedPortion = new SelectedPortion(product.getSelectedPortion());

        selectedProduct.setSelectedPortion(selectedPortion);

        selectedProduct.setSelectedIngredients(SelectedIngredient.fromIngredients(product.getIngredients()));

        return selectedProduct;
    }

    public static List<SelectedProduct> execute(List<Product> products) {
        List<SelectedProduct> sps = new ArrayList<>();
        for(Product p : products){
            if (p.haveRealSelectedPortion() || p.isSelected()) sps.add(execute(p));
        }
        return sps;
    }
}
