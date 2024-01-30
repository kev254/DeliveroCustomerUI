// Generated by view binder compiler. Do not edit!
package com.delivero.customer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.delivero.customer.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHomeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ShapeableImageView avatar;

  @NonNull
  public final MaterialTextView book;

  @NonNull
  public final ViewPager2 pager;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final MaterialTextView userName;

  private FragmentHomeBinding(@NonNull LinearLayout rootView, @NonNull ShapeableImageView avatar,
      @NonNull MaterialTextView book, @NonNull ViewPager2 pager, @NonNull TabLayout tabLayout,
      @NonNull MaterialTextView userName) {
    this.rootView = rootView;
    this.avatar = avatar;
    this.book = book;
    this.pager = pager;
    this.tabLayout = tabLayout;
    this.userName = userName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.avatar;
      ShapeableImageView avatar = ViewBindings.findChildViewById(rootView, id);
      if (avatar == null) {
        break missingId;
      }

      id = R.id.book;
      MaterialTextView book = ViewBindings.findChildViewById(rootView, id);
      if (book == null) {
        break missingId;
      }

      id = R.id.pager;
      ViewPager2 pager = ViewBindings.findChildViewById(rootView, id);
      if (pager == null) {
        break missingId;
      }

      id = R.id.tab_layout;
      TabLayout tabLayout = ViewBindings.findChildViewById(rootView, id);
      if (tabLayout == null) {
        break missingId;
      }

      id = R.id.userName;
      MaterialTextView userName = ViewBindings.findChildViewById(rootView, id);
      if (userName == null) {
        break missingId;
      }

      return new FragmentHomeBinding((LinearLayout) rootView, avatar, book, pager, tabLayout,
          userName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
