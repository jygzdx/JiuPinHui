package com.jiupin.jiupinhui.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jiupin.jiupinhui.R;
import com.jiupin.jiupinhui.activity.FormParticularActivity;
import com.jiupin.jiupinhui.activity.MyFormActivity;
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

        //由于购物车里面的支付之后跟单品支付的流程不一样，如果data是cart的话，直接跳转到我的订单界面
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
                if("cart".equals(data)){
                    Intent intent = new Intent(WXPayEntryActivity.this, MyFormActivity.class);
                    intent.putExtra("orderStatus", "");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(WXPayEntryActivity.this, PaySuccessActivity.class);
                    intent.putExtra("orderId",data);
                    startActivity(intent);
                }
			}else if(resp.errCode==BaseResp.ErrCode.ERR_USER_CANCEL){
				ToastUtils.showShort(this,"取消支付");
                if("cart".equals(data)){
                    Intent intent = new Intent(WXPayEntryActivity.this, MyFormActivity.class);
                    intent.putExtra("orderStatus", Constant.WAIT_PAY);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(WXPayEntryActivity.this, FormParticularActivity.class);
                    intent.putExtra("status", Constant.WAIT_PAY);
                    intent.putExtra("orderId", data);
                    startActivity(intent);
                }
			}else {
				ToastUtils.showShort(this,"支付失败");
                if("cart".equals(data)){
                    Intent intent = new Intent(WXPayEntryActivity.this, MyFormActivity.class);
                    intent.putExtra("orderStatus", Constant.WAIT_PAY);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(WXPayEntryActivity.this, FormParticularActivity.class);
                    intent.putExtra("status", Constant.WAIT_PAY);
                    intent.putExtra("orderId", data);
                    startActivity(intent);
                }
			}
		}
		finish();
	}
}