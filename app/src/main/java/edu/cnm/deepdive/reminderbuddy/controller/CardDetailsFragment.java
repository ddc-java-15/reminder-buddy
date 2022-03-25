package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.reminderbuddy.R;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentCardDetailsBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.viewmodel.CardViewModel;
import edu.cnm.deepdive.reminderbuddy.viewmodel.LoginViewModel;
import java.text.DateFormat;
import java.util.Calendar;

public class CardDetailsFragment extends BottomSheetDialogFragment {

  private NavController navController;
  private FragmentCardDetailsBinding binding;
  private CardViewModel cardViewModel;
  private long userId;
  private long cardId;
  private Card card;
  private DateFormat dateFormat;
  private DateFormat timeFormat;
  private Calendar calendar;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    CardDetailsFragmentArgs args = CardDetailsFragmentArgs.fromBundle(getArguments());
    cardId = args.getCardId();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    //noinspection ConstantConditions
    navController = Navigation
        .findNavController(getActivity(), R.id.nav_host_fragment);
    binding = FragmentCardDetailsBinding.inflate(inflater, container, false);
    binding.save.setOnClickListener((v) -> save());
    binding.date.setOnClickListener(
        (v) -> navController.navigate(CardDetailsFragmentDirections.openDatePicker(calendar)));
    binding.time.setOnClickListener(
        (v) -> navController.navigate(CardDetailsFragmentDirections.openTimePicker(calendar)));
    binding.cancel.setOnClickListener((v) -> dismiss());
    dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
    timeFormat = android.text.format.DateFormat.getTimeFormat(getContext());
    return binding.getRoot();
  }

  @SuppressWarnings("ConstantConditions")
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    LoginViewModel loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
    loginViewModel
        .getUser()
        .observe(getViewLifecycleOwner(), (user) -> userId = user.getId());
    cardViewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
    cardViewModel
        .getCard()
        .observe(getViewLifecycleOwner(), this::bind);
    if (cardId != 0) {
      cardViewModel.setCardId(cardId);
    } else {
      bind(new Card());
    }
    LiveData<Calendar> pickerData = navController
        .getCurrentBackStackEntry()
        .getSavedStateHandle()
        .getLiveData("calendar");
    pickerData.observe(getViewLifecycleOwner(), this::renderDate);
    pickerData.observe(getViewLifecycleOwner(), this::renderTime);
    loginViewModel
        .getUser()
        .observe(getViewLifecycleOwner(), (user) -> userId = user.getId());

  }

  private void renderDate(Calendar cal) {
    calendar.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    binding.date.setText(dateFormat.format(cal.getTime()));
  }

  private void renderTime(Calendar cal) {
    calendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
    calendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
    binding.time.setText(timeFormat.format(cal.getTime()));
  }

  private void bind(Card card) {
    this.card = card;
    calendar = new Calendar.Builder()
        .setInstant(card.getDate())
        .build();
    binding.information.setText(card.getInformation());
    binding.hintLabel.setText(card.getHint());
    binding.location.setText(card.getLocation());
    binding.date.setText(dateFormat.format(card.getDate()));
    binding.time.setText(timeFormat.format(card.getDate()));
  }


  private void save() {
    card.setUserId(userId);
    card.setInformation(binding.information.getText().toString().trim());
    card.setLocation(binding.location.getText().toString().trim());
    card.setHint(binding.hintLabel.getText().toString().trim());
    card.setDate(calendar.getTime());
    cardViewModel.save(card);
    dismiss();
  }

}