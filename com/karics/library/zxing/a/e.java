package com.karics.library.zxing.a;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;

public final class e
{
  private static final String a = "com.karics.library.zxing.a.e";

  public static Camera a()
  {
    return a(-1);
  }

  @SuppressLint({"NewApi"})
  public static Camera a(int paramInt)
  {
    int i = Camera.getNumberOfCameras();
    if (i == 0)
    {
      Log.w(a, "No cameras!");
      return null;
    }
    int j;
    if (paramInt >= 0)
      j = 1;
    else
      j = 0;
    if (j == 0)
      for (paramInt = 0; paramInt < i; paramInt++)
      {
        Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(paramInt, localCameraInfo);
        if (localCameraInfo.facing == 0)
          break;
      }
    Camera localCamera;
    if (paramInt < i)
    {
      String str2 = a;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("Opening camera #");
      localStringBuilder2.append(paramInt);
      Log.i(str2, localStringBuilder2.toString());
      localCamera = Camera.open(paramInt);
    }
    else if (j != 0)
    {
      String str1 = a;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Requested camera does not exist: ");
      localStringBuilder1.append(paramInt);
      Log.w(str1, localStringBuilder1.toString());
      localCamera = null;
    }
    else
    {
      Log.i(a, "No camera facing back; returning camera #0");
      localCamera = Camera.open(0);
    }
    return localCamera;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.karics.library.zxing.a.e
 * JD-Core Version:    0.6.1
 */