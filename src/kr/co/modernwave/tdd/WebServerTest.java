package kr.co.modernwave.tdd;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import kr.co.modernwave.server.WSServer;
import kr.co.modernwave.server.WebServer;

public class WebServerTest {

	@Test
	public void webServerTest() throws Exception {
		int port = 8080;

		// given : 준비과정
		WebServer server = new WebServer(port);
		URL url = new URL("http://localhost:" + port + "/api/gunwook");

		// when : 저장
		server.setWebSocket(new WSServer(port));
		server.start();

		// then : 확인
		assertEquals(port, server.getPort());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		int responseCode = connection.getResponseCode();
		assertEquals(HttpURLConnection.HTTP_OK, responseCode);

	}

}
