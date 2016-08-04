package com.example.getninjastest.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.getninjastest.R;
import com.example.getninjastest.model.details.LeadDetails;
import com.example.getninjastest.model.details.OfferDetails;
import com.example.getninjastest.model.view.DetailsView;
import com.example.getninjastest.util.Utils;
import com.example.getninjastest.webservice.ApiClient;
import com.example.getninjastest.webservice.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent;
    private String titleText = "";
    private String href = "";
    LinearLayout llOfferButtons, llLeadButtons, llClientContact;
    TextView  tvTitle, tvAddress, tvClientPhone, tvClientEmail;
    DetailsView detailsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isOffer()){
            loadOfferDetails();
        }else {
            loadLeadDetails();
        }
    }

    private void initViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        llOfferButtons = (LinearLayout) findViewById(R.id.llOfferButtons);
        llLeadButtons = (LinearLayout) findViewById(R.id.llLeadButtons);
        llClientContact = (LinearLayout) findViewById(R.id.llClientContact);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvClientPhone = (TextView) findViewById(R.id.tvClientPhone);
        tvClientEmail = (TextView) findViewById(R.id.tvClientEmail);

        intent = getIntent();
        if(intent.hasExtra("title")){
            titleText = intent.getStringExtra("title");
            getSupportActionBar().setTitle(titleText);
        }
        if(intent.hasExtra("href")) {
            href = intent.getStringExtra("href");
        }
        if(isOffer()){
            llOfferButtons.setVisibility(View.VISIBLE);
            llLeadButtons.setVisibility(View.GONE);
            llClientContact.setBackgroundColor(Color.parseColor("#4ec6fe"));
        }else {//lead
            llLeadButtons.setVisibility(View.VISIBLE);
            llOfferButtons.setVisibility(View.GONE);
            llClientContact.setBackgroundColor(Color.parseColor("#c8f57e"));
        }
        detailsView = new DetailsView();
    }

    private boolean isOffer(){
        if(intent.hasExtra("title")){
            titleText = intent.getStringExtra("title");
            if(titleText.equals("Offer")){
               return true;
            }
        }
        return false;
    }

    /**
     * react to the user tapping the back/up icon in the action bar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //TODO - FIXME
    //Ugly coding
    //following section should be called and loaded into view from a centralized place
    Retrofit retrofit = ApiClient.getClient();
    ApiInterface request = retrofit.create(ApiInterface.class);

    //offer details
    private void loadOfferDetails(){
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        Call<OfferDetails> callOfferDetails = request.getOfferDetails(href);
        callOfferDetails.enqueue(new Callback<OfferDetails>() {
            @Override
            public void onResponse(Call<OfferDetails> call, Response<OfferDetails> response) {

                OfferDetails jsonResponse = response.body();
                detailsView.setTitle(jsonResponse.getTitle());
                detailsView.setAddress(jsonResponse.getEmbedded().getAddress().getCity() + " - "+jsonResponse.getEmbedded().getAddress().getNeighborhood());
                detailsView.setAcceptLink(jsonResponse.getLinks().getAccept().getHref());
                detailsView.setRejectLink(jsonResponse.getLinks().getReject().getHref());
                detailsView.setPhoneNumber(Utils.getMaskedPhoneNumber( jsonResponse.getEmbedded().getUser().getEmbedded().getPhones().get(0).getNumber()) ); //taking only 1 phone number
                detailsView.setEmail( Utils.getMaskedEmail( jsonResponse.getEmbedded().getUser().getEmail()) );
                populateViews();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();


            }

            @Override
            public void onFailure(Call<OfferDetails> call, Throwable t) {
                Log.d("Error",t.getMessage());
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }
        });
    }

    private void loadLeadDetails(){
        //TODO clean
        //smelly code
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        //lead details
        Call<LeadDetails> callLeadDetails = request.getLeadDetails(href);
        callLeadDetails.enqueue(new Callback<LeadDetails>() {
            @Override
            public void onResponse(Call<LeadDetails> call, Response<LeadDetails> response) {

                LeadDetails jsonResponse = response.body();
                detailsView.setTitle(jsonResponse.getTitle());
                detailsView.setAddress(jsonResponse.getEmbedded().getAddress().getCity() + " - "+jsonResponse.getEmbedded().getAddress().getNeighborhood());
                detailsView.setPhoneNumber(jsonResponse.getEmbedded().getUser().getEmbedded().getPhones().get(0).getNumber()); //taking only 1 phone number
                detailsView.setEmail(jsonResponse.getEmbedded().getUser().getEmail());
               // Log.d("LeadDetails",""+jsonResponse.getTitle()+" "+jsonResponse.getEmbedded().getInfo().get(1).getFormattedValue());
                populateViews();
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<LeadDetails> call, Throwable t) {
                Log.d("Error",t.getMessage());
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

            }
        });
    }

    private void populateViews(){
        tvTitle.setText(detailsView.getTitle());
        tvAddress.setText(detailsView.getAddress());
        tvClientPhone.setText(detailsView.getPhoneNumber());
        tvClientEmail.setText(detailsView.getEmail());
    }

    //button click events
    public void offerAccept(View view){
        Intent leadIntent = new Intent(DetailsActivity.this, DetailsActivity.class);
        leadIntent.putExtra("title", "Lead");
        leadIntent.putExtra("href", detailsView.getAcceptLink());
        startActivity(leadIntent);
        finish();
    }

    public void offerReject(View view){
        //TODO
        //should go to reject link but finishing it for now
        finish();
    }

    public void phoneCall(View view){
        if(detailsView.getPhoneNumber() == null || detailsView.getPhoneNumber().equals("")){
            //TODO inform user
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+detailsView.getPhoneNumber()));
        startActivity(callIntent);
    }

    public void whatsAppCall(View view){
        if(detailsView.getPhoneNumber() == null || detailsView.getPhoneNumber().equals("")){
            return;
        }
        //check if whats app is installed
        if( Utils.isApplicationWithPackageNameInstalled("com.whatsapp", getApplicationContext()) ){
            Uri uri = Uri.parse("smsto:" + detailsView.getPhoneNumber());
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.setPackage("com.whatsapp");
            startActivity(i.createChooser(i,"") );
            //startActivity(i);
        }
    }




}
