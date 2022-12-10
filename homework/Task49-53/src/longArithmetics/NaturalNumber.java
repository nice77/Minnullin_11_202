package longArithmetics;

class NaturalNumber extends Number {
	public NaturalNumber(String num) {
		super(num);
	}
	public NaturalNumber(int[] num, int size) {
		super(num, size);
	}
	public NaturalNumber add(NaturalNumber other) {
		int[] out = new int[Math.max(this.number.length, other.number.length)];
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
			else {
				out[i] += this.number[i];
			}
			size++;
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
		int size = 0;
		for (int i = 0; i < this.size; i++) {
			out[i] += this.number[i] * digit;
			out[i + 1] += out[i] / 10;
			out[i] %= 10;
			size++;
		}
		size = (out[size] == 0) ? size : size + 1;
		for (int i = size; i < this.number.length; i++) {
			out[i] = 0;
		}
		return new NaturalNumber(out, size);
	}
	public NaturalNumber mul(NaturalNumber other) {
		/*int[] out = new int[this.number.length + other.number.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = (i < this.size) ? this.number[i] : 0;
		}
		NaturalNumber temp;
		NaturalNumber outNum = this.mulOnDigit(other.number[0]);
		for (int i = 1; i < other.number.length; i++) {
			temp = this.mulOnDigit(other.number[i]);
			System.out.println("I: " + i + "; temp: " + temp + "; other[i]: " + other.number[i]);
			for (int j = 0; j < i; j++) {
				temp = temp.mulOnDigit(10);
			}
			System.out.println("I: " + i + "; temp: " + temp + "; other[i]: " + other.number[i]);
			outNum = outNum.add(temp);
		}
		return outNum;*/
		int[] out = new int[this.number.length + other.number.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = (i < this.size) ? this.number[i] : 0;
		}
	}
}
