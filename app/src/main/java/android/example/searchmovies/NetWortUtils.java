package android.example.searchmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetWortUtils {

    final static String URL = "https://api.themoviedb.org/3/movie/";
    final static String PARAM_API_KEY = "api_key";
    final static String api_key = "Write Your API_Key here";
    final static String PARAM_LANGUAGE = "language";
    final static String language = "en-US";

    public static URL buildUrl(String movieSearchQuery) {
        Uri buildUri = Uri.parse(URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, api_key)
                .appendQueryParameter(PARAM_LANGUAGE, language)
                .appendEncodedPath(movieSearchQuery)
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

