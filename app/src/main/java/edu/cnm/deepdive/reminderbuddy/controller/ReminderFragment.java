package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentReminderBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.viewmodel.LoginViewModel;
import edu.cnm.deepdive.reminderbuddy.viewmodel.ResponseViewModel;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ReminderFragment extends Fragment {

  private ResponseViewModel responseViewModel;
  private LoginViewModel loginViewModel;
  private List<Card> cards;
  private long userId;
  private Card card;
  private FragmentReminderBinding binding;
  private ListIterator<Card> cardIterator;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentReminderBinding.inflate(inflater, container, false);
    // TODO Attach event listeners to buttons,
    return binding.getRoot();

  }

  // TODO override onviewcreated and connect to a viewmodel.
  @SuppressWarnings("ConstantConditions")
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    responseViewModel = new ViewModelProvider(getActivity()).get(ResponseViewModel.class);
    responseViewModel
        .getThrowable()
        .observe(getViewLifecycleOwner(), this::handleThrowable);
    responseViewModel
        .getCard()
        .observe(getViewLifecycleOwner(), (card) -> {
          // TODO display the hint and the image from the current card
        });
    responseViewModel
        .getFutureCards()
        .observe(getViewLifecycleOwner(), (cards) -> {
          this.cards = cards;
          Collections.shuffle(cards); // TODO Consider shuffling in SQL, Using ORDER BY to pull from random column
          cardIterator = cards.listIterator();
          next();
        });
    loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
    loginViewModel
        .getUser()
        .observe(getViewLifecycleOwner(), (user) -> {
          userId = user.getId();
          // TODO explore personalization of the UX (based on user)
          responseViewModel.setUserId(userId);
        });
  }

  // TODO Attach next and previous to respective buttons
  private void next() {
    if (cardIterator.hasNext()) {
      card = cardIterator.next();
    }
    updateNavigation();
  }

  private void previous() {
    if (cardIterator.hasPrevious()) {
      card = cardIterator.previous();
    }
    updateNavigation();
  }

  private void updateNavigation() {
    binding.nextReminder.setVisibility(cardIterator.hasNext() ? View.VISIBLE : View.INVISIBLE);
    binding.previousReminder.setVisibility(cardIterator.hasPrevious() ? View.VISIBLE : View.INVISIBLE);
  }


  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }

  // TODO Have Reminder Fragment get a randomized list of cards to allow the user to "test"
  public List<Card> getCards() {
    return cards;
  }

  private void handleThrowable(Throwable throwable) {
    if (throwable != null) {
      //noinspection ConstantConditions
      Snackbar
          .make(binding.getRoot(), throwable.getMessage(), Snackbar.LENGTH_LONG)
          .show();
    }
  }


}