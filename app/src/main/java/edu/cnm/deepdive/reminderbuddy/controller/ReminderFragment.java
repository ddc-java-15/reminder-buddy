package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentReminderBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import java.util.List;

public class ReminderFragment extends Fragment {


  private List<Card> cards;
  private FragmentReminderBinding binding;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentReminderBinding.inflate(inflater, container, false);
    return binding.getRoot();

  }


// TODO override onviewcreated and connect to a viewmodel.


  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }

  // TODO Have Reminder Fragment get a randomized list of cards to allow the user to "test"
  public List<Card> getCards() {
    return cards;
  }


}