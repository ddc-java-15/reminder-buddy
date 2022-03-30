package edu.cnm.deepdive.reminderbuddy.controller;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.reminderbuddy.R;
import java.util.Calendar;

/**
 * Implements a widget that allows the user to set a time for the card.
 */
public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

  private Calendar calendar;
  private NavController navController;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    calendar = DatePickerDialogFragmentArgs.fromBundle(getArguments()).getDate();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    return new TimePickerDialog(getContext(), this,
        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
        DateFormat.is24HourFormat(getContext()));
  }



  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    navController.getPreviousBackStackEntry().getSavedStateHandle().set("calendar", calendar);
  }
}
