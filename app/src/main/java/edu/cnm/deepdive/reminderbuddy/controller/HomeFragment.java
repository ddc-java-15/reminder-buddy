package edu.cnm.deepdive.reminderbuddy.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.reminderbuddy.adapter.CardAdapter;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.reminderbuddy.viewmodel.CardViewModel;
import edu.cnm.deepdive.reminderbuddy.viewmodel.LoginViewModel;

public class HomeFragment extends Fragment {

  private CardViewModel cardViewModel;
  private LoginViewModel loginViewModel;
  private FragmentHomeBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    binding.create.setOnClickListener((v) -> {
      Navigation
          .findNavController(binding.getRoot())
          .navigate(HomeFragmentDirections.openDetails());
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
    cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
    loginViewModel
        .getUser()
        .observe(getViewLifecycleOwner(), (user) -> cardViewModel.setUserId(user.getId()));
    cardViewModel
        .getCards()
        .observe(getViewLifecycleOwner(), (cards) -> {
          CardAdapter adapter = new CardAdapter(getContext(), cards);
          binding.cards.setAdapter(adapter);
        });
  }
}
