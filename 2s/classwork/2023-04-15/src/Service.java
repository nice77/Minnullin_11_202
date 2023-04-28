import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Service {
    // прохождение теста
    public static void ansOutput() throws IOException {
        int[] outputArr = new int[48];
        int questionNum = 0;

        Scanner scFile = new Scanner(new File("answers.tsv"));
        Scanner scInt = new Scanner(System.in);
        FileOutputStream output = new FileOutputStream(new File("output12.dat"));
        int answer;
        byte input = 0, userId = 12;
        output.write(userId);
        long currTime = System.currentTimeMillis();

        while (scFile.hasNextLine()) {
            for (int k = 0; k < 4; k++) {
                String[] line = scFile.nextLine().split("\t");
                System.out.print(questionNum + 1 + ") ");
                for (int i = 0; i < 5; i++) {
                    System.out.println((i == 0) ? line[i] : i - 1 + ". " + line[i]);
                }
                answer = scInt.nextInt();
                outputArr[questionNum] = answer;
                questionNum++;
                input = (byte) (((byte) (input << 2)) + answer);
                System.out.println("###############");
            }
            output.write(input);
            input = 0;
        }
        System.out.println(Arrays.toString(outputArr));
        int time = (int) (System.currentTimeMillis() - currTime);
        output.write(getBytesFromInt(time));
        System.out.println(Arrays.toString(getBytesFromInt(time)));
        System.out.println("Time: " + time);
        output.close();
    }

    private static byte[] getBytesFromInt(int val) {
        byte[] out = new byte[4];
        out[0] = (byte) (val >> 24);
        out[1] = (byte) (val >> 16);
        out[2] = (byte) (val >> 8);
        out[3] = (byte) (val);
        return out;
    }
    // сканирование ответов других
    public static User ansScanner(int number) throws IOException {
        FileInputStream fis = new FileInputStream("answers/output" + (number > 9 ? number : "0" + number) + ".data");
        int i;
        byte bitMask = 3, space;

        int id = fis.read();
        ArrayList<Integer> answers = new ArrayList<>(48);
        int time = 0;

        for (int ans = 0; ans < 12; ans++) {
            i = fis.read();
            space = 6;
            for (int k = 0; k < 4; k++) {
                answers.add(((((byte) i) >> space) & bitMask));
                space -= 2;
            }
        }

        while ((i = fis.read()) != -1) {
            time <<= 8;
            time += i;
        }
        fis.close();

        return new User(id, answers, time);
    }

    public static List<User> userCreator() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 1; i < 24; i++) {
            try {
                users.add(Service.ansScanner(i));
            }
            catch (IOException e) {
                System.out.println("No file with " + (i + 1) + " id");
            }
        }
        return users;
    }

    public static List<Question> questionCreator(UserList ul) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            questions.add(new Question(i, getQuestion(ul, i)));
        }
        return questions;
    }

    private static List<Integer> getQuestion(UserList ul, int qID) {
        return ul.getUsers().stream().map(u -> u.getCurrentAnswer(qID)).collect(Collectors.toList());
    }
}
