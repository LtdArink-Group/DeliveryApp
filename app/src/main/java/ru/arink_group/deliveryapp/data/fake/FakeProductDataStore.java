package ru.arink_group.deliveryapp.data.fake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import ru.arink_group.deliveryapp.data.repository.datasource.ProductDataStore;
import ru.arink_group.deliveryapp.domain.dto.AdditionalInfoDTO;
import ru.arink_group.deliveryapp.domain.dto.MainOptionDTO;
import ru.arink_group.deliveryapp.domain.dto.ProductDTO;

/**
 * Created by kirillvs on 03.10.17.
 */

public class FakeProductDataStore implements ProductDataStore {

    private static List<ProductDTO> products() {
        // TODO BIG REWORK

        List<ProductDTO> productsList = new ArrayList<>();

        ProductDTO one = new ProductDTO();
        ProductDTO two = new ProductDTO();
        ProductDTO three = new ProductDTO();
        ProductDTO five = new ProductDTO();

        one.setTitle("Lorem");
        two.setTitle("Lorem");
        three.setTitle("Lorem");
        five.setTitle("Lorem");

        one.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");
        two.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");
        three.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");
        five.setDescription("Lorem ipsum dolor sit amet, omnium ceteros nam ut. Odio nullam ut mei. Mea probo possim et, affert legimus erroribus ne sit, mundi vidisse malorum vix ut. Vis alienum omnesque platonem te. Oblique corpora accumsan id per, pri laudem luptatum no, ius eu virtute laoreet verterem.");

        MainOptionDTO[] portions1 = new MainOptionDTO[] {new MainOptionDTO(), new MainOptionDTO(), new MainOptionDTO()};
        portions1[0].setName("Пицель");
        portions1[0].setCost(250.0);
        portions1[1].setName("Шницель");
        portions1[1].setCost(500.0);
        portions1[2].setName("Водка");
        portions1[2].setCost(700.0);
        MainOptionDTO[] portions2 = new MainOptionDTO[] {new MainOptionDTO(), new MainOptionDTO(), new MainOptionDTO()};
        portions2[0].setName("Пицель");
        portions2[0].setCost(250.0);
        portions2[1].setName("Шницель");
        portions2[1].setCost(500.0);
        portions2[2].setName("Водка");
        portions2[2].setCost(700.0);
        MainOptionDTO[] portions3 = new MainOptionDTO[] {new MainOptionDTO(), new MainOptionDTO(), new MainOptionDTO()};
        portions3[0].setName("Пицель");
        portions3[0].setCost(250.0);
        portions3[1].setName("Шницель");
        portions3[1].setCost(500.0);
        portions3[2].setName("Водка");
        portions3[2].setCost(700.0);
        MainOptionDTO[] portions4 = new MainOptionDTO[] {new MainOptionDTO(), new MainOptionDTO(), new MainOptionDTO()};
        portions4[0].setName("Пицель");
        portions4[0].setCost(250.0);
        portions4[1].setName("Шницель");
        portions4[1].setCost(500.0);
        portions4[2].setName("Водка");
        portions4[2].setCost(700.0);

        one.setMainOptions(new ArrayList<>(Arrays.asList(portions1)));
        two.setMainOptions(new ArrayList<>(Arrays.asList(portions2)));
        three.setMainOptions(new ArrayList<>(Arrays.asList(portions3)));
        five.setMainOptions(new ArrayList<>(Arrays.asList(portions4)));


        one.setId(1);
        two.setId(2);
        three.setId(3);
        five.setId(5);

        AdditionalInfoDTO[] ingredients1 = new AdditionalInfoDTO[] {new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO()};
        ingredients1[0].setName("Соус");
        ingredients1[0].setCost(100.0);
        ingredients1[1].setName("Кетчуп");
        ingredients1[1].setCost(250.0);
        ingredients1[2].setName("Подливка");
        ingredients1[2].setCost(30.0);
        ingredients1[3].setName("Зелень");
        ingredients1[3].setCost(50.0);

        AdditionalInfoDTO[] ingredients2 = new AdditionalInfoDTO[] {new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO()};
        ingredients2[0].setName("Соус");
        ingredients2[0].setCost(100.0);
        ingredients2[1].setName("Кетчуп");
        ingredients2[1].setCost(250.0);
        ingredients2[2].setName("Подливка");
        ingredients2[2].setCost(30.0);
        ingredients2[3].setName("Зелень");
        ingredients2[3].setCost(50.0);

        AdditionalInfoDTO[] ingredients3 = new AdditionalInfoDTO[] {new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO()};
        ingredients3[0].setName("Соус");
        ingredients3[0].setCost(100.0);
        ingredients3[1].setName("Кетчуп");
        ingredients3[1].setCost(250.0);
        ingredients3[2].setName("Подливка");
        ingredients3[2].setCost(30.0);
        ingredients3[3].setName("Зелень");
        ingredients3[3].setCost(50.0);

        AdditionalInfoDTO[] ingredients4 = new AdditionalInfoDTO[] {new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO(), new AdditionalInfoDTO()};
        ingredients4[0].setName("Соус");
        ingredients4[0].setCost(100.0);
        ingredients4[1].setName("Кетчуп");
        ingredients4[1].setCost(250.0);
        ingredients4[2].setName("Подливка");
        ingredients4[2].setCost(30.0);
        ingredients4[3].setName("Зелень");
        ingredients4[3].setCost(50.0);

        one.setAdditionalInfo(new ArrayList<>(Arrays.asList(ingredients1)));
        two.setAdditionalInfo(new ArrayList<>(Arrays.asList(ingredients2)));
        three.setAdditionalInfo(new ArrayList<>(Arrays.asList(ingredients3)));
        five.setAdditionalInfo(new ArrayList<>(Arrays.asList(ingredients4)));

        productsList.add(one);
        productsList.add(two);
        productsList.add(three);
        productsList.add(five);

        return productsList;
    }


    @Override
    public Observable<List<ProductDTO>> productsDataList(int categoryId) {

        return Observable.just(products());

    }

    @Override
    public Observable<ProductDTO> productData(int productId) {
        ProductDTO result = null;
        for(ProductDTO product : products()) {
            if (product.getId() == productId) result = product;
        }
        return Observable.just(result);
    }
}
