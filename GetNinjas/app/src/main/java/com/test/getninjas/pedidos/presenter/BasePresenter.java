package com.test.getninjas.pedidos.presenter;


public interface BasePresenter<V> {
    void attachView(V view);

    void detachView();
}
