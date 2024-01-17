package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactRv;

    private FloatingActionButton botao2;

    private DBHelper dbHelper;

    private AdapterContact adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new DBHelper(this);
        botao2 = findViewById(R.id.botão2);
        contactRv = findViewById(R.id.ContatoRv);


        contactRv.setHasFixedSize(true);

        botao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, criacao_contato.class));
            }
        });

        loadData();
    }

    private void loadData() {
        adapterContact = new AdapterContact(this, dbHelper.getAllData());
        contactRv.setAdapter(adapterContact);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();
    }
}
