package org.izv.dam.roomdatabase.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.dam.roomdatabase.R;
import org.izv.dam.roomdatabase.model.data.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemViewHolder> {

    private LayoutInflater inflater;
    private List<Category> itemsList;

    public CategoryAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (itemsList != null) {
            Category current = itemsList.get(position);
            holder.tvItem.setText(current.toString());
        }
    }

    @Override
    public int getItemCount() {
        int elements = 0;
        if(itemsList != null) {
            elements = itemsList.size();
        }
        return elements;
    }

    public void setCategories(List<Category> list) {
        this.itemsList = list;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
}