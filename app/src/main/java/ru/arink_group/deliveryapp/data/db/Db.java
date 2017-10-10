package ru.arink_group.deliveryapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.arink_group.deliveryapp.domain.SelectedIngredient;
import ru.arink_group.deliveryapp.domain.SelectedPortion;
import ru.arink_group.deliveryapp.domain.SelectedProduct;
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

    public SelectedProduct addItemToBasket(SelectedProduct selectedProduct) {

        ContentValues cv = new ContentValues();
        cv.put("product_id", selectedProduct.getId());
        cv.put("name", selectedProduct.getId());
        cv.put("description", selectedProduct.getId());
        cv.put("image_url", selectedProduct.getId());
        long product_id = dbHelper.getWritableDatabase().insert("selected_products", null, cv);
        if (product_id != -1) {
            insertPortion(product_id, selectedProduct.getSelectedPortionFromPortion());
            insertIngredients(product_id, selectedProduct.getSelectedIngredients());
        }
        return null;
    }

    public SelectedProduct removeItemFromBasket(int selectedProductId) {
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
        return null;
    }

    public SelectedProduct[] getListItemsFromBasket() {
        ArrayList<SelectedProduct> sps = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase().query("selected_products", null, null, null, null, null, null);

        int _id = cursor.getColumnIndex("_id");
        int product_id = cursor.getColumnIndex("product_id");
        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int image_url = cursor.getColumnIndex("image_url");

        while (cursor.moveToNext()) {
            SelectedProduct sp = new SelectedProduct();
            sp.setName(cursor.getString(name));
            sp.setDescription(cursor.getString(description));
            sp.setId(cursor.getInt(product_id));
            sp.setImageUrl(cursor.getString(image_url));
            sp.setSelectedIngredients(this.getIngredients(cursor.getLong(_id)));
            sp.setSelectedPortion(this.getPortion(cursor.getLong(_id)));
            sps.add(sp);
        }
        SelectedProduct[] selectedProducts = new SelectedProduct[sps.size()];
        selectedProducts = sps.toArray(selectedProducts);
        cursor.close();
        return selectedProducts;
    }

    private SelectedIngredient[] getIngredients(long productDbId) {
        ArrayList<SelectedIngredient> sis = new ArrayList<>();
        Cursor cursor = dbHelper.getWritableDatabase().query("selected_ingredients", null, "selected_product_id = ?", new String[] {String.valueOf(productDbId)}, null, null, null);

        int ingredient_id = cursor.getColumnIndex("ingredient_id");
        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int price = cursor.getColumnIndex("price");
        int size = cursor.getColumnIndex("size");
        int image_url = cursor.getColumnIndex("image_url");
        int count = cursor.getColumnIndex("count");

        while (cursor.moveToNext()) {
            SelectedIngredient si = new SelectedIngredient();
            si.setId(cursor.getInt(ingredient_id));
            si.setName(cursor.getString(name));
            si.setDescription(cursor.getString(description));
            si.setCount(cursor.getInt(count));
            si.setImageLink(cursor.getString(image_url));
            si.setPrice(cursor.getFloat(price));
            si.setSize(cursor.getString(size));
            sis.add(si);
        }

        SelectedIngredient[] selectedIngredients = new SelectedIngredient[sis.size()];
        selectedIngredients = sis.toArray(selectedIngredients);
        cursor.close();
        return selectedIngredients;
    }

    private SelectedPortion getPortion(long productDbId) {

        SelectedPortion selectedPortion = new SelectedPortion();

        Cursor cursor = dbHelper.getWritableDatabase().query("selected_portions", null, "selected_product_id = ?", new String[] {String.valueOf(productDbId)}, null, null, null);

        int portion_id = cursor.getColumnIndex("portion_id");
        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int price = cursor.getColumnIndex("price");
        int count = cursor.getColumnIndex("count");
        int size = cursor.getColumnIndex("size");

        if (cursor.moveToFirst()) {
            selectedPortion.setSize(cursor.getString(size));
            selectedPortion.setPrice(cursor.getFloat(price));
            selectedPortion.setCount(cursor.getInt(count));
            selectedPortion.setDescription(cursor.getString(description));
            selectedPortion.setId(cursor.getInt(portion_id));
            selectedPortion.setName(cursor.getString(name));
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

    private long[] insertIngredients(long product_id, SelectedIngredient[] ingredients) {
        long[] insertedIds = new long[ingredients.length];
        for(int i = 0; i < ingredients.length; i ++) {
            ContentValues cv = new ContentValues();
            cv.put("portion_id", ingredients[i].getId());
            cv.put("name", ingredients[i].getName());
            cv.put("description", ingredients[i].getDescription());
            cv.put("price", ingredients[i].getPrice());
            cv.put("count", ingredients[i].getCount());
            cv.put("size", ingredients[i].getSize());
            cv.put("image_url", ingredients[i].getImageLink());
            cv.put("selected_product_id", product_id);
            long ingredient_id = dbHelper.getWritableDatabase().insert("selected_ingredients", null, cv);
            insertedIds[i] = ingredient_id;
        }
        return insertedIds;
    }

    private long insertPortion(long product_id, SelectedPortion portion) {
        ContentValues cv = new ContentValues();
        cv.put("portion_id", portion.getId());
        cv.put("name", portion.getName());
        cv.put("description", portion.getDescription());
        cv.put("price", portion.getPrice());
        cv.put("count", portion.getCount());
        cv.put("size", portion.getSize());
        cv.put("selected_product_id", product_id);
        long portion_id = dbHelper.getWritableDatabase().insert("selected_portions", null, cv);
        return portion_id;
    }


}
