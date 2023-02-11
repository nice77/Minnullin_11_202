package longArithmetics;

import java.util.Arrays;

class Number {
	protected int[] number;
	protected int size;
	public Number(String num) {
		this.number = new int[num.length() * 2];
		this.size = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			this.number[size] = num.charAt(i) - '0';
			this.size++;
		}
	}
	public Number(int[] num, int size) {
		this.number = num;
		this.size = size;
	}
	public String toString() {
		String out = "";
		for (int i = size - 1; i >= 0; i--) {
			out += this.number[i];
		}
		return out;
	}
}
