package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaDetalhe2 extends AppCompatActivity {

    private Button Button;
    private TextView textView, textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe2);

        Button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crie uma Intent para abrir a MainActivity
                Intent intent = new Intent(TelaDetalhe2.this, MainActivity.class);

                // Inicie a MainActivity usando a Intent
                startActivity(intent);
            }
        });

    }
}