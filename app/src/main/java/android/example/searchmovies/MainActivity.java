package android.example.searchmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public Movie[] movie = new Movie[0];
    private RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager rvLayout;
    public ImageView imageView;
    public Context context;
    public MovieQueryTask task;
    public String sortBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = new MovieQueryTask();
        task.execute("popular");

        recyclerView = findViewById(R.id.recycler_view);
        imageView = findViewById(R.id.movies_img);

        rvLayout = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(rvLayout);

        adapter = new MoviesRecyclerAdapter(context, movie);
        recyclerView.setAdapter(adapter);

    }

    @SuppressLint("StaticFieldLeak")
    public class MovieQueryTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String urlSearchResult = null;
            try {
                URL searchUrl = NetWortUtils.buildUrl(urls[0]);
                urlSearchResult = NetWortUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return urlSearchResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Movie[] movie = JsonUtils.parseMovieJson(MainActivity.this, s);
                new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(rvLayout);
                adapter = new MoviesRecyclerAdapter(MainActivity.this, movie);
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if (itemSelected == R.id.popular) {
            sortBy = "popular";
            task.cancel(true);
            task = new MovieQueryTask();
            task.execute(sortBy);
            Toast.makeText(this, "Searching By Popular", Toast.LENGTH_SHORT).show();
        } else {
            sortBy = "top_rated";
            task.cancel(true);
            task = new MovieQueryTask();
            task.execute(sortBy);
            Toast.makeText(this, "Searching By Best Rating", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
