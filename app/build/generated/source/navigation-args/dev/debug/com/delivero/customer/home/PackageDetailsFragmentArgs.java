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

public class PackageDetailsFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private PackageDetailsFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private PackageDetailsFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings({
      "unchecked",
      "deprecation"
  })
  public static PackageDetailsFragmentArgs fromBundle(@NonNull Bundle bundle) {
    PackageDetailsFragmentArgs __result = new PackageDetailsFragmentArgs();
    bundle.setClassLoader(PackageDetailsFragmentArgs.class.getClassLoader());
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
    if (bundle.containsKey("latitude")) {
      float latitude;
      latitude = bundle.getFloat("latitude");
      __result.arguments.put("latitude", latitude);
    } else {
      throw new IllegalArgumentException("Required argument \"latitude\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("longitude")) {
      float longitude;
      longitude = bundle.getFloat("longitude");
      __result.arguments.put("longitude", longitude);
    } else {
      throw new IllegalArgumentException("Required argument \"longitude\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("latitude2")) {
      float latitude2;
      latitude2 = bundle.getFloat("latitude2");
      __result.arguments.put("latitude2", latitude2);
    } else {
      throw new IllegalArgumentException("Required argument \"latitude2\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("longitude2")) {
      float longitude2;
      longitude2 = bundle.getFloat("longitude2");
      __result.arguments.put("longitude2", longitude2);
    } else {
      throw new IllegalArgumentException("Required argument \"longitude2\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("pickup")) {
      String pickup;
      pickup = bundle.getString("pickup");
      if (pickup == null) {
        throw new IllegalArgumentException("Argument \"pickup\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("pickup", pickup);
    } else {
      throw new IllegalArgumentException("Required argument \"pickup\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("destination")) {
      String destination;
      destination = bundle.getString("destination");
      if (destination == null) {
        throw new IllegalArgumentException("Argument \"destination\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("destination", destination);
    } else {
      throw new IllegalArgumentException("Required argument \"destination\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static PackageDetailsFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    PackageDetailsFragmentArgs __result = new PackageDetailsFragmentArgs();
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
    if (savedStateHandle.contains("latitude")) {
      float latitude;
      latitude = savedStateHandle.get("latitude");
      __result.arguments.put("latitude", latitude);
    } else {
      throw new IllegalArgumentException("Required argument \"latitude\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("longitude")) {
      float longitude;
      longitude = savedStateHandle.get("longitude");
      __result.arguments.put("longitude", longitude);
    } else {
      throw new IllegalArgumentException("Required argument \"longitude\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("latitude2")) {
      float latitude2;
      latitude2 = savedStateHandle.get("latitude2");
      __result.arguments.put("latitude2", latitude2);
    } else {
      throw new IllegalArgumentException("Required argument \"latitude2\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("longitude2")) {
      float longitude2;
      longitude2 = savedStateHandle.get("longitude2");
      __result.arguments.put("longitude2", longitude2);
    } else {
      throw new IllegalArgumentException("Required argument \"longitude2\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("pickup")) {
      String pickup;
      pickup = savedStateHandle.get("pickup");
      if (pickup == null) {
        throw new IllegalArgumentException("Argument \"pickup\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("pickup", pickup);
    } else {
      throw new IllegalArgumentException("Required argument \"pickup\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("destination")) {
      String destination;
      destination = savedStateHandle.get("destination");
      if (destination == null) {
        throw new IllegalArgumentException("Argument \"destination\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("destination", destination);
    } else {
      throw new IllegalArgumentException("Required argument \"destination\" is missing and does not have an android:defaultValue");
    }
    return __result;
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
    if (arguments.containsKey("latitude")) {
      float latitude = (float) arguments.get("latitude");
      __result.set("latitude", latitude);
    }
    if (arguments.containsKey("longitude")) {
      float longitude = (float) arguments.get("longitude");
      __result.set("longitude", longitude);
    }
    if (arguments.containsKey("latitude2")) {
      float latitude2 = (float) arguments.get("latitude2");
      __result.set("latitude2", latitude2);
    }
    if (arguments.containsKey("longitude2")) {
      float longitude2 = (float) arguments.get("longitude2");
      __result.set("longitude2", longitude2);
    }
    if (arguments.containsKey("pickup")) {
      String pickup = (String) arguments.get("pickup");
      __result.set("pickup", pickup);
    }
    if (arguments.containsKey("destination")) {
      String destination = (String) arguments.get("destination");
      __result.set("destination", destination);
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
    PackageDetailsFragmentArgs that = (PackageDetailsFragmentArgs) object;
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
    return result;
  }

  @Override
  public String toString() {
    return "PackageDetailsFragmentArgs{"
        + "vehicleType=" + getVehicleType()
        + ", latitude=" + getLatitude()
        + ", longitude=" + getLongitude()
        + ", latitude2=" + getLatitude2()
        + ", longitude2=" + getLongitude2()
        + ", pickup=" + getPickup()
        + ", destination=" + getDestination()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull PackageDetailsFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(@NonNull VehicleTypes vehicleType, float latitude, float longitude,
        float latitude2, float longitude2, @NonNull String pickup, @NonNull String destination) {
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
    public PackageDetailsFragmentArgs build() {
      PackageDetailsFragmentArgs result = new PackageDetailsFragmentArgs(arguments);
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

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setLatitude(float latitude) {
      this.arguments.put("latitude", latitude);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setLongitude(float longitude) {
      this.arguments.put("longitude", longitude);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setLatitude2(float latitude2) {
      this.arguments.put("latitude2", latitude2);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setLongitude2(float longitude2) {
      this.arguments.put("longitude2", longitude2);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setPickup(@NonNull String pickup) {
      if (pickup == null) {
        throw new IllegalArgumentException("Argument \"pickup\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("pickup", pickup);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setDestination(@NonNull String destination) {
      if (destination == null) {
        throw new IllegalArgumentException("Argument \"destination\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("destination", destination);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public VehicleTypes getVehicleType() {
      return (VehicleTypes) arguments.get("vehicleType");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public float getLatitude() {
      return (float) arguments.get("latitude");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public float getLongitude() {
      return (float) arguments.get("longitude");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public float getLatitude2() {
      return (float) arguments.get("latitude2");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public float getLongitude2() {
      return (float) arguments.get("longitude2");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getPickup() {
      return (String) arguments.get("pickup");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getDestination() {
      return (String) arguments.get("destination");
    }
  }
}
