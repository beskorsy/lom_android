package lom.lom_android.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import github.chenupt.springindicator.SpringIndicator;
import lom.lom_android.R;
import lom.lom_android.service.App;
import lom.lom_android.service.ResultModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private CustomViewPager mViewPager;
    private SpringIndicator springIndicator;
    private ResultModel resultModel;

    private String[] titles = {"Контакты", "Заказ", "Дополнительно"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setCurrentItem(0);
        App application = (App) getApplication();
        resultModel = application.getResultModel();
        mViewPager.setModel(resultModel);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                View v = getCurrentFocus();
                if (v instanceof EditText) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                setTitle(titles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mViewPager.setPagingEnabled(true);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        springIndicator = findViewById(R.id.indicator);
        springIndicator.setViewPager(mViewPager);
        setTitle(titles[mViewPager.getCurrentItem()]);
    }

    public void onClickSubmitBtn(View view) {
        App.getApi().sendRequest(resultModel.phone,
                resultModel.loader,
                resultModel.cutter,
                resultModel.calculatedInPlace,
                resultModel.discount.toString(),
                resultModel.locality.getName(),
                resultModel.address,
                resultModel.scrapyard.getName(),
                String.valueOf(resultModel.distantce),
                resultModel.transport.getName() + " " + resultModel.transport.getTonn(),
                resultModel.cost.toString(),
                resultModel.tonn.toString(),
                resultModel.data,
                resultModel.comment).enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {

                if (response.body() != null && response.body().size() > 0) {
                    resultModel.isRequestSuccsess = true;
                } else {
                    resultModel.isRequestSuccsess = false;
                }
                Intent intent = new Intent(getBaseContext(), ConfirmActivity.class);
                startActivity(intent);
            }


            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                resultModel.isRequestSuccsess = false;
                Intent intent = new Intent(getBaseContext(), ConfirmActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClickNextButton(View view) {
        mViewPager.setCurrentItem(1, true);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ContactFragment.newInstance();
                case 1:
                    return OrderFragment.newInstance();
                case 2:
                    return ExtraFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "1";
                case 1:
                    return "2";
                case 2:
                    return "3";
            }
            return null;
        }
    }
}
