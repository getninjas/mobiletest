package com.example.wesley.myjobs.leads;

import android.view.View;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventlist.EventListViewHolder;
import com.example.wesley.myjobs.model.Event;

import java.text.SimpleDateFormat;

/**
 * Created by wesley on 9/9/16.
 */
public class LeadsViewHolder extends EventListViewHolder {

    public LeadsViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void refreshViewHolder(Event event) {
        this.event = event;
        configureColors();
        this.txtEventTitle.setText(event.getTitle());
        this.txtUserName.setText(event.getUser().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        this.txtDate.setText(sdf.format(event.getCreation()));
        this.txtLocation.setText(new StringBuilder().append(event.getAddress().getNeighborhood()).append(" - ").append(event.getAddress().getCity()).append(" - ").append(event.getAddress().getUf()));
    }

    private void configureColors() {
        this.titleContainer.setBackgroundColor(itemView.getResources().getColor(R.color.colorLeadTitleBar));
        this.imgUserIcon.setImageResource(R.drawable.ic_user_blue);
        this.imgCalendarIcon.setImageResource(R.drawable.ic_calendar_blue);
        this.imgLocationIcon.setImageResource(R.drawable.ic_location_blue);
    }
}
