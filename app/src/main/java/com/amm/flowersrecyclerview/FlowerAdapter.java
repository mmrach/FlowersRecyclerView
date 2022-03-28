package com.amm.flowersrecyclerview;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {
    private LayoutInflater mInflater;
    private List<String> flowers;

    // Referencia a la default ViewModelFactory de la App, a usar cuando el ViewModel no recibe parámetros y se usa su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;

    // Declaramos una referencia para el ViewModel de SharedViewModel.
    private SharedViewModel sharedViewModel;


    public FlowerAdapter(Context context, List<String> flowers) {
        this.mInflater = LayoutInflater.from(context);
        this.flowers = flowers;
        // Sin Factory, cogiendo la Factory del objeto Application
        theAppFactory = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) context.getApplicationContext());
        sharedViewModel = new ViewModelProvider((ViewModelStoreOwner) context, (ViewModelProvider.Factory) theAppFactory).get(SharedViewModel.class);
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.flower_item, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        holder.bind(flowers.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return this.flowers.size();
    }

    //------------------------------------------------------------------------
    class FlowerViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlower;

        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlower = itemView.findViewById(R.id.tvFlower);

            //El onItemClick lo gestionamos aquí.
            //Modificamos el sharedViewModel, no propagamos el evento onItemClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer position = getLayoutPosition();
                    String flower = flowers.get(position);
                    sharedViewModel.setSharedData(flower);
                }
            });
        }

        public void bind(String string){
            tvFlower.setText(string);
        }
    }
    //------------------------------------------------------------------------
}
