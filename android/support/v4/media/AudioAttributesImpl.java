package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import androidx.versionedparcelable.c;

abstract interface AudioAttributesImpl extends c
{
  public abstract Object getAudioAttributes();

  public abstract int getContentType();

  public abstract int getFlags();

  public abstract int getLegacyStreamType();

  public abstract int getRawLegacyStreamType();

  public abstract int getUsage();

  public abstract int getVolumeControlStream();

  @NonNull
  public abstract Bundle toBundle();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.media.AudioAttributesImpl
 * JD-Core Version:    0.6.1
 */