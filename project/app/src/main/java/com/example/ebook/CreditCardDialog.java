package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreditCardDialog extends DialogFragment {

    TextView creditNum;
    TextView creditDate;
    TextView creditCvv;
    Button creditYes;
    Button creditNo;
    private  PriorityListener listener;


    public interface PriorityListener {
        void setActivityText(CreditCard creditInfo);
    }

    public CreditCardDialog(PriorityListener listener) {
        super();
        this.listener = listener;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_credit_card_dialog, null);
        builder.setView(dialogView);
        builder.setTitle("Please register your credit card");

        creditNum = dialogView.findViewById(R.id.registerCard);
        creditDate = dialogView.findViewById(R.id.registerCardDate);
        creditCvv = dialogView.findViewById(R.id.registerCardCVV);
        creditYes = dialogView.findViewById(R.id.editCreditComfirm);
        creditNo = dialogView.findViewById(R.id.editCreditCancel);

        creditYes.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                CreditCard cinfo = new CreditCard(creditNum.getText().toString(), creditDate.getText().toString(), creditCvv.getText().toString());
                listener.setActivityText(cinfo);
                dismiss();
            }
        });

        creditNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();

    }
}