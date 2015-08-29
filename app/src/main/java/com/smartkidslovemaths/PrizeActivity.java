package com.smartkidslovemaths;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartkidslovemaths.util.QuestionOptionMaps;

public class PrizeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_prize);
		for (QuestionOptions option : QuestionOptionMaps.getOptionsList ()) {
			displayAPrize (option);
		}
	}

	private void displayAPrize (QuestionOptions options) {
		Resources res = getResources ();
		int parentMargin = (int)res.getDimension (R.dimen.prize_display_margin);
		LinearLayout.LayoutParams parentParams =
				new LinearLayout.LayoutParams (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
		parentParams.setMargins (parentMargin, parentMargin, parentMargin, parentMargin);

		LinearLayout.LayoutParams imageParams =
				new LinearLayout.LayoutParams (
						ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
				);

		LinearLayout parent = new LinearLayout (this);
		parent.setLayoutParams (parentParams);
		parent.setOrientation (LinearLayout.VERTICAL);
		parent.setGravity (Gravity.CENTER);

		TextView text = new TextView (this);
		text.setLayoutParams (imageParams);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (this);
		String key = QuestionOptionMaps.getOptionsKeysMap ().get (options);
		int prizeCount = prefs.getInt (key, 0);
		text.setText ("x" + prizeCount);
		parent.addView (text);

		ImageView image = new ImageView (this);
		image.setLayoutParams (imageParams);
		int imageId = QuestionOptionMaps.getOptionsDrawableMap ().get (options);
		image.setImageResource (imageId);
		image.setAdjustViewBounds (true);
		parent.addView (image);


		((LinearLayout)findViewById (R.id.trophy_content)).addView (parent);
	}
}
