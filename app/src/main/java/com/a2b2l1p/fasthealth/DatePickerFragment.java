package com.a2b2l1p.fasthealth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.a2b2l1p.fasthealth.modificaData;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {


    private Calendar d;

    public static DatePickerFragment newInstance() {
        DatePickerFragment frag = new DatePickerFragment();
        Bundle args = new Bundle();

        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final DatePicker datePicker = new DatePicker(getActivity());
        d= Calendar.getInstance();
        Dialog dg;
        dg= new AlertDialog.Builder(getActivity(),R.style.CustomDialog)
                .setView(datePicker)
                .setPositiveButton(R.string.alert_dialog_ok,
                        (dialog, whichButton) -> {
                            d.set(Calendar.YEAR,datePicker.getYear());
                            d.set(Calendar.MONTH,datePicker.getMonth());
                            d.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                            ((modificaData)getActivity()).updateDN(d);
                            ((modificaData)getActivity()).doPositiveClick(d);
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        (dialog, whichButton) -> ((modificaData)getActivity()).doNegativeClick(d)
                )
                .create();

        return dg;
    }





    public Calendar getDate() {
        return d;
    }

    public void setDate(Calendar d) {
        this.d = d;
    }
}

