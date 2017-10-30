package ru.arink_group.deliveryapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kirillvs on 09.10.17.
 */

public class DeliveryAppDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "delivery_app";
    private static final int DB_VERSION = 1;


    public DeliveryAppDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // selectet_products TABLE
        db.execSQL("CREATE TABLE selected_products ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "product_id INTEGER, "
                    + "name TEXT, "
                    + "description TEXT, "
                    + "count INTEGER, "
                    + "image_url TEXT);");

        // selected_portions TABLE
        db.execSQL("CREATE TABLE selected_portions ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT, "
                    + "description TEXT, "
                    + "price REAL, "
                    + "is_checked INTEGER, "
                    + "selected_product_id INTEGER);");

        // selected_ingredients TABLE
        db.execSQL("CREATE TABLE selected_ingredients ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT, "
                    + "description TEXT, "
                    + "price REAL, "
                    + "count INTEGER, "
                    + "selected_product_id INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
