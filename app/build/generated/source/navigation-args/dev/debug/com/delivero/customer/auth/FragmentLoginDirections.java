package com.delivero.customer.auth;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.delivero.customer.R;

public class FragmentLoginDirections {
  private FragmentLoginDirections() {
  }

  @NonNull
  public static NavDirections actionFragmentLoginToFragmentRegister() {
    return new ActionOnlyNavDirections(R.id.action_fragmentLogin_to_fragmentRegister);
  }
}
