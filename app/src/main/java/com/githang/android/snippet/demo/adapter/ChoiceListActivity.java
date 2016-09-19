package com.githang.android.snippet.demo.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.githang.android.snippet.adapter.ChoiceListAdapter;
import com.githang.android.snippet.adapter.ItemCreator;
import com.githang.android.snippet.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 单选，多选，全选，反选的例子。
 *
 * @author Geek_Soledad (msdx.android@qq.com)
 */
public class ChoiceListActivity extends Activity implements ItemCreator<String, ChoiceListAdapter.DefaultChoiceView> {
    private RadioGroup mChoiceMode;
    private View mChoiceAll;
    private View mReverseChoice;
    private View mButtons;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_choice_list);
        mChoiceMode = (RadioGroup) findViewById(R.id.choice_mode);
        mButtons = findViewById(R.id.buttons);
        mChoiceAll = findViewById(R.id.all);
        mReverseChoice = findViewById(R.id.reverse);
        mListView = (ListView) findViewById(android.R.id.list);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("test" + i);
        }
        final ChoiceListAdapter<String, ChoiceListAdapter.DefaultChoiceView> adapter = new ChoiceListAdapter<>(data, this);
        mListView.setAdapter(adapter);

        mChoiceMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.single_choice) {
                    mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    mListView.setAdapter(adapter);
                    mButtons.setVisibility(View.INVISIBLE);
                } else {
                    mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    mButtons.setVisibility(View.VISIBLE);
                }
            }
        });

        mChoiceAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int size = adapter.getCount();
                for (int i = 0; i < size; i++) {
                    mListView.setItemChecked(i, true);
                }
            }
        });

        mReverseChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItems = mListView.getCheckedItemPositions();
                final int size = adapter.getCount();
                for (int i = 0; i < size; i++) {
                    mListView.setItemChecked(i, !checkedItems.get(i));
                }
            }
        });

        mChoiceMode.check(R.id.single_choice);
    }

    @Override
    public ChoiceListAdapter.DefaultChoiceView createHolder(int position, ViewGroup parent) {
        View itemView = View.inflate(this, R.layout.item_single_choice, null);
        ChoiceListAdapter.DefaultChoiceView holder = new ChoiceListAdapter.DefaultChoiceView(this, itemView);
        holder.hold(R.id.text);
        holder.setChoiceId(R.id.checkedView);
        return holder;
    }

    @Override
    public void bindData(int position, ChoiceListAdapter.DefaultChoiceView holder, String data) {
        TextView text = holder.get(R.id.text);
        text.setText(data);
    }
}
