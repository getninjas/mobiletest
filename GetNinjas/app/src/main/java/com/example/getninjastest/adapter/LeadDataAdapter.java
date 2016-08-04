package com.example.getninjastest.adapter;

/**
 * Created by AsifMoinul on 7/27/2016.
 */

//using for test only

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.getninjastest.R;
import com.example.getninjastest.activity.DetailsActivity;
import com.example.getninjastest.model.lead.Lead;
import com.example.getninjastest.util.Utils;

import java.util.ArrayList;

public class LeadDataAdapter extends RecyclerView.Adapter<LeadDataAdapter.ViewHolder> {
    private ArrayList<Lead> leadArrayList;

    public LeadDataAdapter(ArrayList<Lead> leadArrayList) {
        this.leadArrayList = leadArrayList;
    }

    @Override
    public LeadDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeadDataAdapter.ViewHolder viewHolder, int i) {
        final int position[] = new int[]{i};
        viewHolder.tvTitle.setText(leadArrayList.get(i).getEmbedded().getRequest().getTitle());
        viewHolder.tvUser.setText(leadArrayList.get(i).getEmbedded().getUser().getName());
        viewHolder.tvDate.setText(Utils.getFormattedDate(leadArrayList.get(i).getCreatedAt()));
        viewHolder.tvLocation.setText(leadArrayList.get(i).getEmbedded().getAddress().getCity() +" - " +leadArrayList.get(i).getEmbedded().getAddress().getNeighborhood());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), ""+leadArrayList.get(position[0]).getEmbedded().getRequest().getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("title", "Lead");
                intent.putExtra("href", leadArrayList.get(position[0]).getLinks().getSelf().getHref());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return leadArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvUser, tvDate, tvLocation;
        private CardView cardView;
        public ViewHolder(View view) {
            super(view);
            tvTitle = (TextView)view.findViewById(R.id.tvTitle);
            tvUser = (TextView)view.findViewById(R.id.tvUser);
            tvDate = (TextView)view.findViewById(R.id.tvDate);
            tvLocation = (TextView)view.findViewById(R.id.tvLocation);
            cardView = (CardView)view.findViewById(R.id.dataCard);
        }
    }


}