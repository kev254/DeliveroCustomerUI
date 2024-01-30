package com.delivero.customer.home;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.delivero.customer.R;
import com.delivero.customer.models.VehicleTypes;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeFragmentToPickupAndDropOffFragment actionHomeFragmentToPickupAndDropOffFragment(
      @NonNull VehicleTypes vehicleType) {
    return new ActionHomeFragmentToPickupAndDropOffFragment(vehicleType);
  }

  public static class ActionHomeFragmentToPickupAndDropOffFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToPickupAndDropOffFragment(@NonNull VehicleTypes vehicleType) {
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("vehicleType", vehicleType);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToPickupAndDropOffFragment setVehicleType(
        @NonNull VehicleTypes vehicleType) {
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("vehicleType", vehicleType);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_homeFragment_to_pickupAndDropOffFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public VehicleTypes getVehicleType() {
      return (VehicleTypes) arguments.get("vehicleType");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToPickupAndDropOffFragment that = (ActionHomeFragmentToPickupAndDropOffFragment) object;
      if (arguments.containsKey("vehicleType") != that.arguments.containsKey("vehicleType")) {
        return false;
      }
      if (getVehicleType() != null ? !getVehicleType().equals(that.getVehicleType()) : that.getVehicleType() != null) {
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
      result = 31 * result + (getVehicleType() != null ? getVehicleType().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToPickupAndDropOffFragment(actionId=" + getActionId() + "){"
          + "vehicleType=" + getVehicleType()
          + "}";
    }
  }
}
