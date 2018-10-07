package com.polygalov.jsontesttask.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.polygalov.jsontesttask.OnItemClickListener;
import com.polygalov.jsontesttask.R;
import com.polygalov.jsontesttask.model.Advertize;

import java.util.List;

public class AdvertizeAdapter extends RecyclerView.Adapter<AdvertizeAdapter.AdvertizeViewHolder> {

    private final String TAG = "Advertize_Adapter";

    private Context mContext;
    List<Advertize> mAdvertizeList;
    private OnItemClickListener mOnItemClickListener;


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
    public void onBindViewHolder(@NonNull AdvertizeViewHolder holder, final int position) {
        holder.position = position;

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
        ImageView plus, minus;

        public int position = 0;

        public AdvertizeViewHolder(View itemView) {
            super(itemView);

            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);

            id = itemView.findViewById(R.id.entrance_number);
            name = itemView.findViewById(R.id.entrance_name);
            comment = itemView.findViewById(R.id.entrance_comment);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(mContext.getApplicationContext());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_edit);

                    Toast.makeText(mContext.getApplicationContext(), "pos" + position, Toast.LENGTH_SHORT).show();

                    //Bind dialog views
                    final Button plusButton = (Button) dialog.findViewById(R.id.plus_button);
                    final Button minusButton = (Button) dialog.findViewById(R.id.minus_button);

                    plusButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

//                            mAdvertizeList.get(position).setComment("Plus");

                            minus.setVisibility(View.INVISIBLE);
                            plus.setVisibility(View.VISIBLE);
                            notifyItemChanged(position, minus);
                            Log.d(TAG, "position: " + position + " , ");
//                            notifyDataSetChanged();

                            //Close dialog
                            dialog.dismiss();
                        }
                    });
                    minusButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            mAdvertizeList.get(position).setComment("Minus");

                            plus.setVisibility(View.INVISIBLE);
                            minus.setVisibility(View.VISIBLE);

//                            plus.invalidate();
                            notifyItemChanged(position, plus);
                            Log.d(TAG, "position: " + position + " , ");
//                            notifyDataSetChanged();

                            //Close dialog
                            dialog.dismiss();
                        }
                    });
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.show();

                }
            });


        }
    }

}
