package org.apache.commons.io.input;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class ClassLoaderObjectInputStream extends ObjectInputStream
{
  private ClassLoader classLoader;

  public ClassLoaderObjectInputStream(ClassLoader paramClassLoader, InputStream paramInputStream)
  {
    super(paramInputStream);
    this.classLoader = paramClassLoader;
  }

  protected Class resolveClass(ObjectStreamClass paramObjectStreamClass)
  {
    Class localClass = Class.forName(paramObjectStreamClass.getName(), false, this.classLoader);
    if (localClass != null)
      return localClass;
    return super.resolveClass(paramObjectStreamClass);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.input.ClassLoaderObjectInputStream
 * JD-Core Version:    0.6.1
 */