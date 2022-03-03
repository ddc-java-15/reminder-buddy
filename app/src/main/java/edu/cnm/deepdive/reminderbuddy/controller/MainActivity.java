package edu.cnm.deepdive.reminderbuddy.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import edu.cnm.deepdive.reminderbuddy.NavigationMapDirections;
import edu.cnm.deepdive.reminderbuddy.R;
import edu.cnm.deepdive.reminderbuddy.databinding.ActivityMainBinding;
import edu.cnm.deepdive.reminderbuddy.viewmodel.CardViewModel;


public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private CardViewModel viewModel;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    viewModel = new ViewModelProvider(this).get(CardViewModel.class);
    setContentView(binding.getRoot());
    NavController navController = ((NavHostFragment)getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment))
        .getNavController();

    binding.create.setOnClickListener((v) -> {
      navController.navigate(NavigationMapDirections.openDetails());
    });
  }
}