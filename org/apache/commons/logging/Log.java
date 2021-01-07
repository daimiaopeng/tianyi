package org.apache.commons.logging;

@Deprecated
public abstract interface Log
{
  public abstract void debug(Object paramObject);

  public abstract void debug(Object paramObject, Throwable paramThrowable);

  public abstract void error(Object paramObject);

  public abstract void error(Object paramObject, Throwable paramThrowable);

  public abstract void fatal(Object paramObject);

  public abstract void fatal(Object paramObject, Throwable paramThrowable);

  public abstract void info(Object paramObject);

  public abstract void info(Object paramObject, Throwable paramThrowable);

  public abstract boolean isDebugEnabled();

  public abstract boolean isErrorEnabled();

  public abstract boolean isFatalEnabled();

  public abstract boolean isInfoEnabled();

  public abstract boolean isTraceEnabled();

  public abstract boolean isWarnEnabled();

  public abstract void trace(Object paramObject);

  public abstract void trace(Object paramObject, Throwable paramThrowable);

  public abstract void warn(Object paramObject);

  public abstract void warn(Object paramObject, Throwable paramThrowable);
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.logging.Log
 * JD-Core Version:    0.6.1
 */