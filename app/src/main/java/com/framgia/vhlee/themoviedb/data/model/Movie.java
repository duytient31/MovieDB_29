package com.framgia.vhlee.themoviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("vote_average")
    private float mVoteAverage;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("genres")
    private List<Genre> mGenres;

    @SerializedName("production_companies")
    private List<Company> mCompanies;

    @SerializedName("videos")
    private VideoResponse mVideoResponse;

    @SerializedName("credits")
    private Credit mCredit;


    public Movie() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public List<Company> getCompanies() {
        return mCompanies;
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
    }

    public VideoResponse getVideoResponse() {
        return mVideoResponse;
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        mVideoResponse = videoResponse;
    }

    public Credit getCredit() {
        return mCredit;
    }

    public void setCredit(Credit credit) {
        mCredit = credit;
    }
}
