package com.example.wesley.myjobs.offers;

import android.view.View;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventlist.EventListViewHolder;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Offer;

import java.text.SimpleDateFormat;

/**
 * Created by wesley on 9/9/16.
 */
public class OffersViewHolder extends EventListViewHolder {

    public OffersViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshViewHolder(Event event) {
        this.event = event;
        configureColors(event);
        this.txtEventTitle.setText(event.getTitle());
        this.txtUserName.setText(event.getUser().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        this.txtDate.setText(sdf.format(event.getCreation()));
        this.txtLocation.setText(new StringBuilder().append(event.getAddress().getNeighborhood()).append(" - ").append(event.getAddress().getCity()).append(" - ").append(event.getAddress().getUf()));
    }

    private void configureColors(Event event) {
        Offer offer = (Offer) event;
        if(offer.getState().equals("unread")) {
            this.titleContainer.setBackgroundColor(itemView.getResources().getColor(R.color.colorUnreadTitleBar));
            this.imgUserIcon.setImageResource(R.drawable.ic_user_gray);
            this.imgCalendarIcon.setImageResource(R.drawable.ic_calendar_gray);
            this.imgLocationIcon.setImageResource(R.drawable.ic_location_gray);
        } else {
            this.titleContainer.setBackgroundColor(itemView.getResources().getColor(R.color.colorOfferTitleBar));
            this.imgUserIcon.setImageResource(R.drawable.ic_user_orange);
            this.imgCalendarIcon.setImageResource(R.drawable.ic_calendar_orange);
            this.imgLocationIcon.setImageResource(R.drawable.ic_location_orange);
        }
    }
}
