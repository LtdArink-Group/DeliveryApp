package ru.arink_group.deliveryapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.arink_group.deliveryapp.domain.Ingredient;
import ru.arink_group.deliveryapp.domain.Portion;
import ru.arink_group.deliveryapp.domain.Product;
import ru.arink_group.deliveryapp.presentation.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class Db {

    @Inject public Context context;
    private DeliveryAppDatabaseHelper dbHelper;


    public Db() {
        App.getComponent().inject(this);
        dbHelper = new DeliveryAppDatabaseHelper(context);
    }

    public Boolean addItemToBasket(Product selectedProduct) {

        ContentValues cv = new ContentValues();
        cv.put("product_id", selectedProduct.getId());
        cv.put("name", selectedProduct.getName());
        cv.put("description", selectedProduct.getDescription());
        cv.put("count", selectedProduct.getCount());
        cv.put("image_url", selectedProduct.getImageUrl());
        long product_id = dbHelper.getWritableDatabase().insert("selected_products", null, cv);
        if (product_id == -1) return false;
        if (!insertPortion(product_id, selectedProduct.getSelectedPortion())) return false;
        if (!insertIngredients(product_id, selectedProduct.getSelectedIngredients())) return false;
        return true;
    }

    public Integer addListItemsToBasket(List<Product> selectedProducts) {
        int numb = 0;
        for (Product sp : selectedProducts) {
            if (addItemToBasket(sp)) numb++;
        }
        return numb;
    }

    public Boolean removeItemFromBasket(int selectedProductId) {
        ArrayList<Long> productDbIds = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase().query("selected_products", new String[] {"_id"}, "product_id = ?", new String[] {String.valueOf(selectedProductId)}, null, null, null, null);
        while(cursor.moveToNext()) {
            productDbIds.add(cursor.getLong(0));
        }
        for (long id : productDbIds) {
            dbHelper.getWritableDatabase().delete("selected_products", "_id = ?", new String[] {String.valueOf(id)});
            removeIngredients(id);

            removePortion(id);
        }
        cursor.close();
        return true;
    }

    public List<Product> getListItemsFromBasket() {
        ArrayList<Product> sps = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase().query("selected_products", null, null, null, null, null, null);

        int _id = cursor.getColumnIndex("_id");
        int product_id = cursor.getColumnIndex("product_id");
        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int count = cursor.getColumnIndex("count");
        int image_url = cursor.getColumnIndex("image_url");

        while (cursor.moveToNext()) {
            Product sp = new Product();
            sp.setId(cursor.getInt(product_id));
            sp.setName(cursor.getString(name));
            sp.setDescription(cursor.getString(description));
            sp.setCount(cursor.getInt(count));
            sp.setImageUrl(cursor.getString(image_url));
            sp.setSelectedIngredients(this.getIngredients(cursor.getLong(_id)));
            sp.setSelectedPortion(this.getPortion(cursor.getLong(_id)));
            sps.add(sp);
        }
        cursor.close();
        return sps;
    }

    private Ingredient[] getIngredients(long productDbId) {
        ArrayList<Ingredient> sis = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase().query("selected_ingredients", null, "selected_product_id = ?", new String[] {String.valueOf(productDbId)}, null, null, null);

        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int price = cursor.getColumnIndex("price");
        int count = cursor.getColumnIndex("count");

        while (cursor.moveToNext()) {
            Ingredient si = new Ingredient();
            si.setName(cursor.getString(name));
            si.setDescription(cursor.getString(description));
            si.setCount(cursor.getInt(count));
            si.setPrice(cursor.getFloat(price));
            sis.add(si);
        }

        Ingredient[] selectedIngredients = new Ingredient[sis.size()];
        selectedIngredients = sis.toArray(selectedIngredients);
        cursor.close();
        return selectedIngredients;
    }

    private Portion getPortion(long productDbId) {

        Portion selectedPortion = new Portion();

        Cursor cursor = dbHelper.getWritableDatabase().query("selected_portions", null, "selected_product_id = ?", new String[] {String.valueOf(productDbId)}, null, null, null);

        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int price = cursor.getColumnIndex("price");
//        int is_checked = cursor.getColumnIndex("is_checked");

        if (cursor.moveToFirst()) {
            selectedPortion.setName(cursor.getString(name));
            selectedPortion.setDescription(cursor.getString(description));
            selectedPortion.setPrice(cursor.getFloat(price));
            selectedPortion.setChecked(true);
        }
        cursor.close();
        return selectedPortion;
    }

    private void removeIngredients(long productId) {
        dbHelper.getWritableDatabase().delete("selected_ingredients", "selected_product_id = ?", new String[] {String.valueOf(productId)});
    }

    private void removePortion(long productId) {
        dbHelper.getWritableDatabase().delete("selected_portions", "selected_product_id = ?", new String[] {String.valueOf(productId)});
    }

    private boolean insertIngredients(long product_id, Ingredient[] ingredients) {
        long[] insertedIds = new long[ingredients.length];
        for(int i = 0; i < ingredients.length; i ++) {
            ContentValues cv = new ContentValues();
            cv.put("name", ingredients[i].getName());
            cv.put("description", ingredients[i].getDescription());
            cv.put("price", ingredients[i].getPrice());
            cv.put("count", ingredients[i].getCount());
            cv.put("selected_product_id", product_id);
            long ingredient_id = dbHelper.getWritableDatabase().insert("selected_ingredients", null, cv);
            insertedIds[i] = ingredient_id;
        }
        for(long inserId : insertedIds) {
            if (inserId == -1) return false;
        }
        return true;
    }

    private boolean insertPortion(long product_id, Portion portion) {
        ContentValues cv = new ContentValues();
        cv.put("name", portion.getName());
        cv.put("description", portion.getDescription());
        cv.put("price", portion.getPrice());
        cv.put("is_checked", portion.isChecked());
        cv.put("selected_product_id", product_id);
        long portion_id = dbHelper.getWritableDatabase().insert("selected_portions", null, cv);
        if (portion_id == -1) return false;
        return true;
    }


}
