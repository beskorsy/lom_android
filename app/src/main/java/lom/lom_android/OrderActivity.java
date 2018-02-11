package lom.lom_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

public class OrderActivity extends AppCompatActivity {


    private String[] points = {"Сковородино", "Белогорск", "Тыгда"};
    private String[] localitys = {"Сковородино", "Белогорск", "Тыгда"};
    private String[] transports = {"Воровайка (до 5тонн) - 70 руб/км", "Воровайка (до 10тонн) - 100 руб/км",
            "Камаз (до 20 тонн) - 120 руб/км", "Трал - 150 руб/км"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppCompatSpinner pointsSpinner = (AppCompatSpinner) findViewById(R.id.pointsSpinner);
        ArrayAdapter<String> pointsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, points);
        pointsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointsSpinner.setAdapter(pointsAdapter);

        AppCompatSpinner localitySpinner = (AppCompatSpinner) findViewById(R.id.localitySpinner);
        ArrayAdapter<String> localityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, localitys);
        localityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localitySpinner.setAdapter(localityAdapter);

        AppCompatSpinner transportSpinner = (AppCompatSpinner) findViewById(R.id.transportSpinner);
        ArrayAdapter<String> transporAtdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, transports);
        transporAtdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transportSpinner.setAdapter(transporAtdapter);
    }

}
