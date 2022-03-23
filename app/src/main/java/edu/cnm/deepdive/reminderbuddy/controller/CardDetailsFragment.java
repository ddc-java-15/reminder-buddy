package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
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

public class CardDetailsFragment extends BottomSheetDialogFragment implements
    DatePickerDialogFragment.OnChangeListener {


  private NavController navController;
  private FragmentCardDetailsBinding binding;
  private CardViewModel cardViewModel;
  private long userId;
  private long cardId;
  private Card card;
  private DateFormat dateFormat;
  private LoginViewModel loginViewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    CardDetailsFragmentArgs args = CardDetailsFragmentArgs.fromBundle(getArguments());
    cardId = args.getCardId();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    navController = Navigation
        .findNavController(getActivity(), R.id.nav_host_fragment);
    binding = FragmentCardDetailsBinding.inflate(inflater, container, false);
    binding.save.setOnClickListener((v) -> {
      card.setInformation(binding.information.getText().toString().trim());
      card.setLocation(binding.location.getText().toString().trim());
      card.setHint(binding.hint.getText().toString().trim());
      Calendar calendar = Calendar.getInstance();
//      calendar.set(binding.date.getYear(), binding.date.getMonth(), binding.date.getDayOfMonth());
      card.setDate(calendar.getTime());
      cardViewModel.save(card);
      dismiss();
    });
    binding.date.setOnClickListener((v) -> navController
        .navigate(CardDetailsFragmentDirections.openDatePicker(
            new Calendar.Builder().setInstant(card.getDate()).build())));
    binding.cancel.setOnClickListener((v) -> dismiss());
    dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
    loginViewModel
        .getUser()
        .observe(getViewLifecycleOwner(), (user) -> cardViewModel.setUserId(user.getId()));
    //noinspection ConstantConditions
    cardViewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
    cardViewModel
        .getCard()
        .observe(getViewLifecycleOwner(), (card) -> {
          // TODO Populate view objects
        });
    if (cardId != 0) {
      cardViewModel.setCardId(cardId);
    } else {
      card = new Card();
      card.setDate(Calendar.getInstance().getTime());
      binding.date.setText(dateFormat.format(card.getDate()));
    }
    LiveData<Calendar> datePickerCalender = navController
        .getCurrentBackStackEntry()
        .getSavedStateHandle()
        .getLiveData("calendar");
        datePickerCalender.observe(getViewLifecycleOwner(), (cal) -> {
          Calendar updated = new Calendar.Builder()
              .setInstant(card.getDate())
              .setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                  cal.get(Calendar.DAY_OF_MONTH))
              .build();
          card.setDate(updated.getTime());
          binding.date.setText(dateFormat.format(card.getDate()));

        });
        loginViewModel
            .getUser()
            .observe(getViewLifecycleOwner(), (user) -> userId = user.getId());

  }

  @Override
  public void onChange(Calendar calendar) {
  }
}