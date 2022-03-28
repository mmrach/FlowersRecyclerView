package com.amm.flowersrecyclerview;

import android.content.Context;

import com.amm.flowersrecyclerview.R;

import java.util.Arrays;
import java.util.List;

public class DataSource {
    List<String> getFlowerList(Context context ) {
        return  Arrays.asList(context.getResources().getStringArray(R.array.flower_array));
    }
}
