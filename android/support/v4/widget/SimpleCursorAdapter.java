package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCursorAdapter extends ResourceCursorAdapter
{
  private CursorToStringConverter mCursorToStringConverter;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected int[] mFrom;
  String[] mOriginalFrom;
  private int mStringConversionColumn = -1;

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected int[] mTo;
  private ViewBinder mViewBinder;

  @Deprecated
  public SimpleCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super(paramContext, paramInt, paramCursor);
    this.mTo = paramArrayOfInt;
    this.mOriginalFrom = paramArrayOfString;
    findColumns(paramCursor, paramArrayOfString);
  }

  public SimpleCursorAdapter(Context paramContext, int paramInt1, Cursor paramCursor, String[] paramArrayOfString, int[] paramArrayOfInt, int paramInt2)
  {
    super(paramContext, paramInt1, paramCursor, paramInt2);
    this.mTo = paramArrayOfInt;
    this.mOriginalFrom = paramArrayOfString;
    findColumns(paramCursor, paramArrayOfString);
  }

  private void findColumns(Cursor paramCursor, String[] paramArrayOfString)
  {
    if (paramCursor != null)
    {
      int i = paramArrayOfString.length;
      if ((this.mFrom == null) || (this.mFrom.length != i))
        this.mFrom = new int[i];
      for (int j = 0; j < i; j++)
        this.mFrom[j] = paramCursor.getColumnIndexOrThrow(paramArrayOfString[j]);
    }
    this.mFrom = null;
  }

  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    ViewBinder localViewBinder = this.mViewBinder;
    int i = this.mTo.length;
    int[] arrayOfInt1 = this.mFrom;
    int[] arrayOfInt2 = this.mTo;
    for (int j = 0; j < i; j++)
    {
      View localView = paramView.findViewById(arrayOfInt2[j]);
      if (localView != null)
      {
        boolean bool;
        if (localViewBinder != null)
          bool = localViewBinder.setViewValue(localView, paramCursor, arrayOfInt1[j]);
        else
          bool = false;
        if (!bool)
        {
          String str = paramCursor.getString(arrayOfInt1[j]);
          if (str == null)
            str = "";
          if ((localView instanceof TextView))
          {
            setViewText((TextView)localView, str);
          }
          else if ((localView instanceof ImageView))
          {
            setViewImage((ImageView)localView, str);
          }
          else
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(localView.getClass().getName());
            localStringBuilder.append(" is not a ");
            localStringBuilder.append(" view that can be bounds by this SimpleCursorAdapter");
            throw new IllegalStateException(localStringBuilder.toString());
          }
        }
      }
    }
  }

  public void changeCursorAndColumns(Cursor paramCursor, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    this.mOriginalFrom = paramArrayOfString;
    this.mTo = paramArrayOfInt;
    findColumns(paramCursor, this.mOriginalFrom);
    super.changeCursor(paramCursor);
  }

  public CharSequence convertToString(Cursor paramCursor)
  {
    if (this.mCursorToStringConverter != null)
      return this.mCursorToStringConverter.convertToString(paramCursor);
    if (this.mStringConversionColumn > -1)
      return paramCursor.getString(this.mStringConversionColumn);
    return super.convertToString(paramCursor);
  }

  public CursorToStringConverter getCursorToStringConverter()
  {
    return this.mCursorToStringConverter;
  }

  public int getStringConversionColumn()
  {
    return this.mStringConversionColumn;
  }

  public ViewBinder getViewBinder()
  {
    return this.mViewBinder;
  }

  public void setCursorToStringConverter(CursorToStringConverter paramCursorToStringConverter)
  {
    this.mCursorToStringConverter = paramCursorToStringConverter;
  }

  public void setStringConversionColumn(int paramInt)
  {
    this.mStringConversionColumn = paramInt;
  }

  public void setViewBinder(ViewBinder paramViewBinder)
  {
    this.mViewBinder = paramViewBinder;
  }

  // ERROR //
  public void setViewImage(ImageView paramImageView, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokestatic 148	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   5: invokevirtual 151	android/widget/ImageView:setImageResource	(I)V
    //   8: goto +11 -> 19
    //   11: aload_1
    //   12: aload_2
    //   13: invokestatic 157	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   16: invokevirtual 161	android/widget/ImageView:setImageURI	(Landroid/net/Uri;)V
    //   19: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	11	java/lang/NumberFormatException
  }

  public void setViewText(TextView paramTextView, String paramString)
  {
    paramTextView.setText(paramString);
  }

  public Cursor swapCursor(Cursor paramCursor)
  {
    findColumns(paramCursor, this.mOriginalFrom);
    return super.swapCursor(paramCursor);
  }

  public static abstract interface CursorToStringConverter
  {
    public abstract CharSequence convertToString(Cursor paramCursor);
  }

  public static abstract interface ViewBinder
  {
    public abstract boolean setViewValue(View paramView, Cursor paramCursor, int paramInt);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.SimpleCursorAdapter
 * JD-Core Version:    0.6.1
 */