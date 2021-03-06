package edu.cnm.deepdive.reminderbuddy.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.reminderbuddy.R;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
    //noinspection ConstantConditions
    navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    return new DatePickerDialog(getContext(), this,
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
  }

  @Override
  public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
    calendar.set(year, month, dayOfMonth);
    //noinspection ConstantConditions
    navController.getPreviousBackStackEntry().getSavedStateHandle().set("calendar", calendar);
  }



}
