package com.example.navegacaotelas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.navegacaotelas.transactions.Constants;
import com.example.navegacaotelas.transactions.Jogador;
import com.example.navegacaotelas.transactions.Jogador;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int selected;
    ArrayList<Jogador> listaJogadores;
    ArrayAdapter adapter;
    ListView listViewJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaJogadores = new ArrayList<Jogador>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaJogadores);

        listViewJogadores = findViewById(R.id.listViewJogadores);
        listViewJogadores.setAdapter(adapter);
        listViewJogadores.setSelector(R.color.purple_700);

        listViewJogadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                clicarAdicionar();
                break;
            case R.id.edit:
                clicarEditar();
                break;
            case R.id.delete:
                removerItemLista();
                break;
            case R.id.settings:
                break;
            case R.id.about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void removerItemLista(){
        if (selected >= 0){
            listaJogadores.remove(selected);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this, "Selecione um jogador para remover do seu time", Toast.LENGTH_SHORT).show();
        }
    }

    public void clicarAdicionar(){
        Intent contact = new Intent(this, MovieActivity.class);
        startActivityForResult(contact, Constants.REQUEST_ADD);
    }

    public void clicarEditar(){
        Intent contact = new Intent(this, MovieActivity.class);

        if (selected >= 0){
            Jogador jogador = listaJogadores.get(selected);

            contact.putExtra("nome", jogador.getNome());
            contact.putExtra("time", jogador.getTimeAtual());
            contact.putExtra("numero", jogador.getNumero());
            contact.putExtra("nacionalidade", jogador.getNacionalidade());
            contact.putExtra("id", jogador.getId());

            startActivityForResult(contact, Constants.REQUEST_EDIT);

        }else{
            Toast.makeText(MainActivity.this, "Selecione um jogador", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD){
            String nome = (String) data.getExtras().get("nome");
            String timeAtual = (String) data.getExtras().get("time");
            String numero = (String) data.getExtras().get("numero");
            String nacionalidade = (String) data.getExtras().get("nacionalidade");

            Jogador jogador = new Jogador(nome, timeAtual, numero, nacionalidade);

            listaJogadores.add(jogador);
            adapter.notifyDataSetChanged();
        }else if (requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD){

            String nome = (String) data.getExtras().get("nome");
            String timeAtual = (String) data.getExtras().get("time");
            String numero = (String) data.getExtras().get("numero");
            String nacionalidade = (String) data.getExtras().get("nacionalidade");
            int idEditar = (int) data.getExtras().get("id");

            for(Jogador jogador: listaJogadores){
                if(jogador.getId() == idEditar){
                    jogador.setNome(nome);
                    jogador.setTimeAtual(timeAtual);
                    jogador.setNumero(numero);
                    jogador.setNacionalidade(nacionalidade);
                }
            }

            adapter.notifyDataSetChanged();
        }else if(resultCode == Constants.RESULT_CANCEL){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        }
    }
}