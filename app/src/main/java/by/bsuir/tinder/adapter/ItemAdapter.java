package by.bsuir.tinder.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.tinder.R;
import by.bsuir.tinder.fragment.FragmentType;
import by.bsuir.tinder.fragment.FragmentHelper;
import by.bsuir.tinder.pojo.TinderImage;
import by.bsuir.tinder.view.CustomImageView;
import by.bsuir.tinder.view.CustomTextView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private static final String DISTANCE = " km from you";

    private int itemPerScreen;

    private FragmentHelper fragmentHelper;

    private List<TinderImage> tinderImages;
    private List<TinderImage> likedTinderImages;


    public ItemAdapter(List<TinderImage> tinderImages, FragmentHelper fragmentHelper, int itemPerScreen) {
        likedTinderImages = new ArrayList<>();
        this.tinderImages = tinderImages;
        this.fragmentHelper = fragmentHelper;
        this.itemPerScreen = itemPerScreen;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spot, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(tinderImages.get(position));
    }

    @Override
    public int getItemCount() {
        return itemPerScreen < 0 ? tinderImages.size() : tinderImages.isEmpty() ? 0 : itemPerScreen;
    }

    @Override
    public void onItemSwipedLeft() {

        itemSwiped();
    }

    @Override
    public void onItemSwipedRight() {
        likedTinderImages.add(tinderImages.get(0));
        itemSwiped();
    }

    private void itemSwiped() {
        tinderImages.remove(0);
        notifyItemRemoved(0);

        if (tinderImages.size() == 0) {
            fragmentHelper.setLikedItems(likedTinderImages);
            fragmentHelper.onFragmentSwitched(FragmentType.RESULT);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private CustomImageView imageView;
        private CustomTextView title;
        private CustomTextView distance;

        private static final float HEIGHT_PERCENTAGE = 95f / 100;
        private static final int TEXT_SIZE = 45;

        ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setBackgroundColor(Color.WHITE);
            imageView = itemView.findViewById(R.id.item_image);
            title = itemView.findViewById(R.id.item_name);
            distance = itemView.findViewById(R.id.item_distance);
        }

        void bind(TinderImage tinderImage) {

            title.setText(tinderImage.getTitle());
            title.setHeightPercentage(HEIGHT_PERCENTAGE);
            distance.setText(tinderImage.getDistance() + DISTANCE);
            distance.setTextSize(TEXT_SIZE);

            Glide.with(imageView).asBitmap().load(tinderImage.getImageUrl()).into(new CustomTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                    imageView.setBitmapToDraw(resource);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {
                }
            });
        }
    }
}
