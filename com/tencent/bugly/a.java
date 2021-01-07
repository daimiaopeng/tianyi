package com.tencent.bugly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.x;

public abstract class a
{
  public int id;
  public String moduleName;
  public String version;
  public String versionKey;

  public abstract String[] getTables();

  public abstract void init(Context paramContext, boolean paramBoolean, BuglyStrategy paramBuglyStrategy);

  public void onDbCreate(SQLiteDatabase paramSQLiteDatabase)
  {
  }

  public void onDbDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      if (getTables() == null)
        return;
      for (String str : getTables())
      {
        StringBuilder localStringBuilder = new StringBuilder("DROP TABLE IF EXISTS ");
        localStringBuilder.append(str);
        paramSQLiteDatabase.execSQL(localStringBuilder.toString());
      }
      onDbCreate(paramSQLiteDatabase);
      return;
    }
    catch (Throwable localThrowable)
    {
      if (!x.b(localThrowable))
        localThrowable.printStackTrace();
    }
  }

  public void onDbUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      if (getTables() == null)
        return;
      for (String str : getTables())
      {
        StringBuilder localStringBuilder = new StringBuilder("DROP TABLE IF EXISTS ");
        localStringBuilder.append(str);
        paramSQLiteDatabase.execSQL(localStringBuilder.toString());
      }
      onDbCreate(paramSQLiteDatabase);
      return;
    }
    catch (Throwable localThrowable)
    {
      if (!x.b(localThrowable))
        localThrowable.printStackTrace();
    }
  }

  public void onServerStrategyChanged(StrategyBean paramStrategyBean)
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.tencent.bugly.a
 * JD-Core Version:    0.6.1
 */