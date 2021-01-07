package com.karics.library.zxing.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;

final class b
{
  private final Context a;
  private Point b;
  private Point c;

  b(Context paramContext)
  {
    this.a = paramContext;
  }

  Point a()
  {
    return this.c;
  }

  @SuppressLint({"NewApi"})
  void a(Camera paramCamera)
  {
    Camera.Parameters localParameters = paramCamera.getParameters();
    Display localDisplay = ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay();
    this.b = new Point(localDisplay.getWidth(), localDisplay.getHeight());
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Screen resolution: ");
    localStringBuilder1.append(this.b);
    Log.i("CameraConfiguration", localStringBuilder1.toString());
    Point localPoint = new Point();
    localPoint.x = this.b.x;
    localPoint.y = this.b.y;
    if (this.b.x < this.b.y)
    {
      localPoint.x = this.b.y;
      localPoint.y = this.b.x;
    }
    this.c = c.a(localParameters, localPoint);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Camera resolution: ");
    localStringBuilder2.append(this.c);
    Log.i("CameraConfiguration", localStringBuilder2.toString());
  }

  void a(Camera paramCamera, int paramInt)
  {
    try
    {
      Class localClass = paramCamera.getClass();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      Method localMethod = localClass.getMethod("setDisplayOrientation", arrayOfClass);
      if (localMethod != null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        localMethod.invoke(paramCamera, arrayOfObject);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  void a(Camera paramCamera, boolean paramBoolean)
  {
    Camera.Parameters localParameters = paramCamera.getParameters();
    if (localParameters == null)
    {
      Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
      return;
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Initial camera parameters: ");
    localStringBuilder1.append(localParameters.flatten());
    Log.i("CameraConfiguration", localStringBuilder1.toString());
    if (paramBoolean)
      Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
    c.a(localParameters, localSharedPreferences.getBoolean("preferences_auto_focus", true), localSharedPreferences.getBoolean("preferences_disable_continuous_focus", true), paramBoolean);
    localParameters.setPreviewSize(this.c.x, this.c.y);
    a(paramCamera, 90);
    StringBuilder localStringBuilder2 = new StringBuilder();
    localStringBuilder2.append("Final camera parameters: ");
    localStringBuilder2.append(localParameters.flatten());
    Log.i("CameraConfiguration", localStringBuilder2.toString());
    paramCamera.setParameters(localParameters);
    Camera.Size localSize = paramCamera.getParameters().getPreviewSize();
    if ((localSize != null) && ((this.c.x != localSize.width) || (this.c.y != localSize.height)))
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append("Camera said it supported preview size ");
      localStringBuilder3.append(this.c.x);
      localStringBuilder3.append('x');
      localStringBuilder3.append(this.c.y);
      localStringBuilder3.append(", but after setting it, preview size is ");
      localStringBuilder3.append(localSize.width);
      localStringBuilder3.append('x');
      localStringBuilder3.append(localSize.height);
      Log.w("CameraConfiguration", localStringBuilder3.toString());
      this.c.x = localSize.width;
      this.c.y = localSize.height;
    }
  }

  Point b()
  {
    return this.b;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.a.b
 * JD-Core Version:    0.6.1
 */