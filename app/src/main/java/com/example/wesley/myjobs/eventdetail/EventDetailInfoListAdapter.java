package com.example.wesley.myjobs.eventdetail;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.model.Event;
import com.example.wesley.myjobs.model.Info;
import com.example.wesley.myjobs.util.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/10/16.
 */
public class EventDetailInfoListAdapter extends BaseAdapter {
    private List<Info> list;

    public EventDetailInfoListAdapter(List<Info> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @BindView(R.id.txtInfoTitle) AppCompatTextView txtInfoTitle;
    @BindView(R.id.txtInfoValue) AppCompatTextView txtInfoValue;

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.info_list_item, null);
        }
        ButterKnife.bind(this, view);
        Info info = (Info) getItem(i);

        txtInfoTitle.setText(info.getLabel());
        txtInfoValue.setText(StringUtil.getStringFromArray(info.getValue()));

        return view;
    }
}
