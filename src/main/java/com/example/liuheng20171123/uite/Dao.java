package com.example.liuheng20171123.uite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.liuheng20171123.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liufan on 2017/11/23.
 */

public class Dao {
    public static String setText(String urlstr){
            try {
                URL url = new URL(urlstr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                  String lin ="";
                StringBuilder sb = new StringBuilder();
                while ((lin = br.readLine())!=null){
                    sb.append(lin);
                }
                 return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        public static Bitmap setimg(String urlstr){
            try {
                URL url = new URL(urlstr);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int getResponseCode = urlConnection.getResponseCode();
                if (getResponseCode==200){
                    InputStream inputStream = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                   return bitmap;
                }
            } catch (MalformedURLException e) {
                Log.e("sss", "setimg: "+e.toString() );
                e.printStackTrace();
            }catch (IOException e) {
                Log.e("sss1", "setimg: "+e.toString() );
                e.printStackTrace();
            }
            return null;
        }
         public static DisplayImageOptions getDisplayImageOption() {
                 DisplayImageOptions options = new DisplayImageOptions.Builder()
                         .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
                         .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                         .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                         .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                         .cacheOnDisk(true)
                         .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                         .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                         .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                         .displayer(new RoundedBitmapDisplayer(30))//是否设置为圆角，弧度为多少
                        // .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
         	      .displayer(new CircleBitmapDisplayer())//显示圆形图片
                         .build();
         		//构建完成
                		return options;
          		}
          		public static int getNetype(Context context) {
          		        int netType = -1;
          		        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
          		        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
          		        if (networkInfo == null) {
          		            return netType;
          		        }
          		        int nType = networkInfo.getType();
          		        if (nType == ConnectivityManager.TYPE_MOBILE) {
          		            netType = 2;
          		        } else if (nType == ConnectivityManager.TYPE_WIFI) {
          		            netType = 1;
          		        }
          		        return netType;
          		    }
}
