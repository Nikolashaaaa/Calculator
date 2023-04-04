package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer; // Audio playback variable

    private AdView mAdView;

    String operator = "";
    String oldNumber;
    Boolean isNew = true;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        // Start block code of ad playback
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // End block code of ad playback

        // Start block code of the music playback
        mediaPlayer = MediaPlayer.create(this, R.raw.zvuk11);
        // Link to sound file

        editText = findViewById(R.id.editText);
    }

    public void clickNumber(View view) {
        mediaPlayer.start();
        if(isNew)
            editText.setText("");
        isNew = false;
        String number = editText.getText().toString();
        switch (view.getId()){
            case R.id.Bu1:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "1"; break;
            case R.id.Bu2:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "2"; break;
            case R.id.Bu3:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "3"; break;
            case R.id.Bu4:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "4"; break;
            case R.id.Bu5:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "5"; break;
            case R.id.Bu6:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "6"; break;
            case R.id.Bu7:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "7"; break;
            case R.id.Bu8:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "8"; break;
            case R.id.Bu9:
                if (zeroIsFirst(number) && number.length() == 1) {
                    number = number.substring(1);
                }
                number = number + "9"; break;

            case R.id.Bu0:
                if(zeroIsFirst(number) && number.length() == 1) {
                    number = "0";
                } else {
                    number = number + "0";
                }
                break;

            case R.id.BuDot:
                if (dotIsPresent(number)) {

                } else
                if (zeroIsFirst(number)) {
                    number = "0.";
                }
                else {
                    number = number + ".";
                }
            break;

            case R.id.BuPlusMinus:
               if (numberIsZero(number)) {
                   number = "0";
               } else {
                   if (minusIsPresent(number)) {
                       number = number.substring(1);
                   } else {
                       number = "-" + number;
                   }
               }
                break;
        }
        editText.setText(number);
    }

    private boolean zeroIsFirst(String number) {
      if (number.equals("")) {
          return true;
      }
      if (number.charAt(0) == '0') {
          return true;
      } else {
          return false;
      }
    }

    private boolean numberIsZero(String number) {
        if(number.equals("0") || number.equals("")){
            return true;
        } else {
            return false;
        }
    }

    public void operation(View view) {
        mediaPlayer.start();
        isNew = true;
        oldNumber = editText.getText().toString();
        switch (view.getId()){
            case R.id.BuPlus: operator = "+"; break;
            case R.id.BuMinus: operator = "-"; break;
            case R.id.BuDivide: operator = "/"; break;
            case R.id.BuMultiply: operator = "*"; break;
        }
    }

    public void clickEqual(View view) {
        mediaPlayer.start();
        String newNumber = editText.getText().toString();
        Double result = 0.0;
        if (Double.parseDouble(newNumber) < 0.00000001 && operator == "/"
                || newNumber.equals("") && operator == "/") {

            Toast.makeText(MainActivity.this, R.string.toast_message, Toast.LENGTH_SHORT).show();

        } else {
            switch (operator) {
                case "-":
                    result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                    break;
                case "+":
                    result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                    break;
                case "*":
                    result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                    break;
                case "/":
                    result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                    break;
            }
            editText.setText(result + "");
        }
    }

    public void acClick(View view) {
        mediaPlayer.start();
        editText.setText("0");
        isNew = true;
    }

    private boolean dotIsPresent(String number) {
        if (number.indexOf(".") == -1) {
            return false;
        } else {
            return true;
        }
    }

    private boolean minusIsPresent(String number) {
        if(number.charAt(0) == '-'){
         return true;
        } else {
            return false;
        }
    }

    public void clickPercent(View view) {
        mediaPlayer.start();
        if (operator == "") {
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp + "";
            editText.setText(number);
        } else {
            String newNumber = editText.getText().toString();
            Double result = 0.0;
            switch (operator) {
                case "+": result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                break;
                case "-": result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                break;
                case "*": result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                break;
                case "/": result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber) * 100;
                break;
            }
            editText.setText(result + "");
            operator = "";
        }
    }

}
