package com.example.testandroidapplication.Presenter.registerUser;

import android.os.AsyncTask;
import android.util.Log;

import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.utils.WebClientMethods;

public class RegisterUserPresenter implements IRegisterUserContract.Presenter {


        private final IRegisterUserContract.View view;
        public User user;

        public RegisterUserPresenter(IRegisterUserContract.View view){
            this.view = view;
        }

        public void validateUserObject(User user) {
            new CreateNewUserAsyncTask(user).execute();
        }

        private static class CreateNewUserAsyncTask extends AsyncTask<String, String, String> {

            private User user;

            CreateNewUserAsyncTask(User user) {
                this.user = user;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                return WebClientMethods.createUserAccount(user.getUserID(), user.getName(), user.getEmail());
            }

            protected void onPostExecute(final String result) {
                if (result.equals("1")) {
                    Log.i("Tag for remote DB user", "Successfully added user");
                } else {
                    Log.i("Tag for remote DB user", "Error adding user");
                }
            }
        };
    }
