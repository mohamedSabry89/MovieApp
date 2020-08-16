package android.example.searchmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder> {

    private Movie[] movies;
    Context context;

    public MoviesRecyclerAdapter(Context context, Movie[] movies) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRecyclerAdapter.ViewHolder holder, int position) {

        final Movie theMovie = movies[position];
        String imgUrl = theMovie.getPoster();
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieActivity.class);
                intent.putExtra("get_data", theMovie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.movies_img);
        }
    }
}
