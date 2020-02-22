package com.bird_brown.jankenapp01;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class DisplayView extends View {
    private Bitmap myBitmap;    //ユーザ用Bitmap
    private Bitmap cpuBitmap;   //コンピュータ用Bitmap

    public DisplayView(Context context) {
        super(context);
    }

    public DisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //メンバー変数myBitmapかcpuBitmapがnullならば処理をせず返す
        if (myBitmap == null || cpuBitmap == null) {
            return;
        }

        //ユーザが選択したBitmap（グー、チョキ、パー）をキャンパスに表示
        canvas.drawBitmap(myBitmap, getWidth() / 3, getHeight() / 2, null);

        //CPUが選択したBitmap（グー、チョキ、パー）をキャンバスに表示
        canvas.drawBitmap(cpuBitmap, getWidth() / 3, getHeight() / 4, null);
    }

    public void setChoice(int myNo, int cpuNo) {
        //ユーザ選択によるリソースIDを取得
        int mySelectId =
                myNo == 0 ? R.drawable.my_gu : (myNo == 1 ? R.drawable.my_choki : R.drawable.my_par);

        //コンピュータ選択によるリソースIDを取得
        int cpuSelectId =
                cpuNo == 0 ? R.drawable.cpu_gu : (cpuNo == 1 ? R.drawable.cpu_choki : R.drawable.cpu_par);

        //リソースを取得
        Resources res = getResources();

        //リソースからユーザ用Bitmapを作成
        myBitmap = BitmapFactory.decodeResource(res, mySelectId);

        //リソースからコンピュータ用Bitmapを作成
        cpuBitmap = BitmapFactory.decodeResource(res, cpuSelectId);

        //再描画
        invalidate();
    }
}
