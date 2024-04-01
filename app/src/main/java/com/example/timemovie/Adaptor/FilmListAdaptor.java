package com.example.timemovie.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.timemovie.Activity.DetailActivity;
import com.example.timemovie.Domain.ListFilm;
import com.example.timemovie.Domain.Filmitem;
import com.example.timemovie.R;

public class FilmListAdaptor extends RecyclerView.Adapter<FilmListAdaptor.ViewHolder> {
    ListFilm item;
    Context context;

    public FilmListAdaptor(ListFilm item) {
        this.item = item;
    }

    @NonNull
    @Override
    public FilmListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdaptor.ViewHolder holder, int position) {
holder.titleTxt.setText(item.getData().get(position).getTitle());
holder.ScoreTxt.setText(""+item.getData().get(position).getImdbRating());

        Glide.with(holder.itemView.getContext())
                .load(item.getData().get(position).getPoster())
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id",item.getData().get(position).getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return item.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt,ScoreTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            ScoreTxt=itemView.findViewById(R.id.scoreTxt);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
