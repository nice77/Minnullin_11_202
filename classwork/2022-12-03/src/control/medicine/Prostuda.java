package control.medicine;

import java.util.Random;

class Prostuda extends Illness{
	public Prostuda(String name, String cure, String[] sympthoms) {
		super(name, cure, sympthoms);
	}
	public void getWorse() {
		int chance = this.r.nextInt(100);
		this.worseness = (chance <= 60) ? this.worseness + 10 : this.worseness;
	}
}
