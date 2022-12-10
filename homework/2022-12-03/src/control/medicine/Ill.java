package control.medicine;

class Ill extends Human {
	private Illness illness;
	public Ill(String role, Illness illness) {
		super(role);
		this.illness = illness;
	}
}
