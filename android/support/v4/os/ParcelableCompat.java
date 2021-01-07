package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;

@Deprecated
public final class ParcelableCompat
{
  @Deprecated
  public static <T> Parcelable.Creator<T> newCreator(ParcelableCompatCreatorCallbacks<T> paramParcelableCompatCreatorCallbacks)
  {
    return new ParcelableCompatCreatorHoneycombMR2(paramParcelableCompatCreatorCallbacks);
  }

  static class ParcelableCompatCreatorHoneycombMR2<T>
    implements Parcelable.ClassLoaderCreator<T>
  {
    private final ParcelableCompatCreatorCallbacks<T> mCallbacks;

    ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks<T> paramParcelableCompatCreatorCallbacks)
    {
      this.mCallbacks = paramParcelableCompatCreatorCallbacks;
    }

    public T createFromParcel(Parcel paramParcel)
    {
      return this.mCallbacks.createFromParcel(paramParcel, null);
    }

    public T createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      return this.mCallbacks.createFromParcel(paramParcel, paramClassLoader);
    }

    public T[] newArray(int paramInt)
    {
      return this.mCallbacks.newArray(paramInt);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.os.ParcelableCompat
 * JD-Core Version:    0.6.1
 */