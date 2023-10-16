package developer.boltech.ksustamobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class DepartmentAdapter extends PagerAdapter {

    private Context context;
    private List<DepartmentModel> list;

    public DepartmentAdapter(Context context, List<DepartmentModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.department_item_layout, container, false);

        ImageView departmentIcon;
        TextView departmentTitle, departmentDescription;

        departmentIcon = view.findViewById(R.id.department_icon);
        departmentTitle = view.findViewById(R.id.department_title);
        departmentDescription = view.findViewById(R.id.department_description);

        departmentIcon.setImageResource(list.get(position).getImg());
        departmentTitle.setText(list.get(position).getTitle());
        departmentDescription.setText(list.get(position).getDescription());

        container.addView(view, 0);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
