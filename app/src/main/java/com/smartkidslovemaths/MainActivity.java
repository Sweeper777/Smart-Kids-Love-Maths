package com.smartkidslovemaths;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class MainActivity extends Activity {
	RadioButton rbDigit1, rbDigit2, rbDigit3;
	RadioButton rbOperation1, rbOperation2, rbOperation3, rbOperation4;
	CheckBox cbTimerEnabled;
	Button btnStart, btnTrophy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);
		rbDigit1 = (RadioButton)findViewById (R.id.rb1_number_of_digits);
		rbDigit2 = (RadioButton)findViewById (R.id.rb2_number_of_digits);
		rbDigit3 = (RadioButton)findViewById (R.id.rb3_number_of_digits);

		rbOperation1 = (RadioButton)findViewById (R.id.rb1_operator);
		rbOperation2 = (RadioButton)findViewById (R.id.rb2_operator);
		rbOperation3 = (RadioButton)findViewById (R.id.rb3_operator);
		rbOperation4 = (RadioButton)findViewById (R.id.rb4_operator);

		cbTimerEnabled = (CheckBox)findViewById (R.id.cb_timer_enabled);

		btnStart = (Button)findViewById (R.id.btn_start);
		btnTrophy = (Button)findViewById (R.id.btn_trophy);

		btnStart.setOnClickListener (new View.OnClickListener () {
			@Override
			public void onClick(View v) {
				QuestionOptions.OperationType operation;
				if (rbOperation1.isChecked ()) {
					operation = QuestionOptions.OperationType.ADDITION;
				} else if (rbOperation2.isChecked ()) {
					operation = QuestionOptions.OperationType.SUBTRACTION;
				}  else if (rbOperation3.isChecked ()) {
					operation = QuestionOptions.OperationType.ADD_AND_SUB;
				} else if (rbOperation4.isChecked ()) {
					operation = QuestionOptions.OperationType.MULTIPLICATION;
				} else {
					operation = QuestionOptions.OperationType.ADDITION;
				}

				int digitCount;
				if (rbDigit1.isChecked ()) {
					digitCount = 1;
				} else if (rbDigit2.isChecked ()) {
					digitCount = 2;
				}  else if (rbDigit3.isChecked ()) {
					digitCount = 3;
				} else {
					digitCount = 1;
				}

				QuestionOptions options = new QuestionOptions (
						operation, digitCount,
						cbTimerEnabled.isChecked ());
				Intent i = new Intent (MainActivity.this, QuestionsActivity.class);
				i.putExtra ("com.smartkidslovemaths.option", options);
				startActivity (i);
			}
		});
		rbOperation4.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					MainActivity.this.rbDigit3.setEnabled (false);
					if (MainActivity.this.rbDigit3.isChecked ())
						MainActivity.this.rbDigit2.setChecked (true);
				} else {
					MainActivity.this.rbDigit3.setEnabled (true);
				}
			}
		});

		btnTrophy.setOnClickListener (new View.OnClickListener () {
			@Override
			public void onClick(View v) {
				Intent i = new Intent (MainActivity.this, PrizeActivity.class);
				startActivity (i);
			}
		});
	}
}
