package com.learning.ms.model;

public class Movie {
    private Long movieId;
    private String movieName;

    public Movie() { // for JSON/XML conversion
    }

    public Movie(Long movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

}
