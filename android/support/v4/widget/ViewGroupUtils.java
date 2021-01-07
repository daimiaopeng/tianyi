package android.support.v4.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public class ViewGroupUtils
{
  private static final ThreadLocal<Matrix> sMatrix = new ThreadLocal();
  private static final ThreadLocal<RectF> sRectF = new ThreadLocal();

  public static void getDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect)
  {
    paramRect.set(0, 0, paramView.getWidth(), paramView.getHeight());
    offsetDescendantRect(paramViewGroup, paramView, paramRect);
  }

  private static void offsetDescendantMatrix(ViewParent paramViewParent, View paramView, Matrix paramMatrix)
  {
    ViewParent localViewParent = paramView.getParent();
    if (((localViewParent instanceof View)) && (localViewParent != paramViewParent))
    {
      View localView = (View)localViewParent;
      offsetDescendantMatrix(paramViewParent, localView, paramMatrix);
      paramMatrix.preTranslate(-localView.getScrollX(), -localView.getScrollY());
    }
    paramMatrix.preTranslate(paramView.getLeft(), paramView.getTop());
    if (!paramView.getMatrix().isIdentity())
      paramMatrix.preConcat(paramView.getMatrix());
  }

  static void offsetDescendantRect(ViewGroup paramViewGroup, View paramView, Rect paramRect)
  {
    Matrix localMatrix = (Matrix)sMatrix.get();
    if (localMatrix == null)
    {
      localMatrix = new Matrix();
      sMatrix.set(localMatrix);
    }
    else
    {
      localMatrix.reset();
    }
    offsetDescendantMatrix(paramViewGroup, paramView, localMatrix);
    RectF localRectF = (RectF)sRectF.get();
    if (localRectF == null)
    {
      localRectF = new RectF();
      sRectF.set(localRectF);
    }
    localRectF.set(paramRect);
    localMatrix.mapRect(localRectF);
    paramRect.set((int)(0.5F + localRectF.left), (int)(0.5F + localRectF.top), (int)(0.5F + localRectF.right), (int)(0.5F + localRectF.bottom));
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.widget.ViewGroupUtils
 * JD-Core Version:    0.6.1
 */