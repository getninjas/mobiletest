package com.example.wesley.myjobs;

/**
 * Created by wesley on 9/9/16.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    void showLoading();
    void hideLoading();
    void showError(Throwable e);
}
