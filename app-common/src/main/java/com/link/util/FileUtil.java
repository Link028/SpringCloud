package com.link.util;

import java.io.ByteArrayOutputStream ;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer ;
import java.nio.channels.FileChannel ;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件处理工具类
 */
public class FileUtil
{
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

	public static byte[] readFile(String filePath) throws RuntimeException
	{
		File file = null ;
		FileInputStream fi = null ;
		ByteArrayOutputStream bytes = null ;

		byte[] buffer = new byte[1024 * 10] ;

		try
		{
			file = new File(filePath) ;
			fi = new FileInputStream(file) ;
			bytes = new ByteArrayOutputStream() ;

			int n = 0 ;

			while ((n = fi.read(buffer)) != -1)
			{
				bytes.write(buffer, 0, n) ;
			}

			return bytes.toByteArray() ;
		}
		catch (Exception e)
		{ 
			throw new RuntimeException( e ) ;
		}
		finally
		{
			ResourceUtil.close(bytes) ;
			ResourceUtil.close(fi) ;
			file = null ;
		}
	}
	
	public static String readFileText(String filePath) throws RuntimeException
	{
		return readFileText(  filePath, "UTF-8" ) ;
	}
	
	public static String readFileText( String filePath, String enCoding ) throws RuntimeException
	{
		File file = null ;
		FileInputStream fi = null ;
		ByteArrayOutputStream bytes = null ;

		byte[] buffer = new byte[1024 * 10] ;

		try
		{
			file = new File(filePath) ;
			fi = new FileInputStream(file) ;
			bytes = new ByteArrayOutputStream() ;

			int n = 0 ;

			while ((n = fi.read(buffer)) != -1)
			{
				bytes.write(buffer, 0, n) ;
			}

			return new String(bytes.toByteArray()  , enCoding );
		}
		catch (Exception e)
		{
			throw new RuntimeException( e ) ;
		}
		finally
		{
			ResourceUtil.close(bytes) ;
			ResourceUtil.close(fi) ;
			file = null ;
		}
	}
	
	
	
	/**
	 * 复制文件
	 * 通过该方式复制文件文件越大速度越是明显
	 *
	 * @param file       需要处理的文件
	 * @param targetFile 目标文件
	 * @return 是否成功
	 */
	public static boolean copy(String srcFile, String targetFile)
	{
		FileInputStream fin = null ;
		FileOutputStream fout = null ;
		FileChannel in = null ;
		FileChannel out = null ;
		int BUFFER_SIZE = 1024 * 10 ;

		try
		{
			fin = new FileInputStream(srcFile) ;
			
			File file = new File(targetFile) ;
			file.deleteOnExit() ;
			file = null ;
			
			fout = new FileOutputStream(new File(targetFile)) ;

			in = fin.getChannel() ;
			out = fout.getChannel() ;

			//设定缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE) ;

			while (in.read(buffer) != -1)
			{
				//准备写入，防止其他读取，锁住文件
				buffer.flip() ;
				out.write(buffer) ;
				//准备读取。将缓冲区清理完毕，移动文件内部指针
				buffer.clear() ;
			}
			
			buffer = null ;
			
			return true ;
		}
		catch (IOException e)
		{
			e.printStackTrace() ;
			return false ;
		}
		finally
		{
			ResourceUtil.close(in) ;
			ResourceUtil.close(fin) ;
			ResourceUtil.close(out) ;
			ResourceUtil.close(fout) ;
		}

	}
    
    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os 输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws RuntimeException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
        	throw new RuntimeException( e ) ;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 文件名称验证
     * 
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 下载文件名重新编码
     * 
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
    
    /**
     * 获取系统临时目录
     * @return
     */
    public static String getTemp()
    {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 创建临时文件
     * @param filePath
     * @param data
     * @return
     */
    public static File createTempFile(String filePath, byte[] data) throws IOException
    {
        String temp = getTemp() + filePath;
        File file = new File(temp);
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        if (!file.exists())
        {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data, 0, data.length);
        fos.flush();
        fos.close();
        return file;
    }
}
