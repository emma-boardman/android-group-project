package com.example.testandroidapplication.Presenter.registerUser;

import android.os.AsyncTask;

import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.utils.WebClientMethods;

public class RegisterUserPresenter implements IRegisterUserContract.Presenter {


        private final IRegisterUserContract.View view;
        public User user;

        public RegisterUserPresenter(IRegisterUserContract.View view){
            this.view = view;
        }

        public void processUserObject(User user) {

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
                    //Display success message
//                    view.showToast("Profile Added");
                } else {
//                    view.showToast("Some error occurred while adding user");
                }
            }
        };
    }
