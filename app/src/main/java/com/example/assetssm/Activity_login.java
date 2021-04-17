package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Activity_login extends AppCompatActivity {

    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace Activity_login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Fragment_login(),
                            Utils_login.Login_Fragment).commit();
        }
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login_toolbar, menu);

        Drawable drawable = menu.findItem(R.id.loginMenu).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        menu.findItem(R.id.loginMenu).setIcon(drawable);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Activity_login.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("page", -1);
                editor.commit();

                Intent intent1 = new Intent(Activity_login.this, Activity_charGuide.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Replace Login Fragment with animation
    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Fragment_login(),
                        Utils_login.Login_Fragment).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils_login.ForgotPassword_Fragment);
        Fragment SignUp_Fragment_fragmentParent = fragmentManager.findFragmentByTag(Utils_login.SignUp_Fragment_fragmentParent);
        Fragment SignUp_Fragment_fragmentStaff = fragmentManager.findFragmentByTag(Utils_login.SignUp_Fragment_fragmentStaff);
        Fragment SignUp_Fragment_fragmentDriver = fragmentManager.findFragmentByTag(Utils_login.SignUp_Fragment_fragmentDriver);


        // Check if both are null or not
        // If both are not null then replace Activity_login fragment else do backpressed
        // task

        if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else if (SignUp_Fragment_fragmentParent != null)
            replaceLoginFragment();
        else if (SignUp_Fragment_fragmentStaff != null)
            replaceLoginFragment();
        else if (SignUp_Fragment_fragmentDriver != null)
            replaceLoginFragment();
//        else
//            super.onBackPressed();
    }


}



