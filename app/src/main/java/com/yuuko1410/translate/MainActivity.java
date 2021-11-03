package com.yuuko1410.translate;

import android.app.Dialog;
import android.content.*;
import android.media.MediaPlayer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.io.*;

public class MainActivity extends AppCompatActivity {
    String From="auto",To="zh";
    String[] code=new String[]{"auto", "zh", "en", "jp", "kor", "yue", "cht", "wyw", "ru", "spa", "th", "fra", "it", "de", "pt", "ara", "nl", "est", "cs", "swe", "vie", "pl", "dan", "rom", "hu", "el", "bul", "fin", "slo"};
    String[] language=new String[]{"自动检测", "简体中文", "英语", "日语", "韩语", "粤语", "繁体中文", "文言文", "俄语", "西班牙语", "泰语", "法语", "意大利语", "德语", "葡萄牙语", "阿拉伯语", "荷兰语", "爱沙尼亚语", "捷克语", "瑞典语", "越南语", "波兰语", "丹麦语", "罗马尼亚语", "匈牙利语", "希腊语", "保加利亚语", "芬兰语", "斯洛文尼亚语"};


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.info:{
                new Dialog(this) {
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        //去除标题
                        requestWindowFeature(Window.FEATURE_NO_TITLE);
                        //引入自定义对话框布局
                        setContentView(R.layout.info);
                        //初始化控件
                        initView();
                        //设置标题
                    }
                    //初始化控件
                    private void initView() {
                        CardView juanzeng=findViewById(R.id.juanzeng);
                        CardView okay=findViewById(R.id.okay);
                        //CardView waimai=findViewById(R.id.waimai);
                        final TextView infos=findViewById(R.id.infos);

                        juanzeng.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                //builder.setIcon(R.drawable.ic_launcher);
                                builder.setTitle("请选择捐赠方式");
                                //    指定下拉列表的显示数据
                                final String[] cities = {"微信支付", "支付宝支付"};
                                //    设置一个下拉的列表选择项
                                builder.setItems(cities, new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        if(which==0){
                                            new Dialog(MainActivity.this) {
                                                @Override
                                                protected void onCreate(Bundle savedInstanceState) {
                                                    super.onCreate(savedInstanceState);
                                                    //去除标题
                                                    requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                    //引入自定义对话框布局
                                                    setContentView(R.layout.weixinpay);
                                                    //初始化控件
                                                    CardView okay=findViewById(R.id.okay);
                                                    okay.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            onBackPressed();
                                                            Toast.makeText(MainActivity.this, "u(≧∇≦)ﾉ咪啪~" , Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }.show();
                                        }else{
                                            new Dialog(MainActivity.this) {
                                                @Override
                                                protected void onCreate(Bundle savedInstanceState) {
                                                    super.onCreate(savedInstanceState);
                                                    //去除标题
                                                    requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                    //引入自定义对话框布局
                                                    setContentView(R.layout.ailipay);
                                                    //初始化控件
                                                    CardView okay=findViewById(R.id.okay);
                                                    okay.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            onBackPressed();
                                                            Toast.makeText(MainActivity.this, "u(≧∇≦)ﾉ咪啪~" , Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }.show();

                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

                        okay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onBackPressed();
                                Toast.makeText(MainActivity.this, "u(≧∇≦)ﾉ咪啪~" , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }.show();

            }
            break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText Input=findViewById(R.id.Input);
        final EditText output=findViewById(R.id.editText);
        final CardView fanyi=findViewById(R.id.fanyi);
        final CardView bofang=findViewById(R.id.bofang);
        final CardView copy=findViewById(R.id.copy);
        final CardView remove=findViewById(R.id.remove);
        final TextView from=findViewById(R.id.from);
        final TextView to=findViewById(R.id.to);
        final ImageView jiaohuan=findViewById(R.id.jiaohuan);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MediaPlayer mediaPlayer = new MediaPlayer();

        output.setEnabled(false);
        //输出不可编辑

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("请选择语言");
                //    指定下拉列表的显示数据
                //    设置一个下拉的列表选择项
                builder.setItems(language, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        from.setText(language[which]);
                        From=code[which];
                    }
                });
                builder.show();
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("请选择语言");
                //    指定下拉列表的显示数据
                //    设置一个下拉的列表选择项
                builder.setItems(language, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        to.setText(language[which]);
                        To=code[which];
                    }
                });
                builder.show();
            }
        });
        jiaohuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(From!="auto"){
                    String tmp= String.valueOf(from.getText());
                    String temp=From;
                    from.setText(to.getText());
                    From=To;
                    to.setText(tmp);
                    To=temp;
                }
            }
        });
//翻译部分实现
        fanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run() {
                        try{
                            String text= String.valueOf(Input.getText());
                            //text=text.replaceAll("\\s", "");
                            text=text.replaceAll("\n", " ");
                            final String from,to;
                            from=From;to=To;
                            if(Input.getText().length()!=0){
                                output.setText("请稍后...");
                                TransApi api = new TransApi("20210605000854327","uNflHJaNKeuFnyP3FfHo");
                                StringBuilder sb=new StringBuilder(api.getTransResult(String.valueOf(text), from, to));
                                String str=sb.substring(sb.indexOf("\"dst\":\"")+7,sb.length()-4);
                                output.setText(new UnitCode().unicodeToUtf8(str));
                                //output.setText(Input.getText());
                                //output.setText(sb);
                                //copy.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_content_copy));
                            }
                        }catch (Exception e){
                            output.setText("应该是没网了，(～￣▽￣)～咪啪~");
                        }
                    }
                }.start();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText("");
                Input.setText("");
                //copy.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_assignment));
            }
        });
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(output.getText().length()!=0){
                    //获取剪贴板管理器
                    ClipboardManager cm = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    // 创建普通字符型ClipData
                    ClipData mClipData = ClipData.newPlainText("Label",output.getText());
                    // 将ClipData内容放到系统剪贴板里
                    cm.setPrimaryClip(mClipData);
                    Toast.makeText(MainActivity.this, "复制成功！u(≧∇≦)ﾉ咪啪~" , Toast.LENGTH_SHORT).show();
                }else{
                    ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData data = cm.getPrimaryClip();
                    ClipData.Item item = data.getItemAt(0);
                    Input.setText(item.getText().toString());
                    Input.setSelection(Input.getText().length());
                }
            }
        });

        bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Input.getText().length()!=0){
                    String file="https://fanyi.baidu.com/gettts?lan="+To+"&text="+output.getText()+"&spd=5&source=web";
                    MediaPlayer mediaPlayer;
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(file);//设置播放来源
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.prepareAsync();//异步准备
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        //异步准备监听
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();//播放
                        }
                    });
                }
            }
        });

    }


}




