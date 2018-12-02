package com.framgia.vhlee.themoviedb.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framgia.vhlee.themoviedb.R;
import com.framgia.vhlee.themoviedb.data.model.Genre;
import com.framgia.vhlee.themoviedb.data.model.Movie;
import com.framgia.vhlee.themoviedb.data.model.Video;
import com.framgia.vhlee.themoviedb.data.repository.MoviesRepository;
import com.framgia.vhlee.themoviedb.data.source.local.LocalDataSource;
import com.framgia.vhlee.themoviedb.data.source.remote.RemoteDataSource;
import com.framgia.vhlee.themoviedb.databinding.ActivityDetailBinding;
import com.framgia.vhlee.themoviedb.ui.adapter.GenreAdapter;
import com.framgia.vhlee.themoviedb.ui.adapter.VideoAdapter;
import com.framgia.vhlee.themoviedb.ui.category.CategoryActivity;
import com.framgia.vhlee.themoviedb.ui.dialog.BottomSheetDialog;
import com.google.android.youtube.player.YouTubePlayer;

public class DetailActivity extends AppCompatActivity
        implements DetailNavigator, YouTubePlayer.OnFullscreenListener,
        GenreAdapter.GenreClickListener, VideoAdapter.VideoClickListener {
    private static final String TAG = "DetailActivity";
    private static final String TAG_BOTTOM_SHEET = "BottomSheet";
    private static final String EXTRA_ID = "com.framgia.vhlee.themoviedb.extras.EXTRA_ID";
    private DetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;
    private VideoFragment mVideoFragment;

    public static Intent getDetailIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ID, movie.getId());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        MoviesRepository repository = MoviesRepository.getInstance(
                LocalDataSource.getInstance(getApplicationContext()),
                RemoteDataSource.getInstance());
        mViewModel = new DetailViewModel(this, repository);
        mBinding.setDetailVM(mViewModel);
        initAdapter();
        initYoutubePlayerView();
        configLayout();
    }

    @Override
    protected void onStart() {
        super.onStart();
        int movieId = getIntent().getIntExtra(EXTRA_ID, 0);
        mViewModel.loadDetail(movieId);
    }

    @Override
    public void startCategoryActivity(Genre genre, boolean isGenre) {
        startActivity(CategoryActivity.getCategoryIntent(this, genre, isGenre));
    }

    @Override
    public void showBottomSheet(Movie movie, boolean isCast) {
        BottomSheetDialog bottomSheetDialog = BottomSheetDialog.newInstance(movie, isCast);
        bottomSheetDialog.show(getSupportFragmentManager(), TAG_BOTTOM_SHEET);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        configLayout();
    }

    @Override
    public void onFullscreen(boolean isFullscreen) {
        configLayout();
    }

    private void initAdapter() {
        RecyclerView recyclerGenres = mBinding.viewGenres.recyclerGenresLabel;
        RecyclerView recyclerTrailers = mBinding.viewTrailers.recyclerTrailers;
        recyclerGenres.setAdapter(new GenreAdapter(this));
        recyclerTrailers.setAdapter(new VideoAdapter(this));
    }

    private void initYoutubePlayerView() {
        mVideoFragment =
                (VideoFragment) getFragmentManager().findFragmentById(R.id.fragment_youtube_view);
        mVideoFragment.getView().setVisibility(View.GONE);
    }

    private void configLayout() {
        //TODO Configure layout changes
    }

    @Override
    public void onGenreClick(Genre genre) {
        startCategoryActivity(genre, true);
    }

    @Override
    public void onVideoClick(Video video) {
        mVideoFragment.getView().setVisibility(View.VISIBLE);
        mVideoFragment.setVideoId(video.getKey(), this);
        mVideoFragment.play();
    }
}
