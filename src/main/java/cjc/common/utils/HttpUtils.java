package cjc.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class HttpUtils {
	private static final int TIMEOUT_IN_MILLIONS = 5000;
	 public final static String ENCODING = "UTF-8";
	    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
	    private static CloseableHttpClient httpClient;;
	    private static RequestConfig requestConfig;
	    private static RequestConfig requestConfigComplex;
	 static {
	        try{
	        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
	        
	        ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();
	        connManager.setDefaultConnectionConfig(connectionConfig);
	        
	        connManager.setMaxTotal(350);//最大连接数
	        connManager.setDefaultMaxPerRoute(300);//路由最大连接数
	        
	        SocketConfig socketConfig = SocketConfig.custom()
	                .setTcpNoDelay(true).build();
	        connManager.setDefaultSocketConfig(socketConfig);
	        
	        
	        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
	            //信任所有
	            @Override
	            public boolean isTrusted(X509Certificate[] chain, String authType)
	                    throws CertificateException {
	                return true;
	            }
	        }).build();
	        
	        httpClient = HttpClients.custom()
	                .setConnectionManager(connManager)
	                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

	                    @Override
	                    public boolean verify(String arg0, SSLSession arg1) {
	                        return true;
	                    }

	                    @Override
	                    public void verify(String host, SSLSocket ssl)
	                            throws IOException {
	                    }

	                    @Override
	                    public void verify(String host, X509Certificate cert)
	                            throws SSLException {
	                    }

	                    @Override
	                    public void verify(String host, String[] cns,
	                            String[] subjectAlts) throws SSLException {
	                    }
	                }))
	                .build();
	        
//	      HttpClientBuilder.create().disableRedirectHandling().build(); 
	        
	        requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES)
	                // 获取manager中连接 超时时间 0.5s
	                .setConnectionRequestTimeout(500)
	                // 连接服务器 超时时间  5s
	                .setConnectTimeout(5000)
	                // 服务器处理 超时时间 3s 
	                .setSocketTimeout(3000)
	                .build();
	        
	        requestConfigComplex = RequestConfig.custom()
	        // 获取manager中连接 超时时间 0.5s
	        .setConnectionRequestTimeout(500)
	        // 连接服务器 超时时间  5s
	        .setConnectTimeout(5000)
	        // 服务器处理 超时时间 5.2s+7s 
	        .setSocketTimeout(5200 + 60*1000)
	        .build();
	        }catch(Exception e){
	            throw new RuntimeException("创建httpClient失败", e);
	        }
	    }
	
	public static String doGet(String urlStr) {
		URL url = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("connection", "Keep-Alive");
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];
				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				return baos.toString();
			} else {
				throw new RuntimeException(" responseCode is not 200 ... ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
			}
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
			}
			conn.disconnect();
			return baos.toString();
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static final <T> T doPost(String url, String param, Class<T> returnType) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");
			conn.setUseCaches(false);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
			conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
			if (param != null && !param.trim().equals("")) {
				// 获取URLConnection对象对应的输出流
				out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		 T obj =new Gson().fromJson(result, returnType);
         return obj;
	}

	/**
	 * 根据传入的HttpPost/HttpGet发起请求
	 * @param request
	 * @return 正常返回结果字符串，200以外状态码或异常返回null
	 */
	public static final String excute(final HttpRequestBase request){
		CloseableHttpResponse response = null;
		try {
			request.setConfig(requestConfig);
			response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {//成功
				return EntityUtils.toString(response.getEntity(), ENCODING);
			}
			
		} catch (Exception e) {
			log.error("invoke post error", e);
		} finally{
			try {
				if(response != null)
					response.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	/**
	 * 判断ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())   ) ;
	}
	
	public static void main(String[] args) {
		String result=HttpUtils.doGet("http://api.map.baidu.com/geocoder?address="+"杭州市"+"&output=json");
		JSONObject json=(JSONObject) JSONObject.parse(result);
		System.out.println("address-----------------------"+json.toJSONString());
	}
}
