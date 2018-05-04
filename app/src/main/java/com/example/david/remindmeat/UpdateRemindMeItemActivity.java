package com.example.david.remindmeat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.remindmeat.dao.RemindDao;
import com.example.david.remindmeat.dao.RemindItemDao;
import com.example.david.remindmeat.model.RemindItem;
import com.example.david.remindmeat.utils.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UpdateRemindMeItemActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private RemindDao remindItemDao;
    private RemindItem remindItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_remind_me_item);

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent listClickHandlerIntent = getIntent();
        Bundle extra = listClickHandlerIntent.getExtras();

        remindItemDao = new RemindItemDao(this);

        remindItem = (RemindItem) extra.get(Constants.EXTRA_SELECTED_REMIND_ME_ITEM);

        titleEditText = findViewById(R.id.titleUpdate);
        descriptionEditText = findViewById(R.id.descriptionUpdate);
        latitudeEditText = findViewById(R.id.latitudeUpdate);
        longitudeEditText = findViewById(R.id.longitudeUpdate);

        titleEditText.setText(remindItem.getTitle());
        descriptionEditText.setText(remindItem.getDescription());
        latitudeEditText.setText(String.valueOf(remindItem.getLatitude()));
        longitudeEditText.setText(String.valueOf(remindItem.getLongitude()));
    }

    public void updateItem(View view){
        String title = ((EditText) findViewById(R.id.titleUpdate)).getText().toString();
        String description = ((EditText) findViewById(R.id.descriptionUpdate)).getText().toString();
        String latitude = ((EditText) findViewById(R.id.latitudeUpdate)).getText().toString();
        String longitude = ((EditText) findViewById(R.id.longitudeUpdate)).getText().toString();

        if(!title.equals("") && !description.equals("") && !latitude.equals("") && !longitude.equals("")){
            remindItem.setTitle(title);
            remindItem.setDescription(description);
            remindItem.setLatitude(Double.valueOf(latitude));
            remindItem.setLongitude(Double.valueOf(longitude));


            remindItemDao.update(remindItem);

            goToRemindMeListActivity();

        }else{
            Toast.makeText(this, Constants.TOAST_FILL_FIELDS, Toast.LENGTH_LONG).show();
        }
    }

    public void deleteItem(View view){
        remindItemDao.delete(remindItem);
        goToRemindMeListActivity();
    }

    public void cancelUpdateItem(View view){

        goToRemindMeListActivity();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }else{
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){

            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                latitudeEditText.setText(String.valueOf(latLng.latitude));
                longitudeEditText.setText(String.valueOf(latLng.longitude));

                mMap.clear();

                mMap.addMarker(markerOptions);
            }
        });

    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        if(mCurrLocationMarker != null){
            mCurrLocationMarker.remove();
        }

        //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng latLng = new LatLng(remindItem.getLatitude(), remindItem.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(remindItem.getTitle());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        if(mGoogleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

        //latitudeEditText.setText(String.valueOf(latLng.latitude));
        //longitudeEditText.setText(String.valueOf(latLng.longitude));

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_LOCATION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        if(mGoogleApiClient == null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }else{
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private boolean checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }else{
            return true;
        }
    }

    private void goToRemindMeListActivity(){
        Intent remindMeListIntent = new Intent(this, RemindMeAtListActivity.class);
        startActivity(remindMeListIntent);
    }
}
