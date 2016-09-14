package com.example.wesley.myjobs.customview;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.wesley.myjobs.R;

public class DialogSpinner extends DialogFragment {

    private static DialogSpinner instance;
    private boolean isVisible = false;
    public DialogSpinner() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());

        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);

        return dialog;
    }

    public static DialogSpinner getInstance() {
        if(instance == null) {
            instance = new DialogSpinner();
        }
        return instance;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        isVisible = true;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        isVisible = false;
    }

    public boolean getIsVisible() {
        return isVisible;
    }
}

