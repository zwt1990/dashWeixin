package cjc.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	private static final int TIMEOUT_IN_MILLIONS = 5000;  
	 public final static String ENCODING = "UTF-8";
  
    /** 
     * Get请求，获得返回数据 
     *  
     * @param urlStr 
     * @return 
     * @throws Exception 
     */  
    public static String doGet(String urlStr)   
    {  
        URL url = null;  
        HttpURLConnection conn = null;  
        InputStream is = null;  
        ByteArrayOutputStream baos = null;  
        try  
        {  
            url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);  
            conn.setRequestMethod("GET");  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            if (conn.getResponseCode() == 200)  
            {  
                is = conn.getInputStream();  
                baos = new ByteArrayOutputStream();  
                int len = -1;  
                byte[] buf = new byte[128];  
  
                while ((len = is.read(buf)) != -1)  
                {  
                    baos.write(buf, 0, len);  
                }  
                baos.flush();  
                return baos.toString();  
            } else  
            {  
                throw new RuntimeException(" responseCode is not 200 ... ");  
            }  
  
        } catch (Exception e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            try  
            {  
                if (is != null)  
                    is.close();  
            } catch (IOException e)  
            {  
            }  
            try  
            {  
                if (baos != null)  
                    baos.close();  
            } catch (IOException e)  
            {  
            }  
            conn.disconnect();  
        }  
          
        return null ;  
  
    }  
  
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 * 
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static String doJsonPost(String url, Object param) {
		try {
			String encoderJson = URLEncoder.encode(
					JSONObject.toJSONString(param), ENCODING);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(encoderJson);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {// 成功
				return EntityUtils.toString(response.getEntity(), ENCODING);
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}
	public static boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())   ) ;
	}
    
}
