package org.apache.commons.logging;

import java.lang.reflect.Constructor;
import java.util.Hashtable;

@Deprecated
public class LogSource
{
  protected static boolean jdk14IsAvailable;
  protected static boolean log4jIsAvailable;
  protected static Constructor logImplctor;
  protected static Hashtable logs;

  LogSource()
  {
    throw new RuntimeException("Stub!");
  }

  public static Log getInstance(Class paramClass)
  {
    throw new RuntimeException("Stub!");
  }

  public static Log getInstance(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public static String[] getLogNames()
  {
    throw new RuntimeException("Stub!");
  }

  public static Log makeNewLogInstance(String paramString)
  {
    throw new RuntimeException("Stub!");
  }

  public static void setLogImplementation(Class paramClass)
  {
    throw new RuntimeException("Stub!");
  }

  public static void setLogImplementation(String paramString)
  {
    throw new RuntimeException("Stub!");
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.LogSource
 * JD-Core Version:    0.6.1
 */