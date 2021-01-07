package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition
{
  private static final int[] INVERSE_OPS = { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  private static final FragmentTransitionImpl PLATFORM_IMPL = localFragmentTransitionCompat21;
  private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();

  static
  {
    FragmentTransitionCompat21 localFragmentTransitionCompat21;
    if (Build.VERSION.SDK_INT >= 21)
      localFragmentTransitionCompat21 = new FragmentTransitionCompat21();
    else
      localFragmentTransitionCompat21 = null;
  }

  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection)
  {
    for (int i = -1 + paramArrayMap.size(); i >= 0; i--)
    {
      View localView = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(localView)))
        paramArrayList.add(localView);
    }
  }

  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Fragment localFragment = paramOp.fragment;
    if (localFragment == null)
      return;
    int i = localFragment.mContainerId;
    if (i == 0)
      return;
    int j;
    if (paramBoolean1)
      j = INVERSE_OPS[paramOp.cmd];
    else
      j = paramOp.cmd;
    int k = 0;
    if (j != 1)
      switch (j)
      {
      default:
      case 5:
      case 4:
      case 3:
      case 6:
      case 7:
      }
    int n;
    int i1;
    for (int m = 0; ; m = 1)
    {
      n = 0;
      i1 = 0;
      break;
      if (paramBoolean2)
      {
        if ((!localFragment.mHiddenChanged) || (localFragment.mHidden) || (!localFragment.mAdded))
          break label323;
      }
      else
      {
        bool = localFragment.mHidden;
        break label326;
        if (paramBoolean2)
        {
          if ((!localFragment.mHiddenChanged) || (!localFragment.mAdded) || (!localFragment.mHidden));
        }
        else
          while (true)
          {
            break;
            if ((!localFragment.mAdded) || (localFragment.mHidden))
              break label245;
            continue;
            if (!paramBoolean2)
              break label251;
            if ((localFragment.mAdded) || (localFragment.mView == null) || (localFragment.mView.getVisibility() != 0) || (localFragment.mPostponedAlpha < 0.0F))
              break label245;
          }
        int i2;
        while (true)
        {
          i2 = 1;
          break;
          label245: label251: 
          do
          {
            i2 = 0;
            break;
          }
          while ((!localFragment.mAdded) || (localFragment.mHidden));
        }
        i1 = i2;
        n = 1;
        m = 0;
        k = 0;
        break;
        if (paramBoolean2)
        {
          bool = localFragment.mIsNewlyAdded;
          break label326;
        }
        if ((localFragment.mAdded) || (localFragment.mHidden))
          break label323;
      }
      boolean bool = true;
      break label326;
      label323: bool = false;
      label326: k = bool;
    }
    FragmentContainerTransition localFragmentContainerTransition1 = (FragmentContainerTransition)paramSparseArray.get(i);
    if (k != 0)
    {
      localFragmentContainerTransition1 = ensureContainer(localFragmentContainerTransition1, paramSparseArray, i);
      localFragmentContainerTransition1.lastIn = localFragment;
      localFragmentContainerTransition1.lastInIsPop = paramBoolean1;
      localFragmentContainerTransition1.lastInTransaction = paramBackStackRecord;
    }
    FragmentContainerTransition localFragmentContainerTransition2 = localFragmentContainerTransition1;
    if ((!paramBoolean2) && (m != 0))
    {
      if ((localFragmentContainerTransition2 != null) && (localFragmentContainerTransition2.firstOut == localFragment))
        localFragmentContainerTransition2.firstOut = null;
      FragmentManagerImpl localFragmentManagerImpl = paramBackStackRecord.mManager;
      if ((localFragment.mState < 1) && (localFragmentManagerImpl.mCurState >= 1) && (!paramBackStackRecord.mReorderingAllowed))
      {
        localFragmentManagerImpl.makeActive(localFragment);
        localFragmentManagerImpl.moveToState(localFragment, 1, 0, 0, false);
      }
    }
    if ((i1 != 0) && ((localFragmentContainerTransition2 == null) || (localFragmentContainerTransition2.firstOut == null)))
    {
      localFragmentContainerTransition2 = ensureContainer(localFragmentContainerTransition2, paramSparseArray, i);
      localFragmentContainerTransition2.firstOut = localFragment;
      localFragmentContainerTransition2.firstOutIsPop = paramBoolean1;
      localFragmentContainerTransition2.firstOutTransaction = paramBackStackRecord;
    }
    if ((!paramBoolean2) && (n != 0) && (localFragmentContainerTransition2 != null) && (localFragmentContainerTransition2.lastIn == localFragment))
      localFragmentContainerTransition2.lastIn = null;
  }

  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean)
  {
    int i = paramBackStackRecord.mOps.size();
    for (int j = 0; j < i; j++)
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)paramBackStackRecord.mOps.get(j), paramSparseArray, false, paramBoolean);
  }

  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3)
  {
    ArrayMap localArrayMap = new ArrayMap();
    for (int i = paramInt3 - 1; i >= paramInt2; i--)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(i);
      if (localBackStackRecord.interactsWith(paramInt1))
      {
        boolean bool = ((Boolean)paramArrayList1.get(i)).booleanValue();
        if (localBackStackRecord.mSharedElementSourceNames != null)
        {
          int j = localBackStackRecord.mSharedElementSourceNames.size();
          ArrayList localArrayList2;
          Object localObject;
          if (bool)
          {
            localArrayList2 = localBackStackRecord.mSharedElementSourceNames;
            localObject = localBackStackRecord.mSharedElementTargetNames;
          }
          else
          {
            ArrayList localArrayList1 = localBackStackRecord.mSharedElementSourceNames;
            localArrayList2 = localBackStackRecord.mSharedElementTargetNames;
            localObject = localArrayList1;
          }
          for (int k = 0; k < j; k++)
          {
            String str1 = (String)((ArrayList)localObject).get(k);
            String str2 = (String)localArrayList2.get(k);
            String str3 = (String)localArrayMap.remove(str2);
            if (str3 != null)
              localArrayMap.put(str1, str3);
            else
              localArrayMap.put(str1, str2);
          }
        }
      }
    }
    return localArrayMap;
  }

  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean)
  {
    if (!paramBackStackRecord.mManager.mContainer.onHasView())
      return;
    for (int i = paramBackStackRecord.mOps.size() - 1; i >= 0; i--)
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)paramBackStackRecord.mOps.get(i), paramSparseArray, true, paramBoolean);
  }

  static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2)
  {
    SharedElementCallback localSharedElementCallback;
    if (paramBoolean1)
      localSharedElementCallback = paramFragment2.getEnterTransitionCallback();
    else
      localSharedElementCallback = paramFragment1.getEnterTransitionCallback();
    if (localSharedElementCallback != null)
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int i = 0;
      int j;
      if (paramArrayMap == null)
      {
        i = 0;
        j = 0;
      }
      else
      {
        j = paramArrayMap.size();
      }
      while (i < j)
      {
        localArrayList2.add(paramArrayMap.keyAt(i));
        localArrayList1.add(paramArrayMap.valueAt(i));
        i++;
      }
      if (paramBoolean2)
        localSharedElementCallback.onSharedElementStart(localArrayList2, localArrayList1, null);
      else
        localSharedElementCallback.onSharedElementEnd(localArrayList2, localArrayList1, null);
    }
  }

  private static boolean canHandleAll(FragmentTransitionImpl paramFragmentTransitionImpl, List<Object> paramList)
  {
    int i = paramList.size();
    for (int j = 0; j < i; j++)
      if (!paramFragmentTransitionImpl.canHandle(paramList.get(j)))
        return false;
    return true;
  }

  static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    Fragment localFragment = paramFragmentContainerTransition.lastIn;
    View localView1 = localFragment.getView();
    if ((!paramArrayMap.isEmpty()) && (paramObject != null) && (localView1 != null))
    {
      ArrayMap localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, localView1);
      BackStackRecord localBackStackRecord = paramFragmentContainerTransition.lastInTransaction;
      SharedElementCallback localSharedElementCallback;
      ArrayList localArrayList;
      if (paramFragmentContainerTransition.lastInIsPop)
      {
        localSharedElementCallback = localFragment.getExitTransitionCallback();
        localArrayList = localBackStackRecord.mSharedElementSourceNames;
      }
      else
      {
        localSharedElementCallback = localFragment.getEnterTransitionCallback();
        localArrayList = localBackStackRecord.mSharedElementTargetNames;
      }
      if (localArrayList != null)
      {
        localArrayMap.retainAll(localArrayList);
        localArrayMap.retainAll(paramArrayMap.values());
      }
      if (localSharedElementCallback != null)
      {
        localSharedElementCallback.onMapSharedElements(localArrayList, localArrayMap);
        for (int i = -1 + localArrayList.size(); i >= 0; i--)
        {
          String str1 = (String)localArrayList.get(i);
          View localView2 = (View)localArrayMap.get(str1);
          if (localView2 == null)
          {
            String str3 = findKeyForValue(paramArrayMap, str1);
            if (str3 != null)
              paramArrayMap.remove(str3);
          }
          else if (!str1.equals(ViewCompat.getTransitionName(localView2)))
          {
            String str2 = findKeyForValue(paramArrayMap, str1);
            if (str2 != null)
              paramArrayMap.put(str2, ViewCompat.getTransitionName(localView2));
          }
        }
      }
      retainValues(paramArrayMap, localArrayMap);
      return localArrayMap;
    }
    paramArrayMap.clear();
    return null;
  }

  private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    if ((!paramArrayMap.isEmpty()) && (paramObject != null))
    {
      Fragment localFragment = paramFragmentContainerTransition.firstOut;
      ArrayMap localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, localFragment.getView());
      BackStackRecord localBackStackRecord = paramFragmentContainerTransition.firstOutTransaction;
      SharedElementCallback localSharedElementCallback;
      ArrayList localArrayList;
      if (paramFragmentContainerTransition.firstOutIsPop)
      {
        localSharedElementCallback = localFragment.getEnterTransitionCallback();
        localArrayList = localBackStackRecord.mSharedElementTargetNames;
      }
      else
      {
        localSharedElementCallback = localFragment.getExitTransitionCallback();
        localArrayList = localBackStackRecord.mSharedElementSourceNames;
      }
      localArrayMap.retainAll(localArrayList);
      if (localSharedElementCallback != null)
      {
        localSharedElementCallback.onMapSharedElements(localArrayList, localArrayMap);
        for (int i = -1 + localArrayList.size(); i >= 0; i--)
        {
          String str1 = (String)localArrayList.get(i);
          View localView = (View)localArrayMap.get(str1);
          if (localView == null)
          {
            paramArrayMap.remove(str1);
          }
          else if (!str1.equals(ViewCompat.getTransitionName(localView)))
          {
            String str2 = (String)paramArrayMap.remove(str1);
            paramArrayMap.put(ViewCompat.getTransitionName(localView), str2);
          }
        }
      }
      paramArrayMap.retainAll(localArrayMap.keySet());
      return localArrayMap;
    }
    paramArrayMap.clear();
    return null;
  }

  private static FragmentTransitionImpl chooseImpl(Fragment paramFragment1, Fragment paramFragment2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFragment1 != null)
    {
      Object localObject4 = paramFragment1.getExitTransition();
      if (localObject4 != null)
        localArrayList.add(localObject4);
      Object localObject5 = paramFragment1.getReturnTransition();
      if (localObject5 != null)
        localArrayList.add(localObject5);
      Object localObject6 = paramFragment1.getSharedElementReturnTransition();
      if (localObject6 != null)
        localArrayList.add(localObject6);
    }
    if (paramFragment2 != null)
    {
      Object localObject1 = paramFragment2.getEnterTransition();
      if (localObject1 != null)
        localArrayList.add(localObject1);
      Object localObject2 = paramFragment2.getReenterTransition();
      if (localObject2 != null)
        localArrayList.add(localObject2);
      Object localObject3 = paramFragment2.getSharedElementEnterTransition();
      if (localObject3 != null)
        localArrayList.add(localObject3);
    }
    if (localArrayList.isEmpty())
      return null;
    if ((PLATFORM_IMPL != null) && (canHandleAll(PLATFORM_IMPL, localArrayList)))
      return PLATFORM_IMPL;
    if ((SUPPORT_IMPL != null) && (canHandleAll(SUPPORT_IMPL, localArrayList)))
      return SUPPORT_IMPL;
    if ((PLATFORM_IMPL == null) && (SUPPORT_IMPL == null))
      return null;
    throw new IllegalArgumentException("Invalid Transition types");
  }

  static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView)
  {
    ArrayList localArrayList;
    if (paramObject != null)
    {
      localArrayList = new ArrayList();
      View localView = paramFragment.getView();
      if (localView != null)
        paramFragmentTransitionImpl.captureTransitioningViews(localArrayList, localView);
      if (paramArrayList != null)
        localArrayList.removeAll(paramArrayList);
      if (!localArrayList.isEmpty())
      {
        localArrayList.add(paramView);
        paramFragmentTransitionImpl.addTargets(paramObject, localArrayList);
      }
    }
    else
    {
      localArrayList = null;
    }
    return localArrayList;
  }

  private static Object configureSharedElementsOrdered(FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final View paramView, ArrayMap<String, String> paramArrayMap, final FragmentContainerTransition paramFragmentContainerTransition, final ArrayList<View> paramArrayList1, final ArrayList<View> paramArrayList2, final Object paramObject1, Object paramObject2)
  {
    final Fragment localFragment1 = paramFragmentContainerTransition.lastIn;
    final Fragment localFragment2 = paramFragmentContainerTransition.firstOut;
    if ((localFragment1 != null) && (localFragment2 != null))
    {
      final boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
      final ArrayMap<String, String> localArrayMap;
      Object localObject1;
      if (paramArrayMap.isEmpty())
      {
        localArrayMap = paramArrayMap;
        localObject1 = null;
      }
      else
      {
        localObject1 = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool1);
        localArrayMap = paramArrayMap;
      }
      ArrayMap localArrayMap1 = captureOutSharedElements(paramFragmentTransitionImpl, localArrayMap, localObject1, paramFragmentContainerTransition);
      final Object localObject2;
      if (paramArrayMap.isEmpty())
      {
        localObject2 = null;
      }
      else
      {
        paramArrayList1.addAll(localArrayMap1.values());
        localObject2 = localObject1;
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (localObject2 == null))
        return null;
      callSharedElementStartEnd(localFragment1, localFragment2, bool1, localArrayMap1, true);
      final Rect localRect;
      if (localObject2 != null)
      {
        localRect = new Rect();
        paramFragmentTransitionImpl.setSharedElementTargets(localObject2, paramView, paramArrayList1);
        boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
        BackStackRecord localBackStackRecord = paramFragmentContainerTransition.firstOutTransaction;
        setOutEpicenter(paramFragmentTransitionImpl, localObject2, paramObject2, localArrayMap1, bool2, localBackStackRecord);
        if (paramObject1 != null)
          paramFragmentTransitionImpl.setEpicenter(paramObject1, localRect);
      }
      else
      {
        localRect = null;
      }
      Runnable local4 = new Runnable()
      {
        public void run()
        {
          ArrayMap localArrayMap = FragmentTransition.captureInSharedElements(this.val$impl, localArrayMap, localObject2, paramFragmentContainerTransition);
          if (localArrayMap != null)
          {
            paramArrayList2.addAll(localArrayMap.values());
            paramArrayList2.add(paramView);
          }
          FragmentTransition.callSharedElementStartEnd(localFragment1, localFragment2, bool1, localArrayMap, false);
          if (localObject2 != null)
          {
            this.val$impl.swapSharedElementTargets(localObject2, paramArrayList1, paramArrayList2);
            View localView = FragmentTransition.getInEpicenterView(localArrayMap, paramFragmentContainerTransition, paramObject1, bool1);
            if (localView != null)
              this.val$impl.getBoundsOnScreen(localView, localRect);
          }
        }
      };
      OneShotPreDrawListener.add(paramViewGroup, local4);
      return localObject2;
    }
    return null;
  }

  private static Object configureSharedElementsReordered(final FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, View paramView, ArrayMap<String, String> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = paramFragmentContainerTransition.lastIn;
    final Fragment localFragment2 = paramFragmentContainerTransition.firstOut;
    if (localFragment1 != null)
      localFragment1.getView().setVisibility(0);
    if ((localFragment1 != null) && (localFragment2 != null))
    {
      final boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
      Object localObject1;
      if (paramArrayMap.isEmpty())
        localObject1 = null;
      else
        localObject1 = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool1);
      ArrayMap localArrayMap1 = captureOutSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject1, paramFragmentContainerTransition);
      final ArrayMap localArrayMap2 = captureInSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject1, paramFragmentContainerTransition);
      Object localObject2;
      if (paramArrayMap.isEmpty())
      {
        if (localArrayMap1 != null)
          localArrayMap1.clear();
        if (localArrayMap2 != null)
          localArrayMap2.clear();
        localObject2 = null;
      }
      else
      {
        addSharedElementsWithMatchingNames(paramArrayList1, localArrayMap1, paramArrayMap.keySet());
        addSharedElementsWithMatchingNames(paramArrayList2, localArrayMap2, paramArrayMap.values());
        localObject2 = localObject1;
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (localObject2 == null))
        return null;
      callSharedElementStartEnd(localFragment1, localFragment2, bool1, localArrayMap1, true);
      final Rect localRect1;
      final View localView1;
      if (localObject2 != null)
      {
        paramArrayList2.add(paramView);
        paramFragmentTransitionImpl.setSharedElementTargets(localObject2, paramView, paramArrayList1);
        boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
        BackStackRecord localBackStackRecord = paramFragmentContainerTransition.firstOutTransaction;
        setOutEpicenter(paramFragmentTransitionImpl, localObject2, paramObject2, localArrayMap1, bool2, localBackStackRecord);
        Rect localRect2 = new Rect();
        View localView2 = getInEpicenterView(localArrayMap2, paramFragmentContainerTransition, paramObject1, bool1);
        if (localView2 != null)
          paramFragmentTransitionImpl.setEpicenter(paramObject1, localRect2);
        localRect1 = localRect2;
        localView1 = localView2;
      }
      else
      {
        localView1 = null;
        localRect1 = null;
      }
      Runnable local3 = new Runnable()
      {
        public void run()
        {
          FragmentTransition.callSharedElementStartEnd(this.val$inFragment, localFragment2, bool1, localArrayMap2, false);
          if (localView1 != null)
            paramFragmentTransitionImpl.getBoundsOnScreen(localView1, localRect1);
        }
      };
      OneShotPreDrawListener.add(paramViewGroup, local3);
      return localObject2;
    }
    return null;
  }

  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap)
  {
    ViewGroup localViewGroup;
    if (paramFragmentManagerImpl.mContainer.onHasView())
      localViewGroup = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    else
      localViewGroup = null;
    if (localViewGroup == null)
      return;
    Fragment localFragment1 = paramFragmentContainerTransition.lastIn;
    Fragment localFragment2 = paramFragmentContainerTransition.firstOut;
    FragmentTransitionImpl localFragmentTransitionImpl = chooseImpl(localFragment2, localFragment1);
    if (localFragmentTransitionImpl == null)
      return;
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    Object localObject1 = getEnterTransition(localFragmentTransitionImpl, localFragment1, bool1);
    Object localObject2 = getExitTransition(localFragmentTransitionImpl, localFragment2, bool2);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Object localObject3 = configureSharedElementsOrdered(localFragmentTransitionImpl, localViewGroup, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList1, localArrayList2, localObject1, localObject2);
    Object localObject4;
    if ((localObject1 == null) && (localObject3 == null))
    {
      localObject4 = localObject2;
      if (localObject4 != null);
    }
    else
    {
      localObject4 = localObject2;
    }
    ArrayList localArrayList3 = configureEnteringExitingViews(localFragmentTransitionImpl, localObject4, localFragment2, localArrayList1, paramView);
    Object localObject5;
    if ((localArrayList3 != null) && (!localArrayList3.isEmpty()))
      localObject5 = localObject4;
    else
      localObject5 = null;
    localFragmentTransitionImpl.addTarget(localObject1, paramView);
    boolean bool3 = paramFragmentContainerTransition.lastInIsPop;
    Object localObject6 = mergeTransitions(localFragmentTransitionImpl, localObject1, localObject5, localObject3, localFragment1, bool3);
    if (localObject6 != null)
    {
      ArrayList localArrayList4 = new ArrayList();
      localFragmentTransitionImpl.scheduleRemoveTargets(localObject6, localObject1, localArrayList4, localObject5, localArrayList3, localObject3, localArrayList2);
      scheduleTargetChange(localFragmentTransitionImpl, localViewGroup, localFragment1, paramView, localArrayList2, localObject1, localArrayList4, localObject5, localArrayList3);
      localFragmentTransitionImpl.setNameOverridesOrdered(localViewGroup, localArrayList2, paramArrayMap);
      localFragmentTransitionImpl.beginDelayedTransition(localViewGroup, localObject6);
      localFragmentTransitionImpl.scheduleNameReset(localViewGroup, localArrayList2, paramArrayMap);
    }
  }

  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap)
  {
    ViewGroup localViewGroup1;
    if (paramFragmentManagerImpl.mContainer.onHasView())
      localViewGroup1 = (ViewGroup)paramFragmentManagerImpl.mContainer.onFindViewById(paramInt);
    else
      localViewGroup1 = null;
    ViewGroup localViewGroup2 = localViewGroup1;
    if (localViewGroup2 == null)
      return;
    Fragment localFragment1 = paramFragmentContainerTransition.lastIn;
    Fragment localFragment2 = paramFragmentContainerTransition.firstOut;
    FragmentTransitionImpl localFragmentTransitionImpl = chooseImpl(localFragment2, localFragment1);
    if (localFragmentTransitionImpl == null)
      return;
    boolean bool1 = paramFragmentContainerTransition.lastInIsPop;
    boolean bool2 = paramFragmentContainerTransition.firstOutIsPop;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Object localObject1 = getEnterTransition(localFragmentTransitionImpl, localFragment1, bool1);
    Object localObject2 = getExitTransition(localFragmentTransitionImpl, localFragment2, bool2);
    Object localObject3 = configureSharedElementsReordered(localFragmentTransitionImpl, localViewGroup2, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject1, localObject2);
    Object localObject4;
    if ((localObject1 == null) && (localObject3 == null))
    {
      localObject4 = localObject2;
      if (localObject4 != null);
    }
    else
    {
      localObject4 = localObject2;
    }
    ArrayList localArrayList3 = configureEnteringExitingViews(localFragmentTransitionImpl, localObject4, localFragment2, localArrayList2, paramView);
    ArrayList localArrayList4 = configureEnteringExitingViews(localFragmentTransitionImpl, localObject1, localFragment1, localArrayList1, paramView);
    setViewVisibility(localArrayList4, 4);
    Object localObject5 = mergeTransitions(localFragmentTransitionImpl, localObject1, localObject4, localObject3, localFragment1, bool1);
    if (localObject5 != null)
    {
      replaceHide(localFragmentTransitionImpl, localObject4, localFragment2, localArrayList3);
      ArrayList localArrayList5 = localFragmentTransitionImpl.prepareSetNameOverridesReordered(localArrayList1);
      localFragmentTransitionImpl.scheduleRemoveTargets(localObject5, localObject1, localArrayList4, localObject4, localArrayList3, localObject3, localArrayList1);
      localFragmentTransitionImpl.beginDelayedTransition(localViewGroup2, localObject5);
      localFragmentTransitionImpl.setNameOverridesReordered(localViewGroup2, localArrayList2, localArrayList1, localArrayList5, paramArrayMap);
      setViewVisibility(localArrayList4, 0);
      localFragmentTransitionImpl.swapSharedElementTargets(localObject3, localArrayList2, localArrayList1);
    }
  }

  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt)
  {
    if (paramFragmentContainerTransition == null)
    {
      paramFragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, paramFragmentContainerTransition);
    }
    return paramFragmentContainerTransition;
  }

  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString)
  {
    int i = paramArrayMap.size();
    for (int j = 0; j < i; j++)
      if (paramString.equals(paramArrayMap.valueAt(j)))
        return (String)paramArrayMap.keyAt(j);
    return null;
  }

  private static Object getEnterTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null)
      return null;
    Object localObject;
    if (paramBoolean)
      localObject = paramFragment.getReenterTransition();
    else
      localObject = paramFragment.getEnterTransition();
    return paramFragmentTransitionImpl.cloneTransition(localObject);
  }

  private static Object getExitTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null)
      return null;
    Object localObject;
    if (paramBoolean)
      localObject = paramFragment.getReturnTransition();
    else
      localObject = paramFragment.getExitTransition();
    return paramFragmentTransitionImpl.cloneTransition(localObject);
  }

  static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object paramObject, boolean paramBoolean)
  {
    BackStackRecord localBackStackRecord = paramFragmentContainerTransition.lastInTransaction;
    if ((paramObject != null) && (paramArrayMap != null) && (localBackStackRecord.mSharedElementSourceNames != null) && (!localBackStackRecord.mSharedElementSourceNames.isEmpty()))
    {
      String str;
      if (paramBoolean)
        str = (String)localBackStackRecord.mSharedElementSourceNames.get(0);
      else
        str = (String)localBackStackRecord.mSharedElementTargetNames.get(0);
      return (View)paramArrayMap.get(str);
    }
    return null;
  }

  private static Object getSharedElementTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean)
  {
    if ((paramFragment1 != null) && (paramFragment2 != null))
    {
      Object localObject;
      if (paramBoolean)
        localObject = paramFragment2.getSharedElementReturnTransition();
      else
        localObject = paramFragment1.getSharedElementEnterTransition();
      return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(localObject));
    }
    return null;
  }

  private static Object mergeTransitions(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean)
  {
    boolean bool;
    if ((paramObject1 != null) && (paramObject2 != null) && (paramFragment != null))
    {
      if (paramBoolean)
        bool = paramFragment.getAllowReturnTransitionOverlap();
      else
        bool = paramFragment.getAllowEnterTransitionOverlap();
    }
    else
      bool = true;
    Object localObject;
    if (bool)
      localObject = paramFragmentTransitionImpl.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3);
    else
      localObject = paramFragmentTransitionImpl.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
    return localObject;
  }

  private static void replaceHide(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList)
  {
    if ((paramFragment != null) && (paramObject != null) && (paramFragment.mAdded) && (paramFragment.mHidden) && (paramFragment.mHiddenChanged))
    {
      paramFragment.setHideReplaced(true);
      paramFragmentTransitionImpl.scheduleHideFragmentView(paramObject, paramFragment.getView(), paramArrayList);
      OneShotPreDrawListener.add(paramFragment.mContainer, new Runnable()
      {
        public void run()
        {
          FragmentTransition.setViewVisibility(this.val$exitingViews, 4);
        }
      });
    }
  }

  // ERROR //
  private static FragmentTransitionImpl resolveSupportImpl()
  {
    // Byte code:
    //   0: ldc_w 499
    //   3: invokestatic 505	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: iconst_0
    //   7: anewarray 501	java/lang/Class
    //   10: invokevirtual 509	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   13: iconst_0
    //   14: anewarray 4	java/lang/Object
    //   17: invokevirtual 515	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast 235	android/support/v4/app/FragmentTransitionImpl
    //   23: astore_0
    //   24: aload_0
    //   25: areturn
    //   26: aconst_null
    //   27: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	24	26	java/lang/Exception
  }

  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1)
  {
    for (int i = -1 + paramArrayMap.size(); i >= 0; i--)
      if (!paramArrayMap1.containsKey((String)paramArrayMap.valueAt(i)))
        paramArrayMap.removeAt(i);
  }

  private static void scheduleTargetChange(final FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final Fragment paramFragment, final View paramView, final ArrayList<View> paramArrayList1, Object paramObject1, final ArrayList<View> paramArrayList2, final Object paramObject2, final ArrayList<View> paramArrayList3)
  {
    Runnable local2 = new Runnable()
    {
      public void run()
      {
        if (this.val$enterTransition != null)
        {
          paramFragmentTransitionImpl.removeTarget(this.val$enterTransition, paramView);
          ArrayList localArrayList2 = FragmentTransition.configureEnteringExitingViews(paramFragmentTransitionImpl, this.val$enterTransition, paramFragment, paramArrayList1, paramView);
          paramArrayList2.addAll(localArrayList2);
        }
        if (paramArrayList3 != null)
        {
          if (paramObject2 != null)
          {
            ArrayList localArrayList1 = new ArrayList();
            localArrayList1.add(paramView);
            paramFragmentTransitionImpl.replaceTargets(paramObject2, paramArrayList3, localArrayList1);
          }
          paramArrayList3.clear();
          paramArrayList3.add(paramView);
        }
      }
    };
    OneShotPreDrawListener.add(paramViewGroup, local2);
  }

  private static void setOutEpicenter(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord)
  {
    if ((paramBackStackRecord.mSharedElementSourceNames != null) && (!paramBackStackRecord.mSharedElementSourceNames.isEmpty()))
    {
      String str;
      if (paramBoolean)
        str = (String)paramBackStackRecord.mSharedElementTargetNames.get(0);
      else
        str = (String)paramBackStackRecord.mSharedElementSourceNames.get(0);
      View localView = (View)paramArrayMap.get(str);
      paramFragmentTransitionImpl.setEpicenter(paramObject1, localView);
      if (paramObject2 != null)
        paramFragmentTransitionImpl.setEpicenter(paramObject2, localView);
    }
  }

  static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt)
  {
    if (paramArrayList == null)
      return;
    for (int i = -1 + paramArrayList.size(); i >= 0; i--)
      ((View)paramArrayList.get(i)).setVisibility(paramInt);
  }

  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramFragmentManagerImpl.mCurState < 1)
      return;
    SparseArray localSparseArray = new SparseArray();
    for (int i = paramInt1; i < paramInt2; i++)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(i);
      if (((Boolean)paramArrayList1.get(i)).booleanValue())
        calculatePopFragments(localBackStackRecord, localSparseArray, paramBoolean);
      else
        calculateFragments(localBackStackRecord, localSparseArray, paramBoolean);
    }
    if (localSparseArray.size() != 0)
    {
      View localView = new View(paramFragmentManagerImpl.mHost.getContext());
      int j = localSparseArray.size();
      for (int k = 0; k < j; k++)
      {
        int m = localSparseArray.keyAt(k);
        ArrayMap localArrayMap = calculateNameOverrides(m, paramArrayList, paramArrayList1, paramInt1, paramInt2);
        FragmentContainerTransition localFragmentContainerTransition = (FragmentContainerTransition)localSparseArray.valueAt(k);
        if (paramBoolean)
          configureTransitionsReordered(paramFragmentManagerImpl, m, localFragmentContainerTransition, localView, localArrayMap);
        else
          configureTransitionsOrdered(paramFragmentManagerImpl, m, localFragmentContainerTransition, localView, localArrayMap);
      }
    }
  }

  static boolean supportsTransition()
  {
    boolean bool;
    if ((PLATFORM_IMPL == null) && (SUPPORT_IMPL == null))
      bool = false;
    else
      bool = true;
    return bool;
  }

  static class FragmentContainerTransition
  {
    public Fragment firstOut;
    public boolean firstOutIsPop;
    public BackStackRecord firstOutTransaction;
    public Fragment lastIn;
    public boolean lastInIsPop;
    public BackStackRecord lastInTransaction;
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.app.FragmentTransition
 * JD-Core Version:    0.6.1
 */