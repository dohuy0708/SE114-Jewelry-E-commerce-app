package com.example.jewelryecommerceapp.ViewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.R;

public class AdServiceViewHolder extends RecyclerView.ViewHolder {
    public TextView textView1, textView2, textView3, textView4;
    public EditText editText1, editText2, editText3, editText4;

    public AdServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        textView1 = itemView.findViewById(R.id.textView37);
        textView2 = itemView.findViewById(R.id.textView38);
        textView3 = itemView.findViewById(R.id.textView39);
        textView4 = itemView.findViewById(R.id.textView49);
        editText1 = itemView.findViewById(R.id.editText);
        editText2 = itemView.findViewById(R.id.editText2);
        editText3 = itemView.findViewById(R.id.editText3);
        editText4 = itemView.findViewById(R.id.editText4);
    }

    public void setText(String serviceType) {
    }
}
