package com.smartkidslovemaths.util;

import com.smartkidslovemaths.QuestionOptions;
import com.smartkidslovemaths.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class QuestionOptionMaps {
	/*
	* What I need in this class:
	* 1. A list of questions options
	* 2. A map of question options and drawable id
	* 3. A map of question options and shared preferences keys
	* 4. A map of question options and timer initial values
	*/

	private QuestionOptionMaps () {}

	public static ArrayList<QuestionOptions> getOptionsList() {
		return optionsList;
	}

	public static HashMap<QuestionOptions, Integer> getOptionsDrawableMap() {
		return optionsDrawableMap;
	}

	public static HashMap<QuestionOptions, String> getOptionsKeysMap() {
		return optionsKeysMap;
	}

	public static HashMap<QuestionOptions, Integer> getOptionsTimerMap() {
		return optionsTimerMap;
	}

	private static ArrayList<QuestionOptions> optionsList;
	private static HashMap<QuestionOptions, Integer> optionsDrawableMap;
	private static HashMap<QuestionOptions, String> optionsKeysMap;
	private static HashMap<QuestionOptions, Integer> optionsTimerMap;

	static {
		optionsList = new ArrayList<> ();
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADDITION, 1, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADDITION, 2, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADDITION, 3, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADDITION, 1, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADDITION, 2, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADDITION, 3, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.SUBTRACTION, 1, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.SUBTRACTION, 2, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.SUBTRACTION, 3, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.SUBTRACTION, 1, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.SUBTRACTION, 2, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.SUBTRACTION, 3, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADD_AND_SUB, 1, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADD_AND_SUB, 2, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADD_AND_SUB, 3, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADD_AND_SUB, 1, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADD_AND_SUB, 2, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.ADD_AND_SUB, 3, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.MULTIPLICATION, 1, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.MULTIPLICATION, 2, false));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.MULTIPLICATION, 1, true));
		optionsList.add (new QuestionOptions (QuestionOptions.OperationType.MULTIPLICATION, 2, true));

		ArrayList<String> prefKeyArray = new ArrayList<> ();
		prefKeyArray.add ("p110");
		prefKeyArray.add ("p120");
		prefKeyArray.add ("p130");
		prefKeyArray.add ("p111");
		prefKeyArray.add ("p121");
		prefKeyArray.add ("p131");
		prefKeyArray.add ("p210");
		prefKeyArray.add ("p220");
		prefKeyArray.add ("p230");
		prefKeyArray.add ("p211");
		prefKeyArray.add ("p221");
		prefKeyArray.add ("p231");
		prefKeyArray.add ("p310");
		prefKeyArray.add ("p320");
		prefKeyArray.add ("p330");
		prefKeyArray.add ("p311");
		prefKeyArray.add ("p321");
		prefKeyArray.add ("p331");
		prefKeyArray.add ("p410");
		prefKeyArray.add ("p420");
		prefKeyArray.add ("p411");
		prefKeyArray.add ("p421");
		optionsKeysMap = getHashMapFromCollections (optionsList, prefKeyArray);

		ArrayList<Integer> idArray = new ArrayList<> ();
		idArray.add (R.drawable.p110);
		idArray.add (R.drawable.p120);
		idArray.add (R.drawable.p130);
		idArray.add (R.drawable.p111);
		idArray.add (R.drawable.p121);
		idArray.add (R.drawable.p131);
		idArray.add (R.drawable.p210);
		idArray.add (R.drawable.p220);
		idArray.add (R.drawable.p230);
		idArray.add (R.drawable.p211x);
		idArray.add (R.drawable.p221);
		idArray.add (R.drawable.p231);
		idArray.add (R.drawable.p310);
		idArray.add (R.drawable.p320x);
		idArray.add (R.drawable.p330);
		idArray.add (R.drawable.p311x);
		idArray.add (R.drawable.p321);
		idArray.add (R.drawable.p331);
		idArray.add (R.drawable.p410);
		idArray.add (R.drawable.p420);
		idArray.add (R.drawable.p411);
		idArray.add (R.drawable.p421x);
		optionsDrawableMap = getHashMapFromCollections (optionsList, idArray);

		ArrayList<Integer> timeList = new ArrayList<> ();
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (20);
		timeList.add (60);
		timeList.add (60);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (20);
		timeList.add (30);
		timeList.add (60);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (25);
		timeList.add (80);
		timeList.add (120);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (Integer.MIN_VALUE);
		timeList.add (20);
		timeList.add (195);
		optionsTimerMap = getHashMapFromCollections (optionsList, timeList);
	}

	private static <K, V> HashMap<K, V> getHashMapFromCollections (Collection<K> keys, Collection<V> values) {
		if (keys.size () != values.size ())
			throw new AssertionError ();
		HashMap<K, V> map = new HashMap<> ();
		K[] keyArray = (K[])keys.toArray ();
		V[] valueArray = (V[])values.toArray ();
		for (int i = 0 ; i < keys.size () ; i++) {
			map.put (keyArray[i], valueArray[i]);
		}
		return map;
	}
}
