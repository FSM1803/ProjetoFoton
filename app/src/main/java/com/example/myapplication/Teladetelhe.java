package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Teladetelhe extends AppCompatActivity {


    private FloatingActionButton botao2;

    private ImageView ImagemContato2,ImagemContato1,Telefone;

    private TextView NomeContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teladetelhe);

        botao2 = findViewById(R.id.botão2);

        ImagemContato1 = findViewById(R.id.ImagemContato1);
        ImagemContato2 = findViewById(R.id.ImagemContato2);

        NomeContato = findViewById(R.id.NomeContato);

        botao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Teladetelhe.this, criacao_contato.class));
            }
        });

        ImagemContato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crie uma Intent para abrir a MainActivity
                Intent intent = new Intent(Teladetelhe.this, MainActivity.class);

                // Inicie a MainActivity usando a Intent
                startActivity(intent);
            }
        });


    }
}