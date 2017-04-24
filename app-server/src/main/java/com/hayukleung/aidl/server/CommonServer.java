package com.hayukleung.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.hayukleung.aidl.common.Common;
import com.hayukleung.aidl.common.ICommonAidl;
import java.util.ArrayList;
import java.util.List;

/**
 * 注意：对于部分国产机型，需要手动设置 Server 保持后台运行，否则 Client 将连不上 CommonServer
 */
public class CommonServer extends Service {

  private List<Common> mCommonList = new ArrayList<>();

  public CommonServer() {
  }

  @Override public void onCreate() {
    super.onCreate();
  }

  @Override public IBinder onBind(Intent intent) {
    // Return the communication channel to the service.
    return mStub;
  }

  private ICommonAidl.Stub mStub = new ICommonAidl.Stub() {
    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble,
        String aString) throws RemoteException {

    }

    @Override public void addCommon(Common common) throws RemoteException {
      if (!mCommonList.contains(common)) {
        mCommonList.add(0, common);
      }
    }

    @Override public List<Common> getCommonList() throws RemoteException {
      return mCommonList;
    }
  };
}
