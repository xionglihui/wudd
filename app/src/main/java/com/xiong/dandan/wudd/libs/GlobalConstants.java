package com.xiong.dandan.wudd.libs;

import android.net.Uri;
import android.os.Environment;

import com.xiong.dandan.wudd.Config;

import java.io.File;

public class GlobalConstants {
    private GlobalConstants() {
        // empty
    }

    // log path
    public static final String PATH_LOG = "/wudandan/log/";
    public static final String LOG_FILE_DIR = Environment
            .getExternalStorageDirectory() + PATH_LOG;
    public static final String LOG_FILE_NAME = "wudandan.log";

    // Image path
    public static final String PATH_TEMP = "/shanlin/wudandan/images/temp/";
    public static final String PATH_IMAGE_CACHE = "/shanlin/wudandan/images/cache/";
    public static final String IMAGE_CAMERA_NAME = "tmp_camera.jpg";
    public static final Uri IMAGE_CAMERA_URI = Uri.fromFile(new File(
            Environment.getExternalStorageDirectory() + PATH_TEMP,
            IMAGE_CAMERA_NAME));

    public static final String IMAGE_CAPTURE_NAME = "tmp_crop";
    public static final Uri IMAGE_CAPTURE_URI = Uri.fromFile(new File(
            Environment.getExternalStorageDirectory() + PATH_TEMP,
            IMAGE_CAPTURE_NAME));

    // Image size
    public static final int USER_IMAGE_SIZE_WIDTH = 320;
    public static final int USER_IMAGE_SIZE_HEIGHT = 320;


    public static  final int PAGE_COUNT=20;


    /**公告状态-已审核发布**/
    public static final String SYSTEM_NOTIC_STATUE_CHEKED="已审核发布";



    /**项目菜单**/
    public static final String PROJECT_MUNE_WEIGUI="违规录入";
    /**项目菜单**/
    public static final String PROJECT_MUNE_KAOQING="补考勤录入";
    /**项目菜单**/
    public static final String PROJECT_MUNE_USER="项目人员信息查询";
    /**项目菜单**/
    public static final String PROJECT_MUNE_INFO="企业项目信息查询";


    public static final String MAIN_BANNER_IMG_1= Config.COMMON_URL+"/Resources/Images/banner_01.jpg";
    public static final String MAIN_BANNER_IMG_2=Config.COMMON_URL+"/Resources/Images/banner_02.jpg";




}
