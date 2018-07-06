package com.polygalov.jsontesttask;

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

import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.NewViewHolder> {

    private Context mContext;

    private List<ApiObject> mApiObjectList;
    private List<String> names;

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

        public TextView name;
        public TextView description;
        public LinearLayout item;

        public NewViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            item = itemView.findViewById(R.id.item);
        }
    }
}

