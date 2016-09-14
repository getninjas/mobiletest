package com.example.wesley.myjobs;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

/**
 * Created by wesley on 9/10/16.
 */
public class BaseActivity  extends AppCompatActivity {
    private View revealView;

    public static final String REVEAL_X = "REVEAL_X";
    public static final String REVEAL_Y = "REVEAL_Y";

    protected void showRevealEffect(Bundle savedInstanceState, final View rootView) {
        revealView = rootView;

        if(savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rootView.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();

            if(viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity(rootView);

                        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            rootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void circularRevealActivity(View rootView) {
        int cx = getIntent().getIntExtra(REVEAL_X, 0);
        int cy = getIntent().getIntExtra(REVEAL_Y, 0);

        float finalRadius = Math.max(rootView.getWidth(), rootView.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootView, cx, cy, 0, finalRadius);
        circularReveal.setDuration(400);

        rootView.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        destroyActivity(revealView);
    }

    private void destroyActivity(View rootView) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            destroyCircularViewActivity(rootView);
        } else {
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void destroyCircularViewActivity(final View rootView) {
        if(rootView != null) {
            int cx = getIntent().getIntExtra(REVEAL_X, 0);
            int cy = getIntent().getIntExtra(REVEAL_Y, 0);

            float finalRadius = Math.max(rootView.getWidth(), rootView.getHeight());

            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootView, cx, cy, finalRadius, 0);
            circularReveal.setDuration(400);
            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    rootView.setVisibility(View.INVISIBLE);
                    finishAfterTransition();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            rootView.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }

    public void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}