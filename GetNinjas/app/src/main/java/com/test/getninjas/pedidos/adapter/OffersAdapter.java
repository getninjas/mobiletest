package com.test.getninjas.pedidos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.getninjas.pedidos.R;
import com.test.getninjas.pedidos.model.data.offer.Offer;
import com.test.getninjas.pedidos.utils.DateFormatter;

import java.util.List;


public class OffersAdapter  extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {
    private List<Offer> offers;
    private Context context;
    private OfferButtonClickListener listener;

    public OffersAdapter(List<Offer> offers, Context context, OfferButtonClickListener listener) {
        this.offers = offers;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public OffersAdapter.OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offer, parent, false);

        return new OfferViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OffersAdapter.OfferViewHolder holder, final int position) {
        final Offer offer = offers.get(position);
        if(offer.state == null)
            holder.ivRead.setImageDrawable(context.getResources().getDrawable(R.drawable.green));
        else {
            switch (offer.state) {
                case "read":
                    holder.ivRead.setImageDrawable(context.getResources().getDrawable(R.drawable.blue));
                    break;
                case "unread":
                    holder.ivRead.setImageDrawable(context.getResources().getDrawable(R.drawable.gray));
                    break;
            }
        }
        holder.tvUser.setText(offer.user.name);
        String address = String.format(
                "%s, %s - %s",
                offer.address.neighborhood,
                offer.address.city,
                offer.address.uf);
        holder.tvAddress.setText(address);
        holder.tvTitle.setText(offer.title);
        holder.tvDate.setText(DateFormatter.toFormattedDate(offer.createdAt));
    }

    @Override
    public int getItemCount() {
        return (offers == null) ? 0 : offers.size();
    }



    public class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle, tvDate, tvUser, tvAddress;
        public ImageView ivRead;

        public OfferViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            tvTitle = (TextView)view.findViewById(R.id.tv_title);
            tvDate = (TextView)view.findViewById(R.id.tv_date);
            tvUser = (TextView)view.findViewById(R.id.tv_user);
            tvAddress = (TextView)view.findViewById(R.id.tv_address);
            ivRead = (ImageView)view.findViewById(R.id.iv_read);
        }

        @Override
        public void onClick(View v) {
            listener.onOfferButtonClicked(offers.get(getAdapterPosition()), itemView);
        }
    }

    public interface OfferButtonClickListener {
        void onOfferButtonClicked(Offer offer, View view);
    }
}
