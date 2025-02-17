package kr.co.modernwave.tdd;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;

import org.junit.Test;

import kr.co.modernwave.http.HttpUtils;
import kr.co.modernwave.server.WSServer;
import kr.co.modernwave.server.WebServer;

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
		assertEquals(port, server.getPort());

	}

}
