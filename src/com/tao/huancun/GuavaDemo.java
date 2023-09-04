package com.tao.huancun;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaDemo {

    CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
            .maximumSize(100)   //最大存储条目
            .expireAfterAccess(30, TimeUnit.MINUTES)    //过期时间
            .recordStats(); //开启统计功能
    LoadingCache<String, String> cache = cacheBuilder.build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return "涛 value：" + key;
        }
    });

    /**
     * key = 验证码,value = 长连接
     */
    private LoadingCache<String,String> verifyCodeCache;

    /**
     * key = 设备,value = 验证码
     */
    private LoadingCache<String,String> deviceCodeCache;

    Object sessionService;
    /*
        项目应用场景:
            1、首页的类别目录
            2、微信用户登录的验证码
            3、微信扫码登录,验证码和用户设备有绑定关系,微信公众号输入验证码实现扫码登录
            4、文章编辑的外网图片转存
     */

    /*
        应用场景四:外网图片转存
     */
    private LoadingCache<String,String> imgReplaceCache = CacheBuilder.newBuilder().maximumSize(300).expireAfterAccess(5,TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
        @Override
        public String load(String imgUrl) throws Exception {
            try{
                //获取图片输入流
                InputStream stream = new FileInputStream(new File(""));
                URI uri = URI.create(imgUrl);
                String path = uri.getPath();
                //文件类型
                int index = path.lastIndexOf(".");
                String fileType = null;
                if(index > 0){
                    fileType = imgUrl.substring(index+1);
                }
                //上传图片到OSS,并且返回OSS中的URL
                return upload(stream,fileType);
            }catch (Exception e){
                System.out.println("外网图片转存异常! img:"+e.getMessage());
                return "";
            }
        }
    });

    //上传图片到OSS
    public String upload(InputStream stream,String fileType){
        //图片上传过程
        //略
        //OSS图片服务器生成的图片路径
        String url = "";
        return url;
    }

    //QrLoginHelper
    /*
        思路： 二维码扫码微信登录
            1、用户点击登录按钮，登录界面显示，生成的验证码，验证码放入 verifyCodeCache对象，这时已经绑定长连接
            2、用户扫描二维码后，请求到服务端，服务端获取设备码，且从 deviceCodeCache 获取验证码，没有就重新生成，然后从 verifyCodeCache对象 取得 长连接
            3、最后校验通过后,使用 Spring的SseEmitter 去响应客户端：将session放入这个SseEmitter,且设置cookie,最后返回客户端,返回true,登录成功.
     */
    public GuavaDemo(Object loginService){
        this.sessionService = loginService;
        //创建验证码缓存实例,最多支持300个验证码,有效时间5分钟
        verifyCodeCache = CacheBuilder.newBuilder().maximumSize(300).expireAfterAccess(5,TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                throw new Exception("no val:" + s);
            }
        });

        deviceCodeCache = CacheBuilder.newBuilder().maximumSize(300).expireAfterAccess(5,TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                //生成随机验证码,直到不与已缓存的验证码重复
                while (true){
                    String verifyCode = "调用生成的验证码";
                    if(!verifyCodeCache.asMap().containsKey(verifyCode)){
                        return verifyCode;
                    }
                }
            }
        });
    }


    /*
        demo示例
     */
    @Test
    public void guavaDemo() throws ExecutionException {

        //放入缓存
        cache.put("itTao","彭于涛");

        //获取缓存
        System.out.println(cache.get("itTao"));

        //打印统计信息
        System.out.println(cache.stats());

        //清空缓存
        cache.invalidateAll();
        cache.cleanUp();
        if(cache.size()!=0){
            System.out.println(cache.get("itTao"));
        }else {
            System.out.println("缓存为空!");
        }
    }

    /*
        场景应用一,首页的类别目录
     */
    public List<HashMap<String,String>> loadAllCategories(){
        if(cache.size() <= 5){
            refreshCache();
        }
        List<HashMap<String,String>> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            list.add((HashMap<String, String>) entry);
        }
        //移除id 小于等于 0 的记录
        list.removeIf(s->Integer.valueOf(s.get("id")) <= 0);
        return list;
    }

    /*
        刷新缓存，放入最新数据
     */
    public void refreshCache(){
        //数据库获取数据
        List<HashMap<String,String>> list = new ArrayList<>();
        //清空缓存
        cache.invalidateAll();
        cache.cleanUp();
        //将数据库数据放入缓存
        list.forEach(s->cache.put(s.get("id"),s.get("value")));
    }
}
