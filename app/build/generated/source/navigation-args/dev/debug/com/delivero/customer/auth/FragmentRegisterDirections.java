package com.delivero.customer.auth;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.delivero.customer.R;

public class FragmentRegisterDirections {
  private FragmentRegisterDirections() {
  }

  @NonNull
  public static NavDirections actionFragmentRegisterToFragmentLogin() {
    return new ActionOnlyNavDirections(R.id.action_fragmentRegister_to_fragmentLogin);
  }
}
