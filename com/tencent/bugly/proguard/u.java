package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.tencent.bugly.b;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public final class u
{
  private static u b;
  public boolean a;
  private final p c;
  private final Context d;
  private Map<Integer, Long> e;
  private long f;
  private long g;
  private LinkedBlockingQueue<Runnable> h;
  private LinkedBlockingQueue<Runnable> i;
  private final Object j;
  private String k;
  private byte[] l;
  private long m;
  private byte[] n;
  private long o;
  private String p;
  private long q;
  private final Object r;
  private boolean s;
  private final Object t;
  private int u;

  // ERROR //
  private u(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 46	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: new 48	java/util/HashMap
    //   8: dup
    //   9: invokespecial 49	java/util/HashMap:<init>	()V
    //   12: putfield 51	com/tencent/bugly/proguard/u:e	Ljava/util/Map;
    //   15: aload_0
    //   16: new 53	java/util/concurrent/LinkedBlockingQueue
    //   19: dup
    //   20: invokespecial 54	java/util/concurrent/LinkedBlockingQueue:<init>	()V
    //   23: putfield 56	com/tencent/bugly/proguard/u:h	Ljava/util/concurrent/LinkedBlockingQueue;
    //   26: aload_0
    //   27: new 53	java/util/concurrent/LinkedBlockingQueue
    //   30: dup
    //   31: invokespecial 54	java/util/concurrent/LinkedBlockingQueue:<init>	()V
    //   34: putfield 58	com/tencent/bugly/proguard/u:i	Ljava/util/concurrent/LinkedBlockingQueue;
    //   37: aload_0
    //   38: new 4	java/lang/Object
    //   41: dup
    //   42: invokespecial 46	java/lang/Object:<init>	()V
    //   45: putfield 60	com/tencent/bugly/proguard/u:j	Ljava/lang/Object;
    //   48: aload_0
    //   49: aconst_null
    //   50: putfield 62	com/tencent/bugly/proguard/u:k	Ljava/lang/String;
    //   53: aload_0
    //   54: aconst_null
    //   55: putfield 64	com/tencent/bugly/proguard/u:l	[B
    //   58: aload_0
    //   59: lconst_0
    //   60: putfield 66	com/tencent/bugly/proguard/u:m	J
    //   63: aload_0
    //   64: aconst_null
    //   65: putfield 68	com/tencent/bugly/proguard/u:n	[B
    //   68: aload_0
    //   69: lconst_0
    //   70: putfield 70	com/tencent/bugly/proguard/u:o	J
    //   73: aload_0
    //   74: aconst_null
    //   75: putfield 72	com/tencent/bugly/proguard/u:p	Ljava/lang/String;
    //   78: aload_0
    //   79: lconst_0
    //   80: putfield 74	com/tencent/bugly/proguard/u:q	J
    //   83: aload_0
    //   84: new 4	java/lang/Object
    //   87: dup
    //   88: invokespecial 46	java/lang/Object:<init>	()V
    //   91: putfield 76	com/tencent/bugly/proguard/u:r	Ljava/lang/Object;
    //   94: aload_0
    //   95: iconst_0
    //   96: putfield 78	com/tencent/bugly/proguard/u:s	Z
    //   99: aload_0
    //   100: new 4	java/lang/Object
    //   103: dup
    //   104: invokespecial 46	java/lang/Object:<init>	()V
    //   107: putfield 80	com/tencent/bugly/proguard/u:t	Ljava/lang/Object;
    //   110: aload_0
    //   111: iconst_1
    //   112: putfield 82	com/tencent/bugly/proguard/u:a	Z
    //   115: aload_0
    //   116: iconst_0
    //   117: putfield 84	com/tencent/bugly/proguard/u:u	I
    //   120: aload_0
    //   121: aload_1
    //   122: putfield 86	com/tencent/bugly/proguard/u:d	Landroid/content/Context;
    //   125: aload_0
    //   126: invokestatic 91	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   129: putfield 93	com/tencent/bugly/proguard/u:c	Lcom/tencent/bugly/proguard/p;
    //   132: ldc 95
    //   134: invokestatic 101	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   137: pop
    //   138: goto +18 -> 156
    //   141: ldc 103
    //   143: iconst_0
    //   144: anewarray 4	java/lang/Object
    //   147: invokestatic 108	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   150: pop
    //   151: aload_0
    //   152: iconst_0
    //   153: putfield 82	com/tencent/bugly/proguard/u:a	Z
    //   156: aload_0
    //   157: getfield 82	com/tencent/bugly/proguard/u:a	Z
    //   160: ifeq +33 -> 193
    //   163: new 110	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 111	java/lang/StringBuilder:<init>	()V
    //   170: astore_3
    //   171: aload_3
    //   172: ldc 113
    //   174: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload_3
    //   179: ldc 119
    //   181: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: pop
    //   185: aload_0
    //   186: aload_3
    //   187: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: putfield 62	com/tencent/bugly/proguard/u:k	Ljava/lang/String;
    //   193: return
    //
    // Exception table:
    //   from	to	target	type
    //   132	138	141	java/lang/ClassNotFoundException
  }

  public static u a()
  {
    try
    {
      u localu = b;
      return localu;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static u a(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new u(paramContext);
      u localu = b;
      return localu;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void a(Runnable paramRunnable, long paramLong)
  {
    if (paramRunnable == null)
    {
      x.d("[UploadManager] Upload task should not be null", new Object[0]);
      return;
    }
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(Process.myPid());
    arrayOfObject1[1] = Integer.valueOf(Process.myTid());
    x.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", arrayOfObject1);
    Thread localThread = z.a(paramRunnable, "BUGLY_SYNC_UPLOAD");
    if (localThread == null)
    {
      x.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
      a(paramRunnable, true);
      return;
    }
    try
    {
      localThread.join(paramLong);
      return;
    }
    catch (Throwable localThrowable)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localThrowable.getMessage();
      x.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", arrayOfObject2);
      a(paramRunnable, true);
      c(0);
    }
  }

  private void a(Runnable paramRunnable, boolean paramBoolean1, boolean paramBoolean2, long paramLong)
  {
    if (paramRunnable == null)
      x.d("[UploadManager] Upload task should not be null", new Object[0]);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(Process.myPid());
    arrayOfObject1[1] = Integer.valueOf(Process.myTid());
    x.c("[UploadManager] Add upload task (pid=%d | tid=%d)", arrayOfObject1);
    long l1;
    if (this.p != null)
    {
      if (b())
      {
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = Integer.valueOf(Process.myPid());
        arrayOfObject4[1] = Integer.valueOf(Process.myTid());
        x.c("[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", arrayOfObject4);
        if (paramBoolean2)
        {
          a(paramRunnable, paramLong);
          return;
        }
        a(paramRunnable, paramBoolean1);
        c(0);
        return;
      }
      l1 = paramLong;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = Integer.valueOf(Process.myPid());
      arrayOfObject3[1] = Integer.valueOf(Process.myTid());
      x.a("[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)", arrayOfObject3);
      b(false);
    }
    else
    {
      l1 = paramLong;
    }
    synchronized (this.t)
    {
      if (this.s)
      {
        a(paramRunnable, paramBoolean1);
        return;
      }
      this.s = true;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(Process.myPid());
      arrayOfObject2[1] = Integer.valueOf(Process.myTid());
      x.c("[UploadManager] Initialize security context now (pid=%d | tid=%d)", arrayOfObject2);
      if (paramBoolean2)
      {
        a locala1 = new a(this.d, paramRunnable, l1);
        a(locala1, 0L);
        return;
      }
      a(paramRunnable, paramBoolean1);
      a locala2 = new a(this.d);
      x.a("[UploadManager] Create and start a new thread to execute a task of initializing security context: %s", new Object[] { "BUGLY_ASYNC_UPLOAD" });
      if (z.a(locala2, "BUGLY_ASYNC_UPLOAD") == null)
      {
        x.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
        w localw = w.a();
        if (localw != null)
        {
          localw.a(locala2);
          return;
        }
        x.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
        synchronized (this.t)
        {
          this.s = false;
          return;
        }
      }
      return;
    }
  }

  // ERROR //
  private boolean a(Runnable paramRunnable, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +15 -> 16
    //   4: ldc 142
    //   6: iconst_0
    //   7: anewarray 4	java/lang/Object
    //   10: invokestatic 108	com/tencent/bugly/proguard/x:a	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   13: pop
    //   14: iconst_0
    //   15: ireturn
    //   16: iconst_2
    //   17: anewarray 4	java/lang/Object
    //   20: astore 6
    //   22: aload 6
    //   24: iconst_0
    //   25: invokestatic 150	android/os/Process:myPid	()I
    //   28: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   31: aastore
    //   32: aload 6
    //   34: iconst_1
    //   35: invokestatic 159	android/os/Process:myTid	()I
    //   38: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   41: aastore
    //   42: ldc 230
    //   44: aload 6
    //   46: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   49: pop
    //   50: aload_0
    //   51: getfield 60	com/tencent/bugly/proguard/u:j	Ljava/lang/Object;
    //   54: astore 8
    //   56: aload 8
    //   58: monitorenter
    //   59: iload_2
    //   60: ifeq +14 -> 74
    //   63: aload_0
    //   64: getfield 56	com/tencent/bugly/proguard/u:h	Ljava/util/concurrent/LinkedBlockingQueue;
    //   67: aload_1
    //   68: invokevirtual 234	java/util/concurrent/LinkedBlockingQueue:put	(Ljava/lang/Object;)V
    //   71: goto +11 -> 82
    //   74: aload_0
    //   75: getfield 58	com/tencent/bugly/proguard/u:i	Ljava/util/concurrent/LinkedBlockingQueue;
    //   78: aload_1
    //   79: invokevirtual 234	java/util/concurrent/LinkedBlockingQueue:put	(Ljava/lang/Object;)V
    //   82: aload 8
    //   84: monitorexit
    //   85: iconst_1
    //   86: ireturn
    //   87: aload 8
    //   89: monitorexit
    //   90: aload 9
    //   92: athrow
    //   93: astore_3
    //   94: iconst_1
    //   95: anewarray 4	java/lang/Object
    //   98: astore 4
    //   100: aload 4
    //   102: iconst_0
    //   103: aload_3
    //   104: invokevirtual 186	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   107: aastore
    //   108: ldc 236
    //   110: aload 4
    //   112: invokestatic 174	com/tencent/bugly/proguard/x:e	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   115: pop
    //   116: iconst_0
    //   117: ireturn
    //   118: astore 9
    //   120: goto -33 -> 87
    //
    // Exception table:
    //   from	to	target	type
    //   16	59	93	java/lang/Throwable
    //   87	93	93	java/lang/Throwable
    //   63	85	118	finally
  }

  private void c(int paramInt)
  {
    if (paramInt < 0)
    {
      x.a("[UploadManager] Number of task to execute should >= 0", new Object[0]);
      return;
    }
    w localw = w.a();
    LinkedBlockingQueue localLinkedBlockingQueue1 = new LinkedBlockingQueue();
    final LinkedBlockingQueue localLinkedBlockingQueue2 = new LinkedBlockingQueue();
    while (true)
    {
      int i1;
      int i2;
      int i4;
      int i5;
      final int i3;
      synchronized (this.j)
      {
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = Integer.valueOf(Process.myPid());
        arrayOfObject1[1] = Integer.valueOf(Process.myTid());
        x.c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", arrayOfObject1);
        i1 = this.h.size();
        i2 = this.i.size();
        if ((i1 == 0) && (i2 == 0))
        {
          x.c("[UploadManager] There is no upload task in queue.", new Object[0]);
          return;
          if (localw == null)
            break label612;
          if (localw.c())
            break label615;
          break label612;
          if (i4 >= i1)
            break label627;
          Runnable localRunnable3 = (Runnable)this.h.peek();
          if (localRunnable3 == null)
            break label627;
          try
          {
            localLinkedBlockingQueue1.put(localRunnable3);
            this.h.poll();
          }
          catch (Throwable localThrowable2)
          {
            Object[] arrayOfObject5 = new Object[1];
            arrayOfObject5[0] = localThrowable2.getMessage();
            x.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", arrayOfObject5);
          }
          if (i5 < i3)
          {
            Runnable localRunnable2 = (Runnable)this.i.peek();
            if (localRunnable2 != null)
              try
              {
                localLinkedBlockingQueue2.put(localRunnable2);
                this.i.poll();
              }
              catch (Throwable localThrowable1)
              {
                Object[] arrayOfObject4 = new Object[1];
                arrayOfObject4[0] = localThrowable1.getMessage();
                x.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", arrayOfObject4);
              }
          }
          if (i1 > 0)
          {
            Object[] arrayOfObject3 = new Object[3];
            arrayOfObject3[0] = Integer.valueOf(i1);
            arrayOfObject3[1] = Integer.valueOf(Process.myPid());
            arrayOfObject3[2] = Integer.valueOf(Process.myTid());
            x.c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", arrayOfObject3);
          }
          int i6 = 0;
          final Runnable localRunnable1;
          if (i6 < i1)
          {
            localRunnable1 = (Runnable)localLinkedBlockingQueue1.poll();
            if (localRunnable1 == null);
          }
          synchronized (this.j)
          {
            if ((this.u >= 2) && (localw != null))
            {
              localw.a(localRunnable1);
            }
            else
            {
              x.a("[UploadManager] Create and start a new thread to execute a upload task: %s", new Object[] { "BUGLY_ASYNC_UPLOAD" });
              if (z.a(new Runnable()
              {
                public final void run()
                {
                  localRunnable1.run();
                  synchronized (u.a(u.this))
                  {
                    u.b(u.this);
                    return;
                  }
                }
              }
              , "BUGLY_ASYNC_UPLOAD") != null)
                synchronized (this.j)
                {
                  this.u = (1 + this.u);
                }
              x.d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new Object[0]);
              a(localRunnable1, true);
            }
            i6++;
          }
          Object[] arrayOfObject2 = new Object[3];
          arrayOfObject2[0] = Integer.valueOf(i3);
          arrayOfObject2[1] = Integer.valueOf(Process.myPid());
          arrayOfObject2[2] = Integer.valueOf(Process.myTid());
          x.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", arrayOfObject2);
          if (localw != null)
            localw.a(new Runnable()
            {
              public final void run()
              {
                for (int i = 0; i < i3; i++)
                {
                  Runnable localRunnable = (Runnable)localLinkedBlockingQueue2.poll();
                  if (localRunnable == null)
                    break;
                  localRunnable.run();
                }
              }
            });
          return;
        }
      }
      if (paramInt != 0)
      {
        if (paramInt < i1)
        {
          i1 = paramInt;
          i3 = 0;
        }
        else if (paramInt < i1 + i2)
        {
          i3 = paramInt - i1;
        }
      }
      else
      {
        i3 = i2;
        continue;
        label612: i3 = 0;
        label615: i4 = 0;
        continue;
        i4++;
        continue;
        label627: i5 = 0;
        continue;
        i5++;
      }
    }
  }

  private static boolean c()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(Process.myPid());
    arrayOfObject[1] = Integer.valueOf(Process.myTid());
    x.c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", arrayOfObject);
    try
    {
      p localp = p.a();
      if (localp == null)
      {
        x.d("[UploadManager] Failed to get Database", new Object[0]);
        return false;
      }
      boolean bool = localp.a(555, "security_info", null, true);
      return bool;
    }
    catch (Throwable localThrowable)
    {
      x.a(localThrowable);
    }
    return false;
  }

  private boolean d()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(Process.myPid());
    arrayOfObject[1] = Integer.valueOf(Process.myTid());
    x.c("[UploadManager] Record security info to database (pid=%d | tid=%d)", arrayOfObject);
    try
    {
      p localp = p.a();
      if (localp == null)
      {
        x.d("[UploadManager] Failed to get database", new Object[0]);
        return false;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.n != null)
      {
        localStringBuilder.append(Base64.encodeToString(this.n, 0));
        localStringBuilder.append("#");
        if (this.o != 0L)
          localStringBuilder.append(Long.toString(this.o));
        else
          localStringBuilder.append("null");
        localStringBuilder.append("#");
        if (this.p != null)
          localStringBuilder.append(this.p);
        else
          localStringBuilder.append("null");
        localStringBuilder.append("#");
        if (this.q != 0L)
          localStringBuilder.append(Long.toString(this.q));
        else
          localStringBuilder.append("null");
        localp.a(555, "security_info", localStringBuilder.toString().getBytes(), null, true);
        return true;
      }
      x.c("[UploadManager] AES key is null, will not record", new Object[0]);
      return false;
    }
    catch (Throwable localThrowable)
    {
      x.a(localThrowable);
      c();
    }
    return false;
  }

  private boolean e()
  {
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(Process.myPid());
    arrayOfObject1[1] = Integer.valueOf(Process.myTid());
    x.c("[UploadManager] Load security info from database (pid=%d | tid=%d)", arrayOfObject1);
    while (true)
    {
      try
      {
        p localp = p.a();
        if (localp == null)
        {
          x.d("[UploadManager] Failed to get database", new Object[0]);
          return false;
        }
        Map localMap = localp.a(555, null, true);
        if ((localMap != null) && (localMap.containsKey("security_info")))
        {
          String str = new String((byte[])localMap.get("security_info"));
          String[] arrayOfString = str.split("#");
          if (arrayOfString.length == 4)
          {
            if (arrayOfString[0].isEmpty())
              break label382;
            boolean bool3 = arrayOfString[0].equals("null");
            if (bool3)
              break label382;
            try
            {
              this.n = Base64.decode(arrayOfString[0], 0);
            }
            catch (Throwable localThrowable4)
            {
              x.a(localThrowable4);
              i1 = 1;
            }
            if ((i1 == 0) && (!arrayOfString[1].isEmpty()))
            {
              boolean bool2 = arrayOfString[1].equals("null");
              if (!bool2)
                try
                {
                  this.o = Long.parseLong(arrayOfString[1]);
                }
                catch (Throwable localThrowable3)
                {
                  x.a(localThrowable3);
                  i1 = 1;
                }
            }
            if ((i1 == 0) && (!arrayOfString[2].isEmpty()) && (!arrayOfString[2].equals("null")))
              this.p = arrayOfString[2];
            if ((i1 == 0) && (!arrayOfString[3].isEmpty()))
            {
              boolean bool1 = arrayOfString[3].equals("null");
              if (!bool1)
                try
                {
                  this.q = Long.parseLong(arrayOfString[3]);
                }
                catch (Throwable localThrowable2)
                {
                  x.a(localThrowable2);
                  break label388;
                }
            }
          }
          else
          {
            Object[] arrayOfObject2 = new Object[2];
            arrayOfObject2[0] = str;
            arrayOfObject2[1] = Integer.valueOf(arrayOfString.length);
            x.a("SecurityInfo = %s, Strings.length = %d", arrayOfObject2);
            break label388;
          }
          if (i1 != 0)
            c();
        }
        return true;
      }
      catch (Throwable localThrowable1)
      {
        x.a(localThrowable1);
        return false;
      }
      label382: int i1 = 0;
      continue;
      label388: i1 = 1;
    }
  }

  public final long a(int paramInt)
  {
    long l1 = 0L;
    if (paramInt >= 0);
    try
    {
      Long localLong = (Long)this.e.get(Integer.valueOf(paramInt));
      if (localLong != null)
      {
        long l3 = localLong.longValue();
        return l3;
      }
      List localList = this.c.a(paramInt);
      if ((localList != null) && (localList.size() > 0))
        if (localList.size() > 1)
        {
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            r localr = (r)localIterator.next();
            if (localr.e > l1)
              l1 = localr.e;
          }
          this.c.b(paramInt);
        }
        else
        {
          try
          {
            long l2 = ((r)localList.get(0)).e;
            l1 = l2;
          }
          catch (Throwable localThrowable)
          {
            x.a(localThrowable);
          }
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramInt);
          x.e("[UploadManager] Unknown upload ID: %d", arrayOfObject);
        }
      return l1;
      label200: Object localObject1;
      throw localObject1;
    }
    finally
    {
      break label200;
    }
  }

  // ERROR //
  public final long a(boolean paramBoolean)
  {
    // Byte code:
    //   0: invokestatic 401	com/tencent/bugly/proguard/z:b	()J
    //   3: lstore_2
    //   4: iload_1
    //   5: ifeq +9 -> 14
    //   8: iconst_5
    //   9: istore 4
    //   11: goto +6 -> 17
    //   14: iconst_3
    //   15: istore 4
    //   17: aload_0
    //   18: getfield 93	com/tencent/bugly/proguard/u:c	Lcom/tencent/bugly/proguard/p;
    //   21: iload 4
    //   23: invokevirtual 372	com/tencent/bugly/proguard/p:a	(I)Ljava/util/List;
    //   26: astore 5
    //   28: aload 5
    //   30: ifnull +119 -> 149
    //   33: aload 5
    //   35: invokeinterface 375 1 0
    //   40: ifle +109 -> 149
    //   43: aload 5
    //   45: iconst_0
    //   46: invokeinterface 396 2 0
    //   51: checkcast 389	com/tencent/bugly/proguard/r
    //   54: astore 12
    //   56: aload 12
    //   58: getfield 391	com/tencent/bugly/proguard/r:e	J
    //   61: lload_2
    //   62: lcmp
    //   63: iflt +47 -> 110
    //   66: aload 12
    //   68: getfield 403	com/tencent/bugly/proguard/r:g	[B
    //   71: invokestatic 406	com/tencent/bugly/proguard/z:c	([B)J
    //   74: lstore 6
    //   76: iload 4
    //   78: iconst_3
    //   79: if_icmpne +12 -> 91
    //   82: aload_0
    //   83: lload 6
    //   85: putfield 408	com/tencent/bugly/proguard/u:f	J
    //   88: goto +9 -> 97
    //   91: aload_0
    //   92: lload 6
    //   94: putfield 410	com/tencent/bugly/proguard/u:g	J
    //   97: aload 5
    //   99: aload 12
    //   101: invokeinterface 413 2 0
    //   106: pop
    //   107: goto +20 -> 127
    //   110: lconst_0
    //   111: lstore 6
    //   113: goto +14 -> 127
    //   116: astore 10
    //   118: lconst_0
    //   119: lstore 6
    //   121: aload 10
    //   123: invokestatic 290	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   126: pop
    //   127: aload 5
    //   129: invokeinterface 375 1 0
    //   134: ifle +34 -> 168
    //   137: aload_0
    //   138: getfield 93	com/tencent/bugly/proguard/u:c	Lcom/tencent/bugly/proguard/p;
    //   141: aload 5
    //   143: invokevirtual 416	com/tencent/bugly/proguard/p:a	(Ljava/util/List;)V
    //   146: goto +22 -> 168
    //   149: iload_1
    //   150: ifeq +12 -> 162
    //   153: aload_0
    //   154: getfield 410	com/tencent/bugly/proguard/u:g	J
    //   157: lstore 6
    //   159: goto +9 -> 168
    //   162: aload_0
    //   163: getfield 408	com/tencent/bugly/proguard/u:f	J
    //   166: lstore 6
    //   168: iconst_1
    //   169: anewarray 4	java/lang/Object
    //   172: astore 8
    //   174: aload 8
    //   176: iconst_0
    //   177: lload 6
    //   179: ldc2_w 417
    //   182: ldiv
    //   183: invokestatic 421	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   186: aastore
    //   187: ldc_w 423
    //   190: aload 8
    //   192: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   195: pop
    //   196: lload 6
    //   198: lreturn
    //   199: astore 10
    //   201: goto -80 -> 121
    //
    // Exception table:
    //   from	to	target	type
    //   43	76	116	java/lang/Throwable
    //   82	107	199	java/lang/Throwable
  }

  public final void a(int paramInt1, int paramInt2, byte[] paramArrayOfByte, String paramString1, String paramString2, t paramt, int paramInt3, int paramInt4, boolean paramBoolean, Map<String, String> paramMap)
  {
    try
    {
      v localv = new v(this.d, paramInt1, paramInt2, paramArrayOfByte, paramString1, paramString2, paramt, this.a, paramInt3, paramInt4, false, paramMap);
      a(localv, paramBoolean, false, 0L);
      return;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
  }

  public final void a(int paramInt, long paramLong)
  {
    if (paramInt >= 0)
      try
      {
        this.e.put(Integer.valueOf(paramInt), Long.valueOf(paramLong));
        r localr = new r();
        localr.b = paramInt;
        localr.e = paramLong;
        localr.c = "";
        localr.d = "";
        localr.g = new byte[0];
        this.c.b(paramInt);
        this.c.a(localr);
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        arrayOfObject2[1] = z.a(paramLong);
        x.c("[UploadManager] Uploading(ID:%d) time: %s", arrayOfObject2);
        return;
      }
      finally
      {
        break label152;
      }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramInt);
    x.e("[UploadManager] Unknown uploading ID: %d", arrayOfObject1);
    return;
    label152: throw localObject;
  }

  public final void a(int paramInt, am paramam, String paramString1, String paramString2, t paramt, long paramLong, boolean paramBoolean)
  {
    int i1 = paramam.g;
    byte[] arrayOfByte = a.a(paramam);
    try
    {
      v localv = new v(this.d, paramInt, i1, arrayOfByte, paramString1, paramString2, paramt, this.a, paramBoolean);
      a(localv, true, true, paramLong);
      return;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
  }

  public final void a(int paramInt, am paramam, String paramString1, String paramString2, t paramt, boolean paramBoolean)
  {
    a(paramInt, paramam.g, a.a(paramam), paramString1, paramString2, paramt, 0, 0, paramBoolean, null);
  }

  // ERROR //
  public final void a(int paramInt, an paraman)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 82	com/tencent/bugly/proguard/u:a	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: iconst_1
    //   9: istore_3
    //   10: iload_1
    //   11: iconst_2
    //   12: if_icmpne +46 -> 58
    //   15: iconst_2
    //   16: anewarray 4	java/lang/Object
    //   19: astore 27
    //   21: aload 27
    //   23: iconst_0
    //   24: invokestatic 150	android/os/Process:myPid	()I
    //   27: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   30: aastore
    //   31: aload 27
    //   33: iload_3
    //   34: invokestatic 159	android/os/Process:myTid	()I
    //   37: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   40: aastore
    //   41: ldc_w 477
    //   44: aload 27
    //   46: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   49: pop
    //   50: aload_0
    //   51: iload_3
    //   52: invokevirtual 201	com/tencent/bugly/proguard/u:b	(Z)V
    //   55: goto +413 -> 468
    //   58: aload_0
    //   59: getfield 80	com/tencent/bugly/proguard/u:t	Ljava/lang/Object;
    //   62: astore 4
    //   64: aload 4
    //   66: monitorenter
    //   67: aload_0
    //   68: getfield 78	com/tencent/bugly/proguard/u:s	Z
    //   71: ifne +7 -> 78
    //   74: aload 4
    //   76: monitorexit
    //   77: return
    //   78: aload 4
    //   80: monitorexit
    //   81: aload_2
    //   82: ifnull +346 -> 428
    //   85: iconst_2
    //   86: anewarray 4	java/lang/Object
    //   89: astore 11
    //   91: aload 11
    //   93: iconst_0
    //   94: invokestatic 150	android/os/Process:myPid	()I
    //   97: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   100: aastore
    //   101: aload 11
    //   103: iload_3
    //   104: invokestatic 159	android/os/Process:myTid	()I
    //   107: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   110: aastore
    //   111: ldc_w 479
    //   114: aload 11
    //   116: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   119: pop
    //   120: aload_2
    //   121: getfield 483	com/tencent/bugly/proguard/an:g	Ljava/util/Map;
    //   124: astore 15
    //   126: aload 15
    //   128: ifnull +288 -> 416
    //   131: aload 15
    //   133: ldc_w 485
    //   136: invokeinterface 336 2 0
    //   141: ifeq +275 -> 416
    //   144: aload 15
    //   146: ldc_w 487
    //   149: invokeinterface 336 2 0
    //   154: ifeq +262 -> 416
    //   157: aload_0
    //   158: aload_2
    //   159: getfield 488	com/tencent/bugly/proguard/an:e	J
    //   162: invokestatic 493	java/lang/System:currentTimeMillis	()J
    //   165: lsub
    //   166: putfield 66	com/tencent/bugly/proguard/u:m	J
    //   169: iload_3
    //   170: anewarray 4	java/lang/Object
    //   173: astore 16
    //   175: aload 16
    //   177: iconst_0
    //   178: aload_0
    //   179: getfield 66	com/tencent/bugly/proguard/u:m	J
    //   182: invokestatic 421	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   185: aastore
    //   186: ldc_w 495
    //   189: aload 16
    //   191: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   194: pop
    //   195: aload_0
    //   196: aload 15
    //   198: ldc_w 485
    //   201: invokeinterface 340 2 0
    //   206: checkcast 315	java/lang/String
    //   209: putfield 72	com/tencent/bugly/proguard/u:p	Ljava/lang/String;
    //   212: iload_3
    //   213: anewarray 4	java/lang/Object
    //   216: astore 18
    //   218: aload 18
    //   220: iconst_0
    //   221: aload_0
    //   222: getfield 72	com/tencent/bugly/proguard/u:p	Ljava/lang/String;
    //   225: aastore
    //   226: ldc_w 497
    //   229: aload 18
    //   231: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   234: pop
    //   235: aload_0
    //   236: getfield 72	com/tencent/bugly/proguard/u:p	Ljava/lang/String;
    //   239: invokevirtual 500	java/lang/String:length	()I
    //   242: istore 20
    //   244: iload 20
    //   246: ifle +148 -> 394
    //   249: aload_0
    //   250: aload 15
    //   252: ldc_w 487
    //   255: invokeinterface 340 2 0
    //   260: checkcast 315	java/lang/String
    //   263: invokestatic 362	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   266: putfield 74	com/tencent/bugly/proguard/u:q	J
    //   269: iconst_2
    //   270: anewarray 4	java/lang/Object
    //   273: astore 24
    //   275: aload 24
    //   277: iconst_0
    //   278: aload_0
    //   279: getfield 74	com/tencent/bugly/proguard/u:q	J
    //   282: invokestatic 421	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   285: aastore
    //   286: aload 24
    //   288: iload_3
    //   289: new 502	java/util/Date
    //   292: dup
    //   293: aload_0
    //   294: getfield 74	com/tencent/bugly/proguard/u:q	J
    //   297: invokespecial 504	java/util/Date:<init>	(J)V
    //   300: invokevirtual 505	java/util/Date:toString	()Ljava/lang/String;
    //   303: aastore
    //   304: ldc_w 507
    //   307: aload 24
    //   309: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   312: pop
    //   313: aload_0
    //   314: getfield 74	com/tencent/bugly/proguard/u:q	J
    //   317: ldc2_w 508
    //   320: lcmp
    //   321: ifge +42 -> 363
    //   324: ldc_w 511
    //   327: iconst_0
    //   328: anewarray 4	java/lang/Object
    //   331: invokestatic 144	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   334: pop
    //   335: aload_0
    //   336: ldc2_w 512
    //   339: putfield 74	com/tencent/bugly/proguard/u:q	J
    //   342: goto +21 -> 363
    //   345: ldc_w 515
    //   348: iconst_0
    //   349: anewarray 4	java/lang/Object
    //   352: invokestatic 144	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   355: pop
    //   356: aload_0
    //   357: ldc2_w 512
    //   360: putfield 74	com/tencent/bugly/proguard/u:q	J
    //   363: aload_0
    //   364: invokespecial 517	com/tencent/bugly/proguard/u:d	()Z
    //   367: ifeq +8 -> 375
    //   370: iconst_0
    //   371: istore_3
    //   372: goto +14 -> 386
    //   375: ldc_w 519
    //   378: iconst_0
    //   379: anewarray 4	java/lang/Object
    //   382: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   385: pop
    //   386: aload_0
    //   387: iconst_0
    //   388: invokespecial 134	com/tencent/bugly/proguard/u:c	(I)V
    //   391: goto +25 -> 416
    //   394: ldc_w 521
    //   397: iconst_0
    //   398: anewarray 4	java/lang/Object
    //   401: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   404: pop
    //   405: goto +11 -> 416
    //   408: astore 13
    //   410: aload 13
    //   412: invokestatic 290	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   415: pop
    //   416: iload_3
    //   417: ifeq +51 -> 468
    //   420: aload_0
    //   421: iconst_0
    //   422: invokevirtual 201	com/tencent/bugly/proguard/u:b	(Z)V
    //   425: goto +43 -> 468
    //   428: iconst_2
    //   429: anewarray 4	java/lang/Object
    //   432: astore 6
    //   434: aload 6
    //   436: iconst_0
    //   437: invokestatic 150	android/os/Process:myPid	()I
    //   440: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   443: aastore
    //   444: aload 6
    //   446: iload_3
    //   447: invokestatic 159	android/os/Process:myTid	()I
    //   450: invokestatic 156	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   453: aastore
    //   454: ldc_w 523
    //   457: aload 6
    //   459: invokestatic 163	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   462: pop
    //   463: aload_0
    //   464: iconst_0
    //   465: invokevirtual 201	com/tencent/bugly/proguard/u:b	(Z)V
    //   468: aload_0
    //   469: getfield 80	com/tencent/bugly/proguard/u:t	Ljava/lang/Object;
    //   472: astore 8
    //   474: aload 8
    //   476: monitorenter
    //   477: aload_0
    //   478: getfield 78	com/tencent/bugly/proguard/u:s	Z
    //   481: ifeq +19 -> 500
    //   484: aload_0
    //   485: iconst_0
    //   486: putfield 78	com/tencent/bugly/proguard/u:s	Z
    //   489: aload_0
    //   490: getfield 86	com/tencent/bugly/proguard/u:d	Landroid/content/Context;
    //   493: ldc_w 284
    //   496: invokestatic 526	com/tencent/bugly/proguard/z:b	(Landroid/content/Context;Ljava/lang/String;)Z
    //   499: pop
    //   500: aload 8
    //   502: monitorexit
    //   503: return
    //   504: astore 9
    //   506: aload 8
    //   508: monitorexit
    //   509: aload 9
    //   511: athrow
    //   512: astore 5
    //   514: aload 4
    //   516: monitorexit
    //   517: aload 5
    //   519: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   249	342	345	java/lang/NumberFormatException
    //   120	244	408	java/lang/Throwable
    //   249	342	408	java/lang/Throwable
    //   345	405	408	java/lang/Throwable
    //   477	503	504	finally
    //   67	77	512	finally
  }

  protected final void a(long paramLong, boolean paramBoolean)
  {
    int i1;
    if (paramBoolean)
      i1 = 5;
    else
      i1 = 3;
    try
    {
      r localr = new r();
      localr.b = i1;
      localr.e = z.b();
      localr.c = "";
      localr.d = "";
      localr.g = z.c(paramLong);
      this.c.b(i1);
      this.c.a(localr);
      if (paramBoolean)
        this.g = paramLong;
      else
        this.f = paramLong;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Long.valueOf(paramLong / 1024L);
      x.c("[UploadManager] Network total consume: %d KB", arrayOfObject);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final boolean a(Map<String, String> paramMap)
  {
    if (paramMap == null)
      return false;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(Process.myPid());
    arrayOfObject[1] = Integer.valueOf(Process.myTid());
    x.c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", arrayOfObject);
    if (this.p != null)
    {
      paramMap.put("secureSessionId", this.p);
      return true;
    }
    if ((this.n != null) && (this.n.length << 3 == 128))
    {
      if (this.l == null)
      {
        this.l = Base64.decode(this.k, 0);
        if (this.l == null)
        {
          x.d("[UploadManager] Failed to decode RSA public key", new Object[0]);
          return false;
        }
      }
      byte[] arrayOfByte = z.b(1, this.n, this.l);
      if (arrayOfByte == null)
      {
        x.d("[UploadManager] Failed to encrypt AES key", new Object[0]);
        return false;
      }
      String str = Base64.encodeToString(arrayOfByte, 0);
      if (str == null)
      {
        x.d("[UploadManager] Failed to encode AES key", new Object[0]);
        return false;
      }
      paramMap.put("raKey", str);
      return true;
    }
    x.d("[UploadManager] AES key is invalid", new Object[0]);
    return false;
  }

  public final byte[] a(byte[] paramArrayOfByte)
  {
    if ((this.n != null) && (this.n.length << 3 == 128))
      return z.a(1, paramArrayOfByte, this.n);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(Process.myPid());
    arrayOfObject[1] = Integer.valueOf(Process.myTid());
    x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", arrayOfObject);
    return null;
  }

  protected final void b(boolean paramBoolean)
  {
    synchronized (this.r)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(Process.myPid());
      arrayOfObject[1] = Integer.valueOf(Process.myTid());
      x.c("[UploadManager] Clear security context (pid=%d | tid=%d)", arrayOfObject);
      this.n = null;
      this.p = null;
      this.q = 0L;
      if (paramBoolean)
        c();
      return;
    }
  }

  protected final boolean b()
  {
    if ((this.p != null) && (this.q != 0L))
    {
      long l1 = System.currentTimeMillis() + this.m;
      if (this.q < l1)
      {
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = Long.valueOf(this.q);
        arrayOfObject[1] = new Date(this.q).toString();
        arrayOfObject[2] = Long.valueOf(l1);
        arrayOfObject[3] = new Date(l1).toString();
        x.c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", arrayOfObject);
        return false;
      }
      return true;
    }
    return false;
  }

  public final boolean b(int paramInt)
  {
    if (b.c)
    {
      x.c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
      return true;
    }
    long l1 = System.currentTimeMillis() - a(paramInt);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Long.valueOf(l1 / 1000L);
    arrayOfObject1[1] = Integer.valueOf(paramInt);
    x.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", arrayOfObject1);
    if (l1 < 30000L)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Long.valueOf(30L);
      x.a("[UploadManager] Data only be uploaded once in %d seconds.", arrayOfObject2);
      return false;
    }
    return true;
  }

  public final byte[] b(byte[] paramArrayOfByte)
  {
    if ((this.n != null) && (this.n.length << 3 == 128))
      return z.a(2, paramArrayOfByte, this.n);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(Process.myPid());
    arrayOfObject[1] = Integer.valueOf(Process.myTid());
    x.d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", arrayOfObject);
    return null;
  }

  final class a
    implements Runnable
  {
    private final Context a;
    private final Runnable b;
    private final long c;

    public a(Context arg2)
    {
      Object localObject;
      this.a = localObject;
      this.b = null;
      this.c = 0L;
    }

    public a(Context paramRunnable, Runnable paramLong, long arg4)
    {
      this.a = paramRunnable;
      this.b = paramLong;
      Object localObject;
      this.c = localObject;
    }

    public final void run()
    {
      if (!z.a(this.a, "security_info", 30000L))
      {
        Object[] arrayOfObject4 = new Object[3];
        arrayOfObject4[0] = Integer.valueOf(5000);
        arrayOfObject4[1] = Integer.valueOf(Process.myPid());
        arrayOfObject4[2] = Integer.valueOf(Process.myTid());
        x.c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", arrayOfObject4);
        z.b(5000L);
        if (z.a(this, "BUGLY_ASYNC_UPLOAD") == null)
        {
          x.d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
          w localw = w.a();
          if (localw != null)
          {
            localw.a(this);
            return;
          }
          x.e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
          return;
        }
        return;
      }
      if (!u.c(u.this))
      {
        x.d("[UploadManager] Failed to load security info from database", new Object[0]);
        u.this.b(false);
      }
      if (u.d(u.this) != null)
      {
        if (u.this.b())
        {
          Object[] arrayOfObject3 = new Object[2];
          arrayOfObject3[0] = Integer.valueOf(Process.myPid());
          arrayOfObject3[1] = Integer.valueOf(Process.myTid());
          x.c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", arrayOfObject3);
          if (this.b != null)
            u.a(u.this, this.b, this.c);
          u.a(u.this, 0);
          z.b(this.a, "security_info");
          synchronized (u.e(u.this))
          {
            u.a(u.this, false);
            return;
          }
        }
        x.a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
        u.this.b(true);
      }
      byte[] arrayOfByte = z.a(128);
      if ((arrayOfByte != null) && (arrayOfByte.length << 3 == 128))
      {
        u.a(u.this, arrayOfByte);
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(Process.myPid());
        arrayOfObject2[1] = Integer.valueOf(Process.myTid());
        x.c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", arrayOfObject2);
        if (this.b != null)
        {
          u.a(u.this, this.b, this.c);
          return;
        }
        u.a(u.this, 1);
        return;
      }
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(Process.myPid());
      arrayOfObject1[1] = Integer.valueOf(Process.myTid());
      x.d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", arrayOfObject1);
      u.this.b(false);
      z.b(this.a, "security_info");
      synchronized (u.e(u.this))
      {
        u.a(u.this, false);
        return;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.proguard.u
 * JD-Core Version:    0.6.1
 */