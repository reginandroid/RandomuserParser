package com.example.buh.randomuserparser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Buh on 22.09.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    private List<Item> itemList;

    Context cnt;

Context context;
    public Adapter(Context context, List<Item> list) {
        this.itemList = list;
        this.cnt=context;

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);


    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.card_view_item, viewGroup, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int i) {
        Item item = itemList.get(i);
        holder.vName.setText(item.getName());
        holder.vSurname.setText(item.getSurname());
        Picasso.with(cnt).load(item.getImage()).into(holder.vImage);

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder  {

        protected TextView vName;
        protected TextView vSurname;
        //        protected TextView vEmail;
        protected ImageView vImage;
//        protected TextView vLocation;
//        protected TextView vGender;
//        protected TextView vNat;
//        protected TextView vPhone;

        public ItemViewHolder(View v) {
            super(v);


            vName = (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView) v.findViewById(R.id.txtSurname);
//            vEmail = (TextView) v.findViewById(R.id.email);
            vImage = (ImageView) v.findViewById(R.id.picture);
//            vLocation = (TextView)v.findViewById(R.id.location);
//            vGender = (TextView) v.findViewById(R.id.gender);
//            vNat = (TextView)v.findViewById(R.id.nat);
//            vPhone = (TextView)v.findViewById(R.id.phone);

        }


    }


}