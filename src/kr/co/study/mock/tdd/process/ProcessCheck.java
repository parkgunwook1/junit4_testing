package kr.co.study.mock.tdd.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import kr.co.modernwave.database.utils.DatabaseManager;
import kr.co.modernwave.log.Logger;
import kr.co.study.mock.tdd.condition.Condition;

public class ProcessCheck implements IProcessTime {

	private DatabaseManager db;
	private Condition condition;

	public ProcessCheck(DatabaseManager db) {
		this.db = db;
	}

	@Override
	public Condition isProcessTimeCheck(int verifyMinute, String targerServerCode) throws Exception {

		LocalDateTime now = LocalDateTime.now();

		String table = String.format("t_rec_data%s", now.format(DateTimeFormatter.ofPattern("yyyyMM")));
		String verifyDate = now.minusMinutes(verifyMinute).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		String query = String.format(
				"SELECT COUNT(*) as count FROM %s WHERE REC_END_DATETIME > '%s' AND SERVER_CODE = %d", table,
				verifyDate, Integer.parseInt(targerServerCode));

		db.Execute(() -> query, (PreparedStatement st) -> {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int count = rs.getInt("count");
				if (count > 0)
					condition = Condition.Normal;
				else
					condition = Condition.NotNormal;
			}
		});

		Logger.Write.info("isAcrProceed query result:: " + condition.toString());
		return condition;
	}
}