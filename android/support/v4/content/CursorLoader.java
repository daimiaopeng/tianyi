package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader extends AsyncTaskLoader<Cursor>
{
  CancellationSignal mCancellationSignal;
  Cursor mCursor;
  final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
  String[] mProjection;
  String mSelection;
  String[] mSelectionArgs;
  String mSortOrder;
  Uri mUri;

  public CursorLoader(@NonNull Context paramContext)
  {
    super(paramContext);
  }

  public CursorLoader(@NonNull Context paramContext, @NonNull Uri paramUri, @Nullable String[] paramArrayOfString1, @Nullable String paramString1, @Nullable String[] paramArrayOfString2, @Nullable String paramString2)
  {
    super(paramContext);
    this.mUri = paramUri;
    this.mProjection = paramArrayOfString1;
    this.mSelection = paramString1;
    this.mSelectionArgs = paramArrayOfString2;
    this.mSortOrder = paramString2;
  }

  public void cancelLoadInBackground()
  {
    super.cancelLoadInBackground();
    try
    {
      if (this.mCancellationSignal != null)
        this.mCancellationSignal.cancel();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void deliverResult(Cursor paramCursor)
  {
    if (isReset())
    {
      if (paramCursor != null)
        paramCursor.close();
      return;
    }
    Cursor localCursor = this.mCursor;
    this.mCursor = paramCursor;
    if (isStarted())
      super.deliverResult(paramCursor);
    if ((localCursor != null) && (localCursor != paramCursor) && (!localCursor.isClosed()))
      localCursor.close();
  }

  @Deprecated
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mUri=");
    paramPrintWriter.println(this.mUri);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mProjection=");
    paramPrintWriter.println(Arrays.toString(this.mProjection));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelection=");
    paramPrintWriter.println(this.mSelection);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelectionArgs=");
    paramPrintWriter.println(Arrays.toString(this.mSelectionArgs));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSortOrder=");
    paramPrintWriter.println(this.mSortOrder);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCursor=");
    paramPrintWriter.println(this.mCursor);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mContentChanged=");
    paramPrintWriter.println(this.mContentChanged);
  }

  @Nullable
  public String[] getProjection()
  {
    return this.mProjection;
  }

  @Nullable
  public String getSelection()
  {
    return this.mSelection;
  }

  @Nullable
  public String[] getSelectionArgs()
  {
    return this.mSelectionArgs;
  }

  @Nullable
  public String getSortOrder()
  {
    return this.mSortOrder;
  }

  @NonNull
  public Uri getUri()
  {
    return this.mUri;
  }

  // ERROR //
  public Cursor loadInBackground()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 137	android/support/v4/content/CursorLoader:isLoadInBackgroundCanceled	()Z
    //   6: ifeq +11 -> 17
    //   9: new 139	android/support/v4/os/OperationCanceledException
    //   12: dup
    //   13: invokespecial 141	android/support/v4/os/OperationCanceledException:<init>	()V
    //   16: athrow
    //   17: aload_0
    //   18: new 52	android/support/v4/os/CancellationSignal
    //   21: dup
    //   22: invokespecial 142	android/support/v4/os/CancellationSignal:<init>	()V
    //   25: putfield 50	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_0
    //   31: invokevirtual 146	android/support/v4/content/CursorLoader:getContext	()Landroid/content/Context;
    //   34: invokevirtual 152	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   37: aload_0
    //   38: getfield 36	android/support/v4/content/CursorLoader:mUri	Landroid/net/Uri;
    //   41: aload_0
    //   42: getfield 38	android/support/v4/content/CursorLoader:mProjection	[Ljava/lang/String;
    //   45: aload_0
    //   46: getfield 40	android/support/v4/content/CursorLoader:mSelection	Ljava/lang/String;
    //   49: aload_0
    //   50: getfield 42	android/support/v4/content/CursorLoader:mSelectionArgs	[Ljava/lang/String;
    //   53: aload_0
    //   54: getfield 44	android/support/v4/content/CursorLoader:mSortOrder	Ljava/lang/String;
    //   57: aload_0
    //   58: getfield 50	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   61: invokestatic 158	android/support/v4/content/ContentResolverCompat:query	(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/support/v4/os/CancellationSignal;)Landroid/database/Cursor;
    //   64: astore 4
    //   66: aload 4
    //   68: ifnull +37 -> 105
    //   71: aload 4
    //   73: invokeinterface 162 1 0
    //   78: pop
    //   79: aload 4
    //   81: aload_0
    //   82: getfield 32	android/support/v4/content/CursorLoader:mObserver	Landroid/support/v4/content/Loader$ForceLoadContentObserver;
    //   85: invokeinterface 166 2 0
    //   90: goto +15 -> 105
    //   93: astore 6
    //   95: aload 4
    //   97: invokeinterface 66 1 0
    //   102: aload 6
    //   104: athrow
    //   105: aload_0
    //   106: monitorenter
    //   107: aload_0
    //   108: aconst_null
    //   109: putfield 50	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   112: aload_0
    //   113: monitorexit
    //   114: aload 4
    //   116: areturn
    //   117: astore 5
    //   119: aload_0
    //   120: monitorexit
    //   121: aload 5
    //   123: athrow
    //   124: astore_2
    //   125: aload_0
    //   126: monitorenter
    //   127: aload_0
    //   128: aconst_null
    //   129: putfield 50	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   132: aload_0
    //   133: monitorexit
    //   134: aload_2
    //   135: athrow
    //   136: astore_3
    //   137: aload_0
    //   138: monitorexit
    //   139: aload_3
    //   140: athrow
    //   141: astore_1
    //   142: aload_0
    //   143: monitorexit
    //   144: aload_1
    //   145: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   71	90	93	java/lang/RuntimeException
    //   107	121	117	finally
    //   30	66	124	finally
    //   71	90	124	finally
    //   95	105	124	finally
    //   127	134	136	finally
    //   137	139	136	finally
    //   2	30	141	finally
    //   142	144	141	finally
  }

  public void onCanceled(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed()))
      paramCursor.close();
  }

  protected void onReset()
  {
    super.onReset();
    onStopLoading();
    if ((this.mCursor != null) && (!this.mCursor.isClosed()))
      this.mCursor.close();
    this.mCursor = null;
  }

  protected void onStartLoading()
  {
    if (this.mCursor != null)
      deliverResult(this.mCursor);
    if ((takeContentChanged()) || (this.mCursor == null))
      forceLoad();
  }

  protected void onStopLoading()
  {
    cancelLoad();
  }

  public void setProjection(@Nullable String[] paramArrayOfString)
  {
    this.mProjection = paramArrayOfString;
  }

  public void setSelection(@Nullable String paramString)
  {
    this.mSelection = paramString;
  }

  public void setSelectionArgs(@Nullable String[] paramArrayOfString)
  {
    this.mSelectionArgs = paramArrayOfString;
  }

  public void setSortOrder(@Nullable String paramString)
  {
    this.mSortOrder = paramString;
  }

  public void setUri(@NonNull Uri paramUri)
  {
    this.mUri = paramUri;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.CursorLoader
 * JD-Core Version:    0.6.1
 */