package developer.boltech.ksustamobileapp;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private TextView register, forgetPasswordTextView;
    private EditText editTextEmailAddress, editTextPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Button loginButton;
//    private Button loginButton, btnBioAuth;
//    private BiometricPrompt biometricPrompt;
//    private Executor executor;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnBioAuth = findViewById(R.id.btnBioAuth);
//
//        executor = ContextCompat.getMainExecutor(this);
//        biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
//            @Override
//            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
//                super.onAuthenticationError(errorCode, errString);
//                Toast.makeText(MainActivity.this, "Error:" +errString, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
//                super.onAuthenticationSucceeded(result);
////                Intent goToHome = new Intent(getContext(), HomeFragment.class);
////                startActivity(goToHome);
//
//            }
//
//            @Override
//            public void onAuthenticationFailed() {
//                super.onAuthenticationFailed();
//                Toast.makeText(MainActivity.this, "Biometric authentication failed. Try again", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometric Authentication")
//                .setSubtitle("Sign in using your face ID or fingerprint biometrics")
//                .setNegativeButtonText("Cancel")
//                .build();
//
//        btnBioAuth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                biometricPrompt.authenticate(promptInfo);
//                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(intent);
//            }
//        });

        mAuth = FirebaseAuth.getInstance();

        editTextEmailAddress = findViewById(R.id.emailAddress);
        editTextPassword = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logUserIn();
//                Intent goToDashboard = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(goToDashboard);
            }
        });

        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.learn6);

        i.setAdjustViewBounds(true);
        i.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        TextView register = findViewById(R.id.registerTextView);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetPasswordTextView = findViewById(R.id.forgetPasswordTextView);
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgetPasswordActivity = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(forgetPasswordActivity);
            }
        });
    }

    private void logUserIn() {

        String emailAddress = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (emailAddress.isEmpty()){
            editTextEmailAddress.setError("Email Address is required!");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            editTextEmailAddress.setError("Please enter a valid email address");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Minimum password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    
                    if (user.isEmailVerified()){
                        Intent login = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(login);
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your email to verify your account!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Failed to sign the user in. Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}