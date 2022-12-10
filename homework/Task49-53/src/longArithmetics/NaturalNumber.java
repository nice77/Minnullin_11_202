package longArithmetics;

import java.util.Arrays;

class NaturalNumber extends Number {
	public NaturalNumber(String num) {
		super(num);
	}
	public NaturalNumber(int[] num, int size) {
		super(num, size);
	}
	public NaturalNumber add(NaturalNumber other) {
		int[] out = new int[Math.max(this.number.length, other.number.length) * 2];
		int size = 0;
		for (int i = 0; i < out.length; i++) {
			out[i] = 0;
		}
		for (int i = 0; i < Math.min(this.size, other.size); i++) {
			out[i] += this.number[i] + other.number[i];
			out[i + 1] += out[i] / 10;
			out[i] %= 10;
			size++;
		}
		for (int i = Math.min(this.size, other.size); i < Math.max(this.size, other.size); i++) {
			if (i >= this.size) {
				out[i] += other.number[i];
			}
			else if (i >= other.size) {
				out[i] += this.number[i];
			}
			size++;
		}
		if (out[size + 1] != 0) {
			size += 1;
		}
		return new NaturalNumber(out, size);
	}
	public NaturalNumber sub(NaturalNumber other) {
		int[] out = new int[Math.max(this.number.length, other.number.length)];
		int size = 0;
		for (int i = 0; i < out.length; i++) {
			out[i] = 0;
		}
		for (int i = 0; i < Math.min(this.size, other.size); i++) {
			out[i] += (this.number[i] - other.number[i]);
			out[i + 1] = (out[i] < 0) ? -1 : 0;
			out[i] = (out[i] < 0) ? 10 + out[i] : out[i];
		}
		for (int i = Math.min(this.size, other.size); i < Math.max(this.size, other.size); i++) {
			out[i] = (this.size <= i) ? other.number[i] : this.number[i];
			size++;
		}
		return new NaturalNumber(out, size);
	}
	public NaturalNumber mulOnDigit(int digit) {
		int[] out = new int[this.number.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = 0;
		}
		for (int i = 0; i < this.size; i++) {
			out[i] += this.number[i] * digit;
			out[i + 1] += out[i] / 10;
			out[i] %= 10;
		}
		int sizeDiff = this.size;
		if (out[this.size] != 0) {
			sizeDiff += 1;
		}
		if (out.length - sizeDiff < 5) {
			out = appender(out);
		}
		return new NaturalNumber(out, sizeDiff);
	}
	public NaturalNumber mul(NaturalNumber other) {
		NaturalNumber out = this.mulOnDigit(other.number[0]);
		//System.out.println("Diff: " + this.mulOnDigit(other.number[0]));
		NaturalNumber temp;
		for (int i = 1; i < other.size; i++) {
			temp = this.mulOnDigit(other.number[i]);
			for (int j = 0; j < i; j++) {
				temp = temp.mulOnDigit(2).mulOnDigit(5);
			}

			System.out.println("Diff: " + temp);
			out = out.add(temp);
		}
		return out;
	}

	public static int[] appender(int[] arr) {
		int[] out = new int[arr.length + 5];
		for (int i = 0; i < out.length; i++) {
			if (i < arr.length) {
				out[i] = arr[i];
			}
			else {
				out[i] = 0;
			}
		}
		return out;
	}
	public String getData() {
		return Arrays.toString(this.number);
	}
}