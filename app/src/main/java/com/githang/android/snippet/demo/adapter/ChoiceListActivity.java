package com.githang.android.snippet.demo.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.githang.android.snippet.adapter.ChoiceListAdapter;
import com.githang.android.snippet.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * FIXME
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 */
public class ChoiceListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> data = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            data.add("test" + i);
        }
        ListView listView = new ListView(this);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ChoiceListAdapter adapter = new ChoiceListAdapter<String>(this, R.layout.item_single_choice,
                data, R.id.checkedView) {
            @Override
            protected void holdView(ChoiceLayout view) {
                view.hold(R.id.text);
            }

            @Override
            protected void bindData(ChoiceLayout view, int position, String data) {
                TextView text = view.get(R.id.text);
                text.setText(data);
            }
        };
        listView.setAdapter(adapter);
        this.setContentView(listView);
    }
}
