package com.example.wesley.myjobs.leaddetail;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.wesley.myjobs.R;
import com.example.wesley.myjobs.model.Info;
import com.example.wesley.myjobs.model.User;
import com.example.wesley.myjobs.util.StringUtil;

import java.util.List;

/**
 * Created by wesley on 9/10/16.
 */
public class LeadDetailListAdapter extends BaseAdapter {

    private List<Object> list;

    public LeadDetailListAdapter(List<Object> list) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Object obj = getItem(i);
        LayoutInflater layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(obj instanceof Info) {
            view = layoutInflater.inflate(R.layout.info_list_item, null);
            configureInfoView(view, (Info)obj);
        } else if (obj instanceof User) {
            view = layoutInflater.inflate(R.layout.contact_list_item, null);
            configureContactView(view, (User)obj);
        } else if(obj instanceof List) {
            view = layoutInflater.inflate(R.layout.lead_button_list_item, null);
            List<String> phoneList = (List<String>)obj;
            configureButtonView(view, phoneList.get(0));
        }

        return view;
    }

    private void configureInfoView(View view, Info info) {
        AppCompatTextView txtInfoTitle = (AppCompatTextView) view.findViewById(R.id.txtInfoTitle);
        AppCompatTextView txtInfoValue = (AppCompatTextView) view.findViewById(R.id.txtInfoValue);

        txtInfoTitle.setText(info.getLabel());
        txtInfoValue.setText(StringUtil.getStringFromArray(info.getValue()));
    }

    private void configureContactView(View view, User user) {
        AppCompatTextView txtPhones = (AppCompatTextView) view.findViewById(R.id.txtPhones);
        AppCompatTextView txtEmail = (AppCompatTextView) view.findViewById(R.id.txtEmail);

        txtEmail.setText(user.getEmail());
        txtPhones.setText(StringUtil.getStringFromList(user.getPhones()));
    }

    private void configureButtonView(View view, final String phone) {
        AppCompatImageButton btnPhone = (AppCompatImageButton) view.findViewById(R.id.btnPhone);
        AppCompatImageButton btnWhatsapp = (AppCompatImageButton) view.findViewById(R.id.btnWhatsapp);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(view.getContext(),  Manifest.permission.CALL_PHONE);
                if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    view.getContext().startActivity(intent);
                }
            }
        });

        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = view.getContext().getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "Olá!";

                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    view.getContext().startActivity(Intent.createChooser(waIntent, view.getContext().getString(R.string.intent_title_share_with)));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(view.getContext(), view.getContext().getString(R.string.intent_error_whatsapp_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
