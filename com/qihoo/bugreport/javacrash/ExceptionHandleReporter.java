package com.qihoo.bugreport.javacrash;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.qihoo.jiagu.b;
import com.qihoo.jiagu.c;
import com.qihoo.jiagu.e;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ExceptionHandleReporter
  implements Thread.UncaughtExceptionHandler
{
  private static ExceptionHandleReporter a;
  private final Thread.UncaughtExceptionHandler b;
  private final Context c;
  private final CrashReportDataFactory d;
  private final Thread.UncaughtExceptionHandler e;

  private ExceptionHandleReporter(Context paramContext, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    this.c = paramContext;
    this.e = paramUncaughtExceptionHandler;
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    this.d = new CrashReportDataFactory(this.c, localGregorianCalendar);
    this.b = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  public static ExceptionHandleReporter a(Context paramContext, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
  {
    try
    {
      if (a == null)
        a = new ExceptionHandleReporter(paramContext, paramUncaughtExceptionHandler);
      ExceptionHandleReporter localExceptionHandleReporter = a;
      return localExceptionHandleReporter;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void a(Thread paramThread, Throwable paramThrowable, boolean paramBoolean)
  {
    if (paramBoolean);
    while (true)
    {
      return;
      try
      {
        if (this.b == null)
          break label38;
        this.b.uncaughtException(paramThread, paramThrowable);
      }
      catch (Throwable localThrowable)
      {
        b(paramThread, paramThrowable, paramBoolean);
      }
      continue;
      label38: b(paramThread, paramThrowable, paramBoolean);
    }
  }

  private boolean a(JSONObject paramJSONObject, Throwable paramThrowable)
  {
    while (true)
    {
      boolean bool1;
      long l;
      try
      {
        if (paramJSONObject.has(ReportField.me.name()))
        {
          boolean bool2 = paramJSONObject.has(ReportField.ct.name());
          if (bool2);
        }
        else
        {
          bool1 = true;
          return bool1;
        }
        SharedPreferences localSharedPreferences = this.c.getSharedPreferences("qihoo_jiagu_crash_report", 0);
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        String str1 = localSharedPreferences.getString("last_report_me", "");
        String str2 = localSharedPreferences.getString("last_report_time", "0000/00/00 00:00:00");
        String str3 = localSharedPreferences.getString("last_exception_info", "");
        String str4 = Integer.toString(paramThrowable.hashCode());
        try
        {
          String str5 = paramJSONObject.getString(ReportField.me.name());
          String str6 = paramJSONObject.getString(ReportField.ct.name());
          SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
          Date localDate1 = localSimpleDateFormat.parse(str6);
          Date localDate2 = localSimpleDateFormat.parse(str2);
          if (localDate1.getTime() - localDate2.getTime() < 0L)
          {
            localEditor.putString("last_report_time", paramJSONObject.getString(ReportField.ct.name()));
            localEditor.commit();
            bool1 = true;
            continue;
          }
          if ((str1.equals(str5)) || (str4.equals(str3)))
          {
            l = localDate1.getTime() - localDate2.getTime() - 600000L;
            break label362;
          }
          l = localDate1.getTime() - localDate2.getTime() - 120000L;
          break label362;
          localEditor.putString("last_report_me", paramJSONObject.getString(ReportField.me.name()));
          localEditor.putString("last_report_time", paramJSONObject.getString(ReportField.ct.name()));
          localEditor.putString("last_exception_info", str4);
          localEditor.commit();
          bool1 = false;
        }
        catch (JSONException localJSONException)
        {
          bool1 = true;
        }
        catch (ParseException localParseException)
        {
          bool1 = true;
        }
        continue;
      }
      finally
      {
      }
      label362: if (l < 0L)
        bool1 = true;
    }
  }

  private void b(Thread paramThread, Throwable paramThrowable, boolean paramBoolean)
  {
    if (paramBoolean);
    while (true)
    {
      return;
      try
      {
        if (this.e != null)
          this.e.uncaughtException(paramThread, paramThrowable);
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    uncaughtException(paramThread, paramThrowable, false, 0);
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable, boolean paramBoolean)
  {
    uncaughtException(paramThread, paramThrowable, paramBoolean, 2);
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable, boolean paramBoolean, int paramInt)
  {
    while (true)
    {
      int j;
      EnumMap localEnumMap;
      String str5;
      JSONObject localJSONObject;
      try
      {
        String[] arrayOfString = b.a;
        Context localContext = this.c;
        int i = arrayOfString.length;
        j = 0;
        if (j >= i)
          break label531;
        boolean bool = c.a(localContext, arrayOfString[j]);
        k = 0;
        if (bool)
          break label525;
        if (k == 0)
        {
          a(paramThread, paramThrowable, paramBoolean);
        }
        else
        {
          CrashReportDataFactory localCrashReportDataFactory = this.d;
          localEnumMap = new EnumMap(ReportField.class);
          localEnumMap.put(ReportField.t, "1");
          localEnumMap.put(ReportField.cpv, "3");
          localEnumMap.put(ReportField.st, CrashReportDataFactory.a(localCrashReportDataFactory.a));
          GregorianCalendar localGregorianCalendar = new GregorianCalendar();
          localEnumMap.put(ReportField.ct, CrashReportDataFactory.a(localGregorianCalendar));
          String str1 = paramThrowable.getClass().getName();
          localEnumMap.put(ReportField.et, str1);
          String str2 = paramThrowable.getMessage();
          localEnumMap.put(ReportField.ec, str2);
          localEnumMap.put(ReportField.jc, String.valueOf(paramInt));
          ArrayList localArrayList = CrashReportDataFactory.a(paramThrowable);
          String str3 = localCrashReportDataFactory.a(str1, str2, localArrayList);
          if (str3 != null)
            localEnumMap.put(ReportField.me, str3);
          StringWriter localStringWriter = new StringWriter();
          PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
          paramThrowable.printStackTrace(localPrintWriter);
          String str4 = localStringWriter.toString();
          localPrintWriter.close();
          str5 = CrashReportDataFactory.a(str4);
          if (str5 == null)
          {
            localEnumMap.put(ReportField.crd, "null");
            String str6 = CrashReportDataFactory.a(str4, localArrayList);
            if (str6 != null)
              localEnumMap.put(ReportField.em, str6);
            String str7 = CrashReportDataFactory.c();
            if (str7 != null)
              localEnumMap.put(ReportField.ep, str7);
            HashMap localHashMap = CrashReportDataFactory.a();
            if (localHashMap.get("ed") != null)
              localEnumMap.put(ReportField.ed, localHashMap.get("ed"));
            if (localHashMap.get("epd") != null)
              localEnumMap.put(ReportField.epd, localHashMap.get("epd"));
            localJSONObject = c.a(localEnumMap, localCrashReportDataFactory.b());
            if (localJSONObject != null)
              break label448;
            a(paramThread, paramThrowable, paramBoolean);
          }
        }
      }
      catch (Throwable localThrowable)
      {
        a(paramThread, paramThrowable, paramBoolean);
      }
      localEnumMap.put(ReportField.crd, str5);
      continue;
      label448: localJSONObject.toString();
      try
      {
        if (a(localJSONObject, paramThrowable))
          a(paramThread, paramThrowable, paramBoolean);
      }
      finally
      {
        throw localObject;
        e locale = new e(this, paramBoolean, localJSONObject);
        locale.start();
        locale.join(4000L);
        a(paramThread, paramThrowable, paramBoolean);
      }
      label525: j++;
      continue;
      label531: int k = 1;
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.qihoo.bugreport.javacrash.ExceptionHandleReporter
 * JD-Core Version:    0.6.1
 */