package com.bird_brown.jankenapp01;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private DisplayView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DisplayViewのオブジェクトを取得
        displayView = (DisplayView)findViewById(R.id.displayView1);

        //グー用のイメージボタンを取得し、イベントリスナ登録をする
        ImageButton gu = (ImageButton)findViewById(R.id.imageButton1);
        gu.setOnClickListener(this);

        //チョキ用のイメージボタンを取得し、イベントリスナ登録をする
        ImageButton choki = (ImageButton)findViewById(R.id.imageButton2);
        choki.setOnClickListener(this);

        //パー用のイメージボタンを取得し、イベントリスナ登録をする
        ImageButton par = (ImageButton)findViewById(R.id.imageButton3);
        par.setOnClickListener(this);

        //同クラスのdisplayResultを呼び出す
        displayResult();
    }

    @Override
    public void onClick(View view) {
        //ユーザ選択情報格納用変数
        int myNo = 0;

        //コンピュータ選択情報（０、１、２）を取得
        int cpuNo = CPU.getChoice();

        //押したイメージボタンのリソースIDを取得
        int id = view.getId();

        //ユーザ選択分岐処理
        switch (id) {
            case R.id.imageButton1 : //グーを選択時
                myNo = 0;
                break;
            case R.id.imageButton2 : //チョキを選択時
                myNo = 1;
                break;
            case R.id.imageButton3 : //パーを選択時
                myNo = 2;
                break;
        }

        //DisplayViewクラスのsetChoiceメソッドを呼び出し
        //ユーザ選択情報、コンピュータ選択情報を表示
        displayView.setChoice(myNo, cpuNo);

        //同クラスのsetResultメソッドを呼び出す
        setResult(myNo, cpuNo);

        //同クラスのdisplayResultメソッドを呼び出す
        displayResult();
    }

    public void displayResult() {
        String fileName = "janken"; //ファイル名

        //じゃんけん結果を記録したプリファレンスから「勝」「敗」「分」情報を取得
        SharedPreferences preference = getSharedPreferences(fileName, MODE_PRIVATE);

        int win = preference.getInt("WIN", 0); //勝数取得
        int lost = preference.getInt("LOST", 0); //負数取得
        int draw = preference.getInt("DRAW", 0); //引分数取得

        //TextViewに「○数◯敗◯分」という形で「勝」「敗」「分」情報を表示
        String s = win + "勝" + lost + "敗" + draw + "分";

        TextView text = (TextView)findViewById(R.id.textView1);
        text.setText(s);
    }

    public void setResult(int myNo, int cpuNo) {
        String fileName = "janken"; //ファイル名

        //じゃんけん結果を記録したプリファレンスから「勝」「敗」「分」情報を取得
        SharedPreferences preference = getSharedPreferences(fileName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();

        int win = preference.getInt("WIN", 0); //勝数取得
        int lost = preference.getInt("LOST", 0); //負数取得
        int draw = preference.getInt("DRAW", 0); //引分数取得

        //勝敗判定
        switch (myNo) {
            case 0 :
                if (cpuNo == 0) {
                    draw++;
                } else if (cpuNo == 1) {
                    win++;
                } else if (cpuNo == 2) {
                    lost++;
                }
                break;
            case 1 :
                if (cpuNo == 0) {
                    lost++;
                } else if (cpuNo == 1) {
                    draw++;
                } else if (cpuNo == 2) {
                    win++;
                }
                break;
            case 2 :
                if (cpuNo == 0) {
                    win++;
                } else if (cpuNo == 1) {
                    lost++;
                } else if (cpuNo == 2) {
                    draw++;
                }
                break;
        }

        //変更された「勝」「敗」「分」情報をプリファレンスに設定
        editor.putInt("WIN", win);
        editor.putInt("LOST", lost);
        editor.putInt("DRAW", draw);

        //設定を確定
        editor.commit();
    }
}
