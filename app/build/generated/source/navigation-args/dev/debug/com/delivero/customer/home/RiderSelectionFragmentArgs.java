package com.delivero.customer.home;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.delivero.customer.models.Request;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class RiderSelectionFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private RiderSelectionFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private RiderSelectionFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static RiderSelectionFragmentArgs fromBundle(@NonNull Bundle bundle) {
    RiderSelectionFragmentArgs __result = new RiderSelectionFragmentArgs();
    bundle.setClassLoader(RiderSelectionFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("request")) {
      Request request;
      if (Parcelable.class.isAssignableFrom(Request.class) || Serializable.class.isAssignableFrom(Request.class)) {
        request = (Request) bundle.get("request");
      } else {
        throw new UnsupportedOperationException(Request.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (request == null) {
        throw new IllegalArgumentException("Argument \"request\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("request", request);
    } else {
      throw new IllegalArgumentException("Required argument \"request\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static RiderSelectionFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    RiderSelectionFragmentArgs __result = new RiderSelectionFragmentArgs();
    if (savedStateHandle.contains("request")) {
      Request request;
      request = savedStateHandle.get("request");
      if (request == null) {
        throw new IllegalArgumentException("Argument \"request\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("request", request);
    } else {
      throw new IllegalArgumentException("Required argument \"request\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Request getRequest() {
    return (Request) arguments.get("request");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("request")) {
      Request request = (Request) arguments.get("request");
      if (Parcelable.class.isAssignableFrom(Request.class) || request == null) {
        __result.set("request", Parcelable.class.cast(request));
      } else if (Serializable.class.isAssignableFrom(Request.class)) {
        __result.set("request", Serializable.class.cast(request));
      } else {
        throw new UnsupportedOperationException(Request.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    RiderSelectionFragmentArgs that = (RiderSelectionFragmentArgs) object;
    if (arguments.containsKey("request") != that.arguments.containsKey("request")) {
      return false;
    }
    if (getRequest() != null ? !getRequest().equals(that.getRequest()) : that.getRequest() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getRequest() != null ? getRequest().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "RiderSelectionFragmentArgs{"
        + "request=" + getRequest()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull RiderSelectionFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull Request request) {
      if (request == null) {
        throw new IllegalArgumentException("Argument \"request\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("request", request);
    }

    @NonNull
    public RiderSelectionFragmentArgs build() {
      RiderSelectionFragmentArgs result = new RiderSelectionFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setRequest(@NonNull Request request) {
      if (request == null) {
        throw new IllegalArgumentException("Argument \"request\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("request", request);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public Request getRequest() {
      return (Request) arguments.get("request");
    }
  }
}
