package co.gamespay.www.androidrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://api.github.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Success Listener
                Log.d("CODE", response);
                //Creating GSON object using class GsonBuilder
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                //Passing responce using gson method and defining user array type responce
                User[] users = gson.fromJson(response, User[].class);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Error Listener
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        ////Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        //Adding request to queue
        queue.add(request);

        //RecyclerView programmingList  = findViewById(R.id.programmingList);
        //programmingList.setLayoutManager(new LinearLayoutManager(this));
        //String[] language = {"Java" , "PHP", "Python", "Swift", "Node.js", "Angular.js"};
        //programmingList.setAdapter(new ProgrammingAdapter(language));

    }
}
