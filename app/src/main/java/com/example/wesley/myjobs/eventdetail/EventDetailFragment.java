package com.example.wesley.myjobs.eventdetail;

import android.location.Location;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListViewCompat;

import com.example.wesley.myjobs.BaseFragment;
import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.model.Address;
import com.example.wesley.myjobs.model.Event;

import butterknife.BindView;

/**
 * Created by wesley on 9/9/16.
 */
public abstract class EventDetailFragment extends BaseFragment implements EventDetailContract.View {
    protected EventDetailContract.Presenter presenter;
    protected String detailUrl;
    @BindView(R.id.txtUserName)
    protected AppCompatTextView txtUserName;
    @BindView(R.id.txtUserLocation)
    protected AppCompatTextView txtUserLocation;
    @BindView(R.id.txtUserDistance)
    protected AppCompatTextView txtUserDistance;
    @BindView(R.id.listViewInfo)
    protected ListViewCompat listViewInfo;

    protected Location location;
    protected Event event;

    protected abstract void configureView(Event event);

    public void loadDetail() {
        presenter.loadDetail(detailUrl);
    }

    @Override
    public void detailReceived(Event event) {
        configureView(event);
    }

    @Override
    public void setPresenter(EventDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        this.showDialog();
    }

    @Override
    public void hideLoading() {
        this.hideDialog();
    }

    @Override
    public void showError(Throwable e) {
        this.showErrorDialog(e);
    }

    public void updateUserLocation(Location location) {
        this.location = location;
    }

    protected double calculateDistance(double fromLong, double fromLat, double toLong, double toLat) {
        double d2r = Math.PI / 180;
        double dLong = (toLong - fromLong) * d2r;
        double dLat = (toLat - fromLat) * d2r;
        double a = Math.pow(Math.sin(dLat / 2.0), 2) + Math.cos(fromLat * d2r)
                * Math.cos(toLat * d2r) * Math.pow(Math.sin(dLong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367000 * c;
        return Math.round(d);
    }

    protected void configureMap() {
        if(event != null) {
            Address address = event.getAddress();
            ((EventDetailActivity) getActivity()).updateMapPosition(address.getLatitude(), address.getLongitude(), new StringBuilder().append(address.getNeighborhood()).append(" - ").append(address.getCity()).append(" - ").append(address.getUf()).toString());
        }
    }

    protected void configureListAdapter() {
        if(event != null) {
            EventDetailInfoListAdapter adapter = new EventDetailInfoListAdapter(event.getInfo());
            listViewInfo.setAdapter(adapter);
        }
    }

    public void configureDistanceText() {
        if(location != null && event != null) {
            double distance = calculateDistance(location.getLongitude(), location.getLatitude(), event.getAddress().getLongitude(), event.getAddress().getLatitude());
            StringBuilder distanceText = new StringBuilder();
            distanceText.append(getActivity().getString(R.string.user_distance_label_preposition));
            distanceText.append(" ");
            distanceText.append((distance / 1000));
            distanceText.append(" ");
            distanceText.append(getActivity().getText(R.string.user_distance_label_distance));
            txtUserDistance.setText(distanceText);
        }
    }
}
