package net.gangpeng.pgq.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.gangpeng.pgframe.base.BaseActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import net.gangpeng.pgq.R;

/**
 * className: net.gangpeng.pgq.activity.PicassoActivity
 * function: picasso框架
 * <p/>
 * created at 16/12/29 14:28
 *
 * @author gangpeng
 */
public class PicassoActivity extends BaseActivity {

    private ImageView img_one;
    private ImageView img_two;
    private ImageView img_three;
    private ImageView img_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        img_one = (ImageView) this.findViewById(R.id.img_one);
        img_two = (ImageView) this.findViewById(R.id.img_two);
        img_three = (ImageView) this.findViewById(R.id.img_three);
        img_four = (ImageView) this.findViewById(R.id.img_four);

        initData();
    }

    /**
     * 加载数据
     */
    private void initData() {
        /**
         * 根据ImageView大小，显示图片
         * .fit()                                  说明：控件不能设置成wrap_content,也就是必须有大小才行,fit()才让图片的宽高等于控件的宽高，设置fit()，不能再调用resize()
         * .placeholder(R.drawable.topic_tom)      说明：当图片没有加载上的时候，显示的图片
         * .error(R.drawable.topic_sky)            说明：当图片加载错误的时候，显示图片
         * .into(img_one)                          说明：将图片加载到哪个控件中
         */
        Picasso.with(this).load("http://g.hiphotos.baidu.com/image/pic/item/c9fcc3cec3fdfc03e426845ed03f8794a5c226fd.jpg")
                .fit()
                .placeholder(R.drawable.beauty)
                .error(R.drawable.beauty)
                .into(img_one);
        /**
         * 通过程序代码，来显示图片大小
         *.resize(200, 150)                        说明：为图片重新定义大小
         *.centerCrop()                            说明：图片要填充整个控件，去两边留中间
         */
        Picasso.with(this).load("http://d.hiphotos.baidu.com/image/h%3D200/sign=745574b6a2ec08fa390014a769ee3d4d/cb8065380cd79123148b447daf345982b2b78054.jpg")
                .resize(200, 150)
                .centerInside()
                .placeholder(R.drawable.beauty)
                .error(R.drawable.beauty)
                .into(img_two);

        /**
         * 加载本地数据库,图片的大小，取消于控件设置的大小
         */
        Picasso.with(this).load(R.drawable.beauty).centerCrop().resize(100, 100).into(img_three);


        /**
         * 截取图片
         * .transform(new CropSquareTransformation())        说明：通过程序截取图片
         */
        Picasso.with(this).load("http://g.hiphotos.baidu.com/image/pic/item/6c224f4a20a446230761b9b79c22720e0df3d7bf.jpg")
                .transform(new CropSquareTransformation())
                .placeholder(R.drawable.beauty)
                .error(R.drawable.beauty)
                .into(img_four);
    }

}

/**
 * picasso的Transformation方法，对图片进行截取
 * Created by cg on 2016/2/1.
 */
class CropSquareTransformation implements Transformation {

    //截取从宽度和高度最小作为bitmap的宽度和高度
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
        if (result != source) {
            source.recycle();//释放bitmap
        }
        return result;
    }

    @Override
    public String key() {
        return "square()";
    }
}
