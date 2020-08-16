package android.example.searchmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String poster, title, overview, average, date;
    private int id;

    public Movie() {
    }

    public Movie(String poster, String title, String overview, String average, String date, int id) {
        this.poster = poster;
        this.title = title;
        this.overview = overview;
        this.average = average;
        this.date = date;
        this.id = id;
    }

    // Parcel Read
    protected Movie(Parcel in) {
        poster = in.readString();
        title = in.readString();
        overview = in.readString();
        average = in.readString();
        date = in.readString();
        id = in.readInt();
    }

    // Parcel implementation
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Parcel Write
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(average);
        dest.writeString(date);
        dest.writeInt(id);
    }
}
