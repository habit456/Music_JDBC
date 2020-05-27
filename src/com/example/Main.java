package com.example;

import com.example.model.Artist;
import com.example.model.DataSource;
import com.example.model.SongArtist;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();

        if (!dataSource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

//        printArtists(dataSource, DataSource.ORDER_BY_ASC);
//        List<String> albumsForArtist =
//                dataSource.queryAlbumsForArtist("Carole King", DataSource.ORDER_BY_ASC);
//
//        for (String album: albumsForArtist) {
//            System.out.println("{" + "name: " + album + "}");
//        }

        List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }

        for (SongArtist artist: songArtists) {
            System.out.println("{artist-name: " + artist.getArtistName() + ", album-name: " + artist.getAlbumName() +
                    ", track: " + artist.getTrack() + "}");
        }

        dataSource.querySongsMetadata();

        dataSource.close();
    }

    public static void printArtists(DataSource dataSource, int orderBy) {
        List<Artist> artists = dataSource.queryArtists(orderBy);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }

        for (Artist artist : artists) {
            System.out.println("{" + "Id: " + artist.getId() + ", Name: " + artist.getName() + "}");
        }
    }
}
