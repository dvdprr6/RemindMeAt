package com.example.david.remindmeat.implementation;

import android.arch.persistence.room.EmptyResultSetException;
import android.content.Context;

import com.example.david.remindmeat.callback.UserCallback;
import com.example.david.remindmeat.database.AppDatabase;
import com.example.david.remindmeat.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/*
    REFERENCE: https://github.com/alahammad/RoomSample
 */

public class UserImplementation {
    private AppDatabase appDatabase;

    public UserImplementation(Context context){
        appDatabase = AppDatabase.getInstance(context);
    }

    public void findAllUsers(final UserCallback userCallback) {
        appDatabase.getUserDao().select().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<User> allUsers) throws Exception {
                userCallback.onUserLoaded(allUsers);
            }
        });
    }

    public void findUserByEmail(final UserCallback userCallback, final String email) {
        UserCallback errorUserCallBack = userCallback;

        appDatabase.getUserDao().selectByEmail(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull User user) {

                userCallback.onUserLoaded(user);
            }
        });

    }

    public void createUser(final User user) {
        Completable.fromAction(new Action(){
            @Override
            public void run(){
                appDatabase.getUserDao().insert(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void updateUser(final User user) {
        Completable.fromAction(new Action(){
            @Override
            public void run(){
                appDatabase.getUserDao().update(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void deletUser(final User user) {
        Completable.fromAction(new Action(){
            @Override
            public void run(){
                appDatabase.getUserDao().delete(user);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
