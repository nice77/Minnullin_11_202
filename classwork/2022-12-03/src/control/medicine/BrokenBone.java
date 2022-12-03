package control.medicine;

class BrokenBone {
	class BrokenBone(String name, String cure, String[] sympthoms) {
		super(name, cure, sympthoms);
	}
	public void getWorse() {
		int chance = this.r.nextInt(100);
                this.worseness = (chance <= 10) ? this.worseness + 10 : this.worseness;
	}
}
