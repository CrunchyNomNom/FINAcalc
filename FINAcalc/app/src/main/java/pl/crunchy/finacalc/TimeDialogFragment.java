package pl.crunchy.finacalc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class TimeDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    private EditText editDecs;
    private EditText editSecs;
    private EditText editMins;
    private TimeDialogListener listener;

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.time_dialog, null);

        builder.setView(view)
                .setTitle("Pick a time")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mins = editMins.getText().toString();
                        String secs = editSecs.getText().toString();
                        String decs = editDecs.getText().toString();
                        listener.applyTime(mins,secs,decs);
                    }
                });

        editDecs = view.findViewById(R.id.decs);
        editSecs = view.findViewById(R.id.secs);
        editMins = view.findViewById(R.id.mins);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (TimeDialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString());
        }
    }

    public interface TimeDialogListener{
        void applyTime(String mins, String secs, String decs);
    }
}
