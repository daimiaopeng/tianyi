package android.support.v4.app;

import android.app.Notification.Builder;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface NotificationBuilderWithBuilderAccessor
{
  public abstract Notification.Builder getBuilder();
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationBuilderWithBuilderAccessor
 * JD-Core Version:    0.6.1
 */