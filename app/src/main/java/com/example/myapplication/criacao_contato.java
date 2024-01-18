package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class criacao_contato extends AppCompatActivity {


    private EditText sobrenome, nome, telefone, nascimento, cep,numero;
    private TextView estado, bairro, cidade, rua, logradouroTextView, bairroTextView, EstadoTextView, CidadeTextView;
    private CEPService cepService;
    private Button buscar, criar;
    private  ImageButton voltar;


   private  String nomeET,sobrenomeET,telefoneEt,datadenascimentoET, cepET, estadoET, cidadeET, bairroET,ruaET, numeroET;

   private DBHelper dBhelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criacao_contato);

        dBhelper = new DBHelper(this);




        nome = findViewById(R.id.nome);
        sobrenome = findViewById(R.id.sobrenome);
        logradouroTextView = findViewById(R.id.logradouroTextView);
        CidadeTextView = findViewById(R.id.cidadeTextView);
        bairroTextView = findViewById(R.id.bairroTextView);
        EstadoTextView = findViewById(R.id.EstadoTextView);
        nascimento = findViewById(R.id.datanascimento);
        telefone = findViewById(R.id.telefone);
        cep = findViewById(R.id.cep);
        estado = findViewById(R.id.estado);
        bairro = findViewById(R.id.bairro);
        cidade = findViewById(R.id.cidade);
        rua = findViewById(R.id.rua);
        voltar = findViewById(R.id.voltar);
        criar = findViewById(R.id.criar);
        numero = findViewById(R.id.numero);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cepService = retrofit.create(CEPService.class);

        // Configuração do clique do botão
        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(criacao_contato.this, Teladetelhe.class);

                startActivity(intent);

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre a atividade de criação de contato (criacao_contato)
                Intent intent = new Intent(criacao_contato.this, MainActivity.class);
                startActivity(intent);
            }
        });







        cep.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;

                String str = s.toString().replaceAll("[^0-9]", "");

                if (str.length() > 5) {
                    str = str.substring(0, 5) + "-" + str.substring(5);
                }

                cep.setText(str);
                cep.setSelection(str.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Execute the CEP lookup when the user completes the CEP
                if (s.length() == 9) {
                    consultarCEP();
                }
            }
        });


        nascimento.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;
                String str = s.toString().replaceAll("[^0-9]", "");
                if (str.length() > 4) {
                    str = str.substring(0, 2) + "/" + str.substring(2, 4) + "/" + str.substring(4);
                } else if (str.length() > 2) {
                    str = str.substring(0, 2) + "/" + str.substring(2);
                }

                nascimento.setText(str);
               nascimento.setSelection(str.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Você pode adicionar lógica adicional aqui se necessário
                // Por exemplo, validar a data ou algo do tipo
            }
        });



        telefone.addTextChangedListener(new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;

                String str = s.toString().replaceAll("[^0-9]", "");

                if (str.length() > 2) {
                    str = "(" + str.substring(0, 2) + ")" + str.substring(2);
                }

                if (str.length() > 10) {
                    str = str.substring(0, 9) + "-" + str.substring(9);
                }

                telefone.setText(str);
                telefone.setSelection(str.length());
            }




            @Override
            public void afterTextChanged(Editable s) {
                // Additional logic if needed
            }
        });
    }

    private void saveDate() {

        nomeET = nome.getText().toString();
        sobrenomeET = sobrenome.getText().toString();
        telefoneEt = telefone.getText().toString();
        datadenascimentoET = nascimento.getText().toString();
        cepET = cep.getText().toString();
        estadoET = estado.getText().toString();
        cidadeET = cidade.getText().toString();
        bairroET = bairro.getText().toString();
        ruaET = rua.getText().toString();
        numeroET = numero.getText().toString();

        if (nomeET.isEmpty() || sobrenomeET.isEmpty() || telefoneEt.isEmpty() || datadenascimentoET.isEmpty() || cepET.isEmpty() || estadoET.isEmpty() || cidadeET.isEmpty() || bairroET.isEmpty() || ruaET.isEmpty() || numeroET.isEmpty()){
            Toast.makeText(getApplicationContext(), "Complete todos os campos", Toast.LENGTH_SHORT).show();

            loadData();

            long id = dBhelper.insertContato(
                    "" + nomeET,
                    "" + sobrenomeET,
                    "" + telefoneEt,
                    "" + datadenascimentoET,
                    "" + cepET,
                    "" + estadoET,
                    "" + cidadeET,
                    "" + bairroET,
                    "" + ruaET,
                    "" + numeroET


            );







        }else {

            Toast.makeText(getApplicationContext(), "Contato Salvo", Toast.LENGTH_SHORT).show();
            loadData();

        }
    }

    private void loadData() {
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