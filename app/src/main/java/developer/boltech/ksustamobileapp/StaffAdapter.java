package developer.boltech.ksustamobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewAdapter> {

    private List<StaffData> list;
    private Context context;

    public StaffAdapter(List<StaffData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent, false);
        return new StaffViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewAdapter holder, int position) {

        StaffData item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());
        try {
            Glide.with(context).load(item.getImage()).placeholder(R.drawable.avater).into(holder.imageView);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StaffViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, post;
        private ImageView imageView;

        public StaffViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.staffName);
            post = itemView.findViewById(R.id.staffPost);
            email = itemView.findViewById(R.id.staffEmail);
            imageView = itemView.findViewById(R.id.staffImage);
        }
    }
}
