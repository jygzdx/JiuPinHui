//package com.jiupin.jiupinhui.wxapi;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.jiupin.jiupinhui.R;
//import com.jiupin.jiupinhui.activity.LoginActivity;
//import com.jiupin.jiupinhui.config.Constant;
//import com.jiupin.jiupinhui.utils.LogUtils;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelmsg.SendAuth;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
//    private static final String TAG = "WXEntryActivity";
//    private IWXAPI api;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wxentry);
//        LogUtils.d(TAG,"WXEntryActivity.onCreate");
//        //注册API
//        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
//        api.handleIntent(getIntent(), this);
//    }
//
//    @Override
//    public void onReq(BaseReq baseReq) {
//        LogUtils.d(TAG,"onReq");
//    }
//
//    @Override
//    public void onResp(BaseResp resp) {
//
//        LogUtils.d(TAG,"onResp");
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                LogUtils.i(TAG,"发送成功ERR_OKERR_OK");
//                //发送成功
//                if(resp instanceof SendAuth.Resp){
//                    SendAuth.Resp newResp = (SendAuth.Resp) resp;
//                    //获取微信传回的code
//                    String code = newResp.code;
//                    Intent intent = new Intent(WXEntryActivity.this, LoginActivity.class);
//                    intent.putExtra("code",code);
//                    startActivity(intent);
//                }
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                LogUtils.i(TAG,"发送取消ERR_USER_CANCEL");
//                //发送取消
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                LogUtils.i(TAG,"发送取消ERR_AUTH_DENIEDERR_AUTH_DENIEDERR_AUTH_DENIED");
//                //发送被拒绝
//                break;
//            default:
//                LogUtils.i(TAG,"发送返回breakbreakbreak");
//                //发送返回
//                break;
//        }
//        finish();
//
//    }
//}
