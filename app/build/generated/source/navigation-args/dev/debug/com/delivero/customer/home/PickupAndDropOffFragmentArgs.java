package com.delivero.customer.home;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.delivero.customer.models.VehicleTypes;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class PickupAndDropOffFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private PickupAndDropOffFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private PickupAndDropOffFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static PickupAndDropOffFragmentArgs fromBundle(@NonNull Bundle bundle) {
    PickupAndDropOffFragmentArgs __result = new PickupAndDropOffFragmentArgs();
    bundle.setClassLoader(PickupAndDropOffFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("vehicleType")) {
      VehicleTypes vehicleType;
      if (Parcelable.class.isAssignableFrom(VehicleTypes.class) || Serializable.class.isAssignableFrom(VehicleTypes.class)) {
        vehicleType = (VehicleTypes) bundle.get("vehicleType");
      } else {
        throw new UnsupportedOperationException(VehicleTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("vehicleType", vehicleType);
    } else {
      throw new IllegalArgumentException("Required argument \"vehicleType\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static PickupAndDropOffFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    PickupAndDropOffFragmentArgs __result = new PickupAndDropOffFragmentArgs();
    if (savedStateHandle.contains("vehicleType")) {
      VehicleTypes vehicleType;
      vehicleType = savedStateHandle.get("vehicleType");
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("vehicleType", vehicleType);
    } else {
      throw new IllegalArgumentException("Required argument \"vehicleType\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public VehicleTypes getVehicleType() {
    return (VehicleTypes) arguments.get("vehicleType");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("vehicleType")) {
      VehicleTypes vehicleType = (VehicleTypes) arguments.get("vehicleType");
      if (Parcelable.class.isAssignableFrom(VehicleTypes.class) || vehicleType == null) {
        __result.putParcelable("vehicleType", Parcelable.class.cast(vehicleType));
      } else if (Serializable.class.isAssignableFrom(VehicleTypes.class)) {
        __result.putSerializable("vehicleType", Serializable.class.cast(vehicleType));
      } else {
        throw new UnsupportedOperationException(VehicleTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
      }
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("vehicleType")) {
      VehicleTypes vehicleType = (VehicleTypes) arguments.get("vehicleType");
      if (Parcelable.class.isAssignableFrom(VehicleTypes.class) || vehicleType == null) {
        __result.set("vehicleType", Parcelable.class.cast(vehicleType));
      } else if (Serializable.class.isAssignableFrom(VehicleTypes.class)) {
        __result.set("vehicleType", Serializable.class.cast(vehicleType));
      } else {
        throw new UnsupportedOperationException(VehicleTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
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
    PickupAndDropOffFragmentArgs that = (PickupAndDropOffFragmentArgs) object;
    if (arguments.containsKey("vehicleType") != that.arguments.containsKey("vehicleType")) {
      return false;
    }
    if (getVehicleType() != null ? !getVehicleType().equals(that.getVehicleType()) : that.getVehicleType() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getVehicleType() != null ? getVehicleType().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "PickupAndDropOffFragmentArgs{"
        + "vehicleType=" + getVehicleType()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull PickupAndDropOffFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull VehicleTypes vehicleType) {
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("vehicleType", vehicleType);
    }

    @NonNull
    public PickupAndDropOffFragmentArgs build() {
      PickupAndDropOffFragmentArgs result = new PickupAndDropOffFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setVehicleType(@NonNull VehicleTypes vehicleType) {
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("vehicleType", vehicleType);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public VehicleTypes getVehicleType() {
      return (VehicleTypes) arguments.get("vehicleType");
    }
  }
}
