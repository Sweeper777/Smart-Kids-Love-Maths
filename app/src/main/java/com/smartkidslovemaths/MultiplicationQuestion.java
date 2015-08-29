package com.smartkidslovemaths;

public class MultiplicationQuestion extends Question {
	public MultiplicationQuestion (int... operands) {
		super(operands);
	}

	@Override
	public int getAnswer () {
		int result = 1;
		for (int i = 0 ; i < getOperands ().length ; i++) {
			result *= getOperands ()[i];
		}
		return result;
	}

	@Override
	public String toString () {
		StringBuilder builder = new StringBuilder ("");
		for (int i = 0 ; i < getOperands ().length ; i++) {
			builder.append (getOperands ()[i]);
			if (i < getOperands ().length - 1) {
				builder.append (" x ");
			}
		}
		builder.append (" = ?");
		return builder.toString ();
	}
}
