package control.medicine;

import java.util.Arrays;
import java.util.Random;

abstract class Illness {
	protected String name;
	protected String cure;
	protected String[] sympthoms;
	protected int worseness;
	protected Random r;
	public Illness(String name, String cure, String[] sympthoms) {
		this.name = name;
		this.cure = cure;
		this.sympthoms = sympthoms;
		this.worseness = 10;
		this.r = new Random();
	}
	public String getIllnessName() {
		return this.name;
	}
	public String getCure() {
		return this.cure;
	}
	public String getSympthoms() {
		return Arrays.toString(this.sympthoms);
	}
}
