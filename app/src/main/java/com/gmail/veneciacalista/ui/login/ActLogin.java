package com.gmail.veneciacalista.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.ui.movie.ActMovie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActLogin extends AppCompatActivity {

    @BindView(R.id.etPassword)
    EditText passInput;

    @BindView(R.id.etUsername)
    EditText unameInput;

    @BindView(R.id.btnLogin)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public boolean validateInput(String name, String pass) {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3) {
            unameInput.setError("at least 3 characters");
            valid = false;
        } else {
            unameInput.setError(null);
        }

        if (pass.isEmpty() || pass.length() < 4 || pass.length() > 10) {
            passInput.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passInput.setError(null);
        }

        return valid;
    }

    public void login () {
        String uname = unameInput.getText().toString();
        String pass = passInput.getText().toString();

        Boolean status = validateInput(uname, pass);

        if (status == false) {
            Toast.makeText(ActLogin.this, "Pleae input correctly", Toast.LENGTH_SHORT).show();
        } else {
            finish();
            Intent homepage = new Intent(this, ActMovie.class);
            startActivity(homepage);
        }
    }



}
