/**
 * Created by Alexander Lomat
 */


package by.bsuir.tinder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import by.bsuir.tinder.R;
import by.bsuir.tinder.adapter.ItemAdapter;
import by.bsuir.tinder.view.HomeButton;

/**
 * The type Result fragment.
 */
public class ResultFragment extends Fragment {

    private FragmentHelper fragmentHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentHelper = (FragmentHelper) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HomeButton homeButton = Objects.requireNonNull(getActivity()).findViewById(R.id.homeButton);
        homeButton.setOnClickListener((v) -> fragmentHelper.onFragmentSwitched(FragmentType.MAIN));

        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerLikes);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);

        ItemAdapter adapter = new ItemAdapter(fragmentHelper.getLikedItems(), fragmentHelper, -1);
        recyclerView.setAdapter(adapter);
    }
}
