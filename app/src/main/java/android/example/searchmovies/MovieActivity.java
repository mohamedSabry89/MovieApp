package android.example.searchmovies;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    TextView tvTitle, tvOverview, tvDate, tvRate;
    ImageView imPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_details);

        tvTitle = findViewById(R.id.tv_title);
        tvOverview = findViewById(R.id.tv_overview);
        tvDate = findViewById(R.id.tv_date);
        tvRate = findViewById(R.id.tv_rate);
        imPoster = findViewById(R.id.imageView);

        Intent intent = getIntent();
        Movie theMovie = intent.getParcelableExtra("get_data");

        assert theMovie != null;
        String title = theMovie.getTitle();
        String overView = theMovie.getOverview();
        String date = theMovie.getDate();
        String rate = theMovie.getAverage();
        String moviePoster = theMovie.getPoster();

        Picasso.get().load(moviePoster).fit().centerInside().into(imPoster);
        tvTitle.setText(title);
        tvDate.setText(date);
        tvRate.setText(rate);
        tvOverview.setText(overView);

    }
}