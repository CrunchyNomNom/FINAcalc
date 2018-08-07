package pl.crunchy.finacalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements TimeDialogFragment.TimeDialogListener{
    private Spinner style_spinner;
    private ArrayAdapter<CharSequence> style_spinner_ad;
    private String style;
    private Spinner distance_spinner;
    private ArrayAdapter<CharSequence> distance_spinner_ad;
    private String distance;
    private boolean long_course;
    private boolean male;
    private double time;
    private TextView time_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton gender = (ToggleButton) findViewById(R.id.gender);
        gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    male = true;
                } else {
                    male = false;
                }
            }
        });

        ToggleButton course = (ToggleButton) findViewById(R.id.course);
        gender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    long_course = true;
                } else {
                    long_course = false;
                }
            }
        });

        style_spinner = (Spinner)findViewById(R.id.style);
        style_spinner_ad = ArrayAdapter.createFromResource(this, R.array.styles, android.R.layout.simple_spinner_item);
        style_spinner_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        style_spinner.setAdapter(style_spinner_ad);
        style_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                style = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        distance_spinner = (Spinner)findViewById(R.id.distance);
        distance_spinner_ad = ArrayAdapter.createFromResource(this, R.array.distances, android.R.layout.simple_spinner_item);
        distance_spinner_ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distance_spinner.setAdapter(distance_spinner_ad);
        distance_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                distance = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        time_view = (TextView) findViewById(R.id.time);
        Button time_button = (Button) findViewById(R.id.settime);
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog();
            }
        });
    }

    public void showTimeDialog(){
        TimeDialogFragment dialog = new TimeDialogFragment();
        dialog.show(getSupportFragmentManager(), "time dialog");
    }

    @Override
    public void applyTime(String mins, String secs, String decs) {
        time = 60 * Float.parseFloat(mins) + Float.parseFloat(secs) + 0.01 * Float.parseFloat(decs);
        time_view.setText(mins + ":" + secs + "." + decs);
    }
}
