package com.example.navegacaotelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.navegacaotelas.transactions.Constants;

public class MovieActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextTimeAtual;
    EditText editTextNumero;
    EditText editTextNacionalidade;

    boolean edit;
    int idContatoEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        editTextNome = findViewById(R.id.editTextNome);
        editTextTimeAtual = findViewById(R.id.editTextTimeAtual);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextNacionalidade = findViewById(R.id.editTextNacionalidade);

        edit = false;

        if (getIntent().getExtras() != null){
            String nome = (String) getIntent().getExtras().get("nome");
            String categoria = (String) getIntent().getExtras().get("categoria");
            String anoLancamento = (String) getIntent().getExtras().get("anoLancamento");
            String diretor = (String) getIntent().getExtras().get("diretor");
            idContatoEditar = (int) getIntent().getExtras().get("id");

            editTextNome.setText(nome);
            editTextTimeAtual.setText(categoria);
            editTextNumero.setText(anoLancamento);
            editTextNacionalidade.setText(diretor);

            edit = true;
        }
    }

    public void adicionar(View view){
        Intent intent = new Intent();

        String nome = editTextNome.getText().toString();
        intent.putExtra("nome", nome);

        String timeAtual = editTextTimeAtual.getText().toString();
        intent.putExtra("time", timeAtual);

        String numero = editTextNumero.getText().toString();
        intent.putExtra("numero", numero);

        String nacionalidade = editTextNacionalidade.getText().toString();
        intent.putExtra("nacionalidade", nacionalidade);

        if (edit) intent.putExtra("id", idContatoEditar);

        setResult(Constants.RESULT_ADD, intent);

        finish();
    }

    public void cancelar(View view){
        setResult(Constants.RESULT_CANCEL);
        finish();
    }
}