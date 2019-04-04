package com.example.materialtest;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.List;


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mcontext;
    private List<City> mCityList;
    static class  ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitname;
        TextView detail;
        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            fruitImage=view.findViewById(R.id.fruit_image);
            fruitname=view.findViewById(R.id.fruit_name);
            detail=view.findViewById(R.id.detail);
        }
    }
    public FruitAdapter(List<City> cityList){
        this.mCityList = cityList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mcontext==null){
            mcontext=viewGroup.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fruit_item,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                City city = mCityList.get(position);
                Toast.makeText(v.getContext(), city.getName(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(v.getContext(),Detail.class);
                intent.putExtra("name", city.getName());
                intent.putExtra("http", city.getHttp());
                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        City city = mCityList.get(i);
        viewHolder.fruitname.setText(city.getName());
        viewHolder.detail.setText(city.getDetail());
        Glide.with(mcontext).load(city.getImageId()).into(viewHolder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }
}
