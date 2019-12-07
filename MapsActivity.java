package com.example.bijoya.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{


    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocation;
    private Marker currentLocationmMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toast.makeText(MapsActivity.this,"Turn on your phone location",Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_LOCATION_CODE:
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            bulidGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
                }
        }
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }


    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        client.connect();

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        lastlocation = location;
        if(currentLocationmMarker != null)
        {
            currentLocationmMarker.remove();

        }
        Log.d("lat = ",""+latitude);
        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currentLocationmMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if(client != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }
    }

    public void onClick(View v)
    {
        Object dataTransfer[] = new Object[2];
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        switch(v.getId())
        {
            case R.id.B_search:
                EditText tf_location =  findViewById(R.id.TF_location);
                String location = tf_location.getText().toString();
                List<Address> addressList;


                if(!location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(this);

                    try {
                        addressList = geocoder.getFromLocationName(location, 5);

                        if(addressList != null)
                        {
                            for(int i = 0;i<addressList.size();i++)
                            {
                                LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                markerOptions.title(location);
                                mMap.addMarker(markerOptions);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.B_hopistals:
                mMap.clear();
                String hospital = "hospital";
                String url = getUrl(latitude, longitude, hospital);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();
                break;


            case R.id.B_schools:
                mMap.clear();
                String school = "school";
                url = getUrl(latitude, longitude, school);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Schools", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_restaurants:
                mMap.clear();
                String resturant = "restuarant";
                url = getUrl(latitude, longitude, resturant);
                dataTransfer[0] = mMap;
                dataTransfer[1] = url;

                getNearbyPlacesData.execute(dataTransfer);
                Toast.makeText(MapsActivity.this, "Showing Nearby Restaurants", Toast.LENGTH_SHORT).show();
                break;
            case R.id.B_to:
        }
    }


    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }


    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
            }
            return false;

        }
        else
            return true;
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
//
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import java.io.IOException;
//import java.util.List;
//
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
//        GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener,
//        LocationListener,
//        GoogleMap.OnMarkerClickListener,
//        GoogleMap.OnMarkerDragListener
//{
//
//    private GoogleMap mMap;
//    GoogleApiClient mGoogleApiClient;
//    Location mLastLocation;
//    Marker mCurrLocationMarker;
//    LocationRequest mLocationRequest;
//    int PROXIMITY_RADIUS = 10000;
//    double latitude, longitude;
//    double end_latitude=23.734162, end_longitude=90.392679;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        Toast.makeText(MapsActivity.this, "Turn on your phone location", Toast.LENGTH_SHORT).show();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkLocationPermission();
//        }
//
//        //Check if Google Play Services Available or not
//        if (!CheckGooglePlayServices()) {
//            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
//            finish();
//        }
//        else {
//            Log.d("onCreate","Google Play Services available.");
//        }
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//
//    }
//
//    private boolean CheckGooglePlayServices() {
//        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
//        int result = googleAPI.isGooglePlayServicesAvailable(this);
//        if(result != ConnectionResult.SUCCESS) {
//            if(googleAPI.isUserResolvableError(result)) {
//                googleAPI.getErrorDialog(this, result,
//                        0).show();
//            }
//            return false;
//        }
//        return true;
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        //Initialize Google Play Services
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                buildGoogleApiClient();
//                mMap.setMyLocationEnabled(true);
//            }
//        } else {
//            buildGoogleApiClient();
//            mMap.setMyLocationEnabled(true);
//        }
//
//        mMap.setOnMarkerDragListener(this);
//        mMap.setOnMarkerClickListener(this);
//
//    }
//
//
//
//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//        mGoogleApiClient.connect();
//    }
//
//    public void onClick(View v)
//    {
//        Object dataTransfer[] = new Object[2];
//        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//
//
//        switch(v.getId()) {
//            case R.id.B_search:
//                EditText tf_location =  findViewById(R.id.TF_location);
//                String location = tf_location.getText().toString();
//                List<Address> addressList;
//
//
//                if(!location.equals(""))
//                {
//                    Geocoder geocoder = new Geocoder(this);
//
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 5);
//
//                        if(addressList != null)
//                        {
//                            for(int i = 0;i<addressList.size();i++)
//                            {
//                                LatLng latLng = new LatLng(addressList.get(i).getLatitude() , addressList.get(i).getLongitude());
//                                MarkerOptions markerOptions = new MarkerOptions();
//                                markerOptions.position(latLng);
//                                markerOptions.title(location);
//                                mMap.addMarker(markerOptions);
//                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            case R.id.B_hopistals:
//                mMap.clear();
//                String hospital = "hospital";
//                String url = getUrl(latitude, longitude, hospital);
//
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.B_restaurants:
//                mMap.clear();
//                dataTransfer = new Object[2];
//                String restaurant = "restaurant";
//                url = getUrl(latitude, longitude, restaurant);
//                getNearbyPlacesData = new GetNearbyPlacesData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Nearby Resturants", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.B_schools:
//                mMap.clear();
//                String school = "school";
//                dataTransfer = new Object[2];
//                url = getUrl(latitude, longitude, school);
//                getNearbyPlacesData = new GetNearbyPlacesData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Nearby Hotel", Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.B_to:
//                dataTransfer = new Object[3];
//                url = getDirectionsUrl();
//                GetDirectionsData getDirectionsData = new GetDirectionsData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//                dataTransfer[2] = new LatLng(end_latitude, end_longitude);
//                getDirectionsData.execute(dataTransfer);
//
//                break;
//
//
//        }
//    }
//
//    private String getDirectionsUrl()
//    {
//        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
//        googleDirectionsUrl.append("origin="+latitude+","+longitude);
//        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
//       // googleDirectionsUrl.append("&key="+"AIzaSyCAcfy-02UHSu2F6WeQ1rhQhkCr51eBL9g");
//        googleDirectionsUrl.append("&key="+"AIzaSyCsYFdiQP4FKoV-4s4Cj7rsvhUezRZZbVo");
//
//        return googleDirectionsUrl.toString();
//    }
//
//    private String getUrl(double latitude, double longitude, String nearbyPlace)
//    {
//        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//        googlePlacesUrl.append("location=" + latitude + "," + longitude);
//        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
//        googlePlacesUrl.append("&type=" + nearbyPlace);
//        googlePlacesUrl.append("&sensor=true");
//       // googlePlacesUrl.append("&key=" + "AIzaSyBj-cnmMUY21M0vnIKz0k3tD3bRdyZea-Y");
//        googlePlacesUrl.append("&key=" + "AIzaSyCsYFdiQP4FKoV-4s4Cj7rsvhUezRZZbVo");
//        Log.d("getUrl", googlePlacesUrl.toString());
//        return (googlePlacesUrl.toString());
//    }
//
//
//
//
//    @Override
//    public void onConnected(Bundle bundle) {
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//        }
//    }
//
//
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        Log.d("onLocationChanged", "entered");
//
//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//
//
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.draggable(true);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//
//
//        Toast.makeText(MapsActivity.this,"Your Current Location", Toast.LENGTH_LONG).show();
//
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//            Log.d("onLocationChanged", "Removing Location Updates");
//        }
//
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//
//    }
//
//    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//    public boolean checkLocationPermission(){
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Asking user if explanation is needed
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//                //Prompt the user once explanation has been shown
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted. Do the
//                    // contacts-related task you need to do.
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        if (mGoogleApiClient == null) {
//                            buildGoogleApiClient();
//                        }
//                        mMap.setMyLocationEnabled(true);
//                    }
//
//                } else {
//
//                    // Permission denied, Disable the functionality that depends on this permission.
//                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other permissions this app might request.
//            // You can add here other case statements according to your requirement.
//        }
//    }
//
//
//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        marker.setDraggable(true);
//        return false;
//    }
//
//    @Override
//    public void onMarkerDragStart(Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDrag(Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDragEnd(Marker marker) {
//        end_latitude = marker.getPosition().latitude;
//        end_longitude =  marker.getPosition().longitude;
//
//        Log.d("end_lat",""+end_latitude);
//        Log.d("end_lng",""+end_longitude);
//    }
//}
//
//
//
///*import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//
//import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentActivity;
//import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import java.io.IOException;
//import java.util.List;
//
//public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
//        GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener,
//        GoogleMap.OnMarkerDragListener{
//
//    private GoogleMap mMap;
//    private GoogleApiClient client;
//    private LocationRequest locationRequest;
//    private Location lastlocation;
//    private Marker currentLocationmMarker;
//    public static final int REQUEST_LOCATION_CODE = 99;
//    int PROXIMITY_RADIUS = 10000;
//    double latitude,longitude;
//    double end_latitude=23.734162, end_longitude=90.392679;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//        {
//            checkLocationPermission();
//
//        }
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch(requestCode)
//        {
//            case REQUEST_LOCATION_CODE:
//                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
//                    if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED)
//                    {
//                        if(client == null)
//                        {
//                            buildGoogleApiClient();
//                        }
//                        mMap.setMyLocationEnabled(true);
//                    }
//                }
//                else
//                {
//                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_LONG).show();
//                }
//                return;
//        }
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//   /* @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            buildGoogleApiClient();
//            mMap.setMyLocationEnabled(true);
//        }
//
//        mMap.setOnMarkerDragListener(this);
//        mMap.setOnMarkerClickListener(this);
//
//    }
//
//    public void onClick(View v) {
//
//        Object dataTransfer[]= new Object[2];
//        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
//        switch(v.getId()) {
//            case R.id.B_search:
//                EditText tf_location = findViewById(R.id.TF_location);
//                String location = tf_location.getText().toString();
//                List<Address> addressList;
//
//
//                if (!location.equals("")) {
//                    Geocoder geocoder = new Geocoder(this);
//
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 5);
//
//                        if (addressList != null) {
//                            for (int i = 0; i < addressList.size(); i++) {
//                                LatLng latLng = new LatLng(addressList.get(i).getLatitude(), addressList.get(i).getLongitude());
//                                MarkerOptions markerOptions = new MarkerOptions();
//                                markerOptions.position(latLng);
//                                markerOptions.title(location);
//                                mMap.addMarker(markerOptions);
//                                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            case R.id.B_hopistals:
//                mMap.clear();
//                String hospital = "hospital";
//                String url = getUrl(latitude, longitude, hospital);
//
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Nearby Hospitals", Toast.LENGTH_SHORT).show();
//                break;
//
//
//            case R.id.B_schools:
//                mMap.clear();
//                String school = "school";
//                url = getUrl(latitude, longitude, school);
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Nearby Schools", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.B_restaurants:
//                mMap.clear();
//                String resturant = "restuarant";
//                url = getUrl(latitude, longitude, resturant);
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//
//                getNearbyPlacesData.execute(dataTransfer);
//                Toast.makeText(MapsActivity.this, "Showing Nearby Restaurants", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.B_to:
//                MarkerOptions markerOptions = new MarkerOptions();
//                markerOptions.position(new LatLng(end_latitude, end_longitude));
//                markerOptions.title("Destination");
//
//                float results[] = new float[19];
//                Location.distanceBetween(latitude, longitude, end_latitude, end_longitude, results);
//                markerOptions.snippet("Distance: " + results[0]+"m");
//                mMap.addMarker(markerOptions);
//
//                dataTransfer = new Object[3];
//                url = getDirectionsUrl();
//                GetDirectionsData getDirectionsData = new GetDirectionsData();
//                dataTransfer[0] = mMap;
//                dataTransfer[1] = url;
//               dataTransfer[2] = new LatLng(end_latitude, end_longitude);
//                getDirectionsData.execute(dataTransfer);
//                break;
//        }
//    }
//
//    private String getDirectionsUrl()
//    {
//        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
//        googleDirectionsUrl.append("origin="+latitude+","+longitude);
//        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
//        googleDirectionsUrl.append("&key="+"AIzaSyAxC_n_ZxVYC07UwYKihf1FLTONZ1rBJ_Q");
//
//        return googleDirectionsUrl.toString();
//    }
//
//    private String getUrl(double latitude , double longitude , String nearbyPlace)
//    {
//
//        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//        googlePlaceUrl.append("location="+latitude+","+longitude);
//        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
//        googlePlaceUrl.append("&type="+nearbyPlace);
//        googlePlaceUrl.append("&sensor=true");
//        googlePlaceUrl.append("&key="+"AIzaSyAxC_n_ZxVYC07UwYKihf1FLTONZ1rBJ_Q");
//
//        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());
//
//        return googlePlaceUrl.toString();
//    }
//
//
//
//
//
//    protected synchronized void buildGoogleApiClient(){
//
//        client = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
//
//        client.connect();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//
//
//        latitude = location.getLatitude();
//        longitude = location.getLongitude();
//
//        lastlocation = location;
//        if(currentLocationmMarker != null)
//        {
//            currentLocationmMarker.remove();
//
//        }
//
//        LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Location");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        currentLocationmMarker = mMap.addMarker(markerOptions);
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));
//
//        if(client != null)
//        {
//            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
//        }
//
//    }
//
//
//
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//        locationRequest = new LocationRequest();
//
//        locationRequest.setInterval(1000);
//        locationRequest.setFastestInterval(1000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED)
//        {
//            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
//        }
//
//    }
//
//    public boolean checkLocationPermission()
//    {
//        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
//        {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
//            {
//                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
//            }
//            else
//            {
//                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_LOCATION_CODE);
//            }
//            return false;
//
//        }
//        else
//            return true;
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public boolean onMarkerClick(Marker marker) {
//
//        marker.setDraggable(true);
//        return false;
//
//    }
//
//    @Override
//    public void onMarkerDragStart(Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDrag(Marker marker) {
//
//    }
//
//    @Override
//    public void onMarkerDragEnd(Marker marker) {
//
//      //  end_latitude = marker.getPosition().latitude;
//       // end_longitude =  marker.getPosition().longitude;
//
//        Log.d("end_lat",""+end_latitude);
//        Log.d("end_lng",""+end_longitude);
//    }
//}*/
