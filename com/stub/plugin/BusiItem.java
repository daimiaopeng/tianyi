package com.stub.plugin;

public class BusiItem
{
  private Class<?> delegateClz = null;
  private Object delegateImpl = null;

  public Class<?> getDelegateClz()
  {
    return this.delegateClz;
  }

  public Object getDelegateImpl()
  {
    return this.delegateImpl;
  }

  public void setDelegateClz(Class<?> paramClass)
  {
    this.delegateClz = paramClass;
  }

  public void setDelegateImpl(Object paramObject)
  {
    this.delegateImpl = paramObject;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.plugin.BusiItem
 * JD-Core Version:    0.6.1
 */