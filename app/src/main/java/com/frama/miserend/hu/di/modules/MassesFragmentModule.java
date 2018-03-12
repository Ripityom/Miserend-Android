package com.frama.miserend.hu.di.modules;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import com.frama.miserend.hu.churchdetails.MassAdapter;
import com.frama.miserend.hu.database.miserend.MiserendDatabase;
import com.frama.miserend.hu.di.scopes.PerFragment;
import com.frama.miserend.hu.home.pages.churches.near.NearChurchesAdapter;
import com.frama.miserend.hu.home.pages.churches.near.NearChurchesFragment;
import com.frama.miserend.hu.home.pages.churches.near.NearChurchesViewModel;
import com.frama.miserend.hu.home.pages.masses.MassesAdapter;
import com.frama.miserend.hu.home.pages.masses.MassesFragment;
import com.frama.miserend.hu.home.pages.masses.MassesViewModel;
import com.frama.miserend.hu.location.LocationRetriever;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs on 2018. 02. 11..
 */
@Module
public class MassesFragmentModule {

    @PerFragment
    @Provides
    Fragment provideFragment(MassesFragment fragment) {
        return fragment;
    }

    @PerFragment
    @Provides
    MassesAdapter provideMassesAdapter(MassesFragment fragment) {
        return new MassesAdapter();
    }

    @PerFragment
    @Provides
    MassesViewModel provideMassesViewModel(MassesFragment fragment, MassesViewModel.Factory factory) {
        return ViewModelProviders.of(fragment, factory)
                .get(MassesViewModel.class);
    }

    @PerFragment
    @Provides
    MassesViewModel.Factory provideMassesViewModelFactory(Application application, MiserendDatabase database) {
        return new MassesViewModel.Factory(application, database);
    }

    @PerFragment
    @Provides
    LocationRetriever.LocationResultListener provideLocationResultListener(MassesFragment fragment) {
        return fragment;
    }

}
