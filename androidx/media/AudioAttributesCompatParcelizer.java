package androidx.media;

import android.support.annotation.RestrictTo;
import android.support.v4.media.AudioAttributesCompat;
import android.support.v4.media.AudioAttributesImpl;
import androidx.versionedparcelable.a;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public final class AudioAttributesCompatParcelizer
{
  public static AudioAttributesCompat read(a parama)
  {
    AudioAttributesCompat localAudioAttributesCompat = new AudioAttributesCompat();
    localAudioAttributesCompat.mImpl = ((AudioAttributesImpl)parama.b(localAudioAttributesCompat.mImpl, 1));
    return localAudioAttributesCompat;
  }

  public static void write(AudioAttributesCompat paramAudioAttributesCompat, a parama)
  {
    parama.a(false, false);
    parama.a(paramAudioAttributesCompat.mImpl, 1);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     androidx.media.AudioAttributesCompatParcelizer
 * JD-Core Version:    0.6.1
 */