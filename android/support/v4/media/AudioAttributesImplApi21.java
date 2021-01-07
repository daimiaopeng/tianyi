package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@TargetApi(21)
class AudioAttributesImplApi21
  implements AudioAttributesImpl
{
  private static final String TAG = "AudioAttributesCompat21";
  static Method sAudioAttributesToLegacyStreamType;
  AudioAttributes mAudioAttributes;
  int mLegacyStreamType = -1;

  AudioAttributesImplApi21()
  {
  }

  AudioAttributesImplApi21(AudioAttributes paramAudioAttributes)
  {
    this(paramAudioAttributes, -1);
  }

  AudioAttributesImplApi21(AudioAttributes paramAudioAttributes, int paramInt)
  {
    this.mAudioAttributes = paramAudioAttributes;
    this.mLegacyStreamType = paramInt;
  }

  public static AudioAttributesImpl fromBundle(Bundle paramBundle)
  {
    if (paramBundle == null)
      return null;
    AudioAttributes localAudioAttributes = (AudioAttributes)paramBundle.getParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS");
    if (localAudioAttributes == null)
      return null;
    return new AudioAttributesImplApi21(localAudioAttributes, paramBundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
  }

  // ERROR //
  static Method getAudioAttributesToLegacyStreamTypeMethod()
  {
    // Byte code:
    //   0: getstatic 55	android/support/v4/media/AudioAttributesImplApi21:sAudioAttributesToLegacyStreamType	Ljava/lang/reflect/Method;
    //   3: ifnonnull +22 -> 25
    //   6: ldc 43
    //   8: ldc 57
    //   10: iconst_1
    //   11: anewarray 59	java/lang/Class
    //   14: dup
    //   15: iconst_0
    //   16: ldc 43
    //   18: aastore
    //   19: invokevirtual 63	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   22: putstatic 55	android/support/v4/media/AudioAttributesImplApi21:sAudioAttributesToLegacyStreamType	Ljava/lang/reflect/Method;
    //   25: getstatic 55	android/support/v4/media/AudioAttributesImplApi21:sAudioAttributesToLegacyStreamType	Ljava/lang/reflect/Method;
    //   28: areturn
    //   29: aconst_null
    //   30: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	25	29	java/lang/NoSuchMethodException
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AudioAttributesImplApi21))
      return false;
    AudioAttributesImplApi21 localAudioAttributesImplApi21 = (AudioAttributesImplApi21)paramObject;
    return this.mAudioAttributes.equals(localAudioAttributesImplApi21.mAudioAttributes);
  }

  public Object getAudioAttributes()
  {
    return this.mAudioAttributes;
  }

  public int getContentType()
  {
    return this.mAudioAttributes.getContentType();
  }

  public int getFlags()
  {
    return this.mAudioAttributes.getFlags();
  }

  public int getLegacyStreamType()
  {
    if (this.mLegacyStreamType != -1)
      return this.mLegacyStreamType;
    Method localMethod = getAudioAttributesToLegacyStreamTypeMethod();
    if (localMethod == null)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("No AudioAttributes#toLegacyStreamType() on API: ");
      localStringBuilder1.append(Build.VERSION.SDK_INT);
      Log.w("AudioAttributesCompat21", localStringBuilder1.toString());
      return -1;
    }
    try
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mAudioAttributes;
      int i = ((Integer)localMethod.invoke(null, arrayOfObject)).intValue();
      return i;
    }
    catch (InvocationTargetException|IllegalAccessException localInvocationTargetException)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("getLegacyStreamType() failed on API: ");
      localStringBuilder2.append(Build.VERSION.SDK_INT);
      Log.w("AudioAttributesCompat21", localStringBuilder2.toString(), localInvocationTargetException);
    }
    return -1;
  }

  public int getRawLegacyStreamType()
  {
    return this.mLegacyStreamType;
  }

  public int getUsage()
  {
    return this.mAudioAttributes.getUsage();
  }

  public int getVolumeControlStream()
  {
    if (Build.VERSION.SDK_INT >= 26)
      return this.mAudioAttributes.getVolumeControlStream();
    return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
  }

  public int hashCode()
  {
    return this.mAudioAttributes.hashCode();
  }

  @NonNull
  public Bundle toBundle()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("android.support.v4.media.audio_attrs.FRAMEWORKS", this.mAudioAttributes);
    if (this.mLegacyStreamType != -1)
      localBundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", this.mLegacyStreamType);
    return localBundle;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("AudioAttributesCompat: audioattributes=");
    localStringBuilder.append(this.mAudioAttributes);
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.AudioAttributesImplApi21
 * JD-Core Version:    0.6.1
 */