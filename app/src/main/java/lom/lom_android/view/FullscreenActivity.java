package lom.lom_android.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import lom.lom_android.R;
import lom.lom_android.service.App;
import lom.lom_android.service.CustomerModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends Activity {
    private View errorMsg;
    private Button retryBtn;

    private final Handler mHideHandler = new Handler();
    private Boolean canShowNext = false;

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            if (canShowNext) {
                tryToMain();
            } else {
                canShowNext = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        errorMsg = findViewById(R.id.errorMsg);
        retryBtn = findViewById(R.id.retryBtn);

        mHideHandler.postDelayed(mHideRunnable, 15000);
        loadContext();
    }


    private void loadContext() {
        errorMsg.setVisibility(View.INVISIBLE);
        retryBtn.setVisibility(View.INVISIBLE);

        App.getApi().getCustomer("bash").enqueue(new Callback<List<CustomerModel>>() {
            @Override
            public void onResponse(Call<List<CustomerModel>> call, Response<List<CustomerModel>> response) {
                tryToMain();
            }

            @Override
            public void onFailure(Call<List<CustomerModel>> call, Throwable t) {
                errorMsg.setVisibility(View.VISIBLE);
                retryBtn.setVisibility(View.VISIBLE);
                tryToMain();
            }
        });
    }

    private void tryToMain() {
        if (canShowNext) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } else {
            canShowNext = true;
        }
    }

    public void onClickBtn(View v) {
        loadContext();
    }
}
