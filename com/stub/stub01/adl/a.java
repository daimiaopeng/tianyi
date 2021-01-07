package com.stub.stub01.adl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class a extends SQLiteOpenHelper
{
  public a(Context paramContext, String paramString)
  {
    super(paramContext, paramString, null, 1);
  }

  private static ContentValues d(com.stub.stub01.a.d paramd)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("url", DownloadManager.md5(paramd.a()));
    localContentValues.put("state", paramd.f().toString());
    localContentValues.put("complete", Long.valueOf(paramd.e()));
    localContentValues.put("size", Long.valueOf(paramd.d()));
    localContentValues.put("create_time", Long.valueOf(paramd.g()));
    return localContentValues;
  }

  public final com.stub.stub01.a.d a(d paramd)
  {
    while (true)
    {
      try
      {
        new StringBuilder("DownloadDBHelper query(String url) ").append(paramd.toString());
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString1 = { "state", "complete", "size", "create_time" };
        String[] arrayOfString2 = new String[1];
        arrayOfString2[0] = paramd.toString();
        Cursor localCursor = localSQLiteDatabase.query("app_download", arrayOfString1, "state=?", arrayOfString2, null, null, "create_time DESC");
        if ((localCursor != null) && (localCursor.moveToNext()))
        {
          locald = new com.stub.stub01.a.d();
          locald.a(d.valueOf(localCursor.getString(0)));
          locald.b(localCursor.getLong(1));
          locald.a(localCursor.getLong(2));
          locald.c(localCursor.getLong(3));
          localSQLiteDatabase.close();
          return locald;
        }
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
      com.stub.stub01.a.d locald = null;
    }
  }

  public final com.stub.stub01.a.d a(String paramString)
  {
    while (true)
    {
      try
      {
        new StringBuilder("DownloadDBHelper query(String url) ").append(paramString);
        SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
        String[] arrayOfString1 = { "state", "complete", "size", "create_time" };
        String[] arrayOfString2 = new String[1];
        arrayOfString2[0] = DownloadManager.md5(paramString);
        Cursor localCursor = localSQLiteDatabase.query("app_download", arrayOfString1, "url=?", arrayOfString2, null, null, null);
        if (localCursor == null)
          break label187;
        if (localCursor.moveToNext())
        {
          locald = new com.stub.stub01.a.d();
          locald.a(d.valueOf(localCursor.getString(0)));
          locald.b(localCursor.getLong(1));
          locald.a(localCursor.getLong(2));
          locald.c(localCursor.getLong(3));
          localCursor.close();
          localSQLiteDatabase.close();
          return locald;
        }
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
      com.stub.stub01.a.d locald = null;
      continue;
      label187: locald = null;
    }
  }

  // ERROR //
  public final void a(com.stub.stub01.a.d paramd)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 72	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   9: aload_0
    //   10: invokevirtual 146	java/lang/Object:getClass	()Ljava/lang/Class;
    //   13: invokevirtual 151	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   16: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: ldc 153
    //   21: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload_0
    //   26: invokevirtual 156	com/stub/stub01/adl/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   29: astore 4
    //   31: aload 4
    //   33: invokevirtual 159	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   36: aload 4
    //   38: ldc 89
    //   40: aconst_null
    //   41: aload_1
    //   42: invokestatic 161	com/stub/stub01/adl/a:d	(Lcom/stub/stub01/a/d;)Landroid/content/ContentValues;
    //   45: invokevirtual 165	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   48: pop2
    //   49: aload 4
    //   51: invokevirtual 168	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   54: aload 4
    //   56: invokevirtual 171	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   59: aload 4
    //   61: invokevirtual 132	android/database/sqlite/SQLiteDatabase:close	()V
    //   64: aload_0
    //   65: monitorexit
    //   66: return
    //   67: astore 6
    //   69: aload 6
    //   71: invokevirtual 174	java/lang/Throwable:printStackTrace	()V
    //   74: aload 4
    //   76: invokevirtual 171	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   79: goto -20 -> 59
    //   82: astore_2
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_2
    //   86: athrow
    //   87: astore 5
    //   89: aload 4
    //   91: invokevirtual 171	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   94: aload 5
    //   96: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   36	54	67	java/lang/Throwable
    //   2	36	82	finally
    //   54	64	82	finally
    //   74	79	82	finally
    //   89	97	82	finally
    //   36	54	87	finally
    //   69	74	87	finally
  }

  // ERROR //
  public final void b(com.stub.stub01.a.d paramd)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 156	com/stub/stub01/adl/a:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore_3
    //   7: aload_3
    //   8: invokevirtual 159	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   11: aload_1
    //   12: invokestatic 161	com/stub/stub01/adl/a:d	(Lcom/stub/stub01/a/d;)Landroid/content/ContentValues;
    //   15: astore 6
    //   17: iconst_1
    //   18: anewarray 87	java/lang/String
    //   21: astore 7
    //   23: aload 7
    //   25: iconst_0
    //   26: aload_1
    //   27: invokevirtual 24	com/stub/stub01/a/d:a	()Ljava/lang/String;
    //   30: invokestatic 30	com/stub/stub01/adl/DownloadManager:md5	(Ljava/lang/String;)Ljava/lang/String;
    //   33: aastore
    //   34: aload_3
    //   35: ldc 89
    //   37: aload 6
    //   39: ldc 135
    //   41: aload 7
    //   43: invokevirtual 178	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   46: pop
    //   47: aload_3
    //   48: invokevirtual 168	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   51: aload_3
    //   52: invokevirtual 171	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   55: aload_3
    //   56: invokevirtual 132	android/database/sqlite/SQLiteDatabase:close	()V
    //   59: aload_0
    //   60: monitorexit
    //   61: return
    //   62: astore 5
    //   64: aload 5
    //   66: invokevirtual 174	java/lang/Throwable:printStackTrace	()V
    //   69: aload_3
    //   70: invokevirtual 171	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   73: goto -18 -> 55
    //   76: astore_2
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_2
    //   80: athrow
    //   81: astore 4
    //   83: aload_3
    //   84: invokevirtual 171	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   87: aload 4
    //   89: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   11	51	62	java/lang/Throwable
    //   2	11	76	finally
    //   51	59	76	finally
    //   69	73	76	finally
    //   83	90	76	finally
    //   11	51	81	finally
    //   64	69	81	finally
  }

  public final void c(com.stub.stub01.a.d paramd)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = getWritableDatabase();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = DownloadManager.md5(paramd.a());
      localSQLiteDatabase.delete("app_download", "url=?", arrayOfString);
      localSQLiteDatabase.close();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS app_download(_id INTEGER primary key autoincrement, url TEXT UNIQUE, state TEXT, complete INTEGER, size INTEGER, create_time INTEGER)");
  }

  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.stub.stub01.adl.a
 * JD-Core Version:    0.6.1
 */