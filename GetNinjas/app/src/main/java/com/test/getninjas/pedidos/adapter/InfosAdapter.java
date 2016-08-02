package com.test.getninjas.pedidos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.getninjas.pedidos.R;
import com.test.getninjas.pedidos.model.data.offer.Info;
import com.test.getninjas.pedidos.utils.DateFormatter;

import java.util.List;

/**
 * Created by vanlopes on 30/07/16.
 */
public class InfosAdapter extends RecyclerView.Adapter<InfosAdapter.InfoViewHolder> {
    private List<Info> infos;
    private Context context;

    public InfosAdapter(List<Info>infos,Context context){
            this.infos=infos;
            this.context=context;
    }

    @Override
    public InfosAdapter.InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView= LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_info,parent,false);

        return new InfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfosAdapter.InfoViewHolder holder,final int position){
        final Info info = infos.get(position);
        holder.tvLabel.setText(info.label);
        holder.tvValue.setText(info.value);
    }

    @Override
    public int getItemCount(){
        return(infos==null)?0:infos.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLabel, tvValue;

        public InfoViewHolder(View view) {
            super(view);
            tvLabel = (TextView) view.findViewById(R.id.tv_label);
            tvValue = (TextView) view.findViewById(R.id.tv_value);
        }
    }
}
