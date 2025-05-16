package com.example.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContatoDAO {
    @Insert
    void insertContato(Contato contato);

    @Query("SELECT * FROM tab_contatos")
    List<Contato> buscaTodosContatos();



}
