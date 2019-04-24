package com.example.testandroidapplication.Presenter.registerUser;

import com.example.testandroidapplication.objects.User;

public interface IRegisterUserContract {

        interface Presenter {
            void validateUserObject(User user);
        }

        interface View {

        }
    }
