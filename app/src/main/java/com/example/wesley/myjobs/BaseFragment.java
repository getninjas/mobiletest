package com.example.wesley.myjobs;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import com.example.wesley.myjobs.customview.DialogSpinner;
import com.example.wesley.myjobs.util.DialogUtil;

/**
 * Created by wesley on 9/9/16.
 */
public abstract class BaseFragment extends Fragment {
    protected void showDialog() {
        DialogSpinner loadingDialog = DialogSpinner.getInstance();
        if(!loadingDialog.isVisible() && !loadingDialog.isInLayout() && !loadingDialog.isAdded() &&!loadingDialog.getIsVisible())
            loadingDialog.show(getActivity().getFragmentManager(), "loading");
    }

    protected void hideDialog() {
        DialogSpinner loadingDialog = DialogSpinner.getInstance();
        loadingDialog.dismiss();
    }

    protected void showErrorDialog(Throwable e) {
        DialogUtil.showError(getActivity(), e.getLocalizedMessage());
    }

    public abstract void setFabVisibility(FloatingActionButton fab);
}
