package com.amm.flowersrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.amm.flowersrecyclerview.DataSource;
import com.amm.flowersrecyclerview.FlowerAdapter;
import com.amm.flowersrecyclerview.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFlowers;
    private TextView tvFlowerName;
    private FlowerAdapter rvAdapter;

    // Referencia a la default Factory de la App, a usar cuando el ViewModel no recibe parámetros y usando su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;

    // Declaramos una referencia para el ViewModel de SharedViewMoel.
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sin Factory, cogiendo la Factory del objeto App.
        theAppFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        sharedViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) theAppFactory).get(SharedViewModel.class);

        List<String> flowerList = new DataSource().getFlowerList(this);

        tvFlowerName = (TextView) findViewById(R.id.tvFlowerName);
        rvFlowers = (RecyclerView) findViewById(R.id.rvFlowers);
        rvAdapter = new FlowerAdapter(this, flowerList);
        rvFlowers.setAdapter(rvAdapter);

        //Declaramos el observer
        sharedViewModel.getSharedData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvFlowerName.setText(s);
            }
        });
    }
}