package android.support.v13.view;

import android.app.Activity;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;

public final class DragAndDropPermissionsCompat
{
  private Object mDragAndDropPermissions;

  private DragAndDropPermissionsCompat(Object paramObject)
  {
    this.mDragAndDropPermissions = paramObject;
  }

  @Nullable
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static DragAndDropPermissionsCompat request(Activity paramActivity, DragEvent paramDragEvent)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      DragAndDropPermissions localDragAndDropPermissions = paramActivity.requestDragAndDropPermissions(paramDragEvent);
      if (localDragAndDropPermissions != null)
        return new DragAndDropPermissionsCompat(localDragAndDropPermissions);
    }
    return null;
  }

  public void release()
  {
    if (Build.VERSION.SDK_INT >= 24)
      ((DragAndDropPermissions)this.mDragAndDropPermissions).release();
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v13.view.DragAndDropPermissionsCompat
 * JD-Core Version:    0.6.1
 */