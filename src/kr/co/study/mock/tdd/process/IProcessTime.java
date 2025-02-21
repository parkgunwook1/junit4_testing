package kr.co.study.mock.tdd.process;

import kr.co.study.mock.tdd.condition.Condition;

public interface IProcessTime {

	public Condition isProcessTimeCheck(int verifyMinute, String targerServerCode) throws Exception;

}
