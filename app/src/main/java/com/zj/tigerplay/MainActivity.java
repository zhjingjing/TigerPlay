package com.zj.tigerplay;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import com.zj.tigerplay.databinding.ActivityMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private QuickAdapter adapterLeft;
    private QuickAdapter adapterCenter;
    private QuickAdapter adapterRight;

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==1){
//                numLeft= (int) (numLeft+70*Math.random()*10);
//                binding.recyclerLeft.scrollBy(0,numLeft);
//                numCenter= (int) (numCenter+70*Math.random()*10);
//                binding.recyclerCenter.scrollBy(0,numCenter);
//                numRight= (int) (numRight+70*Math.random()*10);
//                binding.recyclerRight.scrollBy(0,numRight);

                if (numLeft>Integer.MAX_VALUE-100){
                    numLeft=0;
                }
                numLeft+=(Math.random()*100);
                binding.recyclerLeft.scrollToPosition(numLeft);
            }else if (msg.what==2){
                if (numCenter>Integer.MAX_VALUE-100){
                    numCenter=0;
                }
                numCenter+=(Math.random()*100);
                binding.recyclerCenter.scrollToPosition(numCenter);
            }else if (msg.what==3){
                if (numRight>Integer.MAX_VALUE-100){
                    numRight=0;
                }
                numRight+=(Math.random()*100);
                binding.recyclerRight.scrollToPosition(numRight);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_main);
        binding.setPresenter(this);

        adapterLeft=new QuickAdapter(this);
        adapterCenter=new QuickAdapter(this);
        adapterRight=new QuickAdapter(this);

        binding.recyclerLeft.setHasFixedSize(true);
        binding.recyclerLeft.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerLeft.setAdapter(adapterLeft);


        binding.recyclerCenter.setHasFixedSize(true);
        binding.recyclerCenter.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerCenter.setAdapter(adapterCenter);

        binding.recyclerRight.setHasFixedSize(true);
        binding.recyclerRight.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerRight.setAdapter(adapterRight);

        initInfo();
    }
    private List<TigerBean> list;
    public void initInfo(){
        list=new ArrayList<>();
        for (int i=0;i<5;i++){
            TigerBean bean=new TigerBean();
            bean.content=i+"";
            list.add(bean);
        }
        adapterLeft.setData(list);
        adapterCenter.setData(list);
        adapterRight.setData(list);
    }


    private boolean isStart=false;
    int numLeft=0;
    int numCenter=0;
    int numRight=0;

    public void onStartClicked(){

        if (isStart){
            binding.btn.setText("开始");
            isStart=false;

            new Thread(new Runnable() {
                @Override
                public void run() {
                   for (int i=0;i<20;i++){
                        if (i<10){
                            try {
                                Thread.sleep(50);
                                handler.sendEmptyMessage(2);
                                handler.sendEmptyMessage(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                Thread.sleep(50);
                                handler.sendEmptyMessage(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();

        }else{
            isStart=true;
            binding.btn.setText("停止");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (isStart){
                            Thread.sleep(50);
                            handler.sendEmptyMessage(1);
                            handler.sendEmptyMessage(2);
                            handler.sendEmptyMessage(3);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
