package ru.arink_group.deliveryapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

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

    public SQLiteDatabase getWritableDb() {
        return dbHelper.getWritableDatabase();
    }

}
