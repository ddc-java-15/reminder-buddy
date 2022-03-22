package edu.cnm.deepdive.reminderbuddy.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.reminderbuddy.R;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

  private Calendar calendar;
  private OnChangeListener listener;
  private NavController navController;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    calendar = DatePickerDialogFragmentArgs.fromBundle(getArguments()).getDate();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Fragment parentFragment = getParentFragment();
    FragmentActivity hostActivity = getActivity();
    navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
    if (parentFragment instanceof OnChangeListener) {
      listener = (OnChangeListener) parentFragment;
    } else if (hostActivity instanceof OnChangeListener) {
      listener = (OnChangeListener) hostActivity;
    } else {
      listener = (cal) -> {};
    }
    Dialog dialog = new DatePickerDialog(getContext(), this,
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    return dialog;
  }

  @Override
  public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
    Calendar updateValue = Calendar.getInstance();
    updateValue.set(year, month, dayOfMonth);
    navController.getPreviousBackStackEntry().getSavedStateHandle().set("calendar", updateValue);
  }


  public interface OnChangeListener {
    void onChange(Calendar calendar);
  }

}
