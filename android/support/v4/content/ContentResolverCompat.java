package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;

public final class ContentResolverCompat
{
  public static Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, android.support.v4.os.CancellationSignal paramCancellationSignal)
  {
    if ((Build.VERSION.SDK_INT < 16) || (paramCancellationSignal != null));
    while (true)
    {
      try
      {
        localObject1 = paramCancellationSignal.getCancellationSignalObject();
        Cursor localCursor = paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, (android.os.CancellationSignal)localObject1);
        return localCursor;
        Object localObject2;
        if ((localObject2 instanceof android.os.OperationCanceledException))
          throw new android.support.v4.os.OperationCanceledException();
        throw localObject2;
        if (paramCancellationSignal != null)
          paramCancellationSignal.throwIfCanceled();
        return paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
      }
      catch (Exception localException)
      {
        continue;
      }
      Object localObject1 = null;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.ContentResolverCompat
 * JD-Core Version:    0.6.1
 */