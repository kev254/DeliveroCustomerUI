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

public class PickupAndDropOffFragmentDirections {
  private PickupAndDropOffFragmentDirections() {
  }

  @NonNull
  public static ActionPickupAndDropOffFragmentToPackageDetailsFragment actionPickupAndDropOffFragmentToPackageDetailsFragment(
      @NonNull VehicleTypes vehicleType, float latitude, float longitude, float latitude2,
      float longitude2, @NonNull String pickup, @NonNull String destination) {
    return new ActionPickupAndDropOffFragmentToPackageDetailsFragment(vehicleType, latitude, longitude, latitude2, longitude2, pickup, destination);
  }

  public static class ActionPickupAndDropOffFragmentToPackageDetailsFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionPickupAndDropOffFragmentToPackageDetailsFragment(
        @NonNull VehicleTypes vehicleType, float latitude, float longitude, float latitude2,
        float longitude2, @NonNull String pickup, @NonNull String destination) {
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("vehicleType", vehicleType);
      this.arguments.put("latitude", latitude);
      this.arguments.put("longitude", longitude);
      this.arguments.put("latitude2", latitude2);
      this.arguments.put("longitude2", longitude2);
      if (pickup == null) {
        throw new IllegalArgumentException("Argument \"pickup\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("pickup", pickup);
      if (destination == null) {
        throw new IllegalArgumentException("Argument \"destination\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("destination", destination);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setVehicleType(
        @NonNull VehicleTypes vehicleType) {
      if (vehicleType == null) {
        throw new IllegalArgumentException("Argument \"vehicleType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("vehicleType", vehicleType);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setLatitude(float latitude) {
      this.arguments.put("latitude", latitude);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setLongitude(float longitude) {
      this.arguments.put("longitude", longitude);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setLatitude2(float latitude2) {
      this.arguments.put("latitude2", latitude2);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setLongitude2(float longitude2) {
      this.arguments.put("longitude2", longitude2);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setPickup(
        @NonNull String pickup) {
      if (pickup == null) {
        throw new IllegalArgumentException("Argument \"pickup\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("pickup", pickup);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionPickupAndDropOffFragmentToPackageDetailsFragment setDestination(
        @NonNull String destination) {
      if (destination == null) {
        throw new IllegalArgumentException("Argument \"destination\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("destination", destination);
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
      if (arguments.containsKey("latitude")) {
        float latitude = (float) arguments.get("latitude");
        __result.putFloat("latitude", latitude);
      }
      if (arguments.containsKey("longitude")) {
        float longitude = (float) arguments.get("longitude");
        __result.putFloat("longitude", longitude);
      }
      if (arguments.containsKey("latitude2")) {
        float latitude2 = (float) arguments.get("latitude2");
        __result.putFloat("latitude2", latitude2);
      }
      if (arguments.containsKey("longitude2")) {
        float longitude2 = (float) arguments.get("longitude2");
        __result.putFloat("longitude2", longitude2);
      }
      if (arguments.containsKey("pickup")) {
        String pickup = (String) arguments.get("pickup");
        __result.putString("pickup", pickup);
      }
      if (arguments.containsKey("destination")) {
        String destination = (String) arguments.get("destination");
        __result.putString("destination", destination);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_pickupAndDropOffFragment_to_packageDetailsFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public VehicleTypes getVehicleType() {
      return (VehicleTypes) arguments.get("vehicleType");
    }

    @SuppressWarnings("unchecked")
    public float getLatitude() {
      return (float) arguments.get("latitude");
    }

    @SuppressWarnings("unchecked")
    public float getLongitude() {
      return (float) arguments.get("longitude");
    }

    @SuppressWarnings("unchecked")
    public float getLatitude2() {
      return (float) arguments.get("latitude2");
    }

    @SuppressWarnings("unchecked")
    public float getLongitude2() {
      return (float) arguments.get("longitude2");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPickup() {
      return (String) arguments.get("pickup");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getDestination() {
      return (String) arguments.get("destination");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionPickupAndDropOffFragmentToPackageDetailsFragment that = (ActionPickupAndDropOffFragmentToPackageDetailsFragment) object;
      if (arguments.containsKey("vehicleType") != that.arguments.containsKey("vehicleType")) {
        return false;
      }
      if (getVehicleType() != null ? !getVehicleType().equals(that.getVehicleType()) : that.getVehicleType() != null) {
        return false;
      }
      if (arguments.containsKey("latitude") != that.arguments.containsKey("latitude")) {
        return false;
      }
      if (Float.compare(that.getLatitude(), getLatitude()) != 0) {
        return false;
      }
      if (arguments.containsKey("longitude") != that.arguments.containsKey("longitude")) {
        return false;
      }
      if (Float.compare(that.getLongitude(), getLongitude()) != 0) {
        return false;
      }
      if (arguments.containsKey("latitude2") != that.arguments.containsKey("latitude2")) {
        return false;
      }
      if (Float.compare(that.getLatitude2(), getLatitude2()) != 0) {
        return false;
      }
      if (arguments.containsKey("longitude2") != that.arguments.containsKey("longitude2")) {
        return false;
      }
      if (Float.compare(that.getLongitude2(), getLongitude2()) != 0) {
        return false;
      }
      if (arguments.containsKey("pickup") != that.arguments.containsKey("pickup")) {
        return false;
      }
      if (getPickup() != null ? !getPickup().equals(that.getPickup()) : that.getPickup() != null) {
        return false;
      }
      if (arguments.containsKey("destination") != that.arguments.containsKey("destination")) {
        return false;
      }
      if (getDestination() != null ? !getDestination().equals(that.getDestination()) : that.getDestination() != null) {
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
      result = 31 * result + Float.floatToIntBits(getLatitude());
      result = 31 * result + Float.floatToIntBits(getLongitude());
      result = 31 * result + Float.floatToIntBits(getLatitude2());
      result = 31 * result + Float.floatToIntBits(getLongitude2());
      result = 31 * result + (getPickup() != null ? getPickup().hashCode() : 0);
      result = 31 * result + (getDestination() != null ? getDestination().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionPickupAndDropOffFragmentToPackageDetailsFragment(actionId=" + getActionId() + "){"
          + "vehicleType=" + getVehicleType()
          + ", latitude=" + getLatitude()
          + ", longitude=" + getLongitude()
          + ", latitude2=" + getLatitude2()
          + ", longitude2=" + getLongitude2()
          + ", pickup=" + getPickup()
          + ", destination=" + getDestination()
          + "}";
    }
  }
}
