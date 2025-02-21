package kr.co.study.mock.tdd.process;

import kr.co.study.mock.tdd.condition.Condition;

public class ProcessMockCheck implements IProcessTime {

	@Override
	public Condition isProcessTimeCheck(int verifyMinute, String targerServerCode) throws Exception {
		return Condition.Normal;
	}

}
