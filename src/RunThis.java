import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RunThis {

    public static void main(String[] args) throws FileNotFoundException {

        File[] fileNames = new File("C:\\Users\\andrew.vannus\\Downloads\\takeout-20201216T161107Z-001\\Takeout\\Google Play Music\\Playlists\\Thumbs Up").listFiles();

        Scanner sc;
        List<SongData> songDataArrayList = new ArrayList<SongData>();
        int i = 0;

        assert fileNames != null;
        for(File f: fileNames){
            sc = new Scanner(f);
            songDataArrayList.add(new SongData(fileExtract(sc)));
            System.out.println(i);
            i++;
        }
        for(SongData s : songDataArrayList){
            System.out.println(s.toString());
        }

        System.out.println("end of program");
    }



    public static List<String> fileExtract(Scanner sc) {
        String[] csvLines = new String[2]; //There should be 2 lines of data, one for the column titles and a second one for the column values
        for(int i=0; i<csvLines.length; i++) { //fill temp array with those 2 lines of data
            csvLines[i] = sc.nextLine();
        }
        if(sc.hasNextLine()) { //check if the csv has more than 2 lines
            System.out.println("CSV FILE HAS >2 LINES");
        }

        List<String> csvValues = commaDelimit(csvLines); //convert from 2 lines of data to discrete data

        sanityCheck(csvValues); //double check that random values are correct

        return csvValues;
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
            System.out.println("sanityCheckFailure: Removed vs. " +csvValues.get(14));
        }

        System.out.println(csvValues.get(8));
    }

}
