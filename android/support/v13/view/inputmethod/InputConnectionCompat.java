package android.support.v13.view.inputmethod;

import android.content.ClipDescription;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

public final class InputConnectionCompat
{
  private static final String COMMIT_CONTENT_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
  private static final String COMMIT_CONTENT_CONTENT_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
  private static final String COMMIT_CONTENT_DESCRIPTION_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
  private static final String COMMIT_CONTENT_FLAGS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
  private static final String COMMIT_CONTENT_LINK_URI_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
  private static final String COMMIT_CONTENT_OPTS_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
  private static final String COMMIT_CONTENT_RESULT_RECEIVER = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
  public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;

  public static boolean commitContent(@NonNull InputConnection paramInputConnection, @NonNull EditorInfo paramEditorInfo, @NonNull InputContentInfoCompat paramInputContentInfoCompat, int paramInt, @Nullable Bundle paramBundle)
  {
    ClipDescription localClipDescription = paramInputContentInfoCompat.getDescription();
    String[] arrayOfString = EditorInfoCompat.getContentMimeTypes(paramEditorInfo);
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (localClipDescription.hasMimeType(arrayOfString[j]))
      {
        k = 1;
        break label55;
      }
    int k = 0;
    label55: if (k == 0)
      return false;
    if (Build.VERSION.SDK_INT >= 25)
      return paramInputConnection.commitContent((InputContentInfo)paramInputContentInfoCompat.unwrap(), paramInt, paramBundle);
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI", paramInputContentInfoCompat.getContentUri());
    localBundle.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION", paramInputContentInfoCompat.getDescription());
    localBundle.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI", paramInputContentInfoCompat.getLinkUri());
    localBundle.putInt("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS", paramInt);
    localBundle.putParcelable("android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS", paramBundle);
    return paramInputConnection.performPrivateCommand("android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT", localBundle);
  }

  @NonNull
  public static InputConnection createWrapper(@NonNull InputConnection paramInputConnection, @NonNull EditorInfo paramEditorInfo, @NonNull final OnCommitContentListener paramOnCommitContentListener)
  {
    if (paramInputConnection == null)
      throw new IllegalArgumentException("inputConnection must be non-null");
    if (paramEditorInfo == null)
      throw new IllegalArgumentException("editorInfo must be non-null");
    if (paramOnCommitContentListener == null)
      throw new IllegalArgumentException("onCommitContentListener must be non-null");
    if (Build.VERSION.SDK_INT >= 25)
      return new InputConnectionWrapper(paramInputConnection, false)
      {
        public boolean commitContent(InputContentInfo paramAnonymousInputContentInfo, int paramAnonymousInt, Bundle paramAnonymousBundle)
        {
          if (paramOnCommitContentListener.onCommitContent(InputContentInfoCompat.wrap(paramAnonymousInputContentInfo), paramAnonymousInt, paramAnonymousBundle))
            return true;
          return super.commitContent(paramAnonymousInputContentInfo, paramAnonymousInt, paramAnonymousBundle);
        }
      };
    if (EditorInfoCompat.getContentMimeTypes(paramEditorInfo).length == 0)
      return paramInputConnection;
    return new InputConnectionWrapper(paramInputConnection, false)
    {
      public boolean performPrivateCommand(String paramAnonymousString, Bundle paramAnonymousBundle)
      {
        if (InputConnectionCompat.handlePerformPrivateCommand(paramAnonymousString, paramAnonymousBundle, paramOnCommitContentListener))
          return true;
        return super.performPrivateCommand(paramAnonymousString, paramAnonymousBundle);
      }
    };
  }

  // ERROR //
  static boolean handlePerformPrivateCommand(@Nullable String paramString, @NonNull Bundle paramBundle, @NonNull OnCommitContentListener paramOnCommitContentListener)
  {
    // Byte code:
    //   0: ldc 8
    //   2: aload_0
    //   3: invokestatic 123	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   6: ifne +5 -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: aload_1
    //   12: ifnonnull +5 -> 17
    //   15: iconst_0
    //   16: ireturn
    //   17: aload_1
    //   18: ldc 26
    //   20: invokevirtual 127	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   23: checkcast 129	android/os/ResultReceiver
    //   26: astore 4
    //   28: aload_1
    //   29: ldc 11
    //   31: invokevirtual 127	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   34: checkcast 131	android/net/Uri
    //   37: astore 5
    //   39: aload_1
    //   40: ldc 14
    //   42: invokevirtual 127	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   45: checkcast 52	android/content/ClipDescription
    //   48: astore 6
    //   50: aload_1
    //   51: ldc 20
    //   53: invokevirtual 127	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   56: checkcast 131	android/net/Uri
    //   59: astore 7
    //   61: aload_1
    //   62: ldc 17
    //   64: invokevirtual 135	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   67: istore 8
    //   69: aload_1
    //   70: ldc 23
    //   72: invokevirtual 127	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   75: checkcast 74	android/os/Bundle
    //   78: astore 9
    //   80: aload_2
    //   81: new 40	android/support/v13/view/inputmethod/InputContentInfoCompat
    //   84: dup
    //   85: aload 5
    //   87: aload 6
    //   89: aload 7
    //   91: invokespecial 138	android/support/v13/view/inputmethod/InputContentInfoCompat:<init>	(Landroid/net/Uri;Landroid/content/ClipDescription;Landroid/net/Uri;)V
    //   94: iload 8
    //   96: aload 9
    //   98: invokeinterface 144 4 0
    //   103: istore 10
    //   105: aload 4
    //   107: ifnull +11 -> 118
    //   110: aload 4
    //   112: iload 10
    //   114: aconst_null
    //   115: invokevirtual 148	android/os/ResultReceiver:send	(ILandroid/os/Bundle;)V
    //   118: iload 10
    //   120: ireturn
    //   121: astore_3
    //   122: goto +7 -> 129
    //   125: astore_3
    //   126: aconst_null
    //   127: astore 4
    //   129: aload 4
    //   131: ifnull +10 -> 141
    //   134: aload 4
    //   136: iconst_0
    //   137: aconst_null
    //   138: invokevirtual 148	android/os/ResultReceiver:send	(ILandroid/os/Bundle;)V
    //   141: aload_3
    //   142: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   28	105	121	finally
    //   17	28	125	finally
  }

  public static abstract interface OnCommitContentListener
  {
    public abstract boolean onCommitContent(InputContentInfoCompat paramInputContentInfoCompat, int paramInt, Bundle paramBundle);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v13.view.inputmethod.InputConnectionCompat
 * JD-Core Version:    0.6.1
 */