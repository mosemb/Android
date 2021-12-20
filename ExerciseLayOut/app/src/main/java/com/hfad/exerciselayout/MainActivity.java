package com.hfad.exerciselayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.exerciselayout.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    // TODO (26) Create an EditText variable called mSearchBoxEditText
    EditText mSearchBoxEditText;
    // TODO (27) Create a TextView variable called mUrlDisplayTextView
    TextView mUrlDisplayTextView;
    // TODO (28) Create a TextView variable called mSearchResultsTextView
    TextView mSearchResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO (29) Use findViewById to get a reference to mSearchBoxEditText
        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);



        // TODO (30) Use findViewById to get a reference to mUrlDisplayTextView
        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);

        // TODO (31) Use findViewById to get a reference to mSearchResultsTextView
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_seach_results_json);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // creating a menu
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }



    public void makeGithubSearchQuery(){
        String githubQuery = mSearchBoxEditText.getText().toString();
        //NetworkUtils test = new NetworkUtils();
        URL url = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(url.toString());

        // TODO (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
        new GithubQueryTask().execute(url);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // making a Toast
        int id = item.getItemId();
        if(id==R.id.action_search){
            // TODO (4) Remove the Toast message when the search menu item is clicked

            Context context = MainActivity.this;
            String message = "Click was clicked";
            Toast.makeText(context,message,Toast.LENGTH_LONG).show();
            // TODO (5) Call makeGithubSearchQuery when the search menu item is clicked
            makeGithubSearchQuery();
        }

        return super.onOptionsItemSelected(item);

    }

    public class GithubQueryTask extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String urlSearchResults = null;

            try {
                urlSearchResults = NetworkUtils.getResponseFromHttpUrl(url);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return urlSearchResults;
        }

        @Override
        protected void onPostExecute(String s){
            // TODO (3) Override onPostExecute to display the results in the TextView
            if(s!=null){
                mSearchResultsTextView.setText(s);
            }

        }
    }




}