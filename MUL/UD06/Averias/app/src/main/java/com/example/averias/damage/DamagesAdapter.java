package com.example.averias.damage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.averias.R;

import java.util.List;

/**
 * @author Victor Piles
 */
public class DamagesAdapter extends ArrayAdapter<Damage> {

	private final Context context;
	private final int resource;
	private final List<Damage> damageList;

	public DamagesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Damage> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.damageList = objects;
	}

	@SuppressLint("SetTextI18n")
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		// Get view
		@SuppressLint("ViewHolder")
		View view = LayoutInflater.from(context).inflate(resource, parent, false);

		// Get elements of this view
		Damage thisDamage = damageList.get(position);
		TextView damageTitle = view.findViewById(R.id.damageTitle);
		TextView damageModel = view.findViewById(R.id.damageModel);
		TextView damageBudget = view.findViewById(R.id.damageBudget);
		ImageView damageImage = view.findViewById(R.id.damageImage);
		ImageView damageDocument = view.findViewById(R.id.damageDocument);

		// Set the data from this damage to the list
		damageTitle.setText(thisDamage.getTitle());
		damageModel.setText(thisDamage.getCarModel());
		damageBudget.setText(thisDamage.getBudgetNumber() + " budget(s).");

		return view;
	}
}