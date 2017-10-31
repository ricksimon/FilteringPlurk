package cc.ricksimon.android.filteringplurk.activity;

import android.content.Intent;
import android.os.Bundle;

import cc.ricksimon.android.filteringplurk.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startActivity(new Intent(this,AuthorizeActivity.class));
    }
}
