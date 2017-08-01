package com.jiupin.jiupinhui.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.adapter.AreaAdapter;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.entity.AddressEntity;
import com.jiupin.jiupinhui.entity.AreaEntity;
import com.jiupin.jiupinhui.manage.UserInfoManager;
import com.jiupin.jiupinhui.presenter.ICompileAddressActivityPresenter;
import com.jiupin.jiupinhui.presenter.impl.CompileAddressActivityPresenterImpl;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ProgressUtils;
import com.jiupin.jiupinhui.utils.SoftKeyboardUtils;
import com.jiupin.jiupinhui.utils.StringUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.jiupin.jiupinhui.view.ICompileAddressActivityView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 编辑地址
 */
public class CompileAddressActivity extends AppCompatActivity implements ICompileAddressActivityView {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.sc_setting_default)
    Switch scSettingDefault;
    @BindView(R.id.btn_delete_address)
    Button btnDeleteAddress;
    @BindView(R.id.ll_parent)
    LinearLayout llParent;
    @BindView(R.id.tv_address_area)
    TextView tvAddressArea;
    @BindView(R.id.et_particular_address)
    EditText etParticularAddress;

    private PopupWindow popupWindow;
    private TabLayout tabLayout;
    private RecyclerView rvAddress;
    List<String> areas = new ArrayList<>();
    private AreaAdapter adapter;
    private AlertDialog ad;
    private ICompileAddressActivityPresenter presenter;
    private int areaId;
    private boolean isCompile;
    private AddressEntity addressEntity;
    private String token = "";
    private String area = "";
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_address);
        ButterKnife.bind(this);

        presenter = new CompileAddressActivityPresenterImpl(this);
        token = UserInfoManager.getInstance().getToken(this);

        initPop();

        //识别用户是编辑地址还是添加地址
        isCompile = getIntent().getBooleanExtra("status", false);
        if (isCompile) {//是编辑地址
            tvTitleName.setText("编辑地址");
            scSettingDefault.setVisibility(View.GONE);
            addressEntity = (AddressEntity) getIntent().getExtras().getSerializable("address");
            etUserName.setText(addressEntity.getTrueName());
            etUserPhone.setText(addressEntity.getMobile());
            tvAddressArea.setText(addressEntity.getArea_main().replace(" ", ""));
            etParticularAddress.setText(addressEntity.getArea_info());
            area = addressEntity.getArea_main();
            id = addressEntity.getId()+"";
        } else {//是添加地址
            tvTitleName.setText("添加新地址");
            btnDeleteAddress.setVisibility(View.GONE);
        }

    }

    //初始化popupwindows
    private void initPop() {
        popupWindow = new PopupWindow(this);
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_choose_area, null);
        tabLayout = (TabLayout) popupView.findViewById(R.id.tl_address_tablayout);
        rvAddress = (RecyclerView) popupView.findViewById(R.id.rv_address);
        ImageView ivClose = (ImageView) popupView.findViewById(R.id.iv_closs);
        rvAddress.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        TabLayout.Tab tab = tabLayout.newTab();
        tabLayout.addTab(tab.setText("请选择"));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //                Toast.makeText(CompileAddressActivity.this, "select tab = " + tab.getText(), Toast.LENGTH_SHORT).show();
                //                adapter.setData(tab.get);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //                Toast.makeText(CompileAddressActivity.this, "Unselected tab = " + tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //                Toast.makeText(CompileAddressActivity.this, "Reselected tab = " + tab.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setContentView(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //popupwindow消失的时候恢复成原来的透明度
                backgroundAlpha(1f);
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btn_delete_address, R.id.ll_address_area})
    public void onViewClicked(View view) {
        //隐藏软键盘
        hideSoftInput();


        String userName = etUserName.getText().toString();
        String phone = etUserPhone.getText().toString();
        String address = etParticularAddress.getText().toString();
        boolean isDefault = scSettingDefault.isChecked();

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right://保存地址
                if (StringUtils.isEmpty(phone) || !StringUtils.isMobileNO(phone)) {
                    ToastUtils.showShort(this, "手机号码输入错误");
                    return;
                }
                if (StringUtils.isEmpty(address) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(area)) {
                    ToastUtils.showShort(this, "请输入完整信息");
                    return;
                }

                if (areas.size() > 0) {
                    area = "";
                    for (int i = 0; i < areas.size(); i++) {
                        area = area + areas.get(i) + " ";
                    }
                    area = area.trim();

                }
                ad = ProgressUtils.show(this);

                int area_id = areaId;
                if(isCompile){
                    isDefault = addressEntity.getIs_default()==1?true:false;
                }

LogUtils.d("---- token = "+token+"---- area_id = "+area_id+"---- userName = "+userName+"---- address = "+address
        +"---- id = "+id+"---- phone = "+phone+"---- area = "+area+"---- isDefault = "+isDefault);

                presenter.saveAddress(token, area_id + "", "", userName, address, id, phone, area, isDefault);

                break;
            case R.id.btn_delete_address:
                showDialog();

                break;
            case R.id.ll_address_area:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();// 关闭
                    LogUtils.d("dismiss");
                } else {
                    popupWindow.showAtLocation(llParent, Gravity.BOTTOM, 50, 50);// 显示
                    LogUtils.d("showAtLocation");
                    backgroundAlpha(0.7f);
                    requestAddress(-1);
                }
                break;
        }
    }

    //隐藏软键盘
    private void hideSoftInput() {
        SoftKeyboardUtils.hideSoftKeyboard(CompileAddressActivity.this);
    }

    //显示diolog
    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_content, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvEnsure = (TextView) view.findViewById(R.id.tv_ensure);

        tvContent.setText("确定要删除该地址？");
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.dismiss();
            }
        });
        tvEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCompile) {
                    if (addressEntity != null) {
                        presenter.deleteAddress(addressEntity.getId(), token);
                    }
                }
                //确定
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void selectedSuccess(int areaId) {
        this.areaId = areaId;
        popupWindow.dismiss();
        tvAddressArea.setText("");
        for (int i = 0; i < areas.size(); i++) {
            tvAddressArea.append(areas.get(i));
        }

    }

    public void setTabLayout(List<String> areas) {
        //        this.areas.clear();
        this.areas = areas;
        tabLayout.removeAllTabs();
        for (int i = 0; i < areas.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(areas.get(i)));
        }
        tabLayout.getTabAt(areas.size() - 1).select();
    }

    private void requestAddress(int pid) {
        OkHttpUtils
                .post()
                .url(Constant.SHENG_SHI_QU_AREA)
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
                                List<AreaEntity> adds = gson.fromJson(list, new TypeToken<List<AreaEntity>>() {
                                }.getType());
                                if (adds != null && adds.size() > 0) {
                                    if (adapter == null) {
                                        adapter = new AreaAdapter(CompileAddressActivity.this, adds);
                                        adapter.setTag(1);
                                        rvAddress.setAdapter(adapter);
                                    } else {
                                        adapter.setData(adds);
                                        adapter.setTag(1);
                                    }


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

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void saveAddressSuccess() {
        ad.dismiss();
        ToastUtils.showShort(this, "上传地址成功");
        finish();
    }

    @Override
    public void saveAddressFailed() {
        ad.dismiss();
        ToastUtils.showShort(this, "上传地址失败");
    }

    @Override
    public void deleteAddressSuccess() {
        finish();
    }
}
