package com.framgia.vhlee.themoviedb.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.vhlee.themoviedb.R;
import com.framgia.vhlee.themoviedb.data.model.Movie;
import com.framgia.vhlee.themoviedb.databinding.ItemHighlightBinding;
import com.framgia.vhlee.themoviedb.utils.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class HighLightAdapter extends PagerAdapter
        implements View.OnClickListener {
    private static final String TAG = "HighLightAdapter";
    private List<Movie> mMovies;
    private HighLightClickListener mHighLightClickListener;
    private int mCurrentPosition;

    public HighLightAdapter(HighLightClickListener listener) {
        mMovies = new ArrayList<>();
        mHighLightClickListener = listener;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ItemHighlightBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.item_highlight, container, true);
        if (binding.getMovieVM() == null) {
            binding.setMovieVM(new MovieViewModel());
        }
        binding.getMovieVM().setMovie(mMovies.get(position));
        binding.executePendingBindings();
        binding.constraintHighlight.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View view) {
        mHighLightClickListener.onHighLightClick(mMovies.get(mCurrentPosition));
    }

    public void update(List<Movie> movies) {
        if (mMovies != null) mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setCurrentPosition(int position) {
        mCurrentPosition = position;
    }

    public interface HighLightClickListener {
        void onHighLightClick(Movie movie);
    }
}
