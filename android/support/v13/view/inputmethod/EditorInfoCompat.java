package android.support.v13.view.inputmethod;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;

public final class EditorInfoCompat
{
  private static final String CONTENT_MIME_TYPES_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  public static final int IME_FLAG_FORCE_ASCII = -2147483648;
  public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;

  @NonNull
  public static String[] getContentMimeTypes(EditorInfo paramEditorInfo)
  {
    if (Build.VERSION.SDK_INT >= 25)
    {
      String[] arrayOfString2 = paramEditorInfo.contentMimeTypes;
      if (arrayOfString2 == null)
        arrayOfString2 = EMPTY_STRING_ARRAY;
      return arrayOfString2;
    }
    if (paramEditorInfo.extras == null)
      return EMPTY_STRING_ARRAY;
    String[] arrayOfString1 = paramEditorInfo.extras.getStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
    if (arrayOfString1 == null)
      arrayOfString1 = EMPTY_STRING_ARRAY;
    return arrayOfString1;
  }

  public static void setContentMimeTypes(@NonNull EditorInfo paramEditorInfo, @Nullable String[] paramArrayOfString)
  {
    if (Build.VERSION.SDK_INT >= 25)
    {
      paramEditorInfo.contentMimeTypes = paramArrayOfString;
    }
    else
    {
      if (paramEditorInfo.extras == null)
        paramEditorInfo.extras = new Bundle();
      paramEditorInfo.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", paramArrayOfString);
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v13.view.inputmethod.EditorInfoCompat
 * JD-Core Version:    0.6.1
 */