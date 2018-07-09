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

import com.polygalov.jsontesttask.R;
import com.polygalov.jsontesttask.activity.DetailsActivity;
import com.polygalov.jsontesttask.api.ApiObject;

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    private Context mContext;

    private List<ApiObject> mApiObjectList;

    public NewAdapter(Context context, List<ApiObject> apiObjectList) {
        mContext = context;
        mApiObjectList = apiObjectList;
    }

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new NewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewViewHolder holder, final int position) {
        final ApiObject apiObject = mApiObjectList.get(position);
        holder.name.setText(apiObject.getFirstName());
        holder.description.setText(apiObject.getDescription());

        for (int i = 1; i < mApiObjectList.size(); i++) {
            holder.number.setText(Integer.toString(position + 1));
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Нажал на " + apiObject.getFirstName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("name", holder.name.getText());
                intent.putExtra("description", holder.description.getText());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mApiObjectList.size();
    }

    public void delete() {
        mApiObjectList.clear();
        notifyDataSetChanged();
    }

    public class NewViewHolder extends RecyclerView.ViewHolder {

        public TextView number;
        public TextView name;
        public TextView description;
        public RelativeLayout item;

        public NewViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            item = itemView.findViewById(R.id.item);
            number = itemView.findViewById(R.id.number);
        }
    }
}

