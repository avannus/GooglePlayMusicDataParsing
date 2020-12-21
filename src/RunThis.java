import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RunThis {

    public static void main(String[] args) throws FileNotFoundException {
        //scanner file location, make generic later
        Scanner sc = new Scanner(new File("C:\\Users\\andrew.vannus\\Downloads\\takeout-20201216T161107Z-001\\Takeout\\Google Play Music\\Playlists\\Thumbs Up\\$30k Mink.csv"));

        //returns a SongData file for each file containing data for each song
        SongData song1 = fileExtract(sc);

        System.out.println("end of program");
    }

    public static SongData fileExtract(Scanner sc) {
        String[] csvLines = new String[2]; //There should be 2 lines of data, one for the column titles and a second one for the column values
        for(int i=0; i<csvLines.length; i++) { //fill temp array with those 2 lines of data
            csvLines[i] = sc.nextLine();
        }
        if(sc.hasNextLine()) { //check if the csv has more than 2 lines
            System.out.println("CSV FILE HAS >2 LINES");
        }

        List<String> csvValues = commaDelimit(csvLines); //convert from 2 lines of data to discrete data

        sanityCheck(csvValues);
        return null;
    }

    public static List<String> commaDelimit(String[] inputStrings){ //TODO optimize?
        List<List<String>> lists = new ArrayList<>(); //create a List for each line of csv info
        for (String value : inputStrings) { //split each line into an array
            lists.add(Arrays.asList(value.split(",")));
        }

        List<String> splitValues = new ArrayList<String>();
        for (List<String> i : lists){
            splitValues.addAll(i);
        }

        return splitValues;
    }

    public static void sanityCheck(List<String> csvValues){
        String[] sanityStrings = new String[]{"Title", "Album", "Artist", "Duration (ms)", "Rating", "Play Count", "Removed", "Playlist Index"};
        for(int i=0;i< sanityStrings.length; i++){
            if(!sanityStrings[i].equals(csvValues.get(i))) {
                System.out.println("sanityCheckFailure: "+ sanityStrings[i] +" vs. "+csvValues.get(i));
            }
        }
        if(!csvValues.get(14).equals("\"\"")){
            System.out.println("sanityCheckFailure: Removed Value vs. " +csvValues.get(14));
        }
    }

}
