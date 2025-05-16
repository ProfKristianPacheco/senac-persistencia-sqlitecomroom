package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    private EditText telelefone;
    private Button salvar;
    private MyDatabase db;
    private ListView agenda;
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
        agenda = findViewById(R.id.listViewAgenda);
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
        Contato contatoAtual = new Contato(0, telelefone.getText().toString(),
                nome.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.contatoDAO().insertContato(contatoAtual);
                List<Contato> listaContatos = db.contatoDAO().buscaTodosContatos();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Contato> myAdapter = new ArrayAdapter<>(getApplicationContext(),
                                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                                listaContatos);
                        agenda.setAdapter(myAdapter);
                    }
                });


            }
        }).start();


    }
}