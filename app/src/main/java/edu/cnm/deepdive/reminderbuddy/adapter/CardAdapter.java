package edu.cnm.deepdive.reminderbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.reminderbuddy.R;
import edu.cnm.deepdive.reminderbuddy.adapter.CardAdapter.Holder;
import edu.cnm.deepdive.reminderbuddy.databinding.ItemCardBinding;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import java.text.DateFormat;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final DateFormat dateFormat;
  private final DateFormat timeFormat;
  private final List<Card> cards;
  private final String dateTimeOrderFormat;
  private final String durationFormat;

  public CardAdapter(Context context, List<Card> cards) {
    this.context = context;
    this.cards = cards;
    this.inflater = LayoutInflater.from(context);
    this.dateFormat = android.text.format.DateFormat.getDateFormat(context);
    timeFormat = android.text.format.DateFormat.getTimeFormat(context);
    durationFormat = context.getString(R.string.mmss_format);
    dateTimeOrderFormat = context.getString(R.string.date_time_order_format);
  }


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemCardBinding binding = ItemCardBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return cards.size();
  }



  class Holder extends RecyclerView.ViewHolder {

    private final ItemCardBinding binding;

    public Holder(@NonNull ItemCardBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      Card item = cards.get(position);
      binding.date.setText(item.getDate().toString());
      binding.information.setText(item.getInformation().toString());
      binding.location.setText(item.getLocation().toString());

    }

  }


}
