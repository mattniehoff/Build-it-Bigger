package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.mattniehoff.jokeui.JokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<MainActivityFragment, Void, String> {
    private static final String TAG = EndpointsAsyncTask.class.getName();
    private static final String LOCALHOST_IP = "http://10.0.2.2:8080/_ah/api/";
    private static MyApi myApiService = null;

    //private Context context;
    private MainActivityFragment fragment;

    @Override
    protected String doInBackground(MainActivityFragment... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(LOCALHOST_IP)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        fragment = params[0];
        //String name = params[0].second;

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(fragment.getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_EXTRA, result);
        fragment.getActivity().startActivity(intent);
    }
}
