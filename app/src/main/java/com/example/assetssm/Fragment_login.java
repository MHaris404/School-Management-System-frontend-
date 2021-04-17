package com.example.assetssm;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment_login extends Fragment implements OnClickListener {
    private static View view;

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp, txt_character, textToolHeader;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private static Integer txt_char_pageValue;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;

    public Fragment_login() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((Activity_login) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarLogin);
        textToolHeader = (TextView) toolbar.findViewById(R.id.toolbar_title);
        textToolHeader.setText("Login to continue");

        view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews();
        setListeners();

        txt_character = view.findViewById(R.id.character);
        String[] chars = {"Parent", "Student", "Staff", "Driver"};
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        txt_char_pageValue = sharedPreferences.getInt("page", -1);
        txt_character.setText(chars[txt_char_pageValue]);

        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = view.findViewById(R.id.login_emailid);
        password = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.loginBtn);
        forgotPassword = view.findViewById(R.id.forgot_password);
        signUp = view.findViewById(R.id.createAccount);
        show_hide_password = view
                .findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

//        Setting text selector over textviews
//        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(),
//                    xrp);
//
//            forgotPassword.setTextColor(csl);
//            show_hide_password.setTextColor(csl);
//            signUp.setTextColor(csl);
//        } catch (Exception e) {
//        }


    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:

                Intent intentLogin = null;
                switch (txt_char_pageValue) {
                    case 0:
                        intentLogin = new Intent((Activity_login) getActivity(), Activity_Parent.class);
                        startActivity(intentLogin);
                        break;
                    case 1:
                        intentLogin = new Intent((Activity_login) getActivity(), Activity_Student.class);
                        startActivity(intentLogin);
                        break;
                    case 2:
                        intentLogin = new Intent((Activity_login) getActivity(), Activity_Staff.class);
                        startActivity(intentLogin);
                        break;
                    case 3:
                        intentLogin = new Intent((Activity_login) getActivity(), Activity_Driver.class);
                        startActivity(intentLogin);
                        break;

                }

                ////////////////////////////checkValidation();
                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new Fragment_login_forgotPassword(), Utils_login.ForgotPassword_Fragment).commit();
                break;

            case R.id.createAccount:

                switch (txt_char_pageValue) {
                    //parent
                    case 0:
                        //signup fragemnt = Parent + child apply // Replace signup frgament with animation
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                                .replace(R.id.frameContainer, new Fragment_signup_fragmentParent(), Utils_login.SignUp_Fragment_fragmentParent).commit();
                        break;
                    case 1:
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Warning")
                                .setMessage("Only Parents can apply for admission")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setCancelable(false)
                                .show();
                        break;
                    case 2:
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                                .replace(R.id.frameContainer, new Fragment_signup_fragmentStaff(), Utils_login.SignUp_Fragment_fragmentStaff).commit();
                        break;

                    case 3:
                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                                .replace(R.id.frameContainer, new Fragment_signup_fragmentDriver(), Utils_login.SignUp_Fragment_fragmentDriver).commit();
                        break;
                }
                break;
        }
    }

    // Check Validation before Activity_login
    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils_login.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
            // Else do Activity_login and do your stuff
        else
            Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
                    .show();

    }

}
