import java.util.List;

public class SongData {

    String title;
    String album;
    String artist;
    String duration;
    int rating;
    int playCount;
    String removed;
    int playlistIndex;

    SongData(String title, String album, String artist, String removed, String duration, int rating, int playCount, int playlistIndex) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.rating = rating;
        this.playCount = playCount;
        this.removed = removed;
        this.playlistIndex = playlistIndex;
    }

    SongData(List<String> stringList) {
        this.title = stringList.get(8);
        this.album = stringList.get(9);
        this.artist = stringList.get(10);
        this.duration = stringList.get(11);
        this.rating = custParse(stringList.get(12));
        this.playCount = custParse(stringList.get(13));
        this.removed = stringList.get(14);
        this.playlistIndex = custParse(stringList.get(15));
    }

    public int custParse(String s) {
        return Integer.parseInt(s.replaceAll("[^\\d.]", ""));
    }

    @Override
    public String toString() {
        return "SongData{" +
                "title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", removed='" + removed + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", playCount=" + playCount +
                ", playlistIndex=" + playlistIndex +
                '}';
    }
}
