package com.jiupin.jiupinhui.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.PaySuccessActivity;
import com.jiupin.jiupinhui.config.Constant;
import com.jiupin.jiupinhui.utils.LogUtils;
import com.jiupin.jiupinhui.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "WXPayEntryActivity";
	
    private IWXAPI api;
	/**
	 * 微信支付附加数据
	 */
	private String data;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wxentry);
		data = getIntent().getStringExtra("_wxapi_payresp_extdata");
    	api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		LogUtils.d(TAG,"onReq");
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if(resp.errCode==BaseResp.ErrCode.ERR_OK){//微信支付成功
				ToastUtils.showShort(this,"支付成功");
				Intent intent = new Intent(WXPayEntryActivity.this, PaySuccessActivity.class);
				intent.putExtra("orderId",data);
				startActivity(intent);
			}else if(resp.errCode==BaseResp.ErrCode.ERR_USER_CANCEL){
				ToastUtils.showShort(this,"取消支付");
			}else {
				ToastUtils.showShort(this,"支付失败");
			}
		}
		finish();
	}
}