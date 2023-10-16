package developer.boltech.ksustamobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import androidx.preference.Preference;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference preference = findPreference("contact_preference");
        if (preference != null) {
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(new Intent(getContext(), ContactActivity.class));
                    return true;
                }
            });
        }


//        SwitchPreferenceCompat biometricSwitchPreference = findPreference("biometric_preference");
////        boolean isBiometricAvailable = ;
//        if (isBiometricAvailable) {
//            biometricSwitchPreference.setEnabled(true);
//
//            biometricSwitchPreference.setOnPreferenceClickListener(preference1 -> {
//                biometricPrompt.authenticate(promptInfo);
//
//                return true;
//            });
//        } else {
//            biometricSwitchPreference.setEnabled(false);
//        }

    }

}
