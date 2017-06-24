package com.example.administrator.tabzzf;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Base64;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import static com.example.administrator.tabzzf.R.id.imageView;
import static com.example.administrator.tabzzf.R.id.radioIsCleaned;
import static com.example.administrator.tabzzf.R.id.scrollView;
import static com.example.administrator.tabzzf.R.id.tabHost;
import static com.example.administrator.tabzzf.R.id.webView1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private Button ChooseBn, UploadBn, Refresh;
    private final int IMG_REQUEST = 3;
    private Button button1;
    private int a = 1;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";
    private String KEY_LAT = "lat";
    private String KEY_LNG = "lng";
    private String UploadUrl = "http://192.168.100.3/upload3.php";
    private Bitmap bitmap;
    private EditText NAME;
    private TextView LAT;
    private TextView LNG;
    private ImageView imgView;
    private String filePath;
    RadioGroup rg;
    RadioButton rb;

    private Location mLocation;
    private LocationManager mLocationManager;
    private GoogleApiClient mGoogleApiClient;
    //  private static final String TAG = "MainActivity";
    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private LocationManager locationManager;

    GalleryPhoto galleryPhoto;

    final int GALLERY_REQUEST = 1200;

    final String TAG = this.getClass().getSimpleName();

    LinearLayout linearMain;

    ArrayList<String> imageList = new ArrayList<>();
    ArrayList<String> selection = new ArrayList<>();
    TextView final_text;
    private static final int PLACE_PICKER_REQUEST = 1;
    private TextView mName;
    private TextView mAddress;
    private TextView mAttributions;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    private static final int RQS_OPEN = 33;

    Button buttonOpen;
    private RecyclerView myRecyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    //new Create DB object and layout to test the DB
    private DBHelp trashTrackerDb;
    private EditText editName;
    private EditText editDescription;
    //Select Is Cleaned
    private RadioGroup radioIsCleanedGroup;
    private RadioButton radioCleanedButton;
    private Button btnDisplayCleanedRadio;
    //Select size
    private RadioGroup radioSizeGroup;
    private RadioButton radioSizeButton;
    private Button btnDisplaySizeRadio;

    private Button btnAddData;
    private Button btnViewAll;
    //end new


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        myRecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        1, //The number of ROWS in the grid
                        LinearLayoutManager.HORIZONTAL);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
        int spaceInPixels = -100;
        myRecyclerView.addItemDecoration(new RecyclerViewItemDecorator(spaceInPixels));


        ChooseBn = (Button) findViewById(R.id.chooseBn);

        UploadBn = (Button) findViewById(R.id.uploadBn);

        rg = (RadioGroup) findViewById(R.id.radioGroup);


        final WebView webview = (WebView) findViewById(R.id.webView1);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.clearCache(true);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        webview.loadUrl("http://192.168.100.3/index.html");

        TabHost tab = (TabHost) findViewById(R.id.tabHost);
        tab.setup();

        tab.getTabWidget().setDividerDrawable(null);


        TabHost.TabSpec spec1 = tab.newTabSpec("TAB 1");


        spec1.setIndicator("", getResources().getDrawable(R.drawable.ic_tab_dialer));
        spec1.setContent(R.id.layout1);
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("TAB 2");
        spec2.setIndicator("", getResources().getDrawable(R.drawable.ic_tab_dialer2));

        spec2.setContent(R.id.layout2);
        tab.addTab(spec2);
        TabHost.TabSpec spec3 = tab.newTabSpec("TAB 3");
        spec3.setIndicator("", getResources().getDrawable(R.drawable.ic_tab_dialer3));
        spec3.setContent(R.id.layout3);
        tab.addTab(spec3);

        //new
        //Add new fourth tab with Title List (I didn't change the icon :-)) to test DB
        TabHost.TabSpec spec4 = tab.newTabSpec("TAB 4");
        spec4.setIndicator("", getResources().getDrawable(R.drawable.ic_tab_dialer4));
        spec4.setContent(R.id.layout4);
        tab.addTab(spec4);
        //End new

        tab.getTabWidget().getChildAt(0).setLayoutParams(new
                LinearLayout.LayoutParams(1, 161, 1));
        tab.getTabWidget().getChildAt(0).setBackgroundColor(Color.rgb(88, 89, 91));
        tab.getTabWidget().getChildAt(1).setBackgroundColor(Color.rgb(88, 89, 91));
        tab.getTabWidget().getChildAt(2).setBackgroundColor(Color.rgb(88, 89, 91));
        tab.getTabWidget().getChildAt(1).setLayoutParams(new
                LinearLayout.LayoutParams(1, 161, 1));
        tab.getTabWidget().getChildAt(2).setLayoutParams(new
                LinearLayout.LayoutParams(1, 161, 1));


        final_text = (TextView) findViewById(R.id.textView4);

        NAME = (EditText) findViewById(R.id.InputNAME);
        LAT = (TextView) findViewById(R.id.InputLag);
        LNG = (TextView) findViewById(R.id.InputLat);
        imgView = (ImageView) findViewById(R.id.imageView);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        checkLocation(); //check whether location service is enable or not in your  phone

        //linearMain = (LinearLayout)findViewById(R.id.linearMain);

        //new
        addListenerOnIsCleanedButton();
        addListenerOnSizeButton();
        trashTrackerDb = new DBHelp(this);

        editName = (EditText) findViewById(R.id.InputTRASHNAME);
        editDescription = (EditText) findViewById(R.id.InputDESCRIPTION);
        radioIsCleanedGroup = (RadioGroup) findViewById(R.id.SelectIsCleaned);
        radioSizeGroup = (RadioGroup) findViewById(R.id.SelectSize);

        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewAll = (Button) findViewById(R.id.button_viewAll);
        AddData();
        viewAll();
        //end new

        ChooseBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open multi-type using Intent.ACTION_OPEN_DOCUMENT
                //Open multi-file
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, RQS_OPEN);

                Toast.makeText(MainActivity.this,
                        "Single-selection: Tap on any file.\n" +
                                "Multi-selection: Tap & Hold on the first file, " +
                                "tap for more, tap on OPEN to finish.",
                        Toast.LENGTH_LONG).show();

            }
        });


        UploadBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (filePath != null) {
                    imageUpload(filePath);
                } else {
                    Toast.makeText(getApplicationContext(), "Image not selected!", Toast.LENGTH_LONG).show();
                }

            }


        });


        mAddress = (TextView) findViewById(R.id.textView2);

        Button pickerButton = (Button) findViewById(R.id.pickerButton);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    PlacePicker.IntentBuilder intentBuilder =
                            new PlacePicker.IntentBuilder();
                    intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
                    Intent intent = intentBuilder.build(MainActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException
                        | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void imageUpload(final String imagePath) {

      /* non-working  SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, UploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String message = jObj.getString("message");

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        smr.addFile("image", imagePath);
        MySingleton.getInstance().addToRequestQueue(smr);

*/

    }


    public void selectItem(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox4:
                if (checked) {
                    selection.add("apple");
                } else {
                    selection.remove("apple");
                }
                break;
            case R.id.checkBox5:
                if (checked) {
                    selection.add("orange");
                } else {
                    selection.remove("orange");
                }
                break;
            case R.id.checkBox6:
                if (checked) {
                    selection.add("grapes");
                } else {
                    selection.remove("grapes");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        myRecyclerViewAdapter.clearAll();
        if (resultCode == RESULT_OK) {
            if (requestCode == RQS_OPEN) {
                ClipData clipData = data.getClipData();

                if (clipData == null) {


                    myRecyclerViewAdapter.add(
                            myRecyclerViewAdapter.getItemCount(),
                            data.getData());
                } else {
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri uri = item.getUri();

                        //s += uri.toString() + "\n";
                        myRecyclerViewAdapter.add(
                                myRecyclerViewAdapter.getItemCount(),
                                uri);
                    }
                }

            }
        }

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(this, data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            final double location = place.getLatLng().longitude;
            final double location2 = place.getLatLng().latitude;


            String attributions = (String) place.getAttributions();


            if (attributions == null) {
                attributions = "";
            }

            mAddress.setText(address);
            LAT.setText(String.valueOf(location2));
            LNG.setText(String.valueOf(location));

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private String getPath(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onClick(View v) {


    }

    public class Image {
        public String imageString;
        public String name;
        public double lat, lng;
    }

    public void rbclick(View v) {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);
        Toast.makeText(getBaseContext(), rb.getText(), Toast.LENGTH_LONG).show();
    }

    public class RecyclerViewItemDecorator extends RecyclerView.ItemDecoration {
        private int spaceInPixels;

        public RecyclerViewItemDecorator(int spaceInPixels) {
            this.spaceInPixels = spaceInPixels;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = spaceInPixels;
            outRect.right = spaceInPixels;
            outRect.bottom = spaceInPixels;

            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = spaceInPixels;
            } else {
                outRect.top = 0;
            }
        }
    }

 /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
              imgView.setImageBitmap(bitmap);
                imgView.setVisibility(View.VISIBLE);
                ChooseBn.setVisibility(View.INVISIBLE);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage()
 {
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UploadUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(MainActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = imageToString(bitmap);

                //Getting Image Name
                String name = NAME.getText().toString().trim();
                String lat =LAT.getText().toString().trim();
                String lng = LNG.getText().toString().trim();



                //Creating parameters
                Map<String, String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);
                params.put(KEY_LAT,lat);
                params.put(KEY_LNG,lng);
                return params;

            }


        };
        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }*/

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }


    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        startLocationUpdates();

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {
            LAT.setText(String.valueOf(mLocation.getLatitude()));
            // mLatitudeTextView.setText(String.valueOf(mLocation.getLatitude()));
            //mLongitudeTextView.setText(String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());

        LAT.setText(String.valueOf(location.getLatitude()));
        LNG.setText(String.valueOf(location.getLongitude()));
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        LAT.setText(String.valueOf(location.getLatitude()));
        LNG.setText(String.valueOf(location.getLongitude()));
    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void goToMap(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityDua.class);
        startActivity(intent);
    }

    //new
    //Adding Data to Trash Table in the DB
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    //TODO insert array of images!!!
                    @Override
                    public void onClick(View v) {
                        //TODO get the current location, if the User didn't select it
                        double latitude = 44.4;
                        double longitude = 45.8;

                        if (!LAT.getText().toString().equals("TextView") || !LNG.getText().toString().equals("TextView")) {
                            latitude = Double.parseDouble(LAT.getText().toString());
                            longitude = Double.parseDouble(LNG.getText().toString());
                        }
                        
                        int isCleanedSelectedId = radioIsCleanedGroup.getCheckedRadioButtonId();
                        int sizeSelectedId = radioSizeGroup.getCheckedRadioButtonId();
                        radioSizeButton = (RadioButton) findViewById(sizeSelectedId);
                        radioCleanedButton = (RadioButton) findViewById(isCleanedSelectedId);


                        //TODO getsize by radionButton id, not by radioButton text
                        int sizeID = 0;
                        if (radioSizeButton.getText().toString().equals("Medium")) {
                            sizeID = 1;
                        } else if (radioSizeButton.getText().toString().equals("Large")) {
                            sizeID = 2;
                        }

                        boolean isCleaned = false;
                        if (radioCleanedButton.getText().toString().equals("Yes")) {
                            isCleaned = true;
                        }
                        boolean isInserted = trashTrackerDb.insertDataIntoTrashTable(editName.getText().toString(), editDescription.getText().toString(),
                                latitude, longitude, isCleaned, sizeID);

                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //Show all the data stored in Trash Table in the DB

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = trashTrackerDb.getAllDataFromTrashTable();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Lat :" + res.getString(2) + "\n");
                            buffer.append("Long :" + res.getString(3) + "\n");
                            buffer.append("Desc :" + res.getString(4) + "\n");
                            buffer.append("IsCleaned :" + res.getString(5) + "\n");
                            buffer.append("Size :" + res.getString(6) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    //Check selected Is Cleaned
    public void addListenerOnIsCleanedButton() {
        radioIsCleanedGroup = (RadioGroup) findViewById(R.id.SelectIsCleaned);
        btnDisplayCleanedRadio = (Button) findViewById(R.id.btnIsCleanedDisplay);

        btnDisplayCleanedRadio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioIsCleanedGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioCleanedButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioCleanedButton.getText() + " " + Integer.toString(selectedId), Toast.LENGTH_SHORT).show();

            }

        });

    }

    //Check selected Size
    public void addListenerOnSizeButton() {
        radioSizeGroup = (RadioGroup) findViewById(R.id.SelectSize);
        btnDisplaySizeRadio = (Button) findViewById(R.id.btnSizeDisplay);

        btnDisplaySizeRadio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioSizeGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSizeButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioSizeButton.getText() + " " + Integer.toString(selectedId) + " " + String.valueOf(radioSizeButton.getId()), Toast.LENGTH_SHORT).show();

            }

        });

    }
    //end new

}
