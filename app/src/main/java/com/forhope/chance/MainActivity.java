package com.forhope.chance;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText n1,n2;
    private TextView text;
    private  TextView boo;
    private int prob =0;
    private LinearLayout linearLayout;
    private Window window;
    private Vibrator vibe;
    private double d=0;
    private int co=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n1 = (EditText) findViewById(R.id.fullNumber);
        n2 = (EditText) findViewById(R.id.secNumber);
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textView8);
        boo = (TextView) findViewById(R.id.bool);
        linearLayout = (LinearLayout) findViewById(R.id.root);
        window = this.getWindow();
        vibe = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                vibe.vibrate(100);

                if (!isEmpty(n2) ) {
                    double a;
                    if (!isEmpty(n1)) {
                         a = Double.parseDouble(n1.getText().toString());
                    } else {
                         a = 100.0;
                    }

                double b = Double.parseDouble(n2.getText().toString());
                if(b>a) {
                    text.setText("The First Number Should be Higher!");
                } else {

                    double c = (b / a)*100;
                    if (d!=c) {
                        prob = 0;
                        d=c;
                    }

                    if (exp(c/100))
                    {
                        boo.setText("Nice Luck! After " +prob+" Times!");
                        linearLayout.setBackgroundColor(Color.parseColor("#1cd13a"));
                        button.setBackgroundColor(Color.parseColor("#ff669900"));
                        button.setTextColor(Color.parseColor("#ff99cc00"));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(Color.parseColor("#1cd13a"));
                        }
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setTitle("Set Label");
                        dialog.setContentView(R.layout.dialog_signin);
                        dialog.show();
                        TextView label = (TextView) dialog.findViewById(R.id.Labeltext);
                        label.setText("Nice Luck! After " +prob+" Times!");
                        prob = 0;
                        TextView cancelButton = (TextView)dialog.findViewById(R.id.cancelButton);

                        cancelButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                co = co++;

                            }
                        });
                        TextView okButton = (TextView)dialog.findViewById(R.id.okButton);
                        okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                EditText name = (EditText) dialog.findViewById(R.id.getLabel);
                               // Toast.makeText(getApplication(), "You have won " + co + " times out of " + prob  ,
                                       // Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);

                            }
                        });


                    } else {
                        boo.setText("Try Again!");

                        prob = prob+1;
                        linearLayout.setBackgroundColor(Color.parseColor("#ffff4444"));
                        button.setBackgroundColor(Color.parseColor("#d93030"));
                        button.setTextColor(Color.parseColor("#8b0404"));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.setStatusBarColor(Color.parseColor("#ffff4444"));
                        }
                    }
                    double number = (int)Math.round(c * 100)/(double)100;

                    text.setText("YOUR CHANCE IS " + number + "%");
                }

            } else {
                text.setText("Please Type Something!");


            }

            }
        });




    }

   private boolean isEmpty(EditText e) {
       return e.getText().toString().trim().length() == 0;
   }

   private boolean exp(double probabilityTrue)
    {
        return Math.random() >= 1.0 - probabilityTrue;
    }

}

