package com.hayukleung.aidl.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.hayukleung.aidl.common.Common;
import com.hayukleung.aidl.common.ICommonAidl;
import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {

  private RecyclerView mRecyclerView;
  private Button mButton;

  private final List<Common> mCommonList = new ArrayList<>();

  private ICommonAidl mICommonAidl;

  private ServiceConnection mServiceConnection = new ServiceConnection() {
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
      mICommonAidl = ICommonAidl.Stub.asInterface(service);
      Log.e("client", "已连接");
    }

    @Override public void onServiceDisconnected(ComponentName name) {
      mICommonAidl = null;
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_common);

    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    mRecyclerView.setAdapter(new CommonAdapter(mCommonList));
    mButton = (Button) findViewById(R.id.add);
    mButton.setOnClickListener(this);

    Intent intentService = new Intent();
    intentService.setAction("com.hayukleung.aidl.server.CommonServer");
    intentService.setPackage("com.hayukleung.aidl.server");
    intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    bindService(intentService, mServiceConnection, BIND_AUTO_CREATE);
  }

  @Override protected void onDestroy() {
    if (null != mICommonAidl) {
      unbindService(mServiceConnection);
    }
    super.onDestroy();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.add: {
        if (null != mICommonAidl) {
          try {
            Common common = new Common();
            common.setId((int) (System.currentTimeMillis() / 1000L));
            common.setName(common.getId() + "");
            mICommonAidl.addCommon(common);
            mCommonList.clear();
            mCommonList.addAll(mICommonAidl.getCommonList());
            mRecyclerView.getAdapter().notifyItemInserted(0);
            mRecyclerView.scrollToPosition(0);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        }
        break;
      }
    }
  }
}
