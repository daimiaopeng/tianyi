package com.tencent.bugly.crashreport.crash.jni;

import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.Map;

public abstract interface NativeExceptionHandler
{
  public abstract void handleNativeException(int paramInt1, int paramInt2, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt3, String paramString5, int paramInt4, int paramInt5, int paramInt6, String paramString6, String paramString7);

  public abstract void handleNativeException2(int paramInt1, int paramInt2, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt3, String paramString5, int paramInt4, int paramInt5, int paramInt6, String paramString6, String paramString7, String[] paramArrayOfString);

  public abstract CrashDetailBean packageCrashDatas(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
 * JD-Core Version:    0.6.1
 */