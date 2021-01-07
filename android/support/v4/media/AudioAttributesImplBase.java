package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.Arrays;

class AudioAttributesImplBase
  implements AudioAttributesImpl
{
  int mContentType = 0;
  int mFlags = 0;
  int mLegacyStream = -1;
  int mUsage = 0;

  AudioAttributesImplBase()
  {
  }

  AudioAttributesImplBase(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContentType = paramInt1;
    this.mFlags = paramInt2;
    this.mUsage = paramInt3;
    this.mLegacyStream = paramInt4;
  }

  public static AudioAttributesImpl fromBundle(Bundle paramBundle)
  {
    if (paramBundle == null)
      return null;
    int i = paramBundle.getInt("android.support.v4.media.audio_attrs.USAGE", 0);
    return new AudioAttributesImplBase(paramBundle.getInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", 0), paramBundle.getInt("android.support.v4.media.audio_attrs.FLAGS", 0), i, paramBundle.getInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AudioAttributesImplBase))
      return false;
    AudioAttributesImplBase localAudioAttributesImplBase = (AudioAttributesImplBase)paramObject;
    int i = this.mContentType;
    int j = localAudioAttributesImplBase.getContentType();
    boolean bool = false;
    if (i == j)
    {
      int k = this.mFlags;
      int m = localAudioAttributesImplBase.getFlags();
      bool = false;
      if (k == m)
      {
        int n = this.mUsage;
        int i1 = localAudioAttributesImplBase.getUsage();
        bool = false;
        if (n == i1)
        {
          int i2 = this.mLegacyStream;
          int i3 = localAudioAttributesImplBase.mLegacyStream;
          bool = false;
          if (i2 == i3)
            bool = true;
        }
      }
    }
    return bool;
  }

  public Object getAudioAttributes()
  {
    return null;
  }

  public int getContentType()
  {
    return this.mContentType;
  }

  public int getFlags()
  {
    int i = this.mFlags;
    int j = getLegacyStreamType();
    if (j == 6)
      i |= 4;
    else if (j == 7)
      i |= 1;
    return i & 0x111;
  }

  public int getLegacyStreamType()
  {
    if (this.mLegacyStream != -1)
      return this.mLegacyStream;
    return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
  }

  public int getRawLegacyStreamType()
  {
    return this.mLegacyStream;
  }

  public int getUsage()
  {
    return this.mUsage;
  }

  public int getVolumeControlStream()
  {
    return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(this.mContentType);
    arrayOfObject[1] = Integer.valueOf(this.mFlags);
    arrayOfObject[2] = Integer.valueOf(this.mUsage);
    arrayOfObject[3] = Integer.valueOf(this.mLegacyStream);
    return Arrays.hashCode(arrayOfObject);
  }

  @NonNull
  public Bundle toBundle()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("android.support.v4.media.audio_attrs.USAGE", this.mUsage);
    localBundle.putInt("android.support.v4.media.audio_attrs.CONTENT_TYPE", this.mContentType);
    localBundle.putInt("android.support.v4.media.audio_attrs.FLAGS", this.mFlags);
    if (this.mLegacyStream != -1)
      localBundle.putInt("android.support.v4.media.audio_attrs.LEGACY_STREAM_TYPE", this.mLegacyStream);
    return localBundle;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("AudioAttributesCompat:");
    if (this.mLegacyStream != -1)
    {
      localStringBuilder.append(" stream=");
      localStringBuilder.append(this.mLegacyStream);
      localStringBuilder.append(" derived");
    }
    localStringBuilder.append(" usage=");
    localStringBuilder.append(AudioAttributesCompat.usageToString(this.mUsage));
    localStringBuilder.append(" content=");
    localStringBuilder.append(this.mContentType);
    localStringBuilder.append(" flags=0x");
    localStringBuilder.append(Integer.toHexString(this.mFlags).toUpperCase());
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.AudioAttributesImplBase
 * JD-Core Version:    0.6.1
 */