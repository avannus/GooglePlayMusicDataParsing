public class SongData {

    String title, album, artist, removed;
    int duration, rating, playCount, playlistIndex;

    SongData(String title, String album, String artist, String removed, int duration, int rating, int playCount, int playlistIndex){
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.removed = removed;
        this.duration = duration;
        this.rating = rating;
        this.playCount = playCount;
        this.playlistIndex = playlistIndex;
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
