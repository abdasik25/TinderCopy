/**
 * Created by Alexander Lomat
 */


package by.bsuir.tinder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.tinder.fragment.FragmentHelper;
import by.bsuir.tinder.fragment.FragmentType;
import by.bsuir.tinder.fragment.MainFragment;
import by.bsuir.tinder.fragment.ResultFragment;
import by.bsuir.tinder.pojo.TinderImage;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements FragmentHelper {

    private MainFragment mainFragment;
    private ResultFragment resultFragment;
    private FragmentManager fragmentManager;

    private List<TinderImage> likedItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likedItems = new ArrayList<>();

        mainFragment = new MainFragment();
        resultFragment = new ResultFragment();

        fragmentManager = getSupportFragmentManager();

        onFragmentSwitched(FragmentType.MAIN);
    }

    @Override
    public void onFragmentSwitched(FragmentType type) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (type) {
            case MAIN:
                fragmentTransaction.replace(R.id.contentPage, mainFragment);
                break;
            case RESULT:
                fragmentTransaction.replace(R.id.contentPage, resultFragment);
                break;
        }

        fragmentTransaction.commit();
    }

    @Override
    public List<TinderImage> getLikedItems() {
        return likedItems;
    }

    @Override
    public void setLikedItems(List<TinderImage> likedItems) {
        this.likedItems = likedItems;
    }
}
