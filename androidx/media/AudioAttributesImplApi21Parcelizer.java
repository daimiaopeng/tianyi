package androidx.media;

import android.media.AudioAttributes;
import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesImplApi21;
import androidx.versionedparcelable.a;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public final class AudioAttributesImplApi21Parcelizer
{
  public static AudioAttributesImplApi21 read(a parama)
  {
    AudioAttributesImplApi21 localAudioAttributesImplApi21 = new AudioAttributesImplApi21();
    localAudioAttributesImplApi21.mAudioAttributes = ((AudioAttributes)parama.b(localAudioAttributesImplApi21.mAudioAttributes, 1));
    localAudioAttributesImplApi21.mLegacyStreamType = parama.b(localAudioAttributesImplApi21.mLegacyStreamType, 2);
    return localAudioAttributesImplApi21;
  }

  public static void write(AudioAttributesImplApi21 paramAudioAttributesImplApi21, a parama)
  {
    parama.a(false, false);
    parama.a(paramAudioAttributesImplApi21.mAudioAttributes, 1);
    parama.a(paramAudioAttributesImplApi21.mLegacyStreamType, 2);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     androidx.media.AudioAttributesImplApi21Parcelizer
 * JD-Core Version:    0.6.1
 */