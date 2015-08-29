package com.smartkidslovemaths.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.widget.TextView;

import com.smartkidslovemaths.R;

public class CountDown {
	private Handler handler;
	private int timeLeft;
	private TextView textView;
	private boolean paused;
	private CountDownListener listener;

	private Runnable countDownTask = new Runnable () {
		@Override
		public void run() {
			if (!paused) {
				timeLeft--;
				displayTime ();
				handler.postDelayed (this, 100);
				if (timeLeft == 0) {
					pauseCountDown ();
					if (listener != null)
						listener.onCountDownFinished ();
				}
			}
		}
	};

	private void displayTime () {
		Context context = textView.getContext ();
		String timerPrefix = context.getResources ().getString (R.string.time_left_text);
		textView.setText (timerPrefix + (Double.toString (timeLeft / 10.0)));
		if (timeLeft < 100) {
			textView.setTextColor (Color.RED);
		}
	}

	public void pauseCountDown () {
		paused = true;
	}

	public void startCountDown () {
		paused = false;
		handler.post (countDownTask);
	}

	public void setCountDownListener (CountDownListener listener) {
		this.listener = listener;
	}

	public CountDown (TextView tv, int seconds) {
		textView = tv;
		timeLeft = seconds * 10;
		handler = new Handler ();
	}
}
