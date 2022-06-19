package com.etheur.rxproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.etheur.rxproject.R;
import com.etheur.rxproject.models.CardItem;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.ViewHolder> {

    private final List<CardItem> items;
    private final OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onItemClick(AppCompatActivity activity);
    }

    public HomeCardAdapter(List<CardItem> items, OnItemClickListener clickListener){
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_components,
                null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCardAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView tvTitle;
        private final MaterialTextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }

        public void bind(final CardItem item, final OnItemClickListener clickListener){
            tvTitle.setText(item.getTitle());
            tvContent.setText(item.getContent());
            itemView.setOnClickListener(v -> clickListener.onItemClick(item.getActivity()));
        }
    }
}
