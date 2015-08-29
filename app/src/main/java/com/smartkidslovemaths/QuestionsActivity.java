package com.smartkidslovemaths;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartkidslovemaths.util.CountDown;
import com.smartkidslovemaths.util.CountDownListener;
import com.smartkidslovemaths.util.QuestionOptionMaps;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QuestionsActivity extends Activity {
	private QuestionOptions options;
	private Question current;
	private Queue<Question> questions;
	private Queue<Boolean> questionsCorrect;
	private CountDown timer;

	TextView questionText, timerText;
	Button choice1, choice2, choice3, choice4;

	public static class Results {
		public static QuestionResults getResults() {
			return results;
		}

		public static void setResults(QuestionResults results) {
			Results.results = results;
		}

		private static QuestionResults results;

		private Results () {}
	}

	public static class Options {
		public static QuestionOptions getOptions () {
			return options;
		}

		private static void setOptions (QuestionOptions o) {
			options = o;
		}

		private static QuestionOptions options;

		private Options () {}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_questions);

		questionText = (TextView) findViewById (R.id.question_text);
		timerText = (TextView)findViewById (R.id.timer_text);
		choice1 = (Button) findViewById (R.id.choice1);
		choice2 = (Button) findViewById (R.id.choice2);
		choice3 = (Button) findViewById (R.id.choice3);
		choice4 = (Button) findViewById (R.id.choice4);

		options = getIntent ().getParcelableExtra ("com.smartkidslovemaths.option");
		Options.setOptions (options);

		if (options.isTimerEnabled ()) {
			timer = new CountDown (timerText,
					QuestionOptionMaps.getOptionsTimerMap ()
							.get (options));
			timer.setCountDownListener (new CountDownListener () {
				@Override
				public void onCountDownFinished() {
					Resources res = getResources ();
					Toast.makeText (
							QuestionsActivity.this,
							res.getText (R.string.times_up_text),
							Toast.LENGTH_LONG).show ();
					finish ();
				}
			});
			timerText.setVisibility (View.VISIBLE);
		}

		questions = new LinkedList<> ();
		questionsCorrect = new LinkedList<> ();

		generateAQuestion ();

		if (timer != null) {
			timer.startCountDown ();
		}
	}

	public void generateAQuestion() {
		switch (options.getOperationType ()) {
			case ADDITION:
				current = generateAddition ();
				break;
			case SUBTRACTION:
				current = generateSubtraction ();
				break;
			case ADD_AND_SUB:
				current = generateAddAndSub ();
				break;
			case MULTIPLICATION:
				current = generateMultiplication ();
				break;
		}
		questions.add (current);
		questionText.setText (current.toString ());
		Button[] choices = {choice1, choice2, choice3, choice4};
		int[] answers = generateAnswers (current.getAnswer ());
		for (int i = 0; i < choices.length; i++) {
			choices[i].setText (Integer.toString (answers[i]));
		}
	}

	private int generateNumberWithDigitCount() {
		int minValue = (int) Math.pow (10, options.getDigitCount () - 1);
		int maxValue = (int) Math.pow (10, options.getDigitCount ()) - 1;
		Random r = new Random ();
		return r.nextInt (maxValue - minValue + 1) + minValue;
	}

	private Question generateAddition() {
		int operand1, operand2;
		operand1 = generateNumberWithDigitCount ();
		operand2 = generateNumberWithDigitCount ();
		return new Question (operand1, operand2);
	}

	private Question generateSubtraction() {
		int operand1 = generateNumberWithDigitCount ();
		Random r = new Random ();
		int operand2 = -(r.nextInt (operand1));

		if (operand2 == 0) {
			operand2 = -1;
		}

		return new Question (operand1, operand2);
	}

	private Question generateAddAndSub() {
		Question firstPart;
		if (Math.random () > 0.5) {
			firstPart = generateAddition ();
		} else {
			firstPart = generateSubtraction ();
		}
		int[] operands = new int[3];
		operands[0] = firstPart.getOperands ()[0];
		operands[1] = firstPart.getOperands ()[1];

		if (Math.random () > 0.5) {
			Random r = new Random ();
			operands[2] = -(r.nextInt (firstPart.getAnswer ()));
		} else {
			operands[2] = generateNumberWithDigitCount ();
		}

		return new Question (operands);
	}

	private MultiplicationQuestion generateMultiplication() {
		return new MultiplicationQuestion (generateNumberWithDigitCount (), generateNumberWithDigitCount ());
	}

	private int[] generateAnswers(int correctAnswer) {
		int[] answers = new int[4];
		Random r = new Random ();
		int correctAnswerIndex = r.nextInt (4);
		answers[correctAnswerIndex] = correctAnswer;
		for (int i = 0; i < answers.length; i++) {
			if (i == correctAnswerIndex) {
				continue;
			}

			int multiplier = options.getDigitCount () > 1? 10 : 1;
			if (Math.random () > 0.5) {
				answers[i] = correctAnswer + multiplier * (i + 1);
			} else {
				int candidate = correctAnswer - multiplier * (i + 1);
				if (candidate < 0) {
					candidate = correctAnswer + multiplier * (i + 5);
				}
				answers[i] = candidate;
			}
		}
		return answers;
	}

	public void choicesOnClick(View view) {
		if (questions.size () != questionsCorrect.size ()) {
			if (((Button) view).getText ().equals (Integer.toString (current.getAnswer ()))) {
				questionsCorrect.add (true);
			} else {
				questionsCorrect.add (false);
			}
		}

		if (getQuestionCount () < 10) {
			generateAQuestion ();
		} else {
			if (questions.size () != questionsCorrect.size ())
				throw new AssertionError ();
			Results.setResults (new QuestionResults (questions, questionsCorrect));

			if (timer != null) {
				timer.pauseCountDown ();
			}

			Intent i = new Intent (this, ResultsActivity.class);
			startActivity (i);
			finish ();
		}
	}

	public int getQuestionCount() {
		return questions.size ();
	}

	@Override
	public void onRestart () {
		super.onRestart ();
		finish ();
	}
}
