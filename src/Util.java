import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	static String getDateStr() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
