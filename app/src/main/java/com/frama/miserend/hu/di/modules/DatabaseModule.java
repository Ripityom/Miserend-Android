package com.frama.miserend.hu.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.frama.miserend.hu.api.MiserendApi;
import com.frama.miserend.hu.database.local.LocalDatabase;
import com.frama.miserend.hu.database.miserend.MiserendDatabase;
import com.frama.miserend.hu.database.miserend.manager.DatabaseManager;
import com.frama.miserend.hu.di.qualifiers.ApplicationContext;
import com.frama.miserend.hu.preferences.Preferences;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs on 2018. 02. 11..
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    DatabaseManager provideDatabaseManager(@ApplicationContext Context context, MiserendApi api, Preferences preferences, MiserendDatabase miserendDatabase) {
        return new DatabaseManager(context, api, preferences, miserendDatabase.churchDao());
    }

    @Provides
    @Singleton
    public MiserendDatabase provideMiserendDatabase(@ApplicationContext Context context, @Named("database name") String databaseName) {
        return Room.databaseBuilder(context, MiserendDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    public LocalDatabase provideLocalDatabase(@ApplicationContext Context context, @Named("local database name") String databaseName) {
        return Room.databaseBuilder(context, LocalDatabase.class, databaseName).build();
    }

    @Provides
    @Singleton
    @Named("database name")
    String provideDatabaseName() {
        return DatabaseManager.DATABASE_FILE_NAME;
    }

    @Provides
    @Singleton
    @Named("local database name")
    String provideLocalDatabaseName() {
        return "localdatabase.sqlite3";
    }
}
