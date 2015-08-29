package com.smartkidslovemaths;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionOptions implements Parcelable{
	protected QuestionOptions(Parcel in) {
		this.operationType = OperationType.valueOf(in.readString());
		this.digitCount = in.readInt();
		this.timerEnabled = in.readByte() != 0;
	}

	public static final Creator<QuestionOptions> CREATOR = new Creator<QuestionOptions> () {
		@Override
		public QuestionOptions createFromParcel(Parcel in) {
			return new QuestionOptions (in);
		}

		@Override
		public QuestionOptions[] newArray(int size) {
			return new QuestionOptions[size];
		}
	};

	public QuestionOptions (OperationType operationType, int digitCount, boolean timerEnabled) {
		setOperationType (operationType);
		setDigitCount (digitCount);
		setTimerEnabled (timerEnabled);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.operationType.name());
		dest.writeInt(this.digitCount);
		dest.writeByte((byte) (this.timerEnabled ? 1 : 0));
	}

	@Override
	public int hashCode () {
		int value;
		switch (operationType) {
			case ADDITION:
				value = 0;
				break;
			case SUBTRACTION:
				value = 4;
				break;
			case ADD_AND_SUB:
				value = 8;
				break;
			case MULTIPLICATION:
				value = 12;
				break;
			default:
				value = 0;
				break;
		}
		value += digitCount;
		if (timerEnabled)
			value += 100;
		return value;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof QuestionOptions && o.hashCode () == this.hashCode ();

	}

	public enum OperationType {
		ADDITION,
		SUBTRACTION,
		ADD_AND_SUB,
		MULTIPLICATION
	}

	public boolean isTimerEnabled() {
		return timerEnabled;
	}

	public void setTimerEnabled(boolean timerEnabled) {
		this.timerEnabled = timerEnabled;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public int getDigitCount() {
		return digitCount;
	}

	public void setDigitCount(int digitCount) {
		this.digitCount = digitCount;
	}

	private OperationType operationType;
	private int digitCount;
	private boolean timerEnabled;
}
