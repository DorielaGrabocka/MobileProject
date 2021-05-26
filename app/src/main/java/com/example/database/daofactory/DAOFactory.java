package com.example.database.daofactory;

import android.content.Context;

public class DAOFactory {
    public static DatabaseHelper getDatabaseAccessObject(Context context){
        return new DatabaseHelper(context);
    }
}
