package com.polygalov.jsontesttask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.polygalov.jsontesttask.R;
import com.polygalov.jsontesttask.activity.AdvertizeActivity;
import com.polygalov.jsontesttask.model.Entrance;

import java.io.Serializable;
import java.util.List;

public class EntrancesAdapter extends RecyclerView.Adapter<EntrancesAdapter.EntranceViewHolder> {

    private Context mContext;
    List<Entrance> mEntranceList;

    public EntrancesAdapter(Context context, List<Entrance> entranceList) {
        mContext = context;
        mEntranceList = entranceList;
    }

    @NonNull
    @Override
    public EntranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrance, parent, false);
        return new EntranceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntranceViewHolder holder, int position) {

        final Entrance entrance = mEntranceList.get(position);

        holder.mTextView.setText(entrance.getNumber() + " Парадная, ");

        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Нажал на " + entrance.getNumber() , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, AdvertizeActivity.class);
//                intent.putExtra("address", holder.address.getText());

                intent.putExtra("entranceNumber", entrance.getNumber());
                intent.putExtra("advertizes", (Serializable) entrance.getAdvertizes());

//                intent.putExtra("description", holder.description.getText());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEntranceList.size();
    }

    public class EntranceViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        LinearLayout mLinearLayout;

        public EntranceViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.entrance_number);
            mLinearLayout = itemView.findViewById(R.id.item_antrance);
        }
    }
}
