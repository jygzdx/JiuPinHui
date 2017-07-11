package com.jiupin.jiupinhui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.CompileAddressActivity;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AreaEntity;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 作者：czb on 2017/7/11 14:22
 */

public class AreaAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<AreaEntity> adds;
    private int tag;
    private List<String> areas = new ArrayList<>();

    public AreaAdapter(Context context,List<AreaEntity> adds) {
        this.mContext = context;
        this.adds = adds;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.area_item, parent, false);
        AreaViewHolder holder = new AreaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((AreaViewHolder)holder).tvShengShiQu.setText(adds.get(position).getAreaName());
        ((AreaViewHolder)holder).tvShengShiQu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AreaViewHolder)holder).tvShengShiQu.setTextColor(ContextCompat.getColor(mContext,R.color.mainTextColor));
                switch (tag){
                    case 1:
                        areas.clear();
                        areas.add(adds.get(position).getAreaName());
                        areas.add("请选择");
                        ((CompileAddressActivity)mContext).setTabLayout(areas);
                        requestData(adds.get(position).getId());
                        setTag(2);
                        break;
                    case 2:
                        areas.remove(areas.size()-1);
                        areas.add(adds.get(position).getAreaName());
                        areas.add("请选择");
                        ((CompileAddressActivity)mContext).setTabLayout(areas);
                        requestData(adds.get(position).getId());
                        setTag(3);
                        break;
                    case 3:
                        areas.remove(areas.size()-1);
                        areas.add(adds.get(position).getAreaName());
                        ((CompileAddressActivity)mContext).setTabLayout(areas);
                        ((CompileAddressActivity)mContext).selectedSuccess();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return adds.size();
    }

    public void setData(List<AreaEntity> adds){
        this.adds = adds;
        notifyDataSetChanged();
    }

    public void setTag(int tag){
        this.tag = tag;
    }

    class AreaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_sheng_shi_qu)
        TextView tvShengShiQu;

        AreaViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private void requestData(int id) {
        OkHttpUtils
                .post()
                .url(Constant.SHENG_SHI_QU_AREA)
                .addParams("pid",id+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("requestAddress" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("requestAddress" + response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if ("OK".equals(jsonObject.getString("msg"))) {
                                Gson gson = new Gson();
                                String data = jsonObject.getString("data");
                                JSONObject dataObj = new JSONObject(data);
                                String list = dataObj.getString("list");
                                List<AreaEntity> adds = gson.fromJson(list,new TypeToken<List<AreaEntity>>(){}.getType());
                                if(adds!=null||adds.size()>0){
                                    setData(adds);
                                }
                            } else {
                                LogUtils.d("requestAddress.error");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
