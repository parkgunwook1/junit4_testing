package kr.co.study.mock.tdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import kr.co.modernwave.database.utils.DatabaseManager;
import kr.co.modernwave.log.Logger;
import kr.co.study.mock.tdd.monitor.MonitorService;
import kr.co.study.mock.tdd.process.IProcessTime;
import kr.co.study.mock.tdd.process.ProcessCheck;
import kr.co.study.mock.tdd.process.ProcessMockCheck;

public class Application {

	public static void main(String[] args) throws Exception {
		String config = "config.xml";
		Properties p = null;

		try {
			p = GetProperties(config);
		} catch (Exception e) {
			Logger.Write.info(e.getMessage(), e);
		}

		DatabaseManager manager = null;
		String dbMode = p.getProperty("DB.Mode");

		IProcessTime pro = null;
		if (dbMode.equals("Y")) {
			// test mock data
			pro = new ProcessMockCheck();
		} else {
			// real process Class
			pro = new ProcessCheck(manager);
		}

		MonitorService monitor = new MonitorService(pro);
		int verifyTime = Integer.parseInt(p.getProperty("Verify.Time"));

		while (true) {
			try {
				Thread.sleep(verifyTime);
			} catch (Exception e) {
				Logger.Write.error(e.getMessage(), e);
			}
			// monitor service start...
			monitor.runone();
		}
	}

	public static Properties GetProperties(String path) throws InvalidPropertiesFormatException, IOException {
		FileInputStream fin = new FileInputStream(path);
		Properties p = new Properties();
		p.loadFromXML(fin);
		return p;
	}

}
