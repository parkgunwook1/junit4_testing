package kr.co.study.junit.tdd;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;

import org.junit.Test;

import kr.co.study.http.HttpUtils;
import kr.co.study.server.WSServer;
import kr.co.study.server.WebServer;

public class WebServerTest {
	
	@Test
	public void webServerTest() throws Exception {

		// given : 준비과정
		HttpUtils httpUtils = new HttpUtils();
		int port = 8080;
		
		WebServer server = new WebServer(port);
		server.setWebSocket(new WSServer(port));
		server.start();
		
		String url = "http://localhost:" + port + "/api/gunwook";
		String method = "GET";
		String result = "";
		
		HttpURLConnection conn = null;
		
		conn = httpUtils.getHttpURLConnection(url, method);
		result = httpUtils.getHttpRespons(conn);
		System.out.println(result);
		

		// when : 저장

		// then : 확인
		assertEquals("test" ,port, server.getPort());

	}

}
