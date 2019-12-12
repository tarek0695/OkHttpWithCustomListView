package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);



        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://api.piyasbiswas.com/apptesting/V1/catslist.php").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String data = response.body().string();
                Log.e("data",data);

                ArrayList<Model> model = new ArrayList<>();
                Model model1;

                try {

                    JSONArray jsonArray = new JSONArray(data);
                    for (int i=0; i<jsonArray.length();i++){
                        model1 = new Model(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")),
                                jsonArray.getJSONObject(i).getString("name"),
                                jsonArray.getJSONObject(i).getString("types"),
                                jsonArray.getJSONObject(i).getString("colorofeyes"),
                                jsonArray.getJSONObject(i).getString("image"));

                        model.add(model1);
                        Log.e("data2", model1.getName());
                    }


                } catch (Exception e){
                        e.printStackTrace();
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CustomAdapter adapter = new CustomAdapter(MainActivity.this, model);
                        listView.setAdapter(adapter);
                    }
                });

            }
        });

    }
}
