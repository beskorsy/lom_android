package lom.lom_android.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import lom.lom_android.R;
import lom.lom_android.service.App;
import lom.lom_android.service.DataModel;
import lom.lom_android.service.ResultModel;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExtraFragment extends Fragment {

    EditText dateET;
    EditText comment;
    Calendar dateAndTime = Calendar.getInstance();
    private ResultModel resultModel;

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

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    private void setInitialDateTime() {

        String dateTime = DateUtils.formatDateTime(getActivity(),
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        dateET.setText(dateTime);

        resultModel.data = dateTime;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_extra, container, false);

        App app = (App) getActivity().getApplication();
        resultModel = app.getResultModel();
        DataModel data = app.getData();

        CheckBox calculationOnSpot = rootView.findViewById(R.id.calculationOnSpot);
        CheckBox cutter = rootView.findViewById(R.id.cutter);
        CheckBox cargo = rootView.findViewById(R.id.cargo);
        dateET = rootView.findViewById(R.id.dateET);
        comment = rootView.findViewById(R.id.comment);

        setInitialDateTime();

        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View et = getActivity().getCurrentFocus();
                if (et instanceof EditText) {
                    et.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                    }
                }

                new DatePickerDialog(getActivity(), onDateSetListener,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
        comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                resultModel.comment = s.toString();
            }
        });


        if (data.getCalculatedInPlace()) {
            calculationOnSpot.setVisibility(View.VISIBLE);
        } else {
            calculationOnSpot.setVisibility(View.GONE);
        }

        if (data.getCutter()) {
            cutter.setVisibility(View.VISIBLE);
        } else {
            cutter.setVisibility(View.GONE);
        }

        if (data.getLoader()) {
            cargo.setVisibility(View.VISIBLE);
        } else {
            cargo.setVisibility(View.GONE);
        }

        return rootView;
    }
}