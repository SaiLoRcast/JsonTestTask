package com.polygalov.jsontesttask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.polygalov.jsontesttask.EntranceActivity;
import com.polygalov.jsontesttask.R;
import com.polygalov.jsontesttask.model.House;

import java.io.Serializable;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    private Context mContext;

    private List<House> mHouseList;

    public NewAdapter(Context context, List<House> houseList) {
        mContext = context;
        mHouseList = houseList;
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewViewHolder holder, final int position) {

        final House house = mHouseList.get(position);
        holder.address.setText(house.getAddress());
        holder.number.setText(String.valueOf(position + 1));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Нажал на " + house.getAddress() + ", ID: " + house.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, EntranceActivity.class);
                intent.putExtra("address", holder.address.getText());

                intent.putExtra("addressName", house.getAddress());
                intent.putExtra("houseID", house.getId());
                intent.putExtra("entrances", (Serializable) house.getEntrances());

//                intent.putExtra("description", holder.description.getText());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {

        public TextView number;
        public TextView address;
        public TextView description;
        public RelativeLayout item;

        public NewViewHolder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            item = itemView.findViewById(R.id.item);
            number = itemView.findViewById(R.id.number);
        }
    }
}

