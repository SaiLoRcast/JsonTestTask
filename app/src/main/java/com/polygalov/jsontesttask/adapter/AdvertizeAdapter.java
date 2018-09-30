package com.polygalov.jsontesttask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polygalov.jsontesttask.R;
import com.polygalov.jsontesttask.model.Advertize;

import java.util.List;

public class AdvertizeAdapter extends RecyclerView.Adapter<AdvertizeAdapter.AdvertizeViewHolder> {

    private Context mContext;
    List<Advertize> mAdvertizeList;

    public AdvertizeAdapter(Context context, List<Advertize> advertizeList) {
        mContext = context;
        mAdvertizeList = advertizeList;
    }

    @NonNull
    @Override
    public AdvertizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advertize, parent, false);
        return new AdvertizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertizeViewHolder holder, int position) {

        final Advertize advertize = mAdvertizeList.get(position);

        holder.id.setText(advertize.getId());
        holder.name.setText(advertize.getName());
        holder.comment.setText(advertize.getComment());

    }

    @Override
    public int getItemCount() {
        return mAdvertizeList.size();
    }

    public class AdvertizeViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        TextView comment;

        public AdvertizeViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.entrance_number);
            name = itemView.findViewById(R.id.entrance_name);
            comment = itemView.findViewById(R.id.entrance_comment);
        }
    }
}
