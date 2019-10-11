package by.bsuir.tinder.fragment;

import java.util.List;

import by.bsuir.tinder.pojo.TinderImage;

public interface FragmentHelper {
    void onFragmentSwitched(FragmentType fragmentType);
    List<TinderImage> getLikedItems();
    void setLikedItems(List<TinderImage> likedItems);
}
