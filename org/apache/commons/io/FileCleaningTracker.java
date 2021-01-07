package org.apache.commons.io;

import java.io.File;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collection;
import java.util.Vector;

public class FileCleaningTracker
{
  volatile boolean exitWhenFinished = false;
  ReferenceQueue q = new ReferenceQueue();
  Thread reaper;
  final Collection trackers = new Vector();

  private void addTracker(String paramString, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    try
    {
      if (this.exitWhenFinished)
        throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
      if (this.reaper == null)
      {
        this.reaper = new Reaper();
        this.reaper.start();
      }
      this.trackers.add(new Tracker(paramString, paramFileDeleteStrategy, paramObject, this.q));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void exitWhenFinished()
  {
    try
    {
      this.exitWhenFinished = true;
      if (this.reaper != null)
        synchronized (this.reaper)
        {
          this.reaper.interrupt();
        }
      return;
    }
    finally
    {
    }
  }

  public int getTrackCount()
  {
    return this.trackers.size();
  }

  public void track(File paramFile, Object paramObject)
  {
    track(paramFile, paramObject, (FileDeleteStrategy)null);
  }

  public void track(File paramFile, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    if (paramFile == null)
      throw new NullPointerException("The file must not be null");
    addTracker(paramFile.getPath(), paramObject, paramFileDeleteStrategy);
  }

  public void track(String paramString, Object paramObject)
  {
    track(paramString, paramObject, (FileDeleteStrategy)null);
  }

  public void track(String paramString, Object paramObject, FileDeleteStrategy paramFileDeleteStrategy)
  {
    if (paramString == null)
      throw new NullPointerException("The path must not be null");
    addTracker(paramString, paramObject, paramFileDeleteStrategy);
  }

  private final class Reaper extends Thread
  {
    Reaper()
    {
      super();
      setPriority(10);
      setDaemon(true);
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: goto +4 -> 4
      //   3: pop
      //   4: aload_0
      //   5: getfield 15	org/apache/commons/io/FileCleaningTracker$Reaper:this$0	Lorg/apache/commons/io/FileCleaningTracker;
      //   8: getfield 33	org/apache/commons/io/FileCleaningTracker:exitWhenFinished	Z
      //   11: ifeq +22 -> 33
      //   14: aload_0
      //   15: getfield 15	org/apache/commons/io/FileCleaningTracker$Reaper:this$0	Lorg/apache/commons/io/FileCleaningTracker;
      //   18: getfield 37	org/apache/commons/io/FileCleaningTracker:trackers	Ljava/util/Collection;
      //   21: invokeinterface 43 1 0
      //   26: ifle +6 -> 32
      //   29: goto +4 -> 33
      //   32: return
      //   33: aload_0
      //   34: getfield 15	org/apache/commons/io/FileCleaningTracker$Reaper:this$0	Lorg/apache/commons/io/FileCleaningTracker;
      //   37: getfield 47	org/apache/commons/io/FileCleaningTracker:q	Ljava/lang/ref/ReferenceQueue;
      //   40: invokevirtual 53	java/lang/ref/ReferenceQueue:remove	()Ljava/lang/ref/Reference;
      //   43: checkcast 55	org/apache/commons/io/FileCleaningTracker$Tracker
      //   46: astore_1
      //   47: aload_1
      //   48: ifnull -45 -> 3
      //   51: aload_1
      //   52: invokevirtual 59	org/apache/commons/io/FileCleaningTracker$Tracker:delete	()Z
      //   55: pop
      //   56: aload_1
      //   57: invokevirtual 62	org/apache/commons/io/FileCleaningTracker$Tracker:clear	()V
      //   60: aload_0
      //   61: getfield 15	org/apache/commons/io/FileCleaningTracker$Reaper:this$0	Lorg/apache/commons/io/FileCleaningTracker;
      //   64: getfield 37	org/apache/commons/io/FileCleaningTracker:trackers	Ljava/util/Collection;
      //   67: aload_1
      //   68: invokeinterface 65 2 0
      //   73: pop
      //   74: goto -71 -> 3
      //
      // Exception table:
      //   from	to	target	type
      //   33	47	3	java/lang/Exception
    }
  }

  private static final class Tracker extends PhantomReference
  {
    private final FileDeleteStrategy deleteStrategy;
    private final String path;

    Tracker(String paramString, FileDeleteStrategy paramFileDeleteStrategy, Object paramObject, ReferenceQueue paramReferenceQueue)
    {
      super(paramReferenceQueue);
      this.path = paramString;
      if (paramFileDeleteStrategy == null)
        paramFileDeleteStrategy = FileDeleteStrategy.NORMAL;
      this.deleteStrategy = paramFileDeleteStrategy;
    }

    public boolean delete()
    {
      return this.deleteStrategy.deleteQuietly(new File(this.path));
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.FileCleaningTracker
 * JD-Core Version:    0.6.1
 */