package edu.cnm.deepdive.reminderbuddy.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.reminderbuddy.R;
import edu.cnm.deepdive.reminderbuddy.databinding.FragmentHistoryBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import edu.cnm.deepdive.reminderbuddy.viewmodel.LoginViewModel;
import edu.cnm.deepdive.reminderbuddy.viewmodel.ResponseViewModel;

public class HistoryFragment extends Fragment {

  private LoginViewModel loginViewModel;
  private ResponseViewModel responseViewModel;
  private FragmentHistoryBinding binding;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentHistoryBinding.inflate(inflater, container, false);
    // TODO Attach event listeners to controls; if it has buttons. Perform other prep of controls.
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
    loginViewModel
        .getUser()
        .observe(getViewLifecycleOwner(), (user) -> responseViewModel.setUserId(user.getId()));
    responseViewModel = new ViewModelProvider(getActivity()).get(ResponseViewModel.class);
    responseViewModel
        .getSummary()
        .observe(getViewLifecycleOwner(), (summary) -> {
          binding.correct.setText(String.valueOf(summary.getOrDefault(true, 0L)));
          binding.incorrect.setText(String.valueOf(summary.getOrDefault(false, 0L)));
        });
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }
}