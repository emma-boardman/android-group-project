package com.example.testandroidapplication.View.registerUser;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.testandroidapplication.LoginFragment;
import com.example.testandroidapplication.Presenter.registerUser.IRegisterUserContract;
import com.example.testandroidapplication.Presenter.registerUser.RegisterUserPresenter;
import com.example.testandroidapplication.R;
import com.example.testandroidapplication.View.createProfile.RegisterFragmentVOrA;
import com.example.testandroidapplication.objects.User;
import com.example.testandroidapplication.utils.WebClientMethods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterFragment extends Fragment implements IRegisterUserContract.View {

    private RegisterUserPresenter presenter;

    TextInputEditText username, email, password, confirm_password;
    Button btn_register, userCancel, btn_bypass_register;
    // need variable userId to be accessible by the onComplete method, so id can be sent to database.
    // to make it final (which is how it was originally declared in method), requires it to be initialised
    String userid;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_registration, container, false);

        presenter = new RegisterUserPresenter(this);

        username = v.findViewById(R.id.user_name);
        email = v.findViewById(R.id.user_email);
        password = v.findViewById(R.id.user_password);
        confirm_password = v.findViewById(R.id.user_confirm_password);
        btn_register = v.findViewById(R.id.btn_register);
        btn_bypass_register = v.findViewById(R.id.btn_bypass_register);
        userCancel = v.findViewById(R.id.user_cancel);

        auth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_confirm_password = confirm_password.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(getActivity(), "All fields are required to be filled.", Toast.LENGTH_SHORT).show();
                } else {
                    if(txt_password.length() < 6){
                        Toast.makeText(getActivity(), "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();
                    } else {
                        if(txt_password.equals(txt_confirm_password)){
                            register(txt_username, txt_email, txt_password);
                        } else {
                            Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        userCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginView = new LoginFragment();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        loginView).commit();

            }
        });

        btn_bypass_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragmentVOrA registerFragmentVOrA = new RegisterFragmentVOrA();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                        registerFragmentVOrA).commit();
            }
        });

        return v;
    }

    private void register(final String username, final String email, final String password) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            //adds attributes in firebase database
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("imageURL", "offline");
                            hashMap.put("search", username.toLowerCase());

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        User user = new User.UserBuilder(userid, username, email).build();
                                        presenter.processUserObject(user);

                                        RegisterFragmentVOrA registerFragmentVOrA = new RegisterFragmentVOrA();

                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                                                registerFragmentVOrA).commit();

                                        /*Intent intent = new Intent(getActivity(), Messaging.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        getActivity().finish();*/
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getActivity(), "You can't register with this email or password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
