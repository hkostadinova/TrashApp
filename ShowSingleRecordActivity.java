package com.example.administrator.tabzzf;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.jar.Attributes;

public class ShowSingleRecordActivity extends AppCompatActivity {

    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter Trash Data from Id Sent from previous activity.
    String HttpURL = "http://lowcarbeconomy.eu/trashtrackerapp/select_trash.php";

    // Http URL for delete Already Open Trash Record.
    String HttpUrlDeleteRecord = "https://androidjsonblog.000webhostapp.com/Trash/DeleteTrash.php";

    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    String ParseResult;
    HashMap<String, String> ResultHash = new HashMap<>();
    String FinalJSonObject;
    TextView Name, Latitude, Longitude, Description, IsCleaned, Size;
    String nameHolder, latitudeHolder, longitudeHolder, isCleanedHolder, descriptionHolder, sizeHolder;
    Button UpdateButton, DeleteButton;
    String TempItem;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        Name = (TextView) findViewById(R.id.textView2);
        Latitude = (TextView) findViewById(R.id.textView4);
        Longitude = (TextView) findViewById(R.id.textView6);
        Description = (TextView) findViewById(R.id.textView8);
        IsCleaned = (TextView) findViewById(R.id.textView10);
        Size = (TextView) findViewById(R.id.textView12);

        UpdateButton = (Button) findViewById(R.id.buttonUpdate);
        DeleteButton = (Button) findViewById(R.id.buttonDelete);

        //Receiving the ListView Clicked item value send by previous activity.
        TempItem = getIntent().getStringExtra("ListViewValue");

        //Calling method to filter Trash Record and open selected record.
        HttpWebCall(TempItem);


        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowSingleRecordActivity.this, UpdateActivity.class);

                // Sending Trash Id, Name, Latitude, Longitude, Description, isCleaned and Size to next UpdateActivity.
                intent.putExtra("idTrash", TempItem);
                intent.putExtra("Name", nameHolder);
                intent.putExtra("Latitude", latitudeHolder);
                intent.putExtra("Longitude", longitudeHolder);
                intent.putExtra("Description", descriptionHolder);
                intent.putExtra("IsCleaned", isCleanedHolder);
                intent.putExtra("Size_idSize", sizeHolder);

                startActivity(intent);

                // Finishing current activity after opening next activity.
                finish();

            }
        });

        // Add Click listener on Delete button.
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling Trash delete method to delete current record using Trash ID.
                TrashDelete(TempItem);

            }
        });

    }

    // Method to Delete Trash Record
    public void TrashDelete(final String TrashID) {

        class TrashDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog2 = ProgressDialog.show(ShowSingleRecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog2.dismiss();

                Toast.makeText(ShowSingleRecordActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

                finish();

            }

            @Override
            protected String doInBackground(String... params) {

                // Sending TRASH id.
                hashMap.put("idTrash", params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);

                return finalResult;
            }
        }

        TrashDeleteClass trashDeleteClass = new TrashDeleteClass();

        trashDeleteClass.execute(TrashID);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem) {

        class HttpWebCallFunction extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(ShowSingleRecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(ShowSingleRecordActivity.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("idTrash", params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                if (FinalJSonObject != null) {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing Trash Name, Latitude, Longitude, Description, isCleaned, Size into Variables.
                            nameHolder = jsonObject.getString("Name").toString();
                            latitudeHolder = jsonObject.getString("Latitude").toString();
                            longitudeHolder = jsonObject.getString("Longitude").toString();
                            descriptionHolder = jsonObject.getString("Description").toString();
                            isCleanedHolder = jsonObject.getString("IsCleaned").toString();
                            sizeHolder = jsonObject.getString("Size_idSize").toString();
                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // Setting Trash Name, Phone Number, Class into TextView after done all process .
            Name.setText(nameHolder);
            Latitude.setText(latitudeHolder);
            Longitude.setText(longitudeHolder);
            Description.setText(descriptionHolder);
            IsCleaned.setText(isCleanedHolder);
            Size.setText(sizeHolder);
        }
    }

}
