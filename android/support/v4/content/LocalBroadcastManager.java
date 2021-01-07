package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class LocalBroadcastManager
{
  private static final boolean DEBUG = false;
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  private static final String TAG = "LocalBroadcastManager";
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap();

  private LocalBroadcastManager(Context paramContext)
  {
    this.mAppContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (paramAnonymousMessage.what != 1)
          super.handleMessage(paramAnonymousMessage);
        else
          LocalBroadcastManager.this.executePendingBroadcasts();
      }
    };
  }

  @NonNull
  public static LocalBroadcastManager getInstance(@NonNull Context paramContext)
  {
    synchronized (mLock)
    {
      if (mInstance == null)
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      LocalBroadcastManager localLocalBroadcastManager = mInstance;
      return localLocalBroadcastManager;
    }
  }

  void executePendingBroadcasts()
  {
    synchronized (this.mReceivers)
    {
      BroadcastRecord[] arrayOfBroadcastRecord;
      int j;
      do
      {
        int i = this.mPendingBroadcasts.size();
        if (i <= 0)
          return;
        arrayOfBroadcastRecord = new BroadcastRecord[i];
        this.mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        this.mPendingBroadcasts.clear();
        j = 0;
      }
      while (j >= arrayOfBroadcastRecord.length);
      BroadcastRecord localBroadcastRecord = arrayOfBroadcastRecord[j];
      int k = localBroadcastRecord.receivers.size();
      for (int m = 0; m < k; m++)
      {
        ReceiverRecord localReceiverRecord = (ReceiverRecord)localBroadcastRecord.receivers.get(m);
        if (!localReceiverRecord.dead)
          localReceiverRecord.receiver.onReceive(this.mAppContext, localBroadcastRecord.intent);
      }
      j++;
    }
  }

  public void registerReceiver(@NonNull BroadcastReceiver paramBroadcastReceiver, @NonNull IntentFilter paramIntentFilter)
  {
    synchronized (this.mReceivers)
    {
      ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      ArrayList localArrayList1 = (ArrayList)this.mReceivers.get(paramBroadcastReceiver);
      if (localArrayList1 == null)
      {
        localArrayList1 = new ArrayList(1);
        this.mReceivers.put(paramBroadcastReceiver, localArrayList1);
      }
      localArrayList1.add(localReceiverRecord);
      for (int i = 0; i < paramIntentFilter.countActions(); i++)
      {
        String str = paramIntentFilter.getAction(i);
        ArrayList localArrayList2 = (ArrayList)this.mActions.get(str);
        if (localArrayList2 == null)
        {
          localArrayList2 = new ArrayList(1);
          this.mActions.put(str, localArrayList2);
        }
        localArrayList2.add(localReceiverRecord);
      }
      return;
    }
  }

  public boolean sendBroadcast(@NonNull Intent paramIntent)
  {
    label558: label567: label704: 
    while (true)
    {
      Object localObject2;
      Object localObject3;
      Object localObject4;
      Object localObject5;
      int n;
      String str2;
      int k;
      Object localObject7;
      synchronized (this.mReceivers)
      {
        localObject2 = paramIntent.getAction();
        localObject3 = paramIntent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
        localObject4 = paramIntent.getData();
        String str1 = paramIntent.getScheme();
        Set localSet = paramIntent.getCategories();
        if ((0x8 & paramIntent.getFlags()) != 0)
        {
          i = 1;
          if (i != 0)
          {
            StringBuilder localStringBuilder1 = new StringBuilder();
            localStringBuilder1.append("Resolving type ");
            localStringBuilder1.append((String)localObject3);
            localStringBuilder1.append(" scheme ");
            localStringBuilder1.append(str1);
            localStringBuilder1.append(" of intent ");
            localStringBuilder1.append(paramIntent);
            Log.v("LocalBroadcastManager", localStringBuilder1.toString());
          }
          localObject5 = (ArrayList)this.mActions.get(paramIntent.getAction());
          if (localObject5 != null)
          {
            if (i == 0)
              break label558;
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append("Action list: ");
            localStringBuilder2.append(localObject5);
            Log.v("LocalBroadcastManager", localStringBuilder2.toString());
            break label558;
            if (j >= ((ArrayList)localObject5).size())
              break label704;
            ReceiverRecord localReceiverRecord = (ReceiverRecord)((ArrayList)localObject5).get(j);
            if (i != 0)
            {
              StringBuilder localStringBuilder3 = new StringBuilder();
              localStringBuilder3.append("Matching against filter ");
              localStringBuilder3.append(localReceiverRecord.filter);
              Log.v("LocalBroadcastManager", localStringBuilder3.toString());
            }
            if (localReceiverRecord.broadcasting)
            {
              if (i == 0)
                break label567;
              Log.v("LocalBroadcastManager", "  Filter's target already added");
              break label567;
            }
            IntentFilter localIntentFilter = localReceiverRecord.filter;
            Object localObject8 = localObject2;
            localObject9 = localObject2;
            localObject10 = localObject6;
            Object localObject11 = localObject3;
            m = j;
            localObject12 = localObject5;
            Object localObject13 = localObject4;
            localObject14 = localObject3;
            localObject15 = localObject4;
            n = localIntentFilter.match(localObject8, localObject11, str1, localObject13, localSet, "LocalBroadcastManager");
            if (n < 0)
              break label601;
            if (i != 0)
            {
              StringBuilder localStringBuilder5 = new StringBuilder();
              localStringBuilder5.append("  Filter matched!  match=0x");
              localStringBuilder5.append(Integer.toHexString(n));
              Log.v("LocalBroadcastManager", localStringBuilder5.toString());
            }
            if (localObject10 != null)
              break label594;
            localObject6 = new ArrayList();
            ((ArrayList)localObject6).add(localReceiverRecord);
            localReceiverRecord.broadcasting = true;
            break label679;
            StringBuilder localStringBuilder4 = new StringBuilder();
            localStringBuilder4.append("  Filter did not match: ");
            localStringBuilder4.append(str2);
            Log.v("LocalBroadcastManager", localStringBuilder4.toString());
            break label675;
            if (k < ((ArrayList)localObject7).size())
            {
              ((ReceiverRecord)((ArrayList)localObject7).get(k)).broadcasting = false;
              k++;
              continue;
            }
            this.mPendingBroadcasts.add(new BroadcastRecord(paramIntent, (ArrayList)localObject7));
            if (!this.mHandler.hasMessages(1))
              this.mHandler.sendEmptyMessage(1);
            return true;
          }
          return false;
        }
      }
      int i = 0;
      continue;
      Object localObject6 = null;
      int j = 0;
      continue;
      int m = j;
      Object localObject12 = localObject5;
      Object localObject9 = localObject2;
      Object localObject14 = localObject3;
      Object localObject15 = localObject4;
      Object localObject10 = localObject6;
      break label675;
      label594: localObject6 = localObject10;
      continue;
      label601: if (i != 0)
      {
        switch (n)
        {
        default:
          str2 = "unknown reason";
          break;
        case -1:
          str2 = "type";
          break;
        case -2:
          str2 = "data";
          break;
        case -3:
          str2 = "action";
          break;
        case -4:
          str2 = "category";
          break;
        }
      }
      else
      {
        localObject6 = localObject10;
        j = m + 1;
        localObject2 = localObject9;
        localObject5 = localObject12;
        localObject3 = localObject14;
        localObject4 = localObject15;
        continue;
        localObject7 = localObject6;
        if (localObject7 != null)
          k = 0;
      }
    }
  }

  public void sendBroadcastSync(@NonNull Intent paramIntent)
  {
    if (sendBroadcast(paramIntent))
      executePendingBroadcasts();
  }

  public void unregisterReceiver(@NonNull BroadcastReceiver paramBroadcastReceiver)
  {
    while (true)
    {
      int i;
      int j;
      int k;
      synchronized (this.mReceivers)
      {
        ArrayList localArrayList1 = (ArrayList)this.mReceivers.remove(paramBroadcastReceiver);
        if (localArrayList1 == null)
          return;
        i = localArrayList1.size() - 1;
        if (i >= 0)
        {
          ReceiverRecord localReceiverRecord1 = (ReceiverRecord)localArrayList1.get(i);
          localReceiverRecord1.dead = true;
          j = 0;
          if (j >= localReceiverRecord1.filter.countActions())
            break label200;
          String str = localReceiverRecord1.filter.getAction(j);
          ArrayList localArrayList2 = (ArrayList)this.mActions.get(str);
          if (localArrayList2 == null)
            break label194;
          k = localArrayList2.size() - 1;
          if (k >= 0)
          {
            ReceiverRecord localReceiverRecord2 = (ReceiverRecord)localArrayList2.get(k);
            if (localReceiverRecord2.receiver == paramBroadcastReceiver)
            {
              localReceiverRecord2.dead = true;
              localArrayList2.remove(k);
            }
          }
          else
          {
            if (localArrayList2.size() > 0)
              break label194;
            this.mActions.remove(str);
            break label194;
          }
        }
        else
        {
          return;
        }
      }
      k--;
      continue;
      label194: j++;
      continue;
      label200: i--;
    }
  }

  private static final class BroadcastRecord
  {
    final Intent intent;
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;

    BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
    {
      this.intent = paramIntent;
      this.receivers = paramArrayList;
    }
  }

  private static final class ReceiverRecord
  {
    boolean broadcasting;
    boolean dead;
    final IntentFilter filter;
    final BroadcastReceiver receiver;

    ReceiverRecord(IntentFilter paramIntentFilter, BroadcastReceiver paramBroadcastReceiver)
    {
      this.filter = paramIntentFilter;
      this.receiver = paramBroadcastReceiver;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(this.receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(this.filter);
      if (this.dead)
        localStringBuilder.append(" DEAD");
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}

/* Location:           C:\Users\bbbff\Desktop\onekey-decompile-apk\_tools\dex2jar\source-3125444_dex2jar.jar
 * Qualified Name:     android.support.v4.content.LocalBroadcastManager
 * JD-Core Version:    0.6.1
 */