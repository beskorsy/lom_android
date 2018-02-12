package lom.lom_android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import lom.lom_android.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactFragment extends Fragment {
    private String[] localitys = {"Сковородино", "Белогорск", "Тыгда"};

    public ContactFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        AppCompatSpinner localitySpinner = (AppCompatSpinner) rootView.findViewById(R.id.localitySpinner);
        ArrayAdapter<String> localityAdapter = new ArrayAdapter<String>(rootView.getContext(),
                android.R.layout.simple_spinner_item, localitys);
        localityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localitySpinner.setAdapter(localityAdapter);

        return rootView;
    }
}