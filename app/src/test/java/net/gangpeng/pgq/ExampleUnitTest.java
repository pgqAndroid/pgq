package net.gangpeng.pgq;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.gangpeng.pgq.util.PgqUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void shareList() throws Exception {
        Log.d("test", new Gson().toJson(PgqUtil.getShareList()));
    }

    @Test
    public void jsonStr() throws Exception {
        ArrayList arrayList = new ArrayList();
        Map<String, String> map = new HashMap<>();
        map.put("name", "kangkang");
        arrayList.add(map);

        System.out.println("map: " + new Gson().toJson(map));
        System.out.println("list: " + new Gson().toJson(arrayList));
    }

    @Test
    public void jsonStr2() throws Exception {
        JsonObject jo = new JsonObject();
        jo.addProperty("name", "ss");
        jo.addProperty("name", "aa");

        System.out.println("map: " + new Gson().toJson(jo));
        System.out.println("list: " + jo.toString());
    }

    @Test
    public void java() throws Exception {
        new B().sayA();
    }

    class A {
        public void sayA() {
            System.out.println("a");
        }
    }

    class B extends A {

        @Override
        public void sayA() {
            System.out.println("b");
        }
    }

    @Test
    public void javaa() throws Exception {
        String a = "{\"a\":\"a\"}";
        AA aa = new Gson().fromJson(a, AA.class);
        aa.getA();
        int i = 2 * 2;
    }

    class AA {
        private Object a;

        public Object getA() {
            return a;
        }

        public void setA(Object a) {
            this.a = a;
        }
    }
}