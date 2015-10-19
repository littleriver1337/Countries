import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) {//Hasmaps are fast, constant time operation!

        System.out.println("Welcome to your country program!");//Introduction
        HashMap<String, ArrayList<CountryData>> information = new HashMap();//Hashmap with an array list where CountryData references the stand alone class that olds all of the variables listed
        String postContent = readFile("countries.txt");
        String[] lines = postContent.split("\n");

        for (String line : lines) {//this loop loops over the lines of data which are connotated
            String[] columns = line.split("\\|");
            int countryId = Integer.valueOf(columns[0]);//line 1
            String countryAb = columns[1];//line 2
            String country = columns[2];// line 3
            String firstLetter = country.substring(0, 1);//this tells the loop what its looking for (first two letters)
            CountryData countryData = new CountryData(countryId, countryAb, country);
            ArrayList<CountryData> list = information.get(firstLetter);
            if (list == null) {//if nothing is in the array list then add this information to a NEW array list
                list = new ArrayList();
                list.add(countryData);
                information.put(firstLetter, list);
            } else {
                list.add(countryData);
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first letter of the country that you would like to look up!");
        String letter = scanner.nextLine();
        ArrayList<CountryData> numList = information.get(letter);
        String newFile = String.format("%s_information", letter);

        if (information.containsKey(letter)) {
            String contents = "";
            for (CountryData data : numList) {//not finished...reference someone elses code for syntax and do this!
                contents += data.country + "\n";
            }
            writeFile(newFile, contents);
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
        //write file^ is not initialized yet, therefore it will NOT write the file until your HashMap constructor is completed
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {

        }
    }
}
