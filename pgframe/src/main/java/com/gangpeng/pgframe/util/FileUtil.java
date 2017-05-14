package com.gangpeng.pgframe.util;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * className: FileUtil
 * function: 文件工具类
 * <p/>
 * created at 16/9/12 10:15
 *
 * @author pg
 */
public class FileUtil {

    private static final String TAG = "FileUtil";

    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    public static boolean isSDCardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 获取sd卡根目录路径
     *
     * @return
     */
    public static String getSdPath() {
        // 检测sd是否可用
        if (isSDCardExist()) {
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath())
                    .append(File.separator);
            return sb.toString();
        } else {
            Log.e(TAG, "sd卡不可用");
            return "";
        }
    }

    /**
     * 获取内部存储空间大小，已Mb为单位（1024*1024=1048576）
     *
     * @return 内部存储空间大小
     */
    public static long getDataFreeSize() {
        File root = Environment.getDataDirectory();
        StatFs sf = new StatFs(root.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 空闲的数据块的数量
        long availCount = sf.getAvailableBlocks();
        // 返回SD卡空闲大小
        return availCount * blockSize / 1048576;
    }

    /**
     * 获取sd卡存储空间大小，已Mb为单位（1024*1024=1048576）
     *
     * @return 内部存储空间大小
     */
    public static long getSDFreeSize() {
        File root = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(root.getPath());
        // 获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        // 空闲的数据块的数量
        long availCount = sf.getAvailableBlocks();
        // 返回SD卡空闲大小
        return availCount * blockSize / 1048576;
    }

    /**
     * 删除文件或文件夹
     *
     * @param file 需要删除的文件
     * @return true表示删除成功，false表示删除失败
     */
    public static boolean deleteFile(File file) {
        //文件为空，或不存在
        if (file == null || !file.exists()) {
            Log.e(TAG, "删除文件失败，文件为空或不存在！");
            return false;
        }
        //是文件，则删除
        if (file.isFile()) {
            return file.delete();
        }
        //是文件夹，则递归删除
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();

            if (childFiles == null || childFiles.length == 0) {
                return file.delete();
            }
            for (File child : childFiles) {
                deleteFile(child);
            }

            return file.delete();
        }
        return false;
    }

    /**
     * 复制单个文件
     *
     * @param srcFileName  待复制的文件名
     * @param destFileName 目标文件名
     * @param overlay      如果目标文件存在，是否覆盖；
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String destFileName,
                                   boolean overlay) {
        File srcFile = new File(srcFileName);

        // 判断源文件是否存在
        if (!srcFile.exists()) {
            Log.e(TAG, "源文件：" + srcFileName + "不存在！");
            return false;
        } else if (!srcFile.isFile()) {
            Log.e(TAG, "复制文件失败，源文件：" + srcFileName + "不是一个文件！");
            return false;
        }

        // 判断目标文件是否存在
        File destFile = new File(destFileName);
        if (destFile.exists()) {
            // 如果目标文件存在并不允许覆盖，则提示用户信息
            if (!overlay) {
                Log.e(TAG, "复制文件失败：文件目录" + destFileName + "已存在！");
                return false;
            }
        } else {
            // 如果目标文件所在目录不存在，则创建目录
            if (!destFile.getParentFile().exists()) {
                // 目标文件所在目录不存在
                if (!destFile.getParentFile().mkdirs()) {
                    // 复制文件失败：创建目标文件所在目录失败
                    return false;
                }
            }
        }

        // 复制文件
        // 读取的字节数
        int byteRead;
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];

            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 复制整个目录的内容
     *
     * @param srcDirName  待复制目录的目录名
     * @param destDirName 目标目录名
     * @param overlay     如果目标目录存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyDirectory(String srcDirName, String destDirName,
                                        boolean overlay) {
        // 判断源目录是否存在
        File srcDir = new File(srcDirName);
        if (!srcDir.exists()) {
            Log.e(TAG, "复制目录失败：源目录" + srcDirName + "不存在！");
            return false;
        } else if (!srcDir.isDirectory()) {
            Log.e(TAG, "复制目录失败：" + srcDirName + "不是目录！");
            return false;
        }

        // 如果目标目录名不是以文件分隔符结尾，则加上文件分隔符
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        File destDir = new File(destDirName);
        // 如果目标文件夹存在
        if (destDir.exists()) {
            // 如果不允许覆盖则返回信息
            if (!overlay) {
                Log.e(TAG, "复制目录失败：目的目录" + destDirName + "已存在！");
                return false;
            }
        } else {
            // 创建目的目录
            if (!destDir.mkdirs()) {
                Log.e("FieUtil", "复制目录失败：创建目的目录失败！");
                return false;
            }
        }

        boolean flag = true;
        File[] files = srcDir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // 复制文件
                if (files[i].isFile()) {
                    flag = FileUtil.copyFile(files[i].getAbsolutePath(),
                            destDirName + files[i].getName(), overlay);
                    if (!flag)
                        break;
                } else if (files[i].isDirectory()) {
                    flag = FileUtil.copyDirectory(files[i].getAbsolutePath(),
                            destDirName + files[i].getName(), overlay);
                    if (!flag)
                        break;
                }
            }
        }
        if (!flag) {
            Log.e(TAG, "复制目录" + srcDirName + "至" + destDirName + "失败！");
            return false;
        } else {
            return true;
        }
    }

}
