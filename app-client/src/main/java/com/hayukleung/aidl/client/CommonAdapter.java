package com.hayukleung.aidl.client;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hayukleung.aidl.common.Common;
import java.util.List;

/**
 * AIDL
 * com.hayukleung.aidl.client
 * CommonAdapter.java
 *
 * by hayukleung
 * at 2017-04-23 21:28
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonHolder> {

  private List<Common> mCommonList;

  public CommonAdapter(List<Common> commonList) {
    mCommonList = commonList;
  }

  @Override public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CommonHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false));
  }

  @Override public void onBindViewHolder(CommonHolder holder, int position) {
    holder.name.setText(mCommonList.get(position).getName());
  }

  @Override public int getItemCount() {
    return null == mCommonList ? 0 : mCommonList.size();
  }

  public static class CommonHolder extends RecyclerView.ViewHolder {

    TextView name;

    public CommonHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.name);
    }
  }
}
