package yuvajobopps.niit.org.yuva;

/**
 * Created by Deepak Upadhyay on 17-Jul-16.
 */
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView textViewHeadName,textViewHeadDesc,textViewHeadVacancy,textViewHeadDOI,textViewHeadPerson;

        TextView textViewName,textViewDesc,textViewVacancy,textViewDOI,textViewPerson;

        public DataObjectHolder(View itemView) {
            super(itemView);

            textViewHeadName = (TextView) itemView.findViewById(R.id.textViewHead_Name);
            textViewHeadDesc = (TextView) itemView.findViewById(R.id.textViewHead_Desc);
            textViewHeadVacancy = (TextView) itemView.findViewById(R.id.textViewHead_No_Of_Vacancy);
            textViewHeadDOI = (TextView) itemView.findViewById(R.id.textViewHead_date_interview);
            textViewHeadPerson = (TextView) itemView.findViewById(R.id.textViewHead_Contact_Person);

            textViewName = (TextView) itemView.findViewById(R.id.textView_Name);
            textViewDesc = (TextView) itemView.findViewById(R.id.textView_Desc);
            textViewVacancy = (TextView) itemView.findViewById(R.id.textView_No_Of_Vacancy);
            textViewDOI = (TextView) itemView.findViewById(R.id.textView_date_interview);
            textViewPerson = (TextView) itemView.findViewById(R.id.textView_Contact_Person);


            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }



    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.textViewHeadName.setText    ("Company Name");
        holder.textViewHeadDesc.setText    ("Job Description");
        holder.textViewHeadVacancy.setText ("No. of Vacancy");
        holder.textViewHeadDOI.setText     ("Date of Interview");
        holder.textViewHeadPerson.setText  ("Contact Person");

        holder.textViewName.setText(mDataset.get(position).getmText1());
        holder.textViewDesc.setText(mDataset.get(position).getmText2());
        holder.textViewVacancy.setText(mDataset.get(position).getmText3());
        holder.textViewDOI.setText(mDataset.get(position).getmText4());
        holder.textViewPerson.setText(mDataset.get(position).getmText5());
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}