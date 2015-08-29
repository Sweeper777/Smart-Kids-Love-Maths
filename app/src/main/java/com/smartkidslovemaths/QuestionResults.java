package com.smartkidslovemaths;

import java.util.Queue;

public class QuestionResults {
	
	public QuestionResults(Queue<Question> questions, Queue<Boolean> questionsCorrect) {
		this.questionsCorrect = questionsCorrect;
		this.questions = questions;
	}

	public Queue<Boolean> getQuestionsCorrect() {
		return questionsCorrect;
	}

	public Queue<Question> getQuestions() {
		return questions;
	}

	private Queue<Boolean> questionsCorrect;
	private Queue<Question> questions;
}
