package com.smartkidslovemaths;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartkidslovemaths.util.QuestionOptionMaps;

public class ResultsActivity extends Activity {
	private QuestionResults results;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_results);
		results = QuestionsActivity.Results.getResults ();

		loadResults ();
	}

	private void loadResults() {
		int correctCount = 0;
		for (Boolean b : results.getQuestionsCorrect ()) {
			if (b) correctCount++;
		}

		TextView scoreValue = (TextView)findViewById (R.id.score_value);
		double totalScore = (double)correctCount / (double)results.getQuestionsCorrect ().size ();
		totalScore *= 100;
		totalScore = Math.round (totalScore);
		scoreValue.setText (Integer.toString ((int)totalScore));

		if (results.getQuestionsCorrect ().contains (false)) {
			int size = results.getQuestions ().size ();
			for (int i = 0 ; i < size ; i++) {
				Question q = results.getQuestions ().poll ();
				boolean b = results.getQuestionsCorrect ().poll ();
				displayAQuestion (q, b);
			}
		} else {
			displayPrize ();
		}
	}

	private void displayPrize () {
		Resources res = getResources ();
		int marginAll = (int)res.getDimension (R.dimen.prize_display_margin);
		FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams (FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		parentParams.setMargins (marginAll, marginAll, marginAll, marginAll);
		((LinearLayout)findViewById (R.id.right_side)).setLayoutParams (parentParams);

		TextView text = new TextView (this);
		final LinearLayout.LayoutParams textParams =
				new LinearLayout.LayoutParams (
						ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
				);

		final LinearLayout.LayoutParams imageParams =
				new LinearLayout.LayoutParams (
						ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
				);
		text.setLayoutParams (textParams);
		QuestionOptions options = QuestionsActivity.Options.getOptions ();

		switch (options.getDigitCount ()) {
			case 1:
			default:
				text.setText (res.getText (R.string.earn_star_text));
				break;
			case 2:
				text.setText (res.getText (R.string.earn_badge_text));
				break;
			case 3:
				text.setText (res.getText (R.string.earn_trophy_text));
				break;
		}

		((LinearLayout)findViewById (R.id.right_side)).addView (text);

		ImageView image = new ImageView (this);
		image.setLayoutParams (imageParams);
		int resID = QuestionOptionMaps.getOptionsDrawableMap ().get (options);
		image.setImageResource (resID);
		image.setAdjustViewBounds (true);
		((LinearLayout)findViewById (R.id.right_side)).addView (image);

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (this);
		String key = QuestionOptionMaps.getOptionsKeysMap ().get (options);
		SharedPreferences.Editor editor = prefs.edit ();
		int oldValue = prefs.getInt (key, 0);
		editor.putInt (key, oldValue + 1).apply ();
	}

	private void displayAQuestion (Question q, boolean isCorrect) {
		Resources res = getResources ();

		//create a parent layout manager
		LinearLayout parent = new LinearLayout (this);
		parent.setOrientation (LinearLayout.VERTICAL);
		LinearLayout.LayoutParams parentParams =
				new LinearLayout.LayoutParams (
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT
				);

		int margin = (int)res.getDimension (R.dimen.question_display_margin);
		parentParams.setMargins (margin, margin, margin, margin);
		parent.setLayoutParams (parentParams);
		parent.setBackgroundColor (Color.parseColor (
				isCorrect? "#831FFF00" : "#83FF4B00"
		));

		//create three child views
		final LinearLayout.LayoutParams textParams =
				new LinearLayout.LayoutParams (
						ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT
				);

		TextView questionText = new TextView (this);
		questionText.setLayoutParams (textParams);
		questionText.setText (q.toString ());
		parent.addView (questionText);

		TextView tvIsCorrect = new TextView (this);
		tvIsCorrect.setLayoutParams (textParams);
		tvIsCorrect.setText (
				isCorrect ? res.getText (R.string.correct_text)
						: res.getText (R.string.wrong_text)
		);
		parent.addView (tvIsCorrect);

		TextView correctAnswer = new TextView (this);
		correctAnswer.setLayoutParams (textParams);
		correctAnswer.setText (
				res.getText (R.string.correct_answer_text) +
						Integer.toString (q.getAnswer ())
		);
		parent.addView (correctAnswer);

		((LinearLayout)findViewById (R.id.right_side)).addView (parent);
	}

	@Override
	public void onRestart () {
		super.onRestart ();
		finish ();
	}
}
