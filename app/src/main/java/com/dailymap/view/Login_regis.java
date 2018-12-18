package com.dailymap.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dailymap.R;
import com.dailymap.constant.Constants;
import com.dailymap.model.network.LoginResponseInfo;
import com.dailymap.model.network.RegisterResponseInfo;
import com.dailymap.network.SendMessageManager;
import com.dailymap.utils.HttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class Login_regis extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button confirm;
    private EditText password_confirm;
    private EditText number_text;
    private ImageView backtodaily;
    private TextView log_regi_trans;
    Boolean islogin=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_regis);
        //去除标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        EventBus.getDefault().register(this);
        password_confirm=(EditText)findViewById(R.id.confirm_password);
        number_text=(EditText)findViewById(R.id.number_text);
        log_regi_trans=(TextView)findViewById(R.id.log_regi_trans);
        log_regi_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (islogin){
                    password_confirm.setVisibility(View.VISIBLE);
                    number_text.setVisibility(View.VISIBLE);
                    islogin=false;
                    confirm.setText("注册");
                    log_regi_trans.setText("已有账号？登录");
                }
                else {
                    password_confirm.setVisibility(View.GONE);
                    number_text.setVisibility(View.GONE);
                    islogin=true;
                    confirm.setText("登录");
                    log_regi_trans.setText("没有账号？注册");
                }
            }
        });
        //点击返回按钮回到ARmap界面
        backtodaily=(ImageView)findViewById(R.id.backtodaily);
        backtodaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_regis.this.finish();
            }
        });


        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        confirm=(Button)findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (islogin)
                login();
                else register();

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(RegisterResponseInfo messageEvent) {
        //Toast.makeText(this, messageEvent.getError(), Toast.LENGTH_SHORT).show();
        if (messageEvent.getResult().equals("successful")){
            Toast.makeText(this, "注册成功，欢迎！", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "注册成功，欢迎！", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event1(LoginResponseInfo messageEvent) {
        //Toast.makeText(this, messageEvent.getError(), Toast.LENGTH_SHORT).show();
        if (messageEvent.getResult().equals("请检查用户名或密码是否正确")){
            Toast.makeText(this, "登录出错，请检查用户名或密码是否正确！", Toast.LENGTH_SHORT).show();
        }else {
            Constants.USERNAME=messageEvent.getResult().getName();
            Constants.USERID=messageEvent.getResult().getUser_id();
            Toast.makeText(this, "登录成功，欢迎！"+messageEvent.getResult().getName(), Toast.LENGTH_SHORT).show();
            this.finish();
        }


          }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    private void register() {

        if (username.getText().toString().isEmpty()){
            Toast.makeText(Login_regis.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.getText().toString().isEmpty()){
            Toast.makeText(Login_regis.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password_confirm.getText().toString().isEmpty()){
            Toast.makeText(Login_regis.this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (number_text.getText().toString().isEmpty()){
            Toast.makeText(Login_regis.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.getText().toString().equals(password_confirm.getText().toString())){
            Toast.makeText(Login_regis.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        SendMessageManager.getInstance().getRegisterStatus(username.getText().toString(),password.getText().toString(),number_text.getText().toString(),"","","","");

    }


    private void login() {
        // TODO Auto-generated method stub
        String userName = null;
        String passWord = null;

        ///< 简单判断用户是否输入用户名，是否输入密码
        if ((userName = username.getText().toString()).isEmpty())
        {
            if (password.getText().toString().isEmpty())
            {
                Toast.makeText(Login_regis.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(Login_regis.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
            return;
        }
        else if ((passWord = password.getText().toString()).isEmpty())
        {
            Toast.makeText(Login_regis.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        SendMessageManager.getInstance().getLoginStatus(username.getText().toString(),password.getText().toString());

    }
}
