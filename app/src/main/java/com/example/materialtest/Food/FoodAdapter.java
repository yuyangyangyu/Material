package com.example.materialtest.Food;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder_1> {
    private Context mcontext;
    private List<Food> mlist;

    class ViewHolder_1 extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView detail;
        TextView pro;

        public ViewHolder_1(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.food_img);
            name=itemView.findViewById(R.id.food_name);
            detail=itemView.findViewById(R.id.food_detail);
            pro=itemView.findViewById(R.id.food_pro);
        }
    }
    public FoodAdapter(List<Food> mlist){
        this.mlist=mlist;
    }

    @NonNull
    @Override
    public ViewHolder_1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mcontext==null){
            mcontext=viewGroup.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.food_item,viewGroup,false);
        ViewHolder_1 viewHolder_1=new ViewHolder_1(view);
        return viewHolder_1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_1 viewHolder_1, int i) {
        Food food=mlist.get(i);
        viewHolder_1.name.setText(food.getName());
        viewHolder_1.detail.setText(food.getDetail());
        viewHolder_1.pro.setText(food.getPro());
        Glide.with(mcontext).load(food.getImg()).into(viewHolder_1.img);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
