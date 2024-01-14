package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.widget.Button;


public class criacao_contato extends AppCompatActivity {

    private EditText cep;
    private TextView estado, bairro, cidade, rua,logradouroTextView, bairroTextView,EstadoTextView, CidadeTextView;
    private CEPService cepService;
    private Button buscar, criar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_contato);

        logradouroTextView = findViewById(R.id.logradouroTextView);
        CidadeTextView = findViewById(R.id.cidadeTextView);
        bairroTextView = findViewById(R.id.bairroTextView);
        EstadoTextView = findViewById(R.id.EstadoTextView);
        cep = findViewById(R.id.cep);
        estado = findViewById(R.id.estado);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        rua = findViewById(R.id.rua);
        buscar = findViewById(R.id.buscar);
        criar = findViewById(R.id.criar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cepService = retrofit.create(CEPService.class);

        // Configuração do clique do botão


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chama o método para realizar a consulta
                consultarCEP();
            }
        });
    }

    // Método para realizar a consulta do CEP
    private void consultarCEP() {
        String cepString = cep.getText().toString();

        Call<CEPData> call = cepService.consultarCEP(cepString);
        call.enqueue(new Callback<CEPData>() {
            @Override
            public void onResponse(Call<CEPData> call, Response<CEPData> response) {
                if (response.isSuccessful()) {
                    CEPData cepData = response.body();
                    if (cepData != null) {
                        // Atualiza a UI na thread principal
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                atualizarUI(cepData);
                            }
                        });
                    }
                } else {
                    // Tratar resposta de erro

                    
                }
            }

            @Override
            public void onFailure(Call<CEPData> call, Throwable t) {
                // Tratar falha na comunicação
            }
        });
    }

    // Método para atualizar as Views na UI
    private void atualizarUI(CEPData cepData) {

        bairroTextView.setText(cepData.getBairro());
        CidadeTextView.setText(cepData.getLocalidade());
        logradouroTextView.setText(cepData.getLogradouro());
        EstadoTextView.setText(cepData.getLocalidade());

    }
}