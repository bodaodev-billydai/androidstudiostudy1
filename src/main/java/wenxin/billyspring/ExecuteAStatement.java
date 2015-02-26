package wenxin.billyspring;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExecuteAStatement {
	private JdbcTemplate jt;
	private DataSource dataSource;

	public void doExecute() {
		jt = new JdbcTemplate(dataSource);
		jt.execute("create table mytable (id integer, name varchar(100))");
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}