import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TakeoutCSVParser {

    static final int CSV_LINE_COUNT = 2;
    static final String fileName = "\\_COMBINED.csv";

    public static void main(String[] args) {
        String fileDirectory = getFileDirectory(); //get directory full of csv files TODO recursive solve?
        File[] fileNames = new File(fileDirectory).listFiles();

        List<SongData> songDataList = new ArrayList<>();

        assert fileNames != null;
        for (File f : fileNames) {
            if(!f.getName().equals(fileName.substring(1))){
                songDataList.add(new SongData(fileExtract(f)));
            } else {
                System.out.println("combined file detected already");
            }
        }
        setCombinedFile(fileDirectory, songDataList);
    }

    public static void setCombinedFile(String fileDirectory, List<SongData> songDataList) {
        try {
            File combinedCSV = new File(fileDirectory + "\\_COMBINED.csv");
            if (combinedCSV.createNewFile()) {
                System.out.println("Created file: " + fileDirectory + "_COMBINED.csv");
            } else {
                System.out.println("File already exists");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(combinedCSV));
            bw.write(SongData.getDataAsCSVTitle());
            for(SongData sd: songDataList){
                bw.write(sd.getDataAsCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileDirectory() {
        Scanner scanner = new Scanner(System.in);
        String s = "";
        while (s.equals("")) {
            System.out.println("What's the directory of the playlist of .csv files you want to combine?");
            s = scanner.nextLine();
        }
        return s;
    }

    public static List<String> fileExtract(File f) {
        StringBuilder csvLines = new StringBuilder(); //combine the two lines into one line

        try {
            Scanner sc = new Scanner(f);
            for (int i = 0; i < CSV_LINE_COUNT; i++) { //fill temp array with those 2 lines of data
                csvLines.append(sc.nextLine());
                if (i < CSV_LINE_COUNT - 1) csvLines.append(",");
            }
            if (sc.hasNextLine()) { //check if the csv has more than 2 lines, which is all the array holds
                System.out.println("CSV FILE HAS MORE THAN 2 LINES");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> csvValues = new ArrayList<>(Arrays.asList(csvLines.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
        sanityCheck(csvValues); //double check that values are correct

        return csvValues;
    }

    public static void sanityCheck(List<String> csvValues) {
        String[] sanityStrings = new String[]{"Title", "Album", "Artist", "Duration (ms)", "Rating", "Play Count", "Removed", "Playlist Index"};
        for (int i = 0; i < sanityStrings.length; i++) {
            if (!sanityStrings[i].equals(csvValues.get(i))) {
                System.out.print(csvValues.get(8));
                System.out.println(" sanityCheckFailure: " + sanityStrings[i] + " vs. " + csvValues.get(i));
            }
        }
        if (!csvValues.get(14).equals("\"\"")) {
            System.out.print(csvValues.get(8));
            System.out.println(" sanityCheckFailure: Removed vs. " + csvValues.get(14));
        }
    }

}
