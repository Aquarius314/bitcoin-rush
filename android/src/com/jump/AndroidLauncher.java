package com.jump;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class AndroidLauncher extends AndroidApplication implements AdHandler {

    protected AdManager adManager;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adManager = new AdManager(this);

		RelativeLayout layout = new RelativeLayout(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        layout.addView(initializeForView(new JumpGame(this)));
        adManager.setAds(layout);
        setContentView(layout);
        adManager.loadAds();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

    @Override
    public void showAds(boolean show) {
        adManager.handler.sendEmptyMessage(show ? adManager.SHOW_ADS : adManager.HIDE_ADS);
    }
}