package com.borg.vocab_app_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements View.OnClickListener {

    private LayoutInflater inflater;
    private ArrayList<WordItem> items = new ArrayList<>();

    CustomAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setItems(List<WordItem> newItems) {
        items.clear();
        items.addAll(newItems);
        items.trimToSize();
        notifyDataSetChanged();
    }

    public List<WordItem> getItems() {
        return items;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View view) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView tv;
        private  ImageButton imgBtn;
        private  CheckBox checkBoxAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBoxAdapter = itemView.findViewById(R.id.rv_item_checkbox);
            tv = itemView.findViewById(R.id.rv_item_tv);
            imgBtn = itemView.findViewById(R.id.ic_rv_item_pen);

            itemView.setOnClickListener(CustomAdapter.this);
        }

        public void bind(WordItem item) {
            tv.setText(item.getName());
            checkBoxAdapter.setChecked(item.getIsSelected());
            checkBoxAdapter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isSelected) {
                    item.setSelected(isSelected);
                }
            });
        }
    }
}