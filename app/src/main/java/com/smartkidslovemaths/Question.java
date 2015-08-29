package com.smartkidslovemaths;

public class Question {
	public Question (int... operands) {
		this.operands = operands;
	}

	public int[] getOperands() {
		return operands;
	}

	public int getAnswer () {
		int sum = 0;
		for (int i = 0 ; i < getOperands ().length ; i++) {
			sum += getOperands ()[i];
		}
		return sum;
	}

	@Override
	public String toString () {
		StringBuilder builder = new StringBuilder ("");
		for (int i = 0 ; i < operands.length ; i++) {
			if (i == 0) {
				builder.append (operands[i]);
				builder.append (" ");
				continue;
			}

			if (operands[i] < 0) {
				builder.append ("- ");
				builder.append (Math.abs (operands[i]));
				builder.append (" ");
			} else {
				builder.append ("+ ");
				builder.append (operands[i]);
				builder.append (" ");
			}
		}
		builder.append ("= ?");
		return builder.toString ();
	}

	private int[] operands;
}
