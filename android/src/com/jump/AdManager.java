package com.jump;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by jakub on 02.06.18.
 */

public class AdManager {

    protected final String ADMOB_APP_UID = "ca-app-pub-5700920242888376~6807377351";
    protected final String TEST_AD_UID = "ca-app-pub-3940256099942544/6300978111";

    private AdView adView;
    private Activity parent;

    public final int SHOW_ADS = 1;
    public final int HIDE_ADS = 0;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS:
                    setAdVisibility(true);
                    break;
                case HIDE_ADS:
                    setAdVisibility(false);
                    break;
            }
        }
    };

    public AdManager(Activity parent) {
        this.parent = parent;
        MobileAds.initialize(parent, ADMOB_APP_UID);
    }

    public void setAds(RelativeLayout layout) {
        adView = createAdView(parent);
        layout.addView(adView, createAdParams());
    }

    public void loadAds() {
        adView.loadAd(new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());
    }

    public void setAdVisibility(boolean visible) {
        adView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private AdView createAdView(Activity parent) {
        final AdView adView = new AdView(parent);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setFitsSystemWindows(true);
        adView.setAdUnitId(TEST_AD_UID);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                int visibility = adView.getVisibility();
                adView.setVisibility(View.GONE);
                adView.setVisibility(visibility);
            }
        });
        return adView;
    }

    private RelativeLayout.LayoutParams createAdParams() {
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        return adParams;
    }

    public void pause() {
        if (adView != null) {
            adView.pause();
        }
    }

    public void resume() {
        if (adView != null) {
            adView.resume();
        }
    }
}
