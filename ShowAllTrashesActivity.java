package com.example.administrator.tabzzf;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class ShowAllTrashesActivity extends AppCompatActivity {

    ListView TrashListView;
    ProgressBar progressBar;
    String HttpUrl = "http://lowcarbeconomy.eu/trashtrackerapp/select_all_trashes.php";
    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all_trashes);

        TrashListView = (ListView) findViewById(R.id.listview1);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new GetHttpResponse(ShowAllTrashesActivity.this).execute();

        //Adding ListView Item click Listener.
        TrashListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(ShowAllTrashesActivity.this, ShowSingleRecordActivity.class);

                // Sending ListView clicked value using intent.
                intent.putExtra("ListViewValue", IdList.get(position).toString());
                Toast.makeText(ShowAllTrashesActivity.this, IdList.get(position).toString(), Toast.LENGTH_LONG).show();
                startActivity(intent);

                //Finishing current activity after open next activity.
                finish();

            }
        });
    }

    // JSON parse class started from here.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;
        String JSonResult;

        List<Trash> trashList;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Passing HTTP URL to HttpServicesClass Class.
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try {
                httpServicesClass.ExecutePostRequest();
                if (httpServicesClass.getResponseCode() == 200) {

                    JSonResult = httpServicesClass.getResponse();

                    if (JSonResult != null) {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            Trash trash;

                            trashList = new ArrayList<Trash>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                trash = new Trash();

                                jsonObject = jsonArray.getJSONObject(i);


                                // Adding Trash Id TO IdList Array.
                                IdList.add(jsonObject.getString("idTrash").toString());
//Adding Trash Name.
                                trash.TrashId = jsonObject.getString("idTrash").toString();
                                //Adding Trash Name.
                                trash.TrashName = jsonObject.getString("Name").toString();
                                //Adding Trash Latitude.
                                trash.TrashLatitude = jsonObject.getString("Latitude").toString();
                                //Adding Trash Longitude.
                                trash.TrashLongitude = jsonObject.getString("Longitude").toString();
                                //Adding Trash Description.
                                trash.TrashDescription = jsonObject.getString("Description").toString();
                                //Adding Trash IsCleaned.
                                trash.TrashIsCleaned = jsonObject.getString("IsCleaned").toString();
                                //Adding Trash Size.
                                trash.TrashSize = jsonObject.getString("Size_idSize").toString();

                                trashList.add(trash);

                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.GONE);

            TrashListView.setVisibility(View.VISIBLE);

            ListAdapterClass adapter = new ListAdapterClass(trashList, context);

            TrashListView.setAdapter(adapter);


        }
    }
}
