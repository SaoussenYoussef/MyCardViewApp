package fr.clubomnisportsulis.mycarviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.MyViewHolder>

{
    private Context context;
    private List<AppsModels> appsList;


    /**
     * Step 1 : Creating MyViewHolder Class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, appDown;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initializing the wiews
            title = itemView.findViewById(R.id.title);
            appDown = itemView.findViewById(R.id.count);
            thumbnail = itemView.findViewById(R.id.thumbnail);


        }
    }

    /**
     * Step 2 : Variables and constructors
     * @param context
     * @param appsList
     */
    public AppsAdapter(Context context, List<AppsModels> appsList) {
        this.context = context;
        this.appsList = appsList;
    }

    /**
     * Step 3 : Implemnting methods of AppsAdapter Class
     * @param parent
     * @param viewType
     * @return
     */




    @NonNull
    @Override
    public AppsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_app, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.MyViewHolder holder, int position) {

        AppsModels appsModels = appsList.get(position);

        holder.title.setText(appsModels.getName());
        holder.appDown.setText(appsModels.getNumOfDownloads()+" Users");

        // Displaying Image using Glide Library

        Glide.with(context)
                .load(appsModels.getThumbnail())
                .into(holder.thumbnail);



    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }


}
