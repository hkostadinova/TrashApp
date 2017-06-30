package com.example.administrator.tabzzf;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    String HttpURL = "http://lowcarbeconomy.eu/trashtrackerapp/update_trash.php";
    ProgressDialog progressDialog;
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText editName, editDescription, editLatitude, editLongitude, editIsCleaned, editSize;
    Button UpdateTrash;
    String IdTrashHolder, nameHolder, latitudeHolder, longitudeHolder, descriptionHolder, isCleanedHolder, sizeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editName = (EditText) findViewById(R.id.editName);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editLatitude = (EditText) findViewById(R.id.editLatitude);
        editLongitude = (EditText) findViewById(R.id.editLongitude);
        editIsCleaned = (EditText) findViewById(R.id.editIsCleaned);
        editSize = (EditText) findViewById(R.id.editSize);

        UpdateTrash = (Button) findViewById(R.id.UpdateButton);

        // Receive Trash ID, Name, Latitude, Longitude, Description, IsCleaned, Size  Send by previous ShowSingleRecordActivity.
        IdTrashHolder = getIntent().getStringExtra("idTrash");
        nameHolder = getIntent().getStringExtra("Name");
        descriptionHolder = getIntent().getStringExtra("Description");
        latitudeHolder = getIntent().getStringExtra("Latitude");
        longitudeHolder = getIntent().getStringExtra("Longitude");
        isCleanedHolder = getIntent().getStringExtra("IsCleaned");
        sizeHolder = getIntent().getStringExtra("Size_idSize");

        // Setting Received Trash Name, Latitude, Longitude, Description, IsCleaned, Size  into EditText.
        editName.setText(nameHolder);
        editLatitude.setText(latitudeHolder);
        editLongitude.setText(longitudeHolder);
        editDescription.setText(descriptionHolder);
        editIsCleaned.setText(isCleanedHolder);
        editSize.setText(sizeHolder);

        // Adding click listener to update button .
        UpdateTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending Trash Name, Latitude, Longitude, Description, IsCleaned, Size  to method to update on server.
                TrashRecordUpdate(IdTrashHolder, nameHolder, latitudeHolder, longitudeHolder, descriptionHolder, isCleanedHolder, sizeHolder);

            }
        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText() {

        nameHolder = editName.getText().toString();
        latitudeHolder = editLatitude.getText().toString();
        longitudeHolder = editLongitude.getText().toString();
        descriptionHolder = editDescription.getText().toString();
        isCleanedHolder = editIsCleaned.getText().toString();
        sizeHolder = editSize.getText().toString();

    }

    // Method to Update Trash Record.
    public void TrashRecordUpdate(final String Id_Trash, final String Trash_Name,
                                  final String Trash_Latitude, final String Trash_Longitude, final String Trash_Description,
                                  final String Trash_isCleaned, final String Trash_Size) {

        class TrashRecordUpdateClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UpdateActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(UpdateActivity.this, Id_Trash + " " + Trash_Name + " " + Trash_Latitude + " " + Trash_Longitude
                        + " " + Trash_Description + " " + Trash_isCleaned + " " + Trash_Size + " " + httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("idTrash", params[0]);
                hashMap.put("Name", params[1]);
                hashMap.put("Latitude", params[2]);
                hashMap.put("Longitude", params[3]);
                hashMap.put("Description", params[4]);
                hashMap.put("IsCleaned", params[5]);
                hashMap.put("Size_idSize", params[6]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        TrashRecordUpdateClass trashRecordUpdateClass = new TrashRecordUpdateClass();

        trashRecordUpdateClass.execute(Id_Trash, Trash_Name, Trash_Latitude, Trash_Longitude, Trash_Description, Trash_isCleaned, Trash_Size);
    }
}
