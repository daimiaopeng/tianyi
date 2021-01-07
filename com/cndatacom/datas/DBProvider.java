package com.cndatacom.datas;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class DBProvider extends ContentProvider
{
  public final int a = 1;
  public final int b = 2;
  public String c = "com.cndatacom.utils.database";
  public String d = "";
  public Uri e;
  private a f = null;
  private SQLiteDatabase g = null;
  private UriMatcher h = null;

  public DBProvider()
  {
    StringBuilder localStringBuilder = new StringBuilder("content://");
    localStringBuilder.append(this.c);
    localStringBuilder.append("/");
    localStringBuilder.append(this.d);
    this.e = Uri.parse(localStringBuilder.toString());
  }

  private String a(Uri paramUri, String paramString)
  {
    String str1 = String.valueOf(ContentUris.parseId(paramUri));
    String str2;
    if (TextUtils.isEmpty(paramString))
    {
      StringBuilder localStringBuilder1 = new StringBuilder(" [id]= ");
      localStringBuilder1.append(str1);
      str2 = localStringBuilder1.toString();
    }
    else
    {
      StringBuilder localStringBuilder2 = new StringBuilder(" [id]= ");
      localStringBuilder2.append(str1);
      localStringBuilder2.append(" AND(");
      localStringBuilder2.append(paramString);
      localStringBuilder2.append(") ");
      str2 = localStringBuilder2.toString();
    }
    return str2;
  }

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    int i;
    switch (this.h.match(paramUri))
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder("UnknownURI");
      localStringBuilder.append(paramUri);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 2:
      String str = a(paramUri, paramString);
      i = this.g.delete(this.d, str, paramArrayOfString);
      break;
    case 1:
      i = this.g.delete(this.d, paramString, paramArrayOfString);
    }
    getContext().getContentResolver().notifyChange(paramUri, null);
    return i;
  }

  public String getType(Uri paramUri)
  {
    switch (this.h.match(paramUri))
    {
    default:
      StringBuilder localStringBuilder3 = new StringBuilder("UnknownURI");
      localStringBuilder3.append(paramUri);
      throw new IllegalArgumentException(localStringBuilder3.toString());
    case 2:
      StringBuilder localStringBuilder2 = new StringBuilder("vnd.android.cursor.item/vnd.studio.android.");
      localStringBuilder2.append(this.d);
      return localStringBuilder2.toString();
    case 1:
    }
    StringBuilder localStringBuilder1 = new StringBuilder("vnd.android.cursor.dir/vnd.studio.android.");
    localStringBuilder1.append(this.d);
    return localStringBuilder1.toString();
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    if (this.h.match(paramUri) != 1)
    {
      StringBuilder localStringBuilder1 = new StringBuilder("UnknownURI");
      localStringBuilder1.append(paramUri);
      throw new IllegalArgumentException(localStringBuilder1.toString());
    }
    long l = this.g.insert(this.d, "[id]", paramContentValues);
    if (l > 0L)
    {
      Uri localUri = ContentUris.withAppendedId(paramUri, l);
      getContext().getContentResolver().notifyChange(localUri, null);
      return localUri;
    }
    StringBuilder localStringBuilder2 = new StringBuilder("Failedtoinsertrowinto");
    localStringBuilder2.append(paramUri);
    throw new SQLException(localStringBuilder2.toString());
  }

  public boolean onCreate()
  {
    Context localContext = getContext();
    int i = 1;
    this.f = new a(localContext, "", i);
    this.g = this.f.getWritableDatabase();
    this.h = new UriMatcher(-1);
    this.h.addURI(this.c, this.d, i);
    UriMatcher localUriMatcher = this.h;
    String str = this.c;
    StringBuilder localStringBuilder = new StringBuilder(String.valueOf(this.d));
    localStringBuilder.append("/#");
    localUriMatcher.addURI(str, localStringBuilder.toString(), 2);
    if (this.f == null)
      i = 0;
    return i;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    switch (this.h.match(paramUri))
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder("UnknownURI");
      localStringBuilder.append(paramUri);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 2:
      String str = a(paramUri, paramString1);
      return this.g.query(this.d, paramArrayOfString1, str, paramArrayOfString2, null, null, paramString2);
    case 1:
    }
    return this.g.query(this.d, paramArrayOfString1, paramString1, paramArrayOfString2, null, null, paramString2);
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    int i;
    switch (this.h.match(paramUri))
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder("UnknownURI");
      localStringBuilder.append(paramUri);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 2:
      String str = a(paramUri, paramString);
      i = this.g.update(this.d, paramContentValues, str, paramArrayOfString);
      break;
    case 1:
      i = this.g.update(this.d, paramContentValues, paramString, paramArrayOfString);
    }
    getContext().getContentResolver().notifyChange(paramUri, null);
    return i;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     com.cndatacom.datas.DBProvider
 * JD-Core Version:    0.6.1
 */