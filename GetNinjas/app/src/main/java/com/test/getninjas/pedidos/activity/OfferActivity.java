package com.test.getninjas.pedidos.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.getninjas.pedidos.R;
import com.test.getninjas.pedidos.adapter.InfosAdapter;
import com.test.getninjas.pedidos.model.data.offer.Offer;
import com.test.getninjas.pedidos.presenter.OfferPresenter;
import com.test.getninjas.pedidos.view.OfferView;
import com.wang.avi.AVLoadingIndicatorView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OfferActivity extends AppCompatActivity implements OfferView, OnMapReadyCallback {

    @Bind(R.id.tv_address)
    TextView tvAddress;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.tv_user)
    TextView tvUser;

    @Bind(R.id.tvDistance)
    TextView tvDistance;

    @Bind(R.id.tv_email)
    TextView tvEmail;

    @Bind(R.id.tv_phone)
    TextView tvPhone;

    @Bind(R.id.btn_act1)
    Button btnAction1;

    @Bind(R.id.btn_action2)
    Button btnAction2;

    @Bind(R.id.indicator_loading)
    AVLoadingIndicatorView indicatorView;

    @Bind(R.id.layout_details)
    RelativeLayout layoutDetails;

    @Bind(R.id.tv_info_accept)
    TextView tvInfoAccept;

    @Bind(R.id.rv_info)
    RecyclerView rvInfos;

    private String href;
    private String phone;
    private boolean isOffer;
    private GoogleMap googleMap;
    private LatLng point;
    private OfferPresenter presenter;
    private InfosAdapter adapter;

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        ButterKnife.bind(this);

        //Get offer/lead href
        if(this.getIntent().hasExtra("href")) {
            this.href = getIntent().getStringExtra("href");
            if(getIntent().getStringExtra("type").equals("offer"))
                isOffer = true;
        }
        int childcount = layoutDetails.getChildCount();
        for (int i=0; i < childcount; i++){
            View v = layoutDetails.getChildAt(i);
            if(!(v instanceof AVLoadingIndicatorView)) {
                v.setAlpha(0f);
                v.setClickable(false);
            }
        }
        presenter = new OfferPresenter();
        presenter.attachView(this);
        presenter.getOffer(this.href, isOffer);

        indicatorView.setVisibility(View.VISIBLE);
    }

    private void setGoogleMap(double lat, double lng) {
        point = new LatLng(lat,lng);
        ((SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment)).getMapAsync(this);
    }

    @Override
    public void onOfferDetailsReceived(final Offer offer) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(offer!=null)
                    setupOffer(offer);
                else
                    Snackbar
                            .make(layoutDetails,
                                    "Não foi possível trazer os dados.", Snackbar.LENGTH_LONG)
                            .show();
            }
        });
    }

    private void setupOffer(Offer offer) {
        if(offer != null) {

            //Restore all views
            int childcount = layoutDetails.getChildCount();
            for (int i=0; i < childcount; i++){
                View v = layoutDetails.getChildAt(i);
                v.setAlpha(1f);
                v.setClickable(true);
            }
            indicatorView.setVisibility(View.GONE);

            //Set googlemap
            setGoogleMap(offer.address.lat, offer.address.lng);

            tvTitle.setText(offer.title);
            tvAddress.setText(String.format("%s, %s - %s",
                    offer.address.neighborhood,
                    offer.address.city,
                    offer.address.uf));
            tvUser.setText(offer.user.name);
            double distance = (double)(offer.distance / 1000);
            DecimalFormat df = new DecimalFormat("#.##");
            tvDistance.setText(String.format("A %s km de você", df.format(distance)));
            tvEmail.setText(offer.user.email);
            this.phone = offer.user.phone;
            tvPhone.setText(this.phone);

            if(!isOffer) {
                btnAction1.setText("LIGAR");
                btnAction2.setText("WHATSAPP");
                tvInfoAccept.setVisibility(View.GONE);
            }else {
                btnAction1.setText("RECUSAR");
                btnAction2.setText("ACEITAR");

                //Also update href var to ACCEPT link.
                href = offer.links.accept.href;
            }

            RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
            rvInfos.setLayoutManager(manager);
            adapter = new InfosAdapter(offer.infos, this.getContext());
            rvInfos.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MarkerOptions mo = new MarkerOptions();
        mo.position(point);
        this.googleMap.addCircle(new CircleOptions()
                .center(point)
                .radius(100)
                .strokeWidth(2.f)
                .strokeColor(Color.CYAN)
                .fillColor(0x551125ef)); //blue
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
    }

    @OnClick(R.id.btn_act1) void action1Click() {
        if(isOffer)
            onBackPressed();
        else
          call();
    }

    @OnClick(R.id.btn_action2) void action2Click() {
        if(isOffer) {
            Intent i = new Intent(this, OfferActivity.class);
            i.putExtra("href", href);
            i.putExtra("type", "lead");
            startActivity(i);
            this.finish();
        } else {
            try {
                Uri uri = Uri.parse("smsto:" + this.phone);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", "");
                i.setPackage("com.whatsapp");
                startActivity(i);
            }catch (Exception e) {
                Toast.makeText(this, "Não há whatsapp neste celular.", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private void call() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE ) == PackageManager.PERMISSION_GRANTED ) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this.phone));
            startActivity(intent);
        } else
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(layoutDetails, "Não foi possível fazer a ligação.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                return;
            }
        }
    }
}
