package android.support.v4.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class KeyEventDispatcher
{
  private static boolean sActionBarFieldsFetched;
  private static Method sActionBarOnMenuKeyMethod;
  private static boolean sDialogFieldsFetched;
  private static Field sDialogKeyListenerField;

  // ERROR //
  private static boolean actionBarOnMenuKeyEventPre28(ActionBar paramActionBar, KeyEvent paramKeyEvent)
  {
    // Byte code:
    //   0: getstatic 30	android/support/v4/view/KeyEventDispatcher:sActionBarFieldsFetched	Z
    //   3: ifne +32 -> 35
    //   6: aload_0
    //   7: invokevirtual 34	java/lang/Object:getClass	()Ljava/lang/Class;
    //   10: ldc 36
    //   12: iconst_1
    //   13: anewarray 38	java/lang/Class
    //   16: dup
    //   17: iconst_0
    //   18: ldc 40
    //   20: aastore
    //   21: invokevirtual 44	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   24: putstatic 46	android/support/v4/view/KeyEventDispatcher:sActionBarOnMenuKeyMethod	Ljava/lang/reflect/Method;
    //   27: goto +4 -> 31
    //   30: pop
    //   31: iconst_1
    //   32: putstatic 30	android/support/v4/view/KeyEventDispatcher:sActionBarFieldsFetched	Z
    //   35: getstatic 46	android/support/v4/view/KeyEventDispatcher:sActionBarOnMenuKeyMethod	Ljava/lang/reflect/Method;
    //   38: ifnull +27 -> 65
    //   41: getstatic 46	android/support/v4/view/KeyEventDispatcher:sActionBarOnMenuKeyMethod	Ljava/lang/reflect/Method;
    //   44: aload_0
    //   45: iconst_1
    //   46: anewarray 4	java/lang/Object
    //   49: dup
    //   50: iconst_0
    //   51: aload_1
    //   52: aastore
    //   53: invokevirtual 52	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   56: checkcast 54	java/lang/Boolean
    //   59: invokevirtual 58	java/lang/Boolean:booleanValue	()Z
    //   62: istore_2
    //   63: iload_2
    //   64: ireturn
    //   65: iconst_0
    //   66: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   6	30	30	java/lang/NoSuchMethodException
    //   41	63	65	java/lang/IllegalAccessException
    //   41	63	65	java/lang/reflect/InvocationTargetException
  }

  private static boolean activitySuperDispatchKeyEventPre28(Activity paramActivity, KeyEvent paramKeyEvent)
  {
    paramActivity.onUserInteraction();
    Window localWindow = paramActivity.getWindow();
    if (localWindow.hasFeature(8))
    {
      ActionBar localActionBar = paramActivity.getActionBar();
      if ((paramKeyEvent.getKeyCode() == 82) && (localActionBar != null) && (actionBarOnMenuKeyEventPre28(localActionBar, paramKeyEvent)))
        return true;
    }
    if (localWindow.superDispatchKeyEvent(paramKeyEvent))
      return true;
    View localView = localWindow.getDecorView();
    if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(localView, paramKeyEvent))
      return true;
    KeyEvent.DispatcherState localDispatcherState;
    if (localView != null)
      localDispatcherState = localView.getKeyDispatcherState();
    else
      localDispatcherState = null;
    return paramKeyEvent.dispatch(paramActivity, localDispatcherState, paramActivity);
  }

  private static boolean dialogSuperDispatchKeyEventPre28(Dialog paramDialog, KeyEvent paramKeyEvent)
  {
    DialogInterface.OnKeyListener localOnKeyListener = getDialogKeyListenerPre28(paramDialog);
    if ((localOnKeyListener != null) && (localOnKeyListener.onKey(paramDialog, paramKeyEvent.getKeyCode(), paramKeyEvent)))
      return true;
    Window localWindow = paramDialog.getWindow();
    if (localWindow.superDispatchKeyEvent(paramKeyEvent))
      return true;
    View localView = localWindow.getDecorView();
    if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(localView, paramKeyEvent))
      return true;
    KeyEvent.DispatcherState localDispatcherState;
    if (localView != null)
      localDispatcherState = localView.getKeyDispatcherState();
    else
      localDispatcherState = null;
    return paramKeyEvent.dispatch(paramDialog, localDispatcherState, paramDialog);
  }

  public static boolean dispatchBeforeHierarchy(@NonNull View paramView, @NonNull KeyEvent paramKeyEvent)
  {
    return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(paramView, paramKeyEvent);
  }

  public static boolean dispatchKeyEvent(@NonNull Component paramComponent, @Nullable View paramView, @Nullable Window.Callback paramCallback, @NonNull KeyEvent paramKeyEvent)
  {
    if (paramComponent == null)
      return false;
    if (Build.VERSION.SDK_INT >= 28)
      return paramComponent.superDispatchKeyEvent(paramKeyEvent);
    if ((paramCallback instanceof Activity))
      return activitySuperDispatchKeyEventPre28((Activity)paramCallback, paramKeyEvent);
    if ((paramCallback instanceof Dialog))
      return dialogSuperDispatchKeyEventPre28((Dialog)paramCallback, paramKeyEvent);
    boolean bool2;
    if ((paramView == null) || (!ViewCompat.dispatchUnhandledKeyEventBeforeCallback(paramView, paramKeyEvent)))
    {
      boolean bool1 = paramComponent.superDispatchKeyEvent(paramKeyEvent);
      bool2 = false;
      if (!bool1);
    }
    else
    {
      bool2 = true;
    }
    return bool2;
  }

  // ERROR //
  private static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog paramDialog)
  {
    // Byte code:
    //   0: getstatic 149	android/support/v4/view/KeyEventDispatcher:sDialogFieldsFetched	Z
    //   3: ifne +28 -> 31
    //   6: ldc 123
    //   8: ldc 151
    //   10: invokevirtual 155	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   13: putstatic 157	android/support/v4/view/KeyEventDispatcher:sDialogKeyListenerField	Ljava/lang/reflect/Field;
    //   16: getstatic 157	android/support/v4/view/KeyEventDispatcher:sDialogKeyListenerField	Ljava/lang/reflect/Field;
    //   19: iconst_1
    //   20: invokevirtual 163	java/lang/reflect/Field:setAccessible	(Z)V
    //   23: goto +4 -> 27
    //   26: pop
    //   27: iconst_1
    //   28: putstatic 149	android/support/v4/view/KeyEventDispatcher:sDialogFieldsFetched	Z
    //   31: getstatic 157	android/support/v4/view/KeyEventDispatcher:sDialogKeyListenerField	Ljava/lang/reflect/Field;
    //   34: ifnull +16 -> 50
    //   37: getstatic 157	android/support/v4/view/KeyEventDispatcher:sDialogKeyListenerField	Ljava/lang/reflect/Field;
    //   40: aload_0
    //   41: invokevirtual 167	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   44: checkcast 117	android/content/DialogInterface$OnKeyListener
    //   47: astore_1
    //   48: aload_1
    //   49: areturn
    //   50: aconst_null
    //   51: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   6	26	26	java/lang/NoSuchFieldException
    //   37	48	50	java/lang/IllegalAccessException
  }

  public static abstract interface Component
  {
    public abstract boolean superDispatchKeyEvent(KeyEvent paramKeyEvent);
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.view.KeyEventDispatcher
 * JD-Core Version:    0.6.1
 */