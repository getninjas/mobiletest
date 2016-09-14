package com.example.wesley.myjobs.eventdetail;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.example.wesley.myjobs.BaseActivity;
import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.leaddetail.LeadDetailFragment;
import com.example.wesley.myjobs.offerdetail.OfferDetailFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public class EventDetailActivity extends BaseActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 9080;
    private static final int MY_PERMISSIONS_REQUEST_PHONE = 9030;
    public static final String DETAIL_URL_EXTRA = "DETAIL_URL";
    public static final String EVENT_TYPE_EXTRA = "EVENT_TYPE";
    private EventDetailFragment fragment;
    private SupportMapFragment map;
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;

    @BindView(R.id.rootViewLayout) LinearLayout rootViewLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fabAction) FloatingActionButton fabAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_act);
        ButterKnife.bind(this);

        configureActionBar();

        if(getIntent().getStringExtra(EVENT_TYPE_EXTRA).equals("lead")) {
            fragment = (LeadDetailFragment) getSupportFragmentManager().findFragmentById(R.id.innerContentFrame);
            if (fragment == null) {
                fragment = new LeadDetailFragment();
                addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.innerContentFrame);
            }
        } else if(getIntent().getStringExtra(EVENT_TYPE_EXTRA).equals("offer")) {
            fragment = (OfferDetailFragment) getSupportFragmentManager().findFragmentById(R.id.innerContentFrame);
            if (fragment == null) {
                fragment = new OfferDetailFragment();
                addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.innerContentFrame);
            }
        }

        fragment.setFabVisibility(fabAction);

        askForPermission();

        showRevealEffect(savedInstanceState, rootViewLayout);
    }

    private void askForPermission() {
        int locationPermissionCheck = ContextCompat.checkSelfPermission(this,  Manifest.permission.ACCESS_COARSE_LOCATION);
        if(locationPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            configureMap();
        }

        int phonePermissionCheck = ContextCompat.checkSelfPermission(this,  Manifest.permission.CALL_PHONE);
        if(phonePermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION : {
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fragment.loadDetail();
                    configureMap();
                }
            }
        }
    }

    private void configureMap() {
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

        if(googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(googleApiClient != null)
            googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(googleApiClient != null)
            googleApiClient.disconnect();
    }

    private void configureActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void updateMapPosition(Double lat, Double longi, String title) {
        LatLng eventMarker = new LatLng(lat, longi);
        if(googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(eventMarker).title(title));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventMarker, 13));
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        int permissionCheck = ContextCompat.checkSelfPermission(this,  Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if(location != null) {
                fragment.updateUserLocation(location);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
