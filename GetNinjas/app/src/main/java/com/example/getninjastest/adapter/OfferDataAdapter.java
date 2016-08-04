package com.example.getninjastest.adapter;

/**
 * Created by AsifMoinul on 7/27/2016.
 */

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getninjastest.R;
import com.example.getninjastest.activity.DetailsActivity;
import com.example.getninjastest.model.offer.Offer;
import com.example.getninjastest.util.Utils;

import java.util.ArrayList;

public class OfferDataAdapter extends RecyclerView.Adapter<OfferDataAdapter.ViewHolder> {
    private ArrayList<Offer> offerArrayList;

    public OfferDataAdapter(ArrayList<Offer> offerArrayList) {
        this.offerArrayList = offerArrayList;
    }

    @Override
    public OfferDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OfferDataAdapter.ViewHolder viewHolder, int i) {
        //ugly fix need to be changed later
        final int position[] = new int[]{i};
        if(offerArrayList.get(i).getState().equalsIgnoreCase("read")){
            viewHolder.viewStatusRead.setVisibility(View.VISIBLE);
            viewHolder.viewStatusUnread.setVisibility(View.GONE);
        }else {
            viewHolder.viewStatusRead.setVisibility(View.GONE);
            viewHolder.viewStatusUnread.setVisibility(View.VISIBLE);
        }
        viewHolder.tvTitle.setText(offerArrayList.get(i).getEmbeddedRequest().getRequest().getTitle());
        viewHolder.tvUser.setText(offerArrayList.get(i).getEmbeddedRequest().getRequest().getEmbedded().getUser().getName());
        viewHolder.tvDate.setText(Utils.getFormattedDate(offerArrayList.get(i).getEmbeddedRequest().getRequest().getCreatedAt()));
        viewHolder.tvLocation.setText(offerArrayList.get(i).getEmbeddedRequest().getRequest().getEmbedded().getAddress().getCity() +" - " +offerArrayList.get(i).getEmbeddedRequest().getRequest().getEmbedded().getAddress().getNeighborhood());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), ""+offerArrayList.get(position[0]).getEmbeddedRequest().getRequest().getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("title", "Offer");
                intent.putExtra("href", offerArrayList.get(position[0]).getLinks().getSelf().getHref());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvUser, tvDate, tvLocation;
        private View viewStatusRead, viewStatusUnread;
        private CardView cardView;
        public ViewHolder(View view) {
            super(view);
            viewStatusRead = view.findViewById(R.id.viewStatusRead);
            viewStatusUnread = view.findViewById(R.id.viewStatusUnRead);
            tvTitle = (TextView)view.findViewById(R.id.tvTitle);
            tvUser = (TextView)view.findViewById(R.id.tvUser);
            tvDate = (TextView)view.findViewById(R.id.tvDate);
            tvLocation = (TextView)view.findViewById(R.id.tvLocation);
            cardView = (CardView)view.findViewById(R.id.dataCard);
        }
    }


}