package lom.lom_android.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import lom.lom_android.R;
import lom.lom_android.service.App;
import lom.lom_android.service.ResultModel;

public class ConfirmActivity extends Activity {

    private ResultModel resultModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);


        App application = (App) getApplication();
        resultModel = application.getResultModel();
        TextView confirm = findViewById(R.id.confirmMsg);
        TextView error = findViewById(R.id.errorMsg);

        if (resultModel.isRequestSuccsess) {
            confirm.setVisibility(View.VISIBLE);
            error.setVisibility(View.INVISIBLE);
        } else {
            error.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.INVISIBLE);
        }
    }

    public void onCloseClickBtn(View view) {
        onBackPressed();
    }

    public void onRetryClickBtn(View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
