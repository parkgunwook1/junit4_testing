package kr.co.study.mock.tdd.monitor;

import java.util.ArrayList;
import java.util.List;

import kr.co.modernwave.log.Logger;
import kr.co.study.mock.tdd.condition.Condition;
import kr.co.study.mock.tdd.process.IProcessTime;

public class MonitorService {

	private IProcessTime timeCheck;

	public MonitorService(IProcessTime timeCheck) {
		this.timeCheck = timeCheck;
	}

	public void runone() {
		List<String> buffer = new ArrayList<>();

		try {
			if (timeCheck.isProcessTimeCheck(10, "500") == Condition.Normal) {
				try {
					// mock 이면 무조건 Normal이 오니 , 특정 프로세스 실행할 수 있다.
					buffer.add("mock normal day testing...");
					Logger.Write.info("mock normal day testing...");
				} catch (Exception e) {
					Logger.Write.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			Logger.Write.error(e.getMessage(), e);
		}

		if (buffer.size() != 0) {
			Logger.Write.info("db sms insert...");
		}
	}

}
