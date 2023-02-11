class User{
	private String name;
	private String data;
	public User(String data) {
		this.name = data.split(":")[0];
		this.data = data.split(":")[1];
	}
	public int countPlusses() {
		int out = 0;
		for (int i = 0; i < data.length(); i++) {
			if (data.charAt(i) == '+') {
				out++;
			}
		}
		return out;
	}
	public int[] comparePlusses(User[] users) {
		int[] out = new int[users.length];
		double temp;
		int i = 0;
		for (User user : users) {
			temp = 0;
			for (int j = 0; j < user.data.length(); j++) {
				temp = (user.data.charAt(j) == '+' && this.data.charAt(j) == '+') ? temp + 1 : temp;
			}
			out[i] = (int)((100 * 2.0 * temp) / (user.countPlusses() + this.countPlusses()));
			i++;
		}
		return out;
	}
}
