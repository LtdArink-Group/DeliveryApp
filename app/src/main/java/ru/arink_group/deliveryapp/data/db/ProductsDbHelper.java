package ru.arink_group.deliveryapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.arink_group.deliveryapp.domain.dao.Ingredient;
import ru.arink_group.deliveryapp.domain.dao.Portion;
import ru.arink_group.deliveryapp.domain.dao.Product;
import ru.arink_group.deliveryapp.App;

/**
 * Created by kirillvs on 09.10.17.
 */

public class ProductsDbHelper {

    @Inject public Context context;
    private DeliveryAppDatabaseHelper dbHelper;


    public ProductsDbHelper() {
        App.getComponent().inject(this);
        dbHelper = new DeliveryAppDatabaseHelper(context);
    }

    public Boolean clearAllItems() {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        writableDb.delete("selected_products", null, null);
        writableDb.delete("selected_portions", null, null);
        writableDb.delete("selected_ingredients", null, null);

        writableDb.close();
        return true;
    }

    public Boolean addIngredientToProduct(int productId, Ingredient ingredient) {
        long id = getDbIdByProductId(productId);
        if(id == -1) return false;

        if(ingredient.getCount() == 0) {
            return removeIngredient(id, ingredient);
        }

        if(updateIngredient(id, ingredient) >= 1) {
            return true;
        } else if(insertIngredient(id, ingredient) != -1) {
            return true;
        }

        return false;
    }

    private int updateIngredient(long id, Ingredient ingredient) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("count", ingredient.getCount());
        int rowsAffected = writableDb.update("selected_ingredients", cv, "selected_product_id = ? and name = ?", new String[] {String.valueOf(id), ingredient.getName()});

        writableDb.close();
        return rowsAffected;
    }


    private long insertIngredient(long productId, Ingredient ingredient) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", ingredient.getName());
        cv.put("description", ingredient.getDescription());
        cv.put("price", ingredient.getPrice());
        cv.put("count", ingredient.getCount());
        cv.put("selected_product_id", productId);
        long ingredientId = writableDb.insert("selected_ingredients", null, cv);

        writableDb.close();
        return ingredientId;
    }

    private long getDbIdByProductId(int productId) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        Cursor cursor = writableDb.query("selected_products", new String[] {"_id"}, "product_id = ?", new String[] {String.valueOf(productId)}, null, null, null, null);

        long result = -1;

        if(cursor.moveToFirst()) {
            result = cursor.getLong(0);
        }

        cursor.close();
        writableDb.close();

        return result;
    }

    private Boolean removeIngredient(long productId, Ingredient ingredient) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        int result = writableDb.delete("selected_ingredients", "selected_product_id = ? and name = ?", new String[] {String.valueOf(productId), ingredient.getName()});

        writableDb.close();

        return result > 0;
    }

    public Boolean addItemToBasket(Product selectedProduct) {
        removeItemFromBasket(selectedProduct.getId());

        if (selectedProduct.getCount() < 1) return true;

        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("product_id", selectedProduct.getId());
        cv.put("name", selectedProduct.getName());
        cv.put("description", selectedProduct.getDescription());
        cv.put("count", selectedProduct.getCount());
        cv.put("image_url", selectedProduct.getImageUrl());
        long product_id = writableDb.insert("selected_products", null, cv);
        if (product_id == -1) return false;
        if (!insertPortion(product_id, selectedProduct.getSelectedPortion())) return false;
        if (!insertIngredients(product_id, selectedProduct.getSelectedIngredients())) return false;
        writableDb.close();


        return true;
    }

    public Product addItemToBasketOrNull(Product selectedProduct) {
        removeItemFromBasket(selectedProduct.getId());
        Product nothing = new Product();
        nothing.setId(selectedProduct.getId());

        if (selectedProduct.getCount() < 1) return nothing;

        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("product_id", selectedProduct.getId());
        cv.put("name", selectedProduct.getName());
        cv.put("description", selectedProduct.getDescription());
        cv.put("count", selectedProduct.getCount());
        cv.put("image_url", selectedProduct.getImageUrl());
        long product_id = writableDb.insert("selected_products", null, cv);
        if (product_id == -1) return nothing;
        if (!insertPortion(product_id, selectedProduct.getSelectedPortion())) return nothing;
        if (!insertIngredients(product_id, selectedProduct.getSelectedIngredients())) return nothing;
        writableDb.close();


        return getItemFromBasket(selectedProduct.getId());
    }

    public Integer addListItemsToBasket(List<Product> selectedProducts) {
        int numb = 0;
        for (Product sp : selectedProducts) {
            if (addItemToBasket(sp)) numb++;
        }
        return numb;
    }

    public Boolean removeItemFromBasket(int selectedProductId) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        ArrayList<Long> productDbIds = new ArrayList<>();
        Cursor cursor = writableDb.query("selected_products", new String[] {"_id"}, "product_id = ?", new String[] {String.valueOf(selectedProductId)}, null, null, null, null);
        while(cursor.moveToNext()) {
            productDbIds.add(cursor.getLong(0));
        }
        for (long id : productDbIds) {
            writableDb.delete("selected_products", "_id = ?", new String[] {String.valueOf(id)});
            removeIngredients(id);

            removePortion(id);
        }
        cursor.close();
        writableDb.close();
        return true;
    }

    public List<Product> getListItemsFromBasket() {
        ArrayList<Product> sps = new ArrayList<>();
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        Cursor cursor = writableDb.query("selected_products", null, null, null, null, null, null);

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
            sp.setIngredients(this.getIngredients(cursor.getLong(_id)));
            sp.setPortion(this.getPortion(cursor.getLong(_id)));
            sps.add(sp);
        }
        cursor.close();
        writableDb.close();
        return sps;
    }

    public Product getItemFromBasket(int productId) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();
        Product product = new Product();

        Product nothing = new Product();
        nothing.setId(productId);

        Cursor cursor = writableDb.query("selected_products", null, "product_id = ?", new String[] {String.valueOf(productId)}, null, null, null);

        int _id = cursor.getColumnIndex("_id");
        int product_id = cursor.getColumnIndex("product_id");
        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int count = cursor.getColumnIndex("count");
        int image_url = cursor.getColumnIndex("image_url");

        if (cursor.moveToFirst()) {
            product.setId(cursor.getInt(product_id));
            product.setName(cursor.getString(name));
            product.setDescription(cursor.getString(description));
            product.setCount(cursor.getInt(count));
            product.setImageUrl(cursor.getString(image_url));
            product.setIngredients(this.getIngredients(cursor.getLong(_id)));
            product.setPortion(this.getPortion(cursor.getLong(_id)));
        } else {
            cursor.close();
            writableDb.close();
            return nothing;
        }
        cursor.close();
        writableDb.close();
        return product;
    }

    private Ingredient[] getIngredients(long productDbId) {
        ArrayList<Ingredient> sis = new ArrayList<>();
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        Cursor cursor = writableDb.query("selected_ingredients", null, "selected_product_id = ?", new String[] {String.valueOf(productDbId)}, null, null, null);

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
        writableDb.close();
        return selectedIngredients;
    }

    private Portion getPortion(long productDbId) {

        Portion selectedPortion = new Portion();

        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        Cursor cursor = writableDb.query("selected_portions", null, "selected_product_id = ?", new String[] {String.valueOf(productDbId)}, null, null, null);

        int name = cursor.getColumnIndex("name");
        int description = cursor.getColumnIndex("description");
        int price = cursor.getColumnIndex("price");

        if (cursor.moveToFirst()) {
            selectedPortion.setName(cursor.getString(name));
            selectedPortion.setDescription(cursor.getString(description));
            selectedPortion.setPrice(cursor.getFloat(price));
            selectedPortion.setChecked(true);
        }
        cursor.close();
        writableDb.close();
        return selectedPortion;
    }

    private void removeIngredients(long productId) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        writableDb.delete("selected_ingredients", "selected_product_id = ?", new String[] {String.valueOf(productId)});

        writableDb.close();
    }

    private void removePortion(long productId) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        writableDb.delete("selected_portions", "selected_product_id = ?", new String[] {String.valueOf(productId)});

        writableDb.close();
    }

    private boolean insertIngredients(long product_id, Ingredient[] ingredients) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        // TODO move to insertIngredient
        long[] insertedIds = new long[ingredients.length];
        for(int i = 0; i < ingredients.length; i ++) {
            ContentValues cv = new ContentValues();
            cv.put("name", ingredients[i].getName());
            cv.put("description", ingredients[i].getDescription());
            cv.put("price", ingredients[i].getPrice());
            cv.put("count", ingredients[i].getCount());
            cv.put("selected_product_id", product_id);
            long ingredient_id = writableDb.insert("selected_ingredients", null, cv);
            insertedIds[i] = ingredient_id;
        }
        for(long inserId : insertedIds) {
            if (inserId == -1) return false;
        }
        writableDb.close();
        return true;
    }

    private boolean insertPortion(long product_id, Portion portion) {
        SQLiteDatabase writableDb = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", portion.getName());
        cv.put("description", portion.getDescription());
        cv.put("price", portion.getPrice());
        cv.put("is_checked", portion.isChecked());
        cv.put("selected_product_id", product_id);
        long portion_id = writableDb.insert("selected_portions", null, cv);
        writableDb.close();
        if (portion_id == -1) return false;
        return true;
    }


}
