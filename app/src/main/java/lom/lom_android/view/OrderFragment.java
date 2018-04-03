package lom.lom_android.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import lom.lom_android.R;
import lom.lom_android.service.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class OrderFragment extends Fragment {

    private TextView price;
    private TextView discount;
    private TextView deliveryCost;
    private Button submitBtn;
    //    private TextView fullPrice;
    private ResultModel resultModel;
    private EditText tonn;
    private App app;

    public OrderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        app = (App) getActivity().getApplication();
        DataModel data = app.getData();
        resultModel = app.getResultModel();

        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        submitBtn = rootView.findViewById(R.id.submitBtn);
        price = rootView.findViewById(R.id.price);
        discount = rootView.findViewById(R.id.discount);
        deliveryCost = rootView.findViewById(R.id.deliveryCost);
//        fullPrice = rootView.findViewById(R.id.fullPrice);
        tonn = rootView.findViewById(R.id.weightET);
        AppCompatSpinner scrapyardsSpinner = rootView.findViewById(R.id.pointsSpinner);
        ArrayAdapter<ScrapyardModel> pointsAdapter = new ArrayAdapter<ScrapyardModel>(rootView.getContext(),
                android.R.layout.simple_spinner_item, data.getScrapyards());
        pointsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scrapyardsSpinner.setAdapter(pointsAdapter);

        AppCompatSpinner transportSpinner = rootView.findViewById(R.id.transportSpinner);
        ArrayAdapter<TransportModel> transporAtdapter = new ArrayAdapter<TransportModel>(rootView.getContext(),
                android.R.layout.simple_spinner_item, data.getTransports());
        transporAtdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transportSpinner.setAdapter(transporAtdapter);

        scrapyardsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setScrapyard((ScrapyardModel) parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setScrapyard((ScrapyardModel) parent.getSelectedItem());
            }
        });

        transportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setTransport((TransportModel) parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setTransport((TransportModel) parent.getSelectedItem());
            }
        });

        tonn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setTonn(s);
            }
        });


        return rootView;
    }

    private void setTransport(TransportModel selectedItem) {
        resultModel.transport = selectedItem;
        updateCost();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            CustomerModel customer = app.getCustomer();
            String discount = "0.00";
            if (customer != null) {
                discount = customer.getDiscount();

                resultModel.discount = Float.parseFloat(discount);
            }

            this.discount.setText(discount + " руб");
            updateCost();
        }
    }

    @SuppressLint("DefaultLocale")
    private void setScrapyard(ScrapyardModel selectedItem) {

        resultModel.scrapyard = selectedItem;

        price.setText(String.format("%.2f руб", resultModel.getScrapyardPrice()));
        updateCost();
//        fullPrice.setText(String.format("%.2f", resultModel.getFullPrice()));
    }

    @SuppressLint("DefaultLocale")
    private void setTonn(Editable s) {
        if (!s.toString().isEmpty()) {
            resultModel.tonn = Float.parseFloat(s.toString());
//        fullPrice.setText(String.format("%.2f", resultModel.getFullPrice()));
        }
    }

    @SuppressLint("DefaultLocale")
    private void updateCost() {
        float cost = 0f;
        Integer distance = 0;
        if (resultModel != null && resultModel.scrapyard != null && resultModel.locality != null &&
                resultModel.transport != null) {
            switch (resultModel.scrapyard.getName()) {
                case "Белогорск":
                    distance = resultModel.locality.getDistanceBelogorsk();
                    break;
                case "Тыгда":
                    distance = resultModel.locality.getDistanceTygda();
                    break;
                default:
                    distance = resultModel.locality.getDistanceSkovorodino();
                    break;
            }

            cost = Float.parseFloat(resultModel.transport.getPrice()) * distance / 1000;

            deliveryCost.setText(String.format("%.2f руб", cost));

            resultModel.cost = cost;
        }
    }

    private void updateButton(){
        submitBtn.setEnabled(resultModel.isOrderValid());
    }
}