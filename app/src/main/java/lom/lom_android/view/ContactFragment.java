package lom.lom_android.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import lom.lom_android.R;
import lom.lom_android.service.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactFragment extends Fragment {

    private EditText phoneET;
    private EditText address;
    private AppCompatSpinner localitySpinner;
    private ResultModel resultModel;
    private Button nextBtn;

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

        App app = (App) getActivity().getApplication();
        DataModel data = app.getData();
        resultModel = app.getResultModel();

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        nextBtn = rootView.findViewById(R.id.nextBtn);
        phoneET = rootView.findViewById(R.id.phoneET);
        address = rootView.findViewById(R.id.address);
        localitySpinner = rootView.findViewById(R.id.localitySpinner);
        nextBtn.setEnabled(false);

        ArrayAdapter<LocalityModel> localityAdapter = new ArrayAdapter<LocalityModel>(rootView.getContext(),
                android.R.layout.simple_spinner_item, data.getLocalitys());
        localityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        localitySpinner.setAdapter(localityAdapter);


        phoneET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setPhone(s);
            }
        });

        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                resultModel.address = s.toString();
            }
        });

        localitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resultModel.locality = (LocalityModel) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                resultModel.locality = (LocalityModel) parent.getSelectedItem();
            }
        });

        return rootView;
    }

    private void setPhone(Editable s) {
        String phone = s.toString();

        Boolean isValid = true;
        int color = Color.parseColor("#FFC107");
        if (phone.startsWith("+7") && phone.length() == 12) {
            phoneET.setTextColor(color);
            hideKeybord();
        } else {
            if (phone.length() == 11 && phone.startsWith("8")) {
                phoneET.setText(phone.replaceFirst("8", "+7"));
                phoneET.setTextColor(color);
                hideKeybord();
            } else if (phone.length() == 10 && phone.startsWith("9")) {
                phoneET.setText(phone.replaceFirst("9", "+79"));
                phoneET.setTextColor(color);
                hideKeybord();
            } else {
                isValid = false;
                phoneET.setTextColor(Color.RED);
            }
        }

        if (isValid) {
            App.getApi().getCustomer(phone).enqueue(new Callback<List<CustomerModel>>() {
                @Override
                public void onResponse(Call<List<CustomerModel>> call, Response<List<CustomerModel>> response) {

                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        App app = (App) activity.getApplication();
                        if (response.body() != null && response.body().size() > 0) {
                            app.setCustomer(response.body().get(0));
                        } else {
                            app.setCustomer(null);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<CustomerModel>> call, Throwable t) {

                    resultModel.phone = "";
                }
            });
        }

        nextBtn.setEnabled(isValid);
        resultModel.phoneValid = isValid;
        resultModel.phone = phone;
    }

    private void hideKeybord() {
        View v = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            v = Objects.requireNonNull(getActivity()).getCurrentFocus();
            if (v != null) {
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }
}