import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) {

        System.out.println("Welcome to your country program!");
        HashMap<String, ArrayList<CountryData>> information = new HashMap();
        String postContent = readFile("countries.txt");
        String[] lines = postContent.split("\n");

        for (String line : lines) {
            String[] columns = line.split("\\|");
            int countryId = Integer.valueOf(columns[0]);
            String countryAb = columns[1];
            String country = columns[2];
            String firstLetter = country.substring(0, 1);
            CountryData countryData = new CountryData(countryId, countryAb, country);
            ArrayList<CountryData> list = information.get(firstLetter);
            if (list == null){
                list = new ArrayList();
                list.add(countryData);
                information.put(firstLetter, list);
            } else{
                list.add(countryData);
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first letter of the country that you would like to look up!");
        String letter = scanner.nextLine();
        ArrayList<CountryData> numList = information.get(letter);
        String contents = "";
        for (CountryData data : numList){
            contents += data.country + "\n";
        }
    }

    static String readFile(String fileName) {//copy and paste when I need to read/write files
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }

    static void writeFile(String fileName, String fileContent) {//copy and paste when you need to read/write files
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {

        }
    }
}
