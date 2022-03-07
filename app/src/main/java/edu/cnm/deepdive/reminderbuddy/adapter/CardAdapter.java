package edu.cnm.deepdive.reminderbuddy.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.reminderbuddy.adapter.CardAdapter.Holder;
import edu.cnm.deepdive.reminderbuddy.databinding.ItemCardBinding;

public class CardAdapter extends RecyclerView.Adapter<Holder> {
  // TODO Declare fields for a layout inflater, date format, and a list of card

  // TODO Define a constructor that takes a context and a list of card as parameters; populate list
  //  of card field from parameter; populate the layout inflater using the context; populate the
  //  date format using the context.
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null; // TODO
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return 0; // TODO
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemCardBinding binding;

    public Holder(@NonNull ItemCardBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      // TODO Retrieve Card at position from list.
      // TODO Populate view objects in binding with property values from card.
    }

  }


}
