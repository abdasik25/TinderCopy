/**
 * Created by Alexander Lomat
 */


package by.bsuir.tinder.fragment;

import java.util.List;

import by.bsuir.tinder.pojo.TinderImage;

/**
 * The interface Fragment helper.
 */
public interface FragmentHelper {
    /**
     * On fragment switched.
     *
     * @param fragmentType the fragment type
     */
    void onFragmentSwitched(FragmentType fragmentType);

    /**
     * Gets liked items.
     *
     * @return the liked items
     */
    List<TinderImage> getLikedItems();

    /**
     * Sets liked items.
     *
     * @param likedItems the liked items
     */
    void setLikedItems(List<TinderImage> likedItems);
}
