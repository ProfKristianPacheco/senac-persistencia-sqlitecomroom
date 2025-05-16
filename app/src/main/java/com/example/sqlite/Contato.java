package com.example.sqlite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tab_contatos")
public class Contato {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nome")
    String nome;
    @ColumnInfo(name = "telefone")
    String telefone;

    @Override
    public String toString() {
        return nome + " - " + telefone;
    }

    public Contato(int id, String telefone, String nome) {
        this.id = id;
        this.telefone = telefone;
        this.nome = nome;
    }
}
