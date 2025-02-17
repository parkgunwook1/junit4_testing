package kr.co.modernwave.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

	public HttpURLConnection getHttpURLConnection(String strUrl, String method) {
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL(strUrl);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "application/json");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public String getHttpRespons(HttpURLConnection conn) {
		StringBuilder sb = null;

		try {
			if (conn.getResponseCode() == 200) {
				sb = readResopnseData(conn.getInputStream());
			} else {
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());
				sb = readResopnseData(conn.getErrorStream());
				System.out.println("error : " + sb.toString());
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect(); 
		}
		;
		if (sb == null)
			return null;

		return sb.toString();
	}

	public StringBuilder readResopnseData(InputStream in) {
		if (in == null)
			return null;

		StringBuilder sb = new StringBuilder();
		String line = "";

		try (InputStreamReader ir = new InputStreamReader(in); BufferedReader br = new BufferedReader(ir)) {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}
}