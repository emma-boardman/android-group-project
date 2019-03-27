package com.example.testandroidapplication.Presenter.registerUser;

import com.example.testandroidapplication.objects.User;

public interface IRegisterUserContract {

        interface Presenter{
            void processUserObject(User user);
        }

        interface View{

        }
    }
