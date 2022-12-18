package com.example.tugas6;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity4 extends AppCompatActivity {
    ListView pokemonListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        pokemonListView = findViewById(R.id.pokemonListView);

        getPokemons();
    }

    private void getPokemons() {
        Call<PokemonPojo> call = RetrofitClient.getInstance().getMyApi().getPokemons();
        call.enqueue(new Callback<PokemonPojo>() {
            @Override
            public void onResponse(Call<PokemonPojo> call, Response<PokemonPojo> response) {
                PokemonPojo pokemonData = response.body();
                ArrayList<ResultsItem> data = pokemonData.getResults();
                String[] onePokemon = new String[data.size()];

                for (int i = 0; i < data.size(); i++) {
                    onePokemon[i] = data.get(i).getName();
                }

                pokemonListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, onePokemon));
            }

            @Override
            public void onFailure(Call<PokemonPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}
