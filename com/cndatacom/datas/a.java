package com.cndatacom.datas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

public class a extends SQLiteOpenHelper
{
  public a(Context paramContext, String paramString, int paramInt)
  {
    super(paramContext, paramString, null, paramInt);
  }

  private String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(" CREATE TABLE IF NOT EXISTS ");
    localStringBuffer.append(" [Keys] ");
    localStringBuffer.append("(");
    localStringBuffer.append("[Id] INTEGER PRIMARY KEY AutoIncrement,");
    localStringBuffer.append("[Key] VARCHAR(50),");
    localStringBuffer.append("[Value] VARCHAR(255)");
    localStringBuffer.append(");");
    localStringBuffer.append("CREATE INDEX IDX_Keys_Key ON [Keys] (Key);");
    return localStringBuffer.toString();
  }

  private String b()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(" DROP TABLE IF EXISTS [Keys]; ");
    localStringBuffer.append(" CREATE TABLE IF NOT EXISTS ");
    localStringBuffer.append(" [Keys] ");
    localStringBuffer.append("(");
    localStringBuffer.append("[Id] INTEGER PRIMARY KEY AutoIncrement,");
    localStringBuffer.append("[Key] VARCHAR(50),");
    localStringBuffer.append("[Value] VARCHAR(255)");
    localStringBuffer.append(");");
    localStringBuffer.append("CREATE INDEX IDX_Keys_Key ON [Keys] (Key);");
    return localStringBuffer.toString();
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    if (!TextUtils.isEmpty(a()))
      paramSQLiteDatabase.execSQL(a());
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    String str = b();
    if (!TextUtils.isEmpty(str))
      paramSQLiteDatabase.execSQL(str);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.datas.a
 * JD-Core Version:    0.6.1
 */