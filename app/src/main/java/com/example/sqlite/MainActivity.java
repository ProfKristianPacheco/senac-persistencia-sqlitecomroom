package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    private EditText telelefone;
    private Button salvar;
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        inicializa();
    }

    private void inicializa() {
        nome = findViewById(R.id.editTextNome);
        telelefone = findViewById(R.id.editTextPhone);
        salvar = findViewById(R.id.buttonSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaContatoNoBancoDeDados();
            }
        });
    }

    private void salvaContatoNoBancoDeDados() {
        db = Room.databaseBuilder(getApplicationContext(),
                MyDatabase.class, "bancodados.db").build();
        Contato contatoAtual = new Contato();
        contatoAtual.id = 0;
        contatoAtual.nome = nome.getText().toString();
        contatoAtual.telefone = telelefone.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.contatoDAO().insertContato(contatoAtual);
            }
        }).start();


    }
}