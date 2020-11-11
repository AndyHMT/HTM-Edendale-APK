package com.harelmallac.edendale;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DayEndSummaryActivity extends AppCompatActivity {

    TextView tvTypeCash, tvCountCash, tvAmtCash, tvTypeCredit, tvCountCredit, tvAmtCredit, tvCashRep1, tvCashRep2, tvCashInHand, tvCheque, tvRemiAmt, tvProductiveCall, tvCancelCash, tvCancelCredit, tvCashAmt, tvCreditAmt, tvCancelCashQty, tvCancelCreditQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_end_summary);
        tvTypeCash = findViewById(R.id.textView27);
        tvTypeCash.setText("Cdc");
    }
}