package kr.co.modernwave.server;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import kr.co.modernwave.log.Logger;
import kr.co.modernwave.nano.NanoServer;

public class WebServer {

	private NanoServer server;
	private int port;

	public WebServer(int port) {
		this.port = port;
		this.server = new NanoServer(port);
	}

	public int getPort() {
		return port;
	}

	public void setWebSocket(WSServer server) throws Exception {
		this.server.setPingPong(5000, 5);
		this.server.addWebSocketPath("/api/{urlpath}");
		this.server.createNanoServer(server);
	}

	public void start() throws Exception {
		Logger.Write.info("NanoServer Started on port : %d", port);

		server.addContext("/api/{urlpath}", new GetCommonServer());
	}

	public void setSecure(String string1, String string2) throws UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		this.server.setSecure(string1, string2);
	}

}
