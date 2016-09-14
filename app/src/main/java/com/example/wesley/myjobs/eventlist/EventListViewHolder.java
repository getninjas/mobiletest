package com.example.wesley.myjobs.eventlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.myjobs.BaseActivity;
import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.eventdetail.EventDetailActivity;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Lead;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/9/16.
 */
public abstract class EventListViewHolder extends RecyclerView.ViewHolder {

    protected Event event;
    protected @BindView(R.id.txtEventTitle) AppCompatTextView txtEventTitle;
    protected @BindView(R.id.imgUserIcon) AppCompatImageView imgUserIcon;
    protected @BindView(R.id.txtUserName) AppCompatTextView txtUserName;
    protected @BindView(R.id.imgCalendarIcon) AppCompatImageView imgCalendarIcon;
    protected @BindView(R.id.txtDate) AppCompatTextView txtDate;
    protected @BindView(R.id.imgLocationIcon) AppCompatImageView imgLocationIcon;
    protected @BindView(R.id.txtLocation) AppCompatTextView txtLocation;
    protected @BindView(R.id.titleContainer) ViewGroup titleContainer;
    protected @BindView(R.id.cardViewEvent) CardView cardViewEvent;

    public EventListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        configureClick();
    }

    private void configureClick() {
        cardViewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                cardViewEvent.getLocationOnScreen(location);

                Intent intent = new Intent(cardViewEvent.getContext(), EventDetailActivity.class);

                intent.putExtra(BaseActivity.REVEAL_X, location[0] + (cardViewEvent.getWidth() / 2));
                intent.putExtra(BaseActivity.REVEAL_Y, location[1] + (cardViewEvent.getHeight() / 2));
                intent.putExtra(EventDetailActivity.DETAIL_URL_EXTRA, event.getDetailLink());

                if(event instanceof Lead) {
                    intent.putExtra(EventDetailActivity.EVENT_TYPE_EXTRA, "lead");
                } else {
                    intent.putExtra(EventDetailActivity.EVENT_TYPE_EXTRA, "offer");
                }

                ((Activity)cardViewEvent.getContext()).startActivityForResult(intent, EventListActivity.DETAIL_REQUEST);
            }
        });
    }

    protected abstract void refreshViewHolder(Event event);
}
