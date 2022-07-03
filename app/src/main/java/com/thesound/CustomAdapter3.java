package com.thesound;

import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.CustomViewHolder> {

    private ArrayList<listDTO> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView num;
        protected TextView name;
        protected TextView view;

        public CustomViewHolder(View view) {
            super(view);
            this.num = (TextView) view.findViewById(R.id.textView_num);
            this.name = (TextView) view.findViewById(R.id.textView_name);
            this.view = (TextView) view.findViewById(R.id.textView_view);
        }
    }

    public CustomAdapter3(ArrayList<listDTO> list) {
        this.mList = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item2, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.num.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        viewholder.num.setGravity(Gravity.CENTER);
        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.view.setGravity(Gravity.CENTER);

        viewholder.num.setText(mList.get(position).getNum());
        viewholder.name.setText(mList.get(position).getName());
        viewholder.view.setText(mList.get(position).getView());
        View.OnClickListener onclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), seeanswer3.class);
                intent.putExtra("key",mList.get(position).getKey());
                view.getContext().startActivity(intent);
            }
        };

        viewholder.num.setOnClickListener(onclickListener);
        viewholder.name.setOnClickListener(onclickListener);
        viewholder.view.setOnClickListener(onclickListener);

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}