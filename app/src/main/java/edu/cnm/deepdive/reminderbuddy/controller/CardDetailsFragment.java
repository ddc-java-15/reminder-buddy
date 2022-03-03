package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentCardDetailsBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.viewmodel.CardViewModel;

public class CardDetailsFragment extends BottomSheetDialogFragment {


  private FragmentCardDetailsBinding binding;
  private CardViewModel viewModel;
  private long cardId;
  private Card card;

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
    binding = FragmentCardDetailsBinding.inflate(inflater, container, false);
    // TODO Attach listeners to controls
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //noinspection ConstantConditions
    viewModel = new ViewModelProvider(getActivity()).get(CardViewModel.class);
    viewModel
        .getCard()
        .observe(getViewLifecycleOwner(), (card) -> {
          // TODO Populate view objects
        });
    if (cardId == 0) {
      viewModel.setCardId(cardId);
    } else {
      card = new Card();
    }
  }
}