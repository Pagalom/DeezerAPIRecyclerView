package com.example.deezerapirecyclerview.vue;

import java.util.List;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.deezerapirecyclerview.R;
import com.example.deezerapirecyclerview.modele.Music;
import com.squareup.picasso.Picasso;

public class ListAdapterPlaylist extends RecyclerView.Adapter<ListAdapterPlaylist.ViewHolder> {
    private OnItemClickListener listener;
    private List<Music> values;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        public ImageView img;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            img = (ImageView) v.findViewById(R.id.icon2);
            img.setColorFilter(Color.GRAY, PorterDuff.Mode.DARKEN);;
        }
    }

    public void add(int position, Music item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapterPlaylist(List<Music> myDataset, OnItemClickListener listener) {
        values = myDataset;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapterPlaylist.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.playlist_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public interface OnItemClickListener {
        void onItemClick(Music item);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Music currentMusic = values.get(position);
        //Log.i("MyLog",currentMusic.getTitle()+"/"+currentMusic.getId());
        holder.txtHeader.setText(currentMusic.getTitle());
        Picasso.get().load(currentMusic.getAlbum().getCover()).into(holder.img);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentMusic);
            }
        });

        holder.txtFooter.setText(currentMusic.getAlbum().getTitle()+" by "+currentMusic.getArtist().getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
