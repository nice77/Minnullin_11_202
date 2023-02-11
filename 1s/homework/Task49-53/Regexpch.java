import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regexpch {
	public static void main(String[] args) {
		Pattern p = Pattern.compile(args[0]);
		Matcher m = p.matcher(args[1]);
		System.out.println(m.matches());
		// group() - группа по имени/индексу
		//while(m.find()) { m.group(); }
	}
}
