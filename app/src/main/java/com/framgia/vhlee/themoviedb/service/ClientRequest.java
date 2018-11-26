package com.framgia.vhlee.themoviedb.service;

import com.framgia.vhlee.themoviedb.data.model.GenresResponse;
import com.framgia.vhlee.themoviedb.data.model.Movie;
import com.framgia.vhlee.themoviedb.data.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClientRequest {
    @GET("genre/movie/list")
    Observable<GenresResponse> getGenre();

    @GET("trending/movie/day")
    Observable<MovieResponse> getMoviesHighLight();

    @GET("discover/movie")
    Observable<MovieResponse> getMoviesByGenre(@Query("with_genres") String idGenre,
                                               @Query("page") int page);

    @GET("movie/{type}")
    Observable<MovieResponse> getMoviesCategory(@Path("type") String type,
                                                @Query("page") int page);

    @GET("movie/{movie_id}")
    Observable<Movie> getMovieDetail(@Path("movie_id") int id,
                                     @Query("append_to_response") String append);
}
