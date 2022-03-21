package edu.cnm.deepdive.reminderbuddy.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.reminderbuddy.R;
import edu.cnm.deepdive.reminderbuddy.databinding.ActivityMainBinding;
import edu.cnm.deepdive.reminderbuddy.viewmodel.CardViewModel;
import edu.cnm.deepdive.reminderbuddy.viewmodel.LoginViewModel;


public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private CardViewModel viewModel;
  private LoginViewModel loginViewModel;
  private AppBarConfiguration appBarConfiguration;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    viewModel = new ViewModelProvider(this).get(CardViewModel.class);
    setContentView(binding.getRoot());
    NavController navController = ((NavHostFragment)getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment))
        .getNavController();
    appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_reminder)
        .build();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(binding.navView, navController);
    loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    loginViewModel
        .getAccount()
        .observe(this, (account) -> {
          if (account == null) {
            Intent intent = new Intent(this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
          }
        });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.main_options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled;
    if (item.getItemId() == R.id.sign_out) {
      loginViewModel.signOut();
      handled = true;
    } else {
      handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }
}