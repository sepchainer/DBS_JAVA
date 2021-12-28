//Database Systems (Module IDS)

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

// The RandomHelper class wraps around the JAVA Random class to provide convenient access to random data as we need it
// Additionally it provides access to external single-columned files (e.g. courses.csv, names.csv, surnames.csv)
class RandomHelper {
    private final char[] alphabet = getCharSet();
    private Random rand;
    private ArrayList<String> firstNames;
    private ArrayList<String> lastNames;
    private ArrayList<String> birthdates;
    private ArrayList<String> dates;
    private ArrayList<String> sport_facilitys;
    private ArrayList<String> streets;
    private ArrayList<String> grounds;
    private ArrayList<String> sport_types;
    private ArrayList<String> coachingstyles;
    private ArrayList<String> positions;
    private static final String firstNameFile = "./src/firstnames.csv";
    private static final String lastNameFile = "./src/lastnames.csv";
    private static final String birthdatesfile = "./src/birthdates.csv";
    private static final String datesfile = "./src/dates.txt";
    private static final String sport_facilityfile = "./src/sport_facilitys.csv";
    private static final String streetsfile = "./src/streets.csv";
    private static final String groundfile = "./src/ground.csv";
    private static final String sport_typefile = "./src/sports_type.csv";
    private static final String coachingstylefile = "./src/coachingstyles.csv";
    private static final String positionsfile = "./src/positions.csv";

    //instantiate the Random object and store data from files in lists
    RandomHelper() {
        this.rand = new Random();
        this.lastNames = readFile(lastNameFile);
        this.firstNames = readFile(firstNameFile);
        this.birthdates = readFile(birthdatesfile);
        this.dates = readFile(datesfile);
        this.sport_facilitys = readFile(sport_facilityfile);
        this.streets = readFile(streetsfile);
        this.grounds = readFile(groundfile);
        this.sport_types = readFile(sport_typefile);
        this.coachingstyles = readFile(coachingstylefile);
        this.positions = readFile(positionsfile);
    }

    //not used but it might be helpful
    String getRandomString(int minLen, int maxLen) {
        StringBuilder out = new StringBuilder();
        int len = rand.nextInt((maxLen - minLen) + 1) + minLen;
        while (out.length() < len) {
            int idx = Math.abs((rand.nextInt() % alphabet.length));
            out.append(alphabet[idx]);
        }
        return out.toString();
    }

    //returns random element from list
    String getRandomFirstName() {
        return firstNames.get(getRandomInteger(0, firstNames.size() - 1));
    }

    //returns random element from list
    String getRandomLastName() {
        return lastNames.get(getRandomInteger(0, lastNames.size() - 1));
    }

    //returns random element from list
    String getRandomBirthdate() {
        return birthdates.get(getRandomInteger(0, birthdates.size() - 1));
    }

    //returns random element from list
    String getRandomDate() { return dates.get(getRandomInteger(0, dates.size() - 1)); }

    //returns random element from list
    String getRandomSportFacility() { return sport_facilitys.get(getRandomInteger(0, sport_facilitys.size() - 1)); }

    //returns random element from list
    String getRandomStreet() { return streets.get(getRandomInteger(0, streets.size() - 1)); }

    //returns random element from list
    String getRandomGround() { return grounds.get(getRandomInteger(0, grounds.size() - 1)); }

    //returns random element from list
    String getRandomSportTypes() { return sport_types.get(getRandomInteger(0, sport_types.size() - 1)); }

    //returns random element from list
    String getRandomCoachingsytle() { return coachingstyles.get(getRandomInteger(0, coachingstyles.size() - 1)); }

    //returns random element from list
    String getRandomPosition() { return positions.get(getRandomInteger(0, positions.size() - 1)); }

    //never used//
    //returns random double from the Interval [min, max] and a defined precision (e.g. precision:2 => 3.14)
    Double getRandomDouble(double min, double max, int precision) {
        //Hack that is not the cleanest way to ensure a specific precision, but...
        double r = Math.pow(10, precision);
        return Math.round(min + (rand.nextDouble() * (max - min)) * r) / r;
    }

    //return random Integer from the Interval [min, max]; (min, max are possible as well)
    Integer getRandomInteger(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    //reads single-column files and stores its values as Strings in an ArraList
    private ArrayList<String> readFile(String filename) {
        String line;
        ArrayList<String> set = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                try {
                    set.add(line);
                } catch (Exception ignored) {
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    //never used//
    //defines which chars are used to create random strings
    private char[] getCharSet() { // create getCharSet char array
        StringBuffer b = new StringBuffer(128);
        for (int i = 48; i <= 57; i++) b.append((char) i);        // 0-9
        for (int i = 65; i <= 90; i++) b.append((char) i);        // A-Z
        for (int i = 97; i <= 122; i++) b.append((char) i);       // a-z
        return b.toString().toCharArray();
    }
}