package com.example.deezerapirecyclerview.vue;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.deezerapirecyclerview.R;
import com.example.deezerapirecyclerview.modele.Playlist;
import com.squareup.picasso.Picasso;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Playlist> values;
    private OnItemClickListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView img;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            img = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Playlist item);
    }

    public void add(int position, Playlist item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)


    public ListAdapter(List<Playlist> values, OnItemClickListener listener) {
        this.values = values;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Playlist currentPlaylist = values.get(position);
        holder.txtHeader.setText(currentPlaylist.getTitle());
        Picasso.get().load(currentPlaylist.getPicture()).into(holder.img);
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentPlaylist);
            }
        });

        int hTime = currentPlaylist.getDuration()/3600;
        int mTime = (currentPlaylist.getDuration()-(hTime*3600))/60;
        int sTime = (currentPlaylist.getDuration()-(hTime*3600 + mTime*60));
        holder.txtFooter.setText("nombre morceaux : "+currentPlaylist.getNb_tracks()+" / durée : "+(hTime>0?(hTime+":"):"")+(mTime>0?(mTime+":"):"")+sTime);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
