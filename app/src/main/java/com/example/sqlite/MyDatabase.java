package com.example.sqlite;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Contato.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ContatoDAO contatoDAO();
}
