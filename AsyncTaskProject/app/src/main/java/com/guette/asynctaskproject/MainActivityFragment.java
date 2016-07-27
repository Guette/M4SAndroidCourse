package com.guette.asynctaskproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button button;
    ImageView imageView;
    View rootView;
    String image_url="https://raw.githubusercontent.com/Guette/M4SAndroidCourse/master/ThiesMaVille.png";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         rootView = inflater.inflate(R.layout.fragment_main, container, false);

        button = (Button) rootView.findViewById(R.id.button1);
        imageView=(ImageView) rootView.findViewById(R.id.imageView1) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ImageDownloader downloader= new ImageDownloader();
               downloader.execute(image_url);
            }
        });
        return rootView;
    }

    /**
     * Created by MAGUETTE on 12/07/2016.
     */
     class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            Log.i("AsyncTaskProject", "onPreExecute Called");
            ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(getActivity(), " Connected ", Toast.LENGTH_LONG).show();
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Download in progress...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.setProgress(0);
                progressDialog.show();
            } else
                startActivity(new Intent(getActivity(), MainActivity.class));
                Toast.makeText(getActivity(), " Connect to Internet first", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.i("AsyncTaskProject", "doInBackground Called");
            return downloadImage(params[0]);
        }

        public Bitmap downloadImage(String params) {
                try {
                    URL url = new URL(params);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoInput(true);
                    con.connect();
                    if (con.getResponseCode() != 200) {
                        throw new Exception("Failed to connect");
                    }
                    InputStream is = con.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Image", "Failed to load image", e);
                    Log.e("error", e.getMessage());
                    return null;
                }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Log.i("AsyncTaskProject", "onPostExecute Called");
            if(imageView != null) {
                ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView1);
                imageView.setImageBitmap(result);
                Toast.makeText(getActivity(), "Download complete", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getActivity(), "Image Does Not exist or Network Error", Toast.LENGTH_LONG).show();
            }
            progressDialog.hide();
        }
    }

}
