package AdaptersPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ModelClass.Status;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utilitapoweringthelife.R;
import com.example.utilitapoweringthelife.StatusDetailActivty;

import java.util.List;

public class StatusRecyclerAdapter extends RecyclerView.Adapter<StatusRecyclerAdapter.MyViewHolder>{
    private List<Status> statusList;
    private Context mContext;

    public StatusRecyclerAdapter(List<Status> statusList, Context mContext) {

        this.statusList = statusList;
        this.mContext = mContext;
    }

    @NonNull
    @Override


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
 View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_list_item_layout,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Status statusObject = statusList.get(position);
holder.listItem.setText(statusList.get(position).getName());
holder.listItemLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

 mContext.startActivity(new Intent(view.getContext(), StatusDetailActivty.class).putExtra("name",statusObject.getName())
         .putExtra("classStatus",statusObject.getClassStatus()).
                 putExtra("url",statusObject.getUrl()).
                 putExtra("responseTime",statusObject.getResponseTime()).
                 putExtra("responseCode",statusObject.getResponseCode()));
    }
});
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       public TextView listItem;
       public RelativeLayout listItemLayout;

       public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        listItem=itemView.findViewById(R.id.textViewName);
        listItemLayout=itemView.findViewById(R.id.listViewLayout);

    }
}
}
