package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ah;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class b
{
  private static int a;
  private Context b;
  private u c;
  private p d;
  private com.tencent.bugly.crashreport.common.strategy.a e;
  private o f;
  private BuglyStrategy.a g;

  public b(int paramInt, Context paramContext, u paramu, p paramp, com.tencent.bugly.crashreport.common.strategy.a parama, BuglyStrategy.a parama1, o paramo)
  {
    a = paramInt;
    this.b = paramContext;
    this.c = paramu;
    this.d = paramp;
    this.e = parama;
    this.g = parama1;
    this.f = paramo;
  }

  private static CrashDetailBean a(Cursor paramCursor)
  {
    if (paramCursor == null)
      return null;
    try
    {
      byte[] arrayOfByte = paramCursor.getBlob(paramCursor.getColumnIndex("_dt"));
      if (arrayOfByte == null)
        return null;
      long l = paramCursor.getLong(paramCursor.getColumnIndex("_id"));
      CrashDetailBean localCrashDetailBean = (CrashDetailBean)z.a(arrayOfByte, CrashDetailBean.CREATOR);
      if (localCrashDetailBean != null)
        localCrashDetailBean.a = l;
      return localCrashDetailBean;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  private CrashDetailBean a(List<a> paramList, CrashDetailBean paramCrashDetailBean)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      ArrayList localArrayList = new ArrayList(10);
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        a locala2 = (a)localIterator1.next();
        if (locala2.e)
          localArrayList.add(locala2);
      }
      int i = localArrayList.size();
      Object localObject1 = null;
      if (i > 0)
      {
        List localList = b(localArrayList);
        localObject1 = null;
        if (localList != null)
        {
          int j = localList.size();
          localObject1 = null;
          if (j > 0)
          {
            Collections.sort(localList);
            Object localObject2 = null;
            for (int k = 0; k < localList.size(); k++)
            {
              CrashDetailBean localCrashDetailBean = (CrashDetailBean)localList.get(k);
              if (k == 0)
              {
                localObject2 = localCrashDetailBean;
              }
              else if (localCrashDetailBean.s != null)
              {
                String[] arrayOfString = localCrashDetailBean.s.split("\n");
                if (arrayOfString != null)
                {
                  int m = arrayOfString.length;
                  for (int n = 0; n < m; n++)
                  {
                    String str3 = arrayOfString[n];
                    String str4 = localObject2.s;
                    StringBuilder localStringBuilder5 = new StringBuilder();
                    localStringBuilder5.append(str3);
                    if (!str4.contains(localStringBuilder5.toString()))
                    {
                      localObject2.t = (1 + localObject2.t);
                      StringBuilder localStringBuilder6 = new StringBuilder();
                      localStringBuilder6.append(localObject2.s);
                      localStringBuilder6.append(str3);
                      localStringBuilder6.append("\n");
                      localObject2.s = localStringBuilder6.toString();
                    }
                  }
                }
              }
            }
            localObject1 = localObject2;
          }
        }
      }
      if (localObject1 == null)
      {
        paramCrashDetailBean.j = true;
        paramCrashDetailBean.t = 0;
        paramCrashDetailBean.s = "";
        localObject1 = paramCrashDetailBean;
      }
      Iterator localIterator2 = paramList.iterator();
      while (localIterator2.hasNext())
      {
        a locala1 = (a)localIterator2.next();
        if ((!locala1.e) && (!locala1.d))
        {
          String str2 = ((CrashDetailBean)localObject1).s;
          StringBuilder localStringBuilder3 = new StringBuilder();
          localStringBuilder3.append(locala1.b);
          if (!str2.contains(localStringBuilder3.toString()))
          {
            ((CrashDetailBean)localObject1).t = (1 + ((CrashDetailBean)localObject1).t);
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append(((CrashDetailBean)localObject1).s);
            localStringBuilder4.append(locala1.b);
            localStringBuilder4.append("\n");
            ((CrashDetailBean)localObject1).s = localStringBuilder4.toString();
          }
        }
      }
      if (((CrashDetailBean)localObject1).r != paramCrashDetailBean.r)
      {
        String str1 = ((CrashDetailBean)localObject1).s;
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append(paramCrashDetailBean.r);
        if (!str1.contains(localStringBuilder1.toString()))
        {
          ((CrashDetailBean)localObject1).t = (1 + ((CrashDetailBean)localObject1).t);
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append(((CrashDetailBean)localObject1).s);
          localStringBuilder2.append(paramCrashDetailBean.r);
          localStringBuilder2.append("\n");
          ((CrashDetailBean)localObject1).s = localStringBuilder2.toString();
        }
      }
      return localObject1;
    }
    return paramCrashDetailBean;
  }

  // ERROR //
  private static aj a(String paramString1, Context paramContext, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnull +372 -> 373
    //   4: aload_1
    //   5: ifnonnull +6 -> 11
    //   8: goto +365 -> 373
    //   11: ldc 179
    //   13: iconst_1
    //   14: anewarray 4	java/lang/Object
    //   17: dup
    //   18: iconst_0
    //   19: aload_2
    //   20: aastore
    //   21: invokestatic 182	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   24: pop
    //   25: new 184	java/io/File
    //   28: dup
    //   29: aload_2
    //   30: invokespecial 187	java/io/File:<init>	(Ljava/lang/String;)V
    //   33: astore 5
    //   35: new 184	java/io/File
    //   38: dup
    //   39: aload_1
    //   40: invokevirtual 193	android/content/Context:getCacheDir	()Ljava/io/File;
    //   43: aload_0
    //   44: invokespecial 196	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   47: astore 6
    //   49: aload 5
    //   51: aload 6
    //   53: sipush 5000
    //   56: invokestatic 199	com/tencent/bugly/proguard/z:a	(Ljava/io/File;Ljava/io/File;I)Z
    //   59: ifne +15 -> 74
    //   62: ldc 201
    //   64: iconst_0
    //   65: anewarray 4	java/lang/Object
    //   68: invokestatic 203	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   71: pop
    //   72: aconst_null
    //   73: areturn
    //   74: new 205	java/io/ByteArrayOutputStream
    //   77: dup
    //   78: invokespecial 206	java/io/ByteArrayOutputStream:<init>	()V
    //   81: astore 7
    //   83: new 208	java/io/FileInputStream
    //   86: dup
    //   87: aload 6
    //   89: invokespecial 211	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   92: astore 8
    //   94: sipush 4096
    //   97: newarray byte
    //   99: astore 17
    //   101: aload 8
    //   103: aload 17
    //   105: invokevirtual 215	java/io/FileInputStream:read	([B)I
    //   108: istore 18
    //   110: iload 18
    //   112: ifle +21 -> 133
    //   115: aload 7
    //   117: aload 17
    //   119: iconst_0
    //   120: iload 18
    //   122: invokevirtual 219	java/io/ByteArrayOutputStream:write	([BII)V
    //   125: aload 7
    //   127: invokevirtual 222	java/io/ByteArrayOutputStream:flush	()V
    //   130: goto -29 -> 101
    //   133: aload 7
    //   135: invokevirtual 226	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   138: astore 19
    //   140: iconst_1
    //   141: anewarray 4	java/lang/Object
    //   144: astore 20
    //   146: aload 20
    //   148: iconst_0
    //   149: aload 19
    //   151: arraylength
    //   152: invokestatic 232	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   155: aastore
    //   156: ldc 234
    //   158: aload 20
    //   160: invokestatic 182	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   163: pop
    //   164: new 236	com/tencent/bugly/proguard/aj
    //   167: dup
    //   168: iconst_2
    //   169: aload 6
    //   171: invokevirtual 239	java/io/File:getName	()Ljava/lang/String;
    //   174: aload 19
    //   176: invokespecial 242	com/tencent/bugly/proguard/aj:<init>	(BLjava/lang/String;[B)V
    //   179: astore 22
    //   181: aload 8
    //   183: invokevirtual 245	java/io/FileInputStream:close	()V
    //   186: goto +18 -> 204
    //   189: astore 23
    //   191: aload 23
    //   193: invokestatic 78	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   196: ifne +8 -> 204
    //   199: aload 23
    //   201: invokevirtual 246	java/io/IOException:printStackTrace	()V
    //   204: aload 6
    //   206: invokevirtual 249	java/io/File:exists	()Z
    //   209: ifeq +19 -> 228
    //   212: ldc 251
    //   214: iconst_0
    //   215: anewarray 4	java/lang/Object
    //   218: invokestatic 182	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   221: pop
    //   222: aload 6
    //   224: invokevirtual 254	java/io/File:delete	()Z
    //   227: pop
    //   228: aload 22
    //   230: areturn
    //   231: astore 13
    //   233: goto +16 -> 249
    //   236: astore 9
    //   238: aconst_null
    //   239: astore 8
    //   241: goto +77 -> 318
    //   244: astore 13
    //   246: aconst_null
    //   247: astore 8
    //   249: aload 13
    //   251: invokestatic 78	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   254: ifne +8 -> 262
    //   257: aload 13
    //   259: invokevirtual 81	java/lang/Throwable:printStackTrace	()V
    //   262: aload 8
    //   264: ifnull +26 -> 290
    //   267: aload 8
    //   269: invokevirtual 245	java/io/FileInputStream:close	()V
    //   272: goto +18 -> 290
    //   275: astore 16
    //   277: aload 16
    //   279: invokestatic 78	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   282: ifne +8 -> 290
    //   285: aload 16
    //   287: invokevirtual 246	java/io/IOException:printStackTrace	()V
    //   290: aload 6
    //   292: invokevirtual 249	java/io/File:exists	()Z
    //   295: ifeq +19 -> 314
    //   298: ldc 251
    //   300: iconst_0
    //   301: anewarray 4	java/lang/Object
    //   304: invokestatic 182	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   307: pop
    //   308: aload 6
    //   310: invokevirtual 254	java/io/File:delete	()Z
    //   313: pop
    //   314: aconst_null
    //   315: areturn
    //   316: astore 9
    //   318: aload 8
    //   320: ifnull +26 -> 346
    //   323: aload 8
    //   325: invokevirtual 245	java/io/FileInputStream:close	()V
    //   328: goto +18 -> 346
    //   331: astore 12
    //   333: aload 12
    //   335: invokestatic 78	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   338: ifne +8 -> 346
    //   341: aload 12
    //   343: invokevirtual 246	java/io/IOException:printStackTrace	()V
    //   346: aload 6
    //   348: invokevirtual 249	java/io/File:exists	()Z
    //   351: ifeq +19 -> 370
    //   354: ldc 251
    //   356: iconst_0
    //   357: anewarray 4	java/lang/Object
    //   360: invokestatic 182	com/tencent/bugly/proguard/x:c	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   363: pop
    //   364: aload 6
    //   366: invokevirtual 254	java/io/File:delete	()Z
    //   369: pop
    //   370: aload 9
    //   372: athrow
    //   373: ldc_w 256
    //   376: iconst_0
    //   377: anewarray 4	java/lang/Object
    //   380: invokestatic 203	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   383: pop
    //   384: aconst_null
    //   385: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   181	186	189	java/io/IOException
    //   94	181	231	java/lang/Throwable
    //   83	94	236	finally
    //   83	94	244	java/lang/Throwable
    //   267	272	275	java/io/IOException
    //   94	181	316	finally
    //   249	262	316	finally
    //   323	328	331	java/io/IOException
  }

  private static ak a(Context paramContext, CrashDetailBean paramCrashDetailBean, com.tencent.bugly.crashreport.common.info.a parama)
  {
    if ((paramContext != null) && (paramCrashDetailBean != null) && (parama != null))
    {
      ak localak = new ak();
      switch (paramCrashDetailBean.b)
      {
      default:
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = Integer.valueOf(paramCrashDetailBean.b);
        x.e("crash type error! %d", arrayOfObject4);
        break;
      case 7:
        String str8;
        if (paramCrashDetailBean.j)
          str8 = "208";
        else
          str8 = "108";
        localak.a = str8;
        break;
      case 6:
        String str7;
        if (paramCrashDetailBean.j)
          str7 = "206";
        else
          str7 = "106";
        localak.a = str7;
        break;
      case 5:
        String str6;
        if (paramCrashDetailBean.j)
          str6 = "207";
        else
          str6 = "107";
        localak.a = str6;
        break;
      case 4:
        String str5;
        if (paramCrashDetailBean.j)
          str5 = "204";
        else
          str5 = "104";
        localak.a = str5;
        break;
      case 3:
        String str4;
        if (paramCrashDetailBean.j)
          str4 = "203";
        else
          str4 = "103";
        localak.a = str4;
        break;
      case 2:
        String str3;
        if (paramCrashDetailBean.j)
          str3 = "202";
        else
          str3 = "102";
        localak.a = str3;
        break;
      case 1:
        String str2;
        if (paramCrashDetailBean.j)
          str2 = "201";
        else
          str2 = "101";
        localak.a = str2;
        break;
      case 0:
        String str1;
        if (paramCrashDetailBean.j)
          str1 = "200";
        else
          str1 = "100";
        localak.a = str1;
      }
      localak.b = paramCrashDetailBean.r;
      localak.c = paramCrashDetailBean.n;
      localak.d = paramCrashDetailBean.o;
      localak.e = paramCrashDetailBean.p;
      localak.g = paramCrashDetailBean.q;
      localak.h = paramCrashDetailBean.y;
      localak.i = paramCrashDetailBean.c;
      localak.j = null;
      localak.l = paramCrashDetailBean.m;
      localak.m = paramCrashDetailBean.e;
      localak.f = paramCrashDetailBean.A;
      localak.t = com.tencent.bugly.crashreport.common.info.a.b().i();
      localak.n = null;
      if ((paramCrashDetailBean.i != null) && (paramCrashDetailBean.i.size() > 0))
      {
        localak.o = new ArrayList();
        Iterator localIterator5 = paramCrashDetailBean.i.entrySet().iterator();
        while (localIterator5.hasNext())
        {
          Map.Entry localEntry4 = (Map.Entry)localIterator5.next();
          ah localah2 = new ah();
          localah2.a = ((PlugInBean)localEntry4.getValue()).a;
          localah2.c = ((PlugInBean)localEntry4.getValue()).c;
          localah2.d = ((PlugInBean)localEntry4.getValue()).b;
          localah2.b = parama.r();
          localak.o.add(localah2);
        }
      }
      if ((paramCrashDetailBean.h != null) && (paramCrashDetailBean.h.size() > 0))
      {
        localak.p = new ArrayList();
        Iterator localIterator4 = paramCrashDetailBean.h.entrySet().iterator();
        while (localIterator4.hasNext())
        {
          Map.Entry localEntry3 = (Map.Entry)localIterator4.next();
          ah localah1 = new ah();
          localah1.a = ((PlugInBean)localEntry3.getValue()).a;
          localah1.c = ((PlugInBean)localEntry3.getValue()).c;
          localah1.d = ((PlugInBean)localEntry3.getValue()).b;
          localak.p.add(localah1);
        }
      }
      if (paramCrashDetailBean.j)
      {
        localak.k = paramCrashDetailBean.t;
        if ((paramCrashDetailBean.s != null) && (paramCrashDetailBean.s.length() > 0))
        {
          if (localak.q == null)
            localak.q = new ArrayList();
          try
          {
            localak.q.add(new aj((byte)1, "alltimes.txt", paramCrashDetailBean.s.getBytes("utf-8")));
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException6)
          {
            localUnsupportedEncodingException6.printStackTrace();
            localak.q = null;
          }
        }
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = Integer.valueOf(localak.k);
        int j;
        if (localak.q != null)
          j = localak.q.size();
        else
          j = 0;
        arrayOfObject3[1] = Integer.valueOf(j);
        x.c("crashcount:%d sz:%d", arrayOfObject3);
      }
      if (paramCrashDetailBean.w != null)
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        try
        {
          localak.q.add(new aj((byte)1, "log.txt", paramCrashDetailBean.w.getBytes("utf-8")));
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException5)
        {
          localUnsupportedEncodingException5.printStackTrace();
          localak.q = null;
        }
      }
      if (!z.a(paramCrashDetailBean.T))
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        aj localaj5;
        try
        {
          localaj5 = new aj((byte)1, "crashInfos.txt", paramCrashDetailBean.T.getBytes("utf-8"));
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException4)
        {
          localUnsupportedEncodingException4.printStackTrace();
          localaj5 = null;
        }
        if (localaj5 != null)
        {
          x.c("attach crash infos", new Object[0]);
          localak.q.add(localaj5);
        }
      }
      if (paramCrashDetailBean.U != null)
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        aj localaj4 = a("backupRecord.zip", paramContext, paramCrashDetailBean.U);
        if (localaj4 != null)
        {
          x.c("attach backup record", new Object[0]);
          localak.q.add(localaj4);
        }
      }
      if ((paramCrashDetailBean.x != null) && (paramCrashDetailBean.x.length > 0))
      {
        aj localaj3 = new aj((byte)2, "buglylog.zip", paramCrashDetailBean.x);
        x.c("attach user log", new Object[0]);
        if (localak.q == null)
          localak.q = new ArrayList();
        localak.q.add(localaj3);
      }
      if (paramCrashDetailBean.b == 3)
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        if ((paramCrashDetailBean.N != null) && (paramCrashDetailBean.N.containsKey("BUGLY_CR_01")))
        {
          try
          {
            localak.q.add(new aj((byte)1, "anrMessage.txt", ((String)paramCrashDetailBean.N.get("BUGLY_CR_01")).getBytes("utf-8")));
            x.c("attach anr message", new Object[0]);
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException3)
          {
            localUnsupportedEncodingException3.printStackTrace();
            localak.q = null;
          }
          paramCrashDetailBean.N.remove("BUGLY_CR_01");
        }
        if (paramCrashDetailBean.v != null)
        {
          aj localaj2 = a("trace.zip", paramContext, paramCrashDetailBean.v);
          if (localaj2 != null)
          {
            x.c("attach traces", new Object[0]);
            localak.q.add(localaj2);
          }
        }
      }
      if (paramCrashDetailBean.b == 1)
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        if (paramCrashDetailBean.v != null)
        {
          aj localaj1 = a("tomb.zip", paramContext, paramCrashDetailBean.v);
          if (localaj1 != null)
          {
            x.c("attach tombs", new Object[0]);
            localak.q.add(localaj1);
          }
        }
      }
      if ((parama.C != null) && (!parama.C.isEmpty()))
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        StringBuilder localStringBuilder40 = new StringBuilder();
        Iterator localIterator3 = parama.C.iterator();
        while (localIterator3.hasNext())
          localStringBuilder40.append((String)localIterator3.next());
        try
        {
          localak.q.add(new aj((byte)1, "martianlog.txt", localStringBuilder40.toString().getBytes("utf-8")));
          x.c("attach pageTracingList", new Object[0]);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          localUnsupportedEncodingException2.printStackTrace();
        }
      }
      if ((paramCrashDetailBean.S != null) && (paramCrashDetailBean.S.length > 0))
      {
        if (localak.q == null)
          localak.q = new ArrayList();
        localak.q.add(new aj((byte)1, "userExtraByteData", paramCrashDetailBean.S));
        x.c("attach extraData", new Object[0]);
      }
      localak.r = new HashMap();
      Map localMap1 = localak.r;
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append(paramCrashDetailBean.B);
      localMap1.put("A9", localStringBuilder1.toString());
      Map localMap2 = localak.r;
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramCrashDetailBean.C);
      localMap2.put("A11", localStringBuilder2.toString());
      Map localMap3 = localak.r;
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder3.append(paramCrashDetailBean.D);
      localMap3.put("A10", localStringBuilder3.toString());
      Map localMap4 = localak.r;
      StringBuilder localStringBuilder4 = new StringBuilder();
      localStringBuilder4.append(paramCrashDetailBean.f);
      localMap4.put("A23", localStringBuilder4.toString());
      Map localMap5 = localak.r;
      StringBuilder localStringBuilder5 = new StringBuilder();
      localStringBuilder5.append(parama.f);
      localMap5.put("A7", localStringBuilder5.toString());
      Map localMap6 = localak.r;
      StringBuilder localStringBuilder6 = new StringBuilder();
      localStringBuilder6.append(parama.s());
      localMap6.put("A6", localStringBuilder6.toString());
      Map localMap7 = localak.r;
      StringBuilder localStringBuilder7 = new StringBuilder();
      localStringBuilder7.append(parama.r());
      localMap7.put("A5", localStringBuilder7.toString());
      Map localMap8 = localak.r;
      StringBuilder localStringBuilder8 = new StringBuilder();
      localStringBuilder8.append(parama.h());
      localMap8.put("A22", localStringBuilder8.toString());
      Map localMap9 = localak.r;
      StringBuilder localStringBuilder9 = new StringBuilder();
      localStringBuilder9.append(paramCrashDetailBean.F);
      localMap9.put("A2", localStringBuilder9.toString());
      Map localMap10 = localak.r;
      StringBuilder localStringBuilder10 = new StringBuilder();
      localStringBuilder10.append(paramCrashDetailBean.E);
      localMap10.put("A1", localStringBuilder10.toString());
      Map localMap11 = localak.r;
      StringBuilder localStringBuilder11 = new StringBuilder();
      localStringBuilder11.append(parama.h);
      localMap11.put("A24", localStringBuilder11.toString());
      Map localMap12 = localak.r;
      StringBuilder localStringBuilder12 = new StringBuilder();
      localStringBuilder12.append(paramCrashDetailBean.G);
      localMap12.put("A17", localStringBuilder12.toString());
      Map localMap13 = localak.r;
      StringBuilder localStringBuilder13 = new StringBuilder();
      localStringBuilder13.append(parama.k());
      localMap13.put("A3", localStringBuilder13.toString());
      Map localMap14 = localak.r;
      StringBuilder localStringBuilder14 = new StringBuilder();
      localStringBuilder14.append(parama.m());
      localMap14.put("A16", localStringBuilder14.toString());
      Map localMap15 = localak.r;
      StringBuilder localStringBuilder15 = new StringBuilder();
      localStringBuilder15.append(parama.n());
      localMap15.put("A25", localStringBuilder15.toString());
      Map localMap16 = localak.r;
      StringBuilder localStringBuilder16 = new StringBuilder();
      localStringBuilder16.append(parama.l());
      localMap16.put("A14", localStringBuilder16.toString());
      Map localMap17 = localak.r;
      StringBuilder localStringBuilder17 = new StringBuilder();
      localStringBuilder17.append(parama.w());
      localMap17.put("A15", localStringBuilder17.toString());
      Map localMap18 = localak.r;
      StringBuilder localStringBuilder18 = new StringBuilder();
      localStringBuilder18.append(parama.x());
      localMap18.put("A13", localStringBuilder18.toString());
      Map localMap19 = localak.r;
      StringBuilder localStringBuilder19 = new StringBuilder();
      localStringBuilder19.append(paramCrashDetailBean.z);
      localMap19.put("A34", localStringBuilder19.toString());
      if (parama.x != null)
      {
        Map localMap39 = localak.r;
        StringBuilder localStringBuilder39 = new StringBuilder();
        localStringBuilder39.append(parama.x);
        localMap39.put("productIdentify", localStringBuilder39.toString());
      }
      try
      {
        Map localMap38 = localak.r;
        StringBuilder localStringBuilder38 = new StringBuilder();
        localStringBuilder38.append(URLEncoder.encode(paramCrashDetailBean.H, "utf-8"));
        localMap38.put("A26", localStringBuilder38.toString());
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException1)
      {
        localUnsupportedEncodingException1.printStackTrace();
      }
      if (paramCrashDetailBean.b == 1)
      {
        Map localMap35 = localak.r;
        StringBuilder localStringBuilder35 = new StringBuilder();
        localStringBuilder35.append(paramCrashDetailBean.J);
        localMap35.put("A27", localStringBuilder35.toString());
        Map localMap36 = localak.r;
        StringBuilder localStringBuilder36 = new StringBuilder();
        localStringBuilder36.append(paramCrashDetailBean.I);
        localMap36.put("A28", localStringBuilder36.toString());
        Map localMap37 = localak.r;
        StringBuilder localStringBuilder37 = new StringBuilder();
        localStringBuilder37.append(paramCrashDetailBean.k);
        localMap37.put("A29", localStringBuilder37.toString());
      }
      Map localMap20 = localak.r;
      StringBuilder localStringBuilder20 = new StringBuilder();
      localStringBuilder20.append(paramCrashDetailBean.K);
      localMap20.put("A30", localStringBuilder20.toString());
      Map localMap21 = localak.r;
      StringBuilder localStringBuilder21 = new StringBuilder();
      localStringBuilder21.append(paramCrashDetailBean.L);
      localMap21.put("A18", localStringBuilder21.toString());
      Map localMap22 = localak.r;
      StringBuilder localStringBuilder22 = new StringBuilder();
      localStringBuilder22.append(true ^ paramCrashDetailBean.M);
      localMap22.put("A36", localStringBuilder22.toString());
      Map localMap23 = localak.r;
      StringBuilder localStringBuilder23 = new StringBuilder();
      localStringBuilder23.append(parama.q);
      localMap23.put("F02", localStringBuilder23.toString());
      Map localMap24 = localak.r;
      StringBuilder localStringBuilder24 = new StringBuilder();
      localStringBuilder24.append(parama.r);
      localMap24.put("F03", localStringBuilder24.toString());
      Map localMap25 = localak.r;
      StringBuilder localStringBuilder25 = new StringBuilder();
      localStringBuilder25.append(parama.e());
      localMap25.put("F04", localStringBuilder25.toString());
      Map localMap26 = localak.r;
      StringBuilder localStringBuilder26 = new StringBuilder();
      localStringBuilder26.append(parama.s);
      localMap26.put("F05", localStringBuilder26.toString());
      Map localMap27 = localak.r;
      StringBuilder localStringBuilder27 = new StringBuilder();
      localStringBuilder27.append(parama.p);
      localMap27.put("F06", localStringBuilder27.toString());
      Map localMap28 = localak.r;
      StringBuilder localStringBuilder28 = new StringBuilder();
      localStringBuilder28.append(parama.v);
      localMap28.put("F08", localStringBuilder28.toString());
      Map localMap29 = localak.r;
      StringBuilder localStringBuilder29 = new StringBuilder();
      localStringBuilder29.append(parama.w);
      localMap29.put("F09", localStringBuilder29.toString());
      Map localMap30 = localak.r;
      StringBuilder localStringBuilder30 = new StringBuilder();
      localStringBuilder30.append(parama.t);
      localMap30.put("F10", localStringBuilder30.toString());
      if (paramCrashDetailBean.O >= 0)
      {
        Map localMap34 = localak.r;
        StringBuilder localStringBuilder34 = new StringBuilder();
        localStringBuilder34.append(paramCrashDetailBean.O);
        localMap34.put("C01", localStringBuilder34.toString());
      }
      if (paramCrashDetailBean.P >= 0)
      {
        Map localMap33 = localak.r;
        StringBuilder localStringBuilder33 = new StringBuilder();
        localStringBuilder33.append(paramCrashDetailBean.P);
        localMap33.put("C02", localStringBuilder33.toString());
      }
      if ((paramCrashDetailBean.Q != null) && (paramCrashDetailBean.Q.size() > 0))
      {
        Iterator localIterator2 = paramCrashDetailBean.Q.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
          Map localMap32 = localak.r;
          StringBuilder localStringBuilder32 = new StringBuilder("C03_");
          localStringBuilder32.append((String)localEntry2.getKey());
          localMap32.put(localStringBuilder32.toString(), localEntry2.getValue());
        }
      }
      if ((paramCrashDetailBean.R != null) && (paramCrashDetailBean.R.size() > 0))
      {
        Iterator localIterator1 = paramCrashDetailBean.R.entrySet().iterator();
        while (localIterator1.hasNext())
        {
          Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
          Map localMap31 = localak.r;
          StringBuilder localStringBuilder31 = new StringBuilder("C04_");
          localStringBuilder31.append((String)localEntry1.getKey());
          localMap31.put(localStringBuilder31.toString(), localEntry1.getValue());
        }
      }
      localak.s = null;
      if ((paramCrashDetailBean.N != null) && (paramCrashDetailBean.N.size() > 0))
      {
        localak.s = paramCrashDetailBean.N;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(localak.s.size());
        x.a("setted message size %d", arrayOfObject2);
      }
      Object[] arrayOfObject1 = new Object[12];
      arrayOfObject1[0] = paramCrashDetailBean.n;
      arrayOfObject1[1] = paramCrashDetailBean.c;
      arrayOfObject1[2] = parama.e();
      arrayOfObject1[3] = Long.valueOf((paramCrashDetailBean.r - paramCrashDetailBean.L) / 1000L);
      arrayOfObject1[4] = Boolean.valueOf(paramCrashDetailBean.k);
      arrayOfObject1[5] = Boolean.valueOf(paramCrashDetailBean.M);
      arrayOfObject1[6] = Boolean.valueOf(paramCrashDetailBean.j);
      int i = paramCrashDetailBean.b;
      boolean bool = false;
      if (i == 1)
        bool = true;
      arrayOfObject1[7] = Boolean.valueOf(bool);
      arrayOfObject1[8] = Integer.valueOf(paramCrashDetailBean.t);
      arrayOfObject1[9] = paramCrashDetailBean.s;
      arrayOfObject1[10] = Boolean.valueOf(paramCrashDetailBean.d);
      arrayOfObject1[11] = Integer.valueOf(localak.r.size());
      x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", arrayOfObject1);
      return localak;
    }
    x.d("enExp args == null", new Object[0]);
    return null;
  }

  private static List<a> a(List<a> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      long l = System.currentTimeMillis();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        if ((locala.d) && (locala.b <= l - 86400000L))
          localArrayList.add(locala);
      }
      return localArrayList;
    }
    return null;
  }

  public static void a(String paramString1, String paramString2, String paramString3, Thread paramThread, String paramString4, CrashDetailBean paramCrashDetailBean)
  {
    com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
    if (locala == null)
      return;
    x.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
    x.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = locala.c;
    x.e("# PKG NAME: %s", arrayOfObject1);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = locala.j;
    x.e("# APP VER: %s", arrayOfObject2);
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = z.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a));
    x.e("# LAUNCH TIME: %s", arrayOfObject3);
    x.e("# CRASH TYPE: %s", new Object[] { paramString1 });
    x.e("# CRASH TIME: %s", new Object[] { paramString2 });
    x.e("# CRASH PROCESS: %s", new Object[] { paramString3 });
    if (paramThread != null)
    {
      Object[] arrayOfObject10 = new Object[1];
      arrayOfObject10[0] = paramThread.getName();
      x.e("# CRASH THREAD: %s", arrayOfObject10);
    }
    if (paramCrashDetailBean != null)
    {
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = paramCrashDetailBean.c;
      x.e("# REPORT ID: %s", arrayOfObject4);
      Object[] arrayOfObject5 = new Object[2];
      arrayOfObject5[0] = locala.g;
      String str1;
      if (locala.x().booleanValue())
        str1 = "ROOTED";
      else
        str1 = "UNROOT";
      arrayOfObject5[1] = str1;
      x.e("# CRASH DEVICE: %s %s", arrayOfObject5);
      Object[] arrayOfObject6 = new Object[3];
      arrayOfObject6[0] = Long.valueOf(paramCrashDetailBean.B);
      arrayOfObject6[1] = Long.valueOf(paramCrashDetailBean.C);
      arrayOfObject6[2] = Long.valueOf(paramCrashDetailBean.D);
      x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", arrayOfObject6);
      Object[] arrayOfObject7 = new Object[3];
      arrayOfObject7[0] = Long.valueOf(paramCrashDetailBean.E);
      arrayOfObject7[1] = Long.valueOf(paramCrashDetailBean.F);
      arrayOfObject7[2] = Long.valueOf(paramCrashDetailBean.G);
      x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", arrayOfObject7);
      if (!z.a(paramCrashDetailBean.J))
      {
        Object[] arrayOfObject9 = new Object[2];
        arrayOfObject9[0] = paramCrashDetailBean.J;
        arrayOfObject9[1] = paramCrashDetailBean.I;
        x.e("# EXCEPTION FIRED BY %s %s", arrayOfObject9);
      }
      else if (paramCrashDetailBean.b == 3)
      {
        Object[] arrayOfObject8 = new Object[1];
        String str2;
        if (paramCrashDetailBean.N == null)
        {
          str2 = "null";
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)paramCrashDetailBean.N.get("BUGLY_CR_01"));
          str2 = localStringBuilder.toString();
        }
        arrayOfObject8[0] = str2;
        x.e("# EXCEPTION ANR MESSAGE:\n %s", arrayOfObject8);
      }
    }
    if (!z.a(paramString4))
    {
      x.e("# CRASH STACK: ", new Object[0]);
      x.e(paramString4, new Object[0]);
    }
    x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
  }

  public static void a(boolean paramBoolean, List<CrashDetailBean> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Boolean.valueOf(paramBoolean);
      x.c("up finish update state %b", arrayOfObject1);
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        CrashDetailBean localCrashDetailBean2 = (CrashDetailBean)localIterator1.next();
        Object[] arrayOfObject3 = new Object[4];
        arrayOfObject3[0] = localCrashDetailBean2.c;
        arrayOfObject3[1] = Integer.valueOf(localCrashDetailBean2.l);
        arrayOfObject3[2] = Boolean.valueOf(localCrashDetailBean2.d);
        arrayOfObject3[3] = Boolean.valueOf(localCrashDetailBean2.j);
        x.c("pre uid:%s uc:%d re:%b me:%b", arrayOfObject3);
        localCrashDetailBean2.l = (1 + localCrashDetailBean2.l);
        localCrashDetailBean2.d = paramBoolean;
        Object[] arrayOfObject4 = new Object[4];
        arrayOfObject4[0] = localCrashDetailBean2.c;
        arrayOfObject4[1] = Integer.valueOf(localCrashDetailBean2.l);
        arrayOfObject4[2] = Boolean.valueOf(localCrashDetailBean2.d);
        arrayOfObject4[3] = Boolean.valueOf(localCrashDetailBean2.j);
        x.c("set uid:%s uc:%d re:%b me:%b", arrayOfObject4);
      }
      Iterator localIterator2 = paramList.iterator();
      while (localIterator2.hasNext())
      {
        CrashDetailBean localCrashDetailBean1 = (CrashDetailBean)localIterator2.next();
        c.a().a(localCrashDetailBean1);
      }
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramList.size());
      x.c("update state size %d", arrayOfObject2);
    }
    if (!paramBoolean)
      x.b("[crash] upload fail.", new Object[0]);
  }

  private static a b(Cursor paramCursor)
  {
    if (paramCursor == null)
      return null;
    while (true)
    {
      try
      {
        a locala = new a();
        locala.a = paramCursor.getLong(paramCursor.getColumnIndex("_id"));
        locala.b = paramCursor.getLong(paramCursor.getColumnIndex("_tm"));
        locala.c = paramCursor.getString(paramCursor.getColumnIndex("_s1"));
        if (paramCursor.getInt(paramCursor.getColumnIndex("_up")) == 1)
        {
          bool1 = true;
          locala.d = bool1;
          int i = paramCursor.getInt(paramCursor.getColumnIndex("_me"));
          boolean bool2 = false;
          if (i == 1)
            bool2 = true;
          locala.e = bool2;
          locala.f = paramCursor.getInt(paramCursor.getColumnIndex("_uc"));
          return locala;
        }
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return null;
      }
      boolean bool1 = false;
    }
  }

  // ERROR //
  private List<a> b()
  {
    // Byte code:
    //   0: new 90	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 368	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: bipush 6
    //   10: anewarray 137	java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: ldc 55
    //   17: aastore
    //   18: dup
    //   19: iconst_1
    //   20: ldc_w 811
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc_w 813
    //   29: aastore
    //   30: dup
    //   31: iconst_3
    //   32: ldc_w 820
    //   35: aastore
    //   36: dup
    //   37: iconst_4
    //   38: ldc_w 826
    //   41: aastore
    //   42: dup
    //   43: iconst_5
    //   44: ldc_w 828
    //   47: aastore
    //   48: astore_2
    //   49: invokestatic 836	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   52: ldc_w 838
    //   55: aload_2
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: iconst_1
    //   60: invokevirtual 841	com/tencent/bugly/proguard/p:a	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lcom/tencent/bugly/proguard/o;Z)Landroid/database/Cursor;
    //   63: astore 5
    //   65: aload 5
    //   67: ifnonnull +17 -> 84
    //   70: aload 5
    //   72: ifnull +10 -> 82
    //   75: aload 5
    //   77: invokeinterface 842 1 0
    //   82: aconst_null
    //   83: areturn
    //   84: new 143	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   91: astore 7
    //   93: aload 5
    //   95: invokeinterface 845 1 0
    //   100: ifeq +88 -> 188
    //   103: aload 5
    //   105: invokestatic 847	com/tencent/bugly/crashreport/crash/b:b	(Landroid/database/Cursor;)Lcom/tencent/bugly/crashreport/crash/a;
    //   108: astore 13
    //   110: aload 13
    //   112: ifnull +15 -> 127
    //   115: aload_1
    //   116: aload 13
    //   118: invokeinterface 116 2 0
    //   123: pop
    //   124: goto -31 -> 93
    //   127: aload 5
    //   129: aload 5
    //   131: ldc 55
    //   133: invokeinterface 49 2 0
    //   138: invokeinterface 59 2 0
    //   143: lstore 15
    //   145: aload 7
    //   147: ldc_w 849
    //   150: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload 7
    //   156: ldc_w 851
    //   159: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload 7
    //   165: lload 15
    //   167: invokevirtual 171	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   170: pop
    //   171: goto -78 -> 93
    //   174: ldc_w 853
    //   177: iconst_0
    //   178: anewarray 4	java/lang/Object
    //   181: invokestatic 203	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   184: pop
    //   185: goto -92 -> 93
    //   188: aload 7
    //   190: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: astore 8
    //   195: aload 8
    //   197: invokevirtual 408	java/lang/String:length	()I
    //   200: ifle +58 -> 258
    //   203: aload 8
    //   205: iconst_4
    //   206: invokevirtual 856	java/lang/String:substring	(I)Ljava/lang/String;
    //   209: astore 9
    //   211: invokestatic 836	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   214: ldc_w 838
    //   217: aload 9
    //   219: aconst_null
    //   220: aconst_null
    //   221: iconst_1
    //   222: invokevirtual 859	com/tencent/bugly/proguard/p:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lcom/tencent/bugly/proguard/o;Z)I
    //   225: istore 10
    //   227: iconst_2
    //   228: anewarray 4	java/lang/Object
    //   231: astore 11
    //   233: aload 11
    //   235: iconst_0
    //   236: ldc_w 838
    //   239: aastore
    //   240: aload 11
    //   242: iconst_1
    //   243: iload 10
    //   245: invokestatic 232	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   248: aastore
    //   249: ldc_w 861
    //   252: aload 11
    //   254: invokestatic 203	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   257: pop
    //   258: aload 5
    //   260: ifnull +10 -> 270
    //   263: aload 5
    //   265: invokeinterface 842 1 0
    //   270: aload_1
    //   271: areturn
    //   272: astore 4
    //   274: goto +18 -> 292
    //   277: astore 6
    //   279: aconst_null
    //   280: astore 5
    //   282: goto +39 -> 321
    //   285: astore_3
    //   286: aload_3
    //   287: astore 4
    //   289: aconst_null
    //   290: astore 5
    //   292: aload 4
    //   294: invokestatic 78	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   297: ifne +8 -> 305
    //   300: aload 4
    //   302: invokevirtual 81	java/lang/Throwable:printStackTrace	()V
    //   305: aload 5
    //   307: ifnull +10 -> 317
    //   310: aload 5
    //   312: invokeinterface 842 1 0
    //   317: aload_1
    //   318: areturn
    //   319: astore 6
    //   321: aload 5
    //   323: ifnull +10 -> 333
    //   326: aload 5
    //   328: invokeinterface 842 1 0
    //   333: aload 6
    //   335: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   127	171	174	java/lang/Throwable
    //   84	124	272	java/lang/Throwable
    //   174	258	272	java/lang/Throwable
    //   8	65	277	finally
    //   8	65	285	java/lang/Throwable
    //   84	124	319	finally
    //   127	171	319	finally
    //   174	258	319	finally
    //   292	305	319	finally
  }

  // ERROR //
  private List<CrashDetailBean> b(List<a> paramList)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +394 -> 395
    //   4: aload_1
    //   5: invokeinterface 88 1 0
    //   10: ifne +6 -> 16
    //   13: goto +382 -> 395
    //   16: new 143	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 144	java/lang/StringBuilder:<init>	()V
    //   23: astore_2
    //   24: aload_1
    //   25: invokeinterface 97 1 0
    //   30: astore_3
    //   31: aload_3
    //   32: invokeinterface 103 1 0
    //   37: ifeq +43 -> 80
    //   40: aload_3
    //   41: invokeinterface 107 1 0
    //   46: checkcast 109	com/tencent/bugly/crashreport/crash/a
    //   49: astore 23
    //   51: aload_2
    //   52: ldc_w 849
    //   55: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: aload_2
    //   60: ldc_w 851
    //   63: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload_2
    //   68: aload 23
    //   70: getfield 809	com/tencent/bugly/crashreport/crash/a:a	J
    //   73: invokevirtual 171	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: goto -46 -> 31
    //   80: aload_2
    //   81: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore 4
    //   86: aload 4
    //   88: invokevirtual 408	java/lang/String:length	()I
    //   91: ifle +11 -> 102
    //   94: aload 4
    //   96: iconst_4
    //   97: invokevirtual 856	java/lang/String:substring	(I)Ljava/lang/String;
    //   100: astore 4
    //   102: aload 4
    //   104: astore 5
    //   106: aload_2
    //   107: iconst_0
    //   108: invokevirtual 864	java/lang/StringBuilder:setLength	(I)V
    //   111: invokestatic 836	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   114: ldc_w 838
    //   117: aconst_null
    //   118: aload 5
    //   120: aconst_null
    //   121: aconst_null
    //   122: iconst_1
    //   123: invokevirtual 841	com/tencent/bugly/proguard/p:a	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lcom/tencent/bugly/proguard/o;Z)Landroid/database/Cursor;
    //   126: astore 7
    //   128: aload 7
    //   130: ifnonnull +17 -> 147
    //   133: aload 7
    //   135: ifnull +10 -> 145
    //   138: aload 7
    //   140: invokeinterface 842 1 0
    //   145: aconst_null
    //   146: areturn
    //   147: new 90	java/util/ArrayList
    //   150: dup
    //   151: invokespecial 368	java/util/ArrayList:<init>	()V
    //   154: astore 9
    //   156: aload 7
    //   158: invokeinterface 845 1 0
    //   163: ifeq +86 -> 249
    //   166: aload 7
    //   168: invokestatic 866	com/tencent/bugly/crashreport/crash/b:a	(Landroid/database/Cursor;)Lcom/tencent/bugly/crashreport/crash/CrashDetailBean;
    //   171: astore 15
    //   173: aload 15
    //   175: ifnull +16 -> 191
    //   178: aload 9
    //   180: aload 15
    //   182: invokeinterface 116 2 0
    //   187: pop
    //   188: goto -32 -> 156
    //   191: aload 7
    //   193: aload 7
    //   195: ldc 55
    //   197: invokeinterface 49 2 0
    //   202: invokeinterface 59 2 0
    //   207: lstore 17
    //   209: aload_2
    //   210: ldc_w 849
    //   213: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: pop
    //   217: aload_2
    //   218: ldc_w 851
    //   221: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload_2
    //   226: lload 17
    //   228: invokevirtual 171	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: goto -76 -> 156
    //   235: ldc_w 853
    //   238: iconst_0
    //   239: anewarray 4	java/lang/Object
    //   242: invokestatic 203	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   245: pop
    //   246: goto -90 -> 156
    //   249: aload_2
    //   250: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   253: astore 10
    //   255: aload 10
    //   257: invokevirtual 408	java/lang/String:length	()I
    //   260: ifle +58 -> 318
    //   263: aload 10
    //   265: iconst_4
    //   266: invokevirtual 856	java/lang/String:substring	(I)Ljava/lang/String;
    //   269: astore 11
    //   271: invokestatic 836	com/tencent/bugly/proguard/p:a	()Lcom/tencent/bugly/proguard/p;
    //   274: ldc_w 838
    //   277: aload 11
    //   279: aconst_null
    //   280: aconst_null
    //   281: iconst_1
    //   282: invokevirtual 859	com/tencent/bugly/proguard/p:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lcom/tencent/bugly/proguard/o;Z)I
    //   285: istore 12
    //   287: iconst_2
    //   288: anewarray 4	java/lang/Object
    //   291: astore 13
    //   293: aload 13
    //   295: iconst_0
    //   296: ldc_w 838
    //   299: aastore
    //   300: aload 13
    //   302: iconst_1
    //   303: iload 12
    //   305: invokestatic 232	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   308: aastore
    //   309: ldc_w 861
    //   312: aload 13
    //   314: invokestatic 203	com/tencent/bugly/proguard/x:d	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   317: pop
    //   318: aload 7
    //   320: ifnull +10 -> 330
    //   323: aload 7
    //   325: invokeinterface 842 1 0
    //   330: aload 9
    //   332: areturn
    //   333: astore 6
    //   335: goto +16 -> 351
    //   338: astore 8
    //   340: aconst_null
    //   341: astore 7
    //   343: goto +37 -> 380
    //   346: astore 6
    //   348: aconst_null
    //   349: astore 7
    //   351: aload 6
    //   353: invokestatic 78	com/tencent/bugly/proguard/x:a	(Ljava/lang/Throwable;)Z
    //   356: ifne +8 -> 364
    //   359: aload 6
    //   361: invokevirtual 81	java/lang/Throwable:printStackTrace	()V
    //   364: aload 7
    //   366: ifnull +10 -> 376
    //   369: aload 7
    //   371: invokeinterface 842 1 0
    //   376: aconst_null
    //   377: areturn
    //   378: astore 8
    //   380: aload 7
    //   382: ifnull +10 -> 392
    //   385: aload 7
    //   387: invokeinterface 842 1 0
    //   392: aload 8
    //   394: athrow
    //   395: aconst_null
    //   396: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   191	232	235	java/lang/Throwable
    //   147	188	333	java/lang/Throwable
    //   235	318	333	java/lang/Throwable
    //   111	128	338	finally
    //   111	128	346	java/lang/Throwable
    //   147	188	378	finally
    //   191	232	378	finally
    //   235	318	378	finally
    //   351	364	378	finally
  }

  private static void c(List<a> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        localStringBuilder.append(" or _id");
        localStringBuilder.append(" = ");
        localStringBuilder.append(locala.a);
      }
      String str1 = localStringBuilder.toString();
      if (str1.length() > 0)
        str1 = str1.substring(4);
      String str2 = str1;
      localStringBuilder.setLength(0);
      try
      {
        int i = p.a().a("t_cr", str2, null, null, true);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = "t_cr";
        arrayOfObject[1] = Integer.valueOf(i);
        x.c("deleted %s data %d", arrayOfObject);
        return;
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return;
      }
    }
  }

  private static void d(List<CrashDetailBean> paramList)
  {
    if (paramList != null)
      try
      {
        if (paramList.size() != 0)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          Iterator localIterator = paramList.iterator();
          while (localIterator.hasNext())
          {
            CrashDetailBean localCrashDetailBean = (CrashDetailBean)localIterator.next();
            localStringBuilder.append(" or _id");
            localStringBuilder.append(" = ");
            localStringBuilder.append(localCrashDetailBean.a);
          }
          String str1 = localStringBuilder.toString();
          if (str1.length() > 0)
            str1 = str1.substring(4);
          String str2 = str1;
          localStringBuilder.setLength(0);
          int i = p.a().a("t_cr", str2, null, null, true);
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = "t_cr";
          arrayOfObject[1] = Integer.valueOf(i);
          x.c("deleted %s data %d", arrayOfObject);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return;
      }
  }

  private static ContentValues e(CrashDetailBean paramCrashDetailBean)
  {
    if (paramCrashDetailBean == null)
      return null;
    try
    {
      ContentValues localContentValues = new ContentValues();
      if (paramCrashDetailBean.a > 0L)
        localContentValues.put("_id", Long.valueOf(paramCrashDetailBean.a));
      localContentValues.put("_tm", Long.valueOf(paramCrashDetailBean.r));
      localContentValues.put("_s1", paramCrashDetailBean.u);
      localContentValues.put("_up", Integer.valueOf(paramCrashDetailBean.d));
      localContentValues.put("_me", Integer.valueOf(paramCrashDetailBean.j));
      localContentValues.put("_uc", Integer.valueOf(paramCrashDetailBean.l));
      localContentValues.put("_dt", z.a(paramCrashDetailBean));
      return localContentValues;
    }
    catch (Throwable localThrowable)
    {
      if (!x.a(localThrowable))
        localThrowable.printStackTrace();
    }
    return null;
  }

  public final List<CrashDetailBean> a()
  {
    StrategyBean localStrategyBean = com.tencent.bugly.crashreport.common.strategy.a.a().c();
    if (localStrategyBean == null)
    {
      x.d("have not synced remote!", new Object[0]);
      return null;
    }
    if (!localStrategyBean.g)
    {
      x.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
      x.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
      return null;
    }
    long l1 = System.currentTimeMillis();
    long l2 = z.b();
    List localList1 = b();
    if ((localList1 != null) && (localList1.size() > 0))
    {
      ArrayList localArrayList1 = new ArrayList();
      Iterator localIterator1 = localList1.iterator();
      while (localIterator1.hasNext())
      {
        a locala = (a)localIterator1.next();
        if (locala.b < l2 - c.g)
        {
          localIterator1.remove();
          localArrayList1.add(locala);
        }
        else if (locala.d)
        {
          if (locala.b >= l1 - 86400000L)
          {
            localIterator1.remove();
          }
          else if (!locala.e)
          {
            localIterator1.remove();
            localArrayList1.add(locala);
          }
        }
        else if ((locala.f >= 3L) && (locala.b < l1 - 86400000L))
        {
          localIterator1.remove();
          localArrayList1.add(locala);
        }
      }
      if (localArrayList1.size() > 0)
        c(localArrayList1);
      ArrayList localArrayList2 = new ArrayList();
      List localList2 = b(localList1);
      if ((localList2 != null) && (localList2.size() > 0))
      {
        String str = com.tencent.bugly.crashreport.common.info.a.b().j;
        Iterator localIterator2 = localList2.iterator();
        while (localIterator2.hasNext())
        {
          CrashDetailBean localCrashDetailBean = (CrashDetailBean)localIterator2.next();
          if (!str.equals(localCrashDetailBean.f))
          {
            localIterator2.remove();
            localArrayList2.add(localCrashDetailBean);
          }
        }
      }
      if (localArrayList2.size() > 0)
        d(localArrayList2);
      return localList2;
    }
    return null;
  }

  public final void a(CrashDetailBean paramCrashDetailBean, long paramLong, boolean paramBoolean)
  {
    if (c.l)
    {
      x.a("try to upload right now", new Object[0]);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramCrashDetailBean);
      boolean bool;
      if (paramCrashDetailBean.b == 7)
        bool = true;
      else
        bool = false;
      a(localArrayList, 3000L, paramBoolean, bool, paramBoolean);
    }
  }

  public final void a(final List<CrashDetailBean> paramList, long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (!com.tencent.bugly.crashreport.common.info.a.a(this.b).e)
      return;
    if (this.c == null)
      return;
    if ((!paramBoolean3) && (!this.c.b(c.a)))
      return;
    StrategyBean localStrategyBean = this.e.c();
    if (!localStrategyBean.g)
    {
      x.d("remote report is disable!", new Object[0]);
      x.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
      return;
    }
    if ((paramList != null) && (paramList.size() != 0));
    while (true)
    {
      try
      {
        String str1;
        if (this.c.a)
          str1 = localStrategyBean.s;
        else
          str1 = localStrategyBean.t;
        String str2 = str1;
        String str3;
        if (this.c.a)
          str3 = StrategyBean.c;
        else
          str3 = StrategyBean.a;
        String str4 = str3;
        if (!this.c.a)
          break label468;
        i = 830;
        Context localContext = this.b;
        com.tencent.bugly.crashreport.common.info.a locala = com.tencent.bugly.crashreport.common.info.a.b();
        al localal;
        if ((localContext != null) && (paramList != null) && (paramList.size() != 0) && (locala != null))
        {
          localal = new al();
          localal.a = new ArrayList();
          Iterator localIterator = paramList.iterator();
          if (localIterator.hasNext())
          {
            CrashDetailBean localCrashDetailBean = (CrashDetailBean)localIterator.next();
            localal.a.add(a(localContext, localCrashDetailBean, locala));
            continue;
          }
        }
        else
        {
          x.d("enEXPPkg args == null!", new Object[0]);
          localal = null;
        }
        if (localal == null)
        {
          x.d("create eupPkg fail!", new Object[0]);
          return;
        }
        byte[] arrayOfByte = com.tencent.bugly.proguard.a.a(localal);
        if (arrayOfByte == null)
        {
          x.d("send encode fail!", new Object[0]);
          return;
        }
        am localam = com.tencent.bugly.proguard.a.a(this.b, i, arrayOfByte);
        if (localam == null)
        {
          x.d("request package is null.", new Object[0]);
          return;
        }
        t local1 = new t()
        {
          public final void a(boolean paramAnonymousBoolean)
          {
            b.a(paramAnonymousBoolean, paramList);
          }
        };
        if (paramBoolean1)
        {
          this.c.a(a, localam, str2, str4, local1, paramLong, paramBoolean2);
        }
        else
        {
          this.c.a(a, localam, str2, str4, local1, false);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localThrowable.toString();
        x.e("req cr error %s", arrayOfObject);
        if (!x.b(localThrowable))
          localThrowable.printStackTrace();
      }
      return;
      return;
      label468: int i = 630;
    }
  }

  public final boolean a(CrashDetailBean paramCrashDetailBean)
  {
    return a(paramCrashDetailBean, -123456789);
  }

  public final boolean a(CrashDetailBean paramCrashDetailBean, int paramInt)
  {
    if (paramCrashDetailBean == null)
      return true;
    if ((c.m != null) && (!c.m.isEmpty()))
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = c.m;
      x.c("Crash filter for crash stack is: %s", arrayOfObject2);
      if (paramCrashDetailBean.q.contains(c.m))
      {
        x.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
        return true;
      }
    }
    if ((c.n != null) && (!c.n.isEmpty()))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = c.n;
      x.c("Crash regular filter for crash stack is: %s", arrayOfObject1);
      if (Pattern.compile(c.n).matcher(paramCrashDetailBean.q).find())
      {
        x.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
        return true;
      }
    }
    if (this.f != null)
    {
      o localo = this.f;
      if (!localo.c())
      {
        x.d("Crash listener 'onCrashSaving' return 'false' thus will not handle this crash.", new Object[0]);
        return true;
      }
    }
    if (paramCrashDetailBean.b != 2)
    {
      r localr = new r();
      localr.b = 1;
      localr.c = paramCrashDetailBean.z;
      localr.d = paramCrashDetailBean.A;
      localr.e = paramCrashDetailBean.r;
      this.d.b(1);
      this.d.a(localr);
      x.b("[crash] a crash occur, handling...", new Object[0]);
    }
    else
    {
      x.b("[crash] a caught exception occur, handling...", new Object[0]);
    }
    List localList = b();
    ArrayList localArrayList1 = null;
    if (localList != null)
    {
      int i = localList.size();
      localArrayList1 = null;
      if (i > 0)
      {
        localArrayList1 = new ArrayList(10);
        ArrayList localArrayList2 = new ArrayList(10);
        localArrayList1.addAll(a(localList));
        localList.removeAll(localArrayList1);
        if ((!com.tencent.bugly.b.c) && (c.d))
        {
          Iterator localIterator1 = localList.iterator();
          int j = 0;
          while (localIterator1.hasNext())
          {
            a locala2 = (a)localIterator1.next();
            if (paramCrashDetailBean.u.equals(locala2.c))
            {
              if (locala2.e)
                j = 1;
              localArrayList2.add(locala2);
            }
          }
          if ((j != 0) || (localArrayList2.size() >= c.c))
          {
            x.a("same crash occur too much do merged!", new Object[0]);
            CrashDetailBean localCrashDetailBean = a(localArrayList2, paramCrashDetailBean);
            Iterator localIterator2 = localArrayList2.iterator();
            while (localIterator2.hasNext())
            {
              a locala1 = (a)localIterator2.next();
              if (locala1.a != localCrashDetailBean.a)
                localArrayList1.add(locala1);
            }
            d(localCrashDetailBean);
            c(localArrayList1);
            x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
            return true;
          }
        }
      }
    }
    d(paramCrashDetailBean);
    if ((localArrayList1 != null) && (!localArrayList1.isEmpty()))
      c(localArrayList1);
    x.b("[crash] save crash success", new Object[0]);
    return false;
  }

  public final void b(CrashDetailBean paramCrashDetailBean)
  {
    if (this.f != null);
  }

  public final void c(CrashDetailBean paramCrashDetailBean)
  {
    if (paramCrashDetailBean == null)
      return;
    if ((this.g == null) && (this.f == null))
      return;
    while (true)
    {
      try
      {
        x.a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
        switch (paramCrashDetailBean.b)
        {
        default:
          if (this.f != null)
          {
            String str3 = this.f.b();
            if (str3 == null)
              break label767;
            localHashMap = new HashMap(1);
            localHashMap.put("userData", str3);
            break label770;
          }
          if (this.g == null)
            break label777;
          localObject = this.g.onCrashHandleStart(i, paramCrashDetailBean.n, paramCrashDetailBean.o, paramCrashDetailBean.q);
          if ((localObject != null) && (((Map)localObject).size() > 0))
          {
            paramCrashDetailBean.N = new LinkedHashMap(((Map)localObject).size());
            Iterator localIterator = ((Map)localObject).entrySet().iterator();
            if (localIterator.hasNext())
            {
              Map.Entry localEntry = (Map.Entry)localIterator.next();
              if (z.a((String)localEntry.getKey()))
                continue;
              String str1 = (String)localEntry.getKey();
              if (str1.length() > 100)
              {
                str1 = str1.substring(0, 100);
                Object[] arrayOfObject6 = new Object[2];
                arrayOfObject6[0] = Integer.valueOf(100);
                arrayOfObject6[1] = str1;
                x.d("setted key length is over limit %d substring to %s", arrayOfObject6);
              }
              String str2;
              if ((!z.a((String)localEntry.getValue())) && (((String)localEntry.getValue()).length() > 30000))
              {
                str2 = ((String)localEntry.getValue()).substring(((String)localEntry.getValue()).length() - 30000);
                Object[] arrayOfObject5 = new Object[2];
                arrayOfObject5[0] = str1;
                arrayOfObject5[1] = Integer.valueOf(30000);
                x.d("setted %s value length is over limit %d substring", arrayOfObject5);
              }
              else
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append((String)localEntry.getValue());
                str2 = localStringBuilder.toString();
              }
              paramCrashDetailBean.N.put(str1, str2);
              Object[] arrayOfObject4 = new Object[2];
              arrayOfObject4[0] = str1;
              arrayOfObject4[1] = Integer.valueOf(str2.length());
              x.a("add setted key %s value size:%d", arrayOfObject4);
              continue;
            }
          }
          x.a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
          byte[] arrayOfByte;
          if (this.f != null)
          {
            arrayOfByte = this.f.a();
          }
          else
          {
            BuglyStrategy.a locala = this.g;
            arrayOfByte = null;
            if (locala != null)
              arrayOfByte = this.g.onCrashHandleStart2GetExtraDatas(i, paramCrashDetailBean.n, paramCrashDetailBean.o, paramCrashDetailBean.q);
          }
          paramCrashDetailBean.S = arrayOfByte;
          if (paramCrashDetailBean.S != null)
          {
            if (paramCrashDetailBean.S.length > 30000)
            {
              Object[] arrayOfObject3 = new Object[2];
              arrayOfObject3[0] = Integer.valueOf(paramCrashDetailBean.S.length);
              arrayOfObject3[1] = Integer.valueOf(30000);
              x.d("extra bytes size %d is over limit %d will drop over part", arrayOfObject3);
            }
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Integer.valueOf(paramCrashDetailBean.S.length);
            x.a("add extra bytes %d ", arrayOfObject2);
          }
          return;
        case 7:
        case 6:
        case 5:
        case 4:
        case 3:
        case 2:
        case 1:
        case 0:
        }
      }
      catch (Throwable localThrowable)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localThrowable.getClass().getName();
        x.d("crash handle callback somthing wrong! %s", arrayOfObject1);
        if (!x.a(localThrowable))
          localThrowable.printStackTrace();
        return;
      }
      return;
      int i = 7;
      continue;
      i = 6;
      continue;
      i = 5;
      continue;
      i = 3;
      continue;
      i = 4;
      continue;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 0;
      continue;
      label767: HashMap localHashMap = null;
      label770: Object localObject = localHashMap;
      continue;
      label777: localObject = null;
    }
  }

  public final void d(CrashDetailBean paramCrashDetailBean)
  {
    if (paramCrashDetailBean == null)
      return;
    ContentValues localContentValues = e(paramCrashDetailBean);
    if (localContentValues != null)
    {
      long l = p.a().a("t_cr", localContentValues, null, true);
      if (l >= 0L)
      {
        x.c("insert %s success!", new Object[] { "t_cr" });
        paramCrashDetailBean.a = l;
      }
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.crashreport.crash.b
 * JD-Core Version:    0.6.1
 */