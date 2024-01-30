package com.delivero.customer.home;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.delivero.customer.R;
import com.delivero.customer.models.Request;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class PackageDetailsFragmentDirections {
  private PackageDetailsFragmentDirections() {
  }

  @NonNull
  public static ActionPackageDetailsFragmentToRiderSelectionFragment actionPackageDetailsFragmentToRiderSelectionFragment(
      @NonNull Request request) {
    return new ActionPackageDetailsFragmentToRiderSelectionFragment(request);
  }

  public static class ActionPackageDetailsFragmentToRiderSelectionFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionPackageDetailsFragmentToRiderSelectionFragment(@NonNull Request request) {
      if (request == null) {
        throw new IllegalArgumentException("Argument \"request\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("request", request);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPackageDetailsFragmentToRiderSelectionFragment setRequest(
        @NonNull Request request) {
      if (request == null) {
        throw new IllegalArgumentException("Argument \"request\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("request", request);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("request")) {
        Request request = (Request) arguments.get("request");
        if (Parcelable.class.isAssignableFrom(Request.class) || request == null) {
          __result.putParcelable("request", Parcelable.class.cast(request));
        } else if (Serializable.class.isAssignableFrom(Request.class)) {
          __result.putSerializable("request", Serializable.class.cast(request));
        } else {
          throw new UnsupportedOperationException(Request.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_packageDetailsFragment_to_riderSelectionFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public Request getRequest() {
      return (Request) arguments.get("request");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionPackageDetailsFragmentToRiderSelectionFragment that = (ActionPackageDetailsFragmentToRiderSelectionFragment) object;
      if (arguments.containsKey("request") != that.arguments.containsKey("request")) {
        return false;
      }
      if (getRequest() != null ? !getRequest().equals(that.getRequest()) : that.getRequest() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getRequest() != null ? getRequest().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionPackageDetailsFragmentToRiderSelectionFragment(actionId=" + getActionId() + "){"
          + "request=" + getRequest()
          + "}";
    }
  }
}
