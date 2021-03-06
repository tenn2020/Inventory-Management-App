package edu.qc.seclass.fim.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcels;

import edu.qc.seclass.fim.DetailActivity;
import edu.qc.seclass.fim.ProductActivity;
import edu.qc.seclass.fim.R;
import edu.qc.seclass.fim.models.FloorProduct;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Activity activity;
    private Context context;
    private Activity productActivity;
    private ArrayList<FloorProduct> floorList;

    public ProductAdapter(Activity activity, Context context, ArrayList<FloorProduct> floor, Activity productActivity){
        this.activity = activity;
        this.context = context;
        this.floorList = floor;
        this.productActivity = productActivity;
    }
    public void setFilteredList(ArrayList<FloorProduct> filteredList){
        this.floorList = filteredList;
        notifyDataSetChanged();
    }
//    public ProductAdapter(ProductActivity activity){
//        this.activity = activity;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        FloorProduct floorProduct = floorList.get(position);

        holder.bind(floorProduct);
    }

    @Override
    public int getItemCount() {
        return floorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView floor_category_txt, floor_type_txt, floor_quantity_txt;
        View floor_color_view;
        RelativeLayout relativeLayout;

        public ViewHolder(View view){
            super(view);
            floor_category_txt = view.findViewById(R.id.floor_category_txt);
            floor_type_txt = view.findViewById(R.id.floor_type_txt);
            floor_color_view = view.findViewById(R.id.floor_color_view);
            floor_quantity_txt = view.findViewById((R.id.floor_quantity_txt));
            relativeLayout = view.findViewById(R.id.relativelayout);
        }


        public void bind(FloorProduct floorProduct){
            floor_category_txt.setText(floorProduct.getFloorCategory());
            floor_type_txt.setText(floorProduct.getFloorType());
            floor_color_view.setBackgroundColor(Color.parseColor(floorProduct.getFloorColor().toLowerCase()));
            floor_quantity_txt.setText(String.valueOf(floorProduct.getQuantity()));
            floor_color_view.setVisibility(View.VISIBLE);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("floor", Parcels.wrap(floorProduct));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    Bundle extras = productActivity.getIntent().getExtras();
                    if (extras != null) {
                        String value = extras.getString("key");
                        intent.putExtra("key", value);
                    }
                    activity.startActivityForResult(intent, 1);
                }
            });

        }

    }
}
