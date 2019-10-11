package by.bsuir.tinder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.tinder.R;
import by.bsuir.tinder.adapter.ItemAdapter;
import by.bsuir.tinder.callback.ItemTouchHelperCallback;
import by.bsuir.tinder.pojo.TinderImage;

public class MainFragment extends Fragment {

    private static final int ITEMS_PER_SCREEN = 1;
    private FragmentHelper fragmentHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentHelper = (FragmentHelper) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        ItemAdapter adapter = new ItemAdapter(initTinderImages(), fragmentHelper, ITEMS_PER_SCREEN);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((Context) fragmentHelper);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<TinderImage> initTinderImages() {

        List<TinderImage> items = new ArrayList<>();

        items.add(new TinderImage("Anastasiya, 21", "https://sun9-34.userapi.com/c834204/v834204558/15de1d/AgPglW34MUA.jpg", 4.7f));
        items.add(new TinderImage("Alina, 20","https://sun9-1.userapi.com/c854420/v854420456/eb86d/03CZOcQeD-8.jpg",  676f));
        items.add(new TinderImage("Ekaterina, 29", "https://sun9-49.userapi.com/c840539/v840539701/2f8a8/CAA4uYgDTrI.jpg", 3.2f));
        items.add(new TinderImage("Alexandra, 20", "https://sun9-28.userapi.com/c837629/v837629057/57a1b/Is2FiQ3kvKI.jpg", 1.4f));
        items.add(new TinderImage("Sasha, 22", "https://sun9-26.userapi.com/c844321/v844321086/ea33d/Opxb1yDeP-A.jpg", 11073f));
        return items;
    }
}
