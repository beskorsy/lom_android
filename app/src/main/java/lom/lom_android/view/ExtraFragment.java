package lom.lom_android.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lom.lom_android.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExtraFragment extends Fragment {

    public ExtraFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ExtraFragment newInstance() {
        ExtraFragment fragment = new ExtraFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_extra, container, false);



        return rootView;
    }
}