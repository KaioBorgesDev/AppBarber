package com.example.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }

                private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
                    IdpResponse response = result.getIdpResponse();
                    if (result.getResultCode() == RESULT_OK) {
                        // Successfully signed in
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        Toast.makeText(MainActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();
                        user.delete();


                        open_Page();
                    } else {

                    }
                }
            }
    );
    private Button entrar;
    private EditText email,senha;
    private TextView cadastrar, bemvindo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadastrar = findViewById(R.id.cadastrar);

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);

        cadastrar.setOnClickListener(e->{
            email = findViewById(R.id.email);
            senha = findViewById(R.id.senha);

            if (email.getText().equals(null) || senha.getText().equals(null) )
            Toast.makeText(this, "Coloque a senha e o email!", Toast.LENGTH_SHORT).show();
            else {
                String emailUser = String.valueOf(email.getText());
                String senhaUser = String.valueOf(senha.getText());


            }
        });

    }
    public void open_Page(){
        Intent tela = new Intent(this, Page.class);
        startActivity(tela);

    }
}