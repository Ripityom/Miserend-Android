package com.frama.miserend.hu.di.modules;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;

import com.frama.miserend.hu.churchdetails.ChurchDetailsActivity;
import com.frama.miserend.hu.churchdetails.ChurchDetailsViewModel;
import com.frama.miserend.hu.database.local.LocalDatabase;
import com.frama.miserend.hu.database.miserend.MiserendDatabase;
import com.frama.miserend.hu.database.miserend.manager.DatabaseManager;
import com.frama.miserend.hu.di.scopes.PerActivity;
import com.frama.miserend.hu.home.HomeScreenActivity;
import com.frama.miserend.hu.home.HomeViewModel;
import com.frama.miserend.hu.router.Router;
import com.frama.miserend.hu.search.suggestions.SuggestionViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs on 2018. 02. 11..
 */
@Module
public class ChurchDetailsModule {

    @PerActivity
    @Provides
    Activity provideActivity(ChurchDetailsActivity churchDetailsActivity) {
        return churchDetailsActivity;
    }

    @PerActivity
    @Provides
    ChurchDetailsViewModel provideChurchDetailsViewModel(ChurchDetailsActivity churchDetailsActivity, ChurchDetailsViewModel.Factory factory) {
        return ViewModelProviders.of(churchDetailsActivity, factory)
                .get(ChurchDetailsViewModel.class);
    }

    @PerActivity
    @Provides
    ChurchDetailsViewModel.Factory provideChurchDetailsViewModelFactory(Application application, @Named("churchId") int churchId, LocalDatabase localDatabase, MiserendDatabase miserendDatabase) {
        return new ChurchDetailsViewModel.Factory(application, churchId, localDatabase, miserendDatabase);
    }

    @PerActivity
    @Provides
    @Named("churchId")
    int provideChurchId(ChurchDetailsActivity churchDetailsActivity) {
        return churchDetailsActivity.getIntent().getIntExtra(Router.IntentExtra.CHURCH_ID, 0);
    }
}
