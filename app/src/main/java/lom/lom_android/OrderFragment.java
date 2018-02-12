package lom.lom_android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class OrderFragment extends Fragment {
    private String[] points = {"Сковородино", "Белогорск", "Тыгда"};
    private String[] transports = {"Воровайка (до 5тонн) - 70 руб/км", "Воровайка (до 10тонн) - 100 руб/км",
            "Камаз (до 20 тонн) - 120 руб/км", "Трал - 150 руб/км"};

    public OrderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        AppCompatSpinner pointsSpinner = (AppCompatSpinner) rootView.findViewById(R.id.pointsSpinner);
        ArrayAdapter<String> pointsAdapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_spinner_item, points);
        pointsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointsSpinner.setAdapter(pointsAdapter);

        AppCompatSpinner transportSpinner = (AppCompatSpinner) rootView.findViewById(R.id.transportSpinner);
        ArrayAdapter<String> transporAtdapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_spinner_item, transports);
        transporAtdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transportSpinner.setAdapter(transporAtdapter);

        return rootView;
    }
}