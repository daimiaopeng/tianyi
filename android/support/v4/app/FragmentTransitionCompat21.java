package android.support.v4.app;

import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.Transition.TransitionListener;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
class FragmentTransitionCompat21 extends FragmentTransitionImpl
{
  private static boolean hasSimpleTarget(Transition paramTransition)
  {
    boolean bool;
    if ((isNullOrEmpty(paramTransition.getTargetIds())) && (isNullOrEmpty(paramTransition.getTargetNames())) && (isNullOrEmpty(paramTransition.getTargetTypes())))
      bool = false;
    else
      bool = true;
    return bool;
  }

  public void addTarget(Object paramObject, View paramView)
  {
    if (paramObject != null)
      ((Transition)paramObject).addTarget(paramView);
  }

  public void addTargets(Object paramObject, ArrayList<View> paramArrayList)
  {
    Transition localTransition = (Transition)paramObject;
    if (localTransition == null)
      return;
    boolean bool = localTransition instanceof TransitionSet;
    int i = 0;
    if (bool)
    {
      TransitionSet localTransitionSet = (TransitionSet)localTransition;
      int k = localTransitionSet.getTransitionCount();
      while (i < k)
      {
        addTargets(localTransitionSet.getTransitionAt(i), paramArrayList);
        i++;
      }
    }
    if ((!hasSimpleTarget(localTransition)) && (isNullOrEmpty(localTransition.getTargets())))
    {
      int j = paramArrayList.size();
      while (i < j)
      {
        localTransition.addTarget((View)paramArrayList.get(i));
        i++;
      }
    }
  }

  public void beginDelayedTransition(ViewGroup paramViewGroup, Object paramObject)
  {
    TransitionManager.beginDelayedTransition(paramViewGroup, (Transition)paramObject);
  }

  public boolean canHandle(Object paramObject)
  {
    return paramObject instanceof Transition;
  }

  public Object cloneTransition(Object paramObject)
  {
    Transition localTransition;
    if (paramObject != null)
      localTransition = ((Transition)paramObject).clone();
    else
      localTransition = null;
    return localTransition;
  }

  public Object mergeTransitionsInSequence(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object localObject = (Transition)paramObject1;
    Transition localTransition1 = (Transition)paramObject2;
    Transition localTransition2 = (Transition)paramObject3;
    if ((localObject != null) && (localTransition1 != null))
      localObject = new TransitionSet().addTransition((Transition)localObject).addTransition(localTransition1).setOrdering(1);
    else if (localObject == null)
      if (localTransition1 != null)
        localObject = localTransition1;
      else
        localObject = null;
    if (localTransition2 != null)
    {
      TransitionSet localTransitionSet = new TransitionSet();
      if (localObject != null)
        localTransitionSet.addTransition((Transition)localObject);
      localTransitionSet.addTransition(localTransition2);
      return localTransitionSet;
    }
    return localObject;
  }

  public Object mergeTransitionsTogether(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    TransitionSet localTransitionSet = new TransitionSet();
    if (paramObject1 != null)
      localTransitionSet.addTransition((Transition)paramObject1);
    if (paramObject2 != null)
      localTransitionSet.addTransition((Transition)paramObject2);
    if (paramObject3 != null)
      localTransitionSet.addTransition((Transition)paramObject3);
    return localTransitionSet;
  }

  public void removeTarget(Object paramObject, View paramView)
  {
    if (paramObject != null)
      ((Transition)paramObject).removeTarget(paramView);
  }

  public void replaceTargets(Object paramObject, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2)
  {
    Transition localTransition = (Transition)paramObject;
    boolean bool = localTransition instanceof TransitionSet;
    int i = 0;
    if (bool)
    {
      TransitionSet localTransitionSet = (TransitionSet)localTransition;
      int m = localTransitionSet.getTransitionCount();
      while (i < m)
      {
        replaceTargets(localTransitionSet.getTransitionAt(i), paramArrayList1, paramArrayList2);
        i++;
      }
    }
    if (!hasSimpleTarget(localTransition))
    {
      List localList = localTransition.getTargets();
      if ((localList != null) && (localList.size() == paramArrayList1.size()) && (localList.containsAll(paramArrayList1)))
      {
        int j;
        if (paramArrayList2 == null)
        {
          j = 0;
          i = 0;
        }
        else
        {
          j = paramArrayList2.size();
        }
        while (i < j)
        {
          localTransition.addTarget((View)paramArrayList2.get(i));
          i++;
        }
        for (int k = -1 + paramArrayList1.size(); k >= 0; k--)
          localTransition.removeTarget((View)paramArrayList1.get(k));
      }
    }
  }

  public void scheduleHideFragmentView(Object paramObject, final View paramView, final ArrayList<View> paramArrayList)
  {
    ((Transition)paramObject).addListener(new Transition.TransitionListener()
    {
      public void onTransitionCancel(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionEnd(Transition paramAnonymousTransition)
      {
        paramAnonymousTransition.removeListener(this);
        paramView.setVisibility(8);
        int i = paramArrayList.size();
        for (int j = 0; j < i; j++)
          ((View)paramArrayList.get(j)).setVisibility(0);
      }

      public void onTransitionPause(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionResume(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionStart(Transition paramAnonymousTransition)
      {
      }
    });
  }

  public void scheduleRemoveTargets(Object paramObject1, final Object paramObject2, final ArrayList<View> paramArrayList1, final Object paramObject3, final ArrayList<View> paramArrayList2, final Object paramObject4, final ArrayList<View> paramArrayList3)
  {
    Transition localTransition = (Transition)paramObject1;
    Transition.TransitionListener local3 = new Transition.TransitionListener()
    {
      public void onTransitionCancel(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionEnd(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionPause(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionResume(Transition paramAnonymousTransition)
      {
      }

      public void onTransitionStart(Transition paramAnonymousTransition)
      {
        if (paramObject2 != null)
          FragmentTransitionCompat21.this.replaceTargets(paramObject2, paramArrayList1, null);
        if (paramObject3 != null)
          FragmentTransitionCompat21.this.replaceTargets(paramObject3, paramArrayList2, null);
        if (paramObject4 != null)
          FragmentTransitionCompat21.this.replaceTargets(paramObject4, paramArrayList3, null);
      }
    };
    localTransition.addListener(local3);
  }

  public void setEpicenter(Object paramObject, final Rect paramRect)
  {
    if (paramObject != null)
      ((Transition)paramObject).setEpicenterCallback(new Transition.EpicenterCallback()
      {
        public Rect onGetEpicenter(Transition paramAnonymousTransition)
        {
          if ((paramRect != null) && (!paramRect.isEmpty()))
            return paramRect;
          return null;
        }
      });
  }

  public void setEpicenter(Object paramObject, View paramView)
  {
    if (paramView != null)
    {
      Transition localTransition = (Transition)paramObject;
      final Rect localRect = new Rect();
      getBoundsOnScreen(paramView, localRect);
      localTransition.setEpicenterCallback(new Transition.EpicenterCallback()
      {
        public Rect onGetEpicenter(Transition paramAnonymousTransition)
        {
          return localRect;
        }
      });
    }
  }

  public void setSharedElementTargets(Object paramObject, View paramView, ArrayList<View> paramArrayList)
  {
    TransitionSet localTransitionSet = (TransitionSet)paramObject;
    List localList = localTransitionSet.getTargets();
    localList.clear();
    int i = paramArrayList.size();
    for (int j = 0; j < i; j++)
      bfsAddViewChildren(localList, (View)paramArrayList.get(j));
    localList.add(paramView);
    paramArrayList.add(paramView);
    addTargets(localTransitionSet, paramArrayList);
  }

  public void swapSharedElementTargets(Object paramObject, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2)
  {
    TransitionSet localTransitionSet = (TransitionSet)paramObject;
    if (localTransitionSet != null)
    {
      localTransitionSet.getTargets().clear();
      localTransitionSet.getTargets().addAll(paramArrayList2);
      replaceTargets(localTransitionSet, paramArrayList1, paramArrayList2);
    }
  }

  public Object wrapTransitionInSet(Object paramObject)
  {
    if (paramObject == null)
      return null;
    TransitionSet localTransitionSet = new TransitionSet();
    localTransitionSet.addTransition((Transition)paramObject);
    return localTransitionSet;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.FragmentTransitionCompat21
 * JD-Core Version:    0.6.1
 */