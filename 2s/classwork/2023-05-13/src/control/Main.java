package control;

import java.io.*;
import java.util.*;

//вариант 2
public class Main {
    public static void main(String[] args) {
        List<Birth> births = datasetCreator();
        System.out.println("Avg weight by race: " + getStats(births));
        System.out.println("Среднее время вынашивания ребенка у курящих женщин меньше, чем среди некурящих женщин: " + getSmokedLower(births));
        pack(births);

    }

    public static List<Birth> datasetCreator() {
        List<Birth> births = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("controlAssets/BirthsKingCounty2001.txt"));
            String i;
            String[] data;
            while ((i = br.readLine()) != null) {
                i = spaceReplace(i);
                data = i.split(" ");
                births.add(new Birth(data[0].charAt(0), data[3], Integer.parseInt(data[6]), data[11].equals("Y"), Integer.parseInt(data[16]), Integer.parseInt(data[15])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return births;
    }

    public static String spaceReplace(String i) {
        String space = "    ";
        for (int j = 0; j < 4; j++) {
            i = i.replace(space, " ");
            space = (String) space.subSequence(0, space.length() - 1);
        }
        return i;
    }

    public static Map<String, Integer> getStats(List<Birth> births) {
        Map<String, ArrayList<Integer>> stats = new HashMap<>();
        for (Birth birth : births) {
            if (!stats.containsKey(birth.getRace())) {
                stats.put(birth.getRace(), new ArrayList<>());
            }
            stats.get(birth.getRace()).add(birth.getWeight());
        }
        Map<String, Integer> finalStats = new HashMap<>();
        for (String key : stats.keySet()) {
            finalStats.put(key, stats.get(key).stream().reduce(0, Integer::sum) / stats.get(key).size());
        }
        return finalStats;
    }

    public static boolean getSmokedLower(List<Birth> births) {
        double weeksSmoked = 0, smokedCount = 0;
        double weeksNonSmoked = 0, nonSmokedCount = 0;
        for (Birth birth : births) {
            if (birth.isSmoked()) {
                weeksSmoked += birth.getWeeks();
                smokedCount++;
            }
            else {
                weeksNonSmoked += birth.getWeeks();
                nonSmokedCount++;
            }
        }
        return (weeksSmoked / smokedCount) - (weeksNonSmoked / nonSmokedCount) < 1e-8;
    }

    public static void pack(List<Birth> births) {
        int min = births.get(0).getEducationAge(), max = births.get(0).getEducationAge();
        ArrayList<Integer> educationAlph = new ArrayList<>();
        Map<String, Integer> raceMap = new HashMap<>();
        for (Birth birth : births) {
            if (min > birth.getEducationAge()) {
                min = birth.getEducationAge();
            }
            if (max < birth.getEducationAge()) {
                max = birth.getEducationAge();
            }

            if (!educationAlph.contains(birth.getEducationAge())) {
                educationAlph.add(birth.getEducationAge());
            }
            if (!raceMap.containsKey(birth.getRace())) {
                raceMap.put(birth.getRace(), raceMap.keySet().size() + 1);
            }
        }

        System.out.println(educationAlph);
        System.out.println(raceMap);

        int educationRange = max + 1 - min;
        System.out.println("To encode education age range: " + min + "-" + max);
        int raceRange = getStats(births).keySet().size();
        System.out.println("To encode race: " + raceRange);

        int educationBits = (int) Math.ceil(Math.log(educationRange) / Math.log(2));
        System.out.println("Edu Bits: " + educationBits);
        int raceBits = (int) Math.ceil(Math.log(raceRange) / Math.log(2));
        System.out.println("Race Bits: " + raceBits);

        int ceiledEduBits = (int) Math.pow(2, Math.ceil(Math.log(educationBits) / Math.log(2)));
        int ceiledRaceBits = (int) Math.pow(2, Math.ceil(Math.log(raceBits) / Math.log(2)));
        System.out.println("Bits per one education: " + ceiledEduBits);
        System.out.println("Bits per one race " + ceiledRaceBits);
        try {
            byte eduPack, racePack;
            FileOutputStream fos = new FileOutputStream("output.dat");
            for (Birth birth : births) {
                eduPack = (byte) birth.getEducationAge();
                eduPack <<= (8 - ceiledEduBits);
                fos.write(eduPack);
            }
            fos.write("\n".getBytes());
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
