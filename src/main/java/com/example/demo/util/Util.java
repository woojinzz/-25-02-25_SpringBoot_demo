package com.example.demo.util;

public class Util {

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static String jsReplace(String msg, String uri) {
		
		if (msg == null) {
			msg = "";
		}
		
		if (uri == null) {
			uri = "";
		}
		
		return String.format("""
				<script>
					const msg = '%s'.trim();
					
					if (msg.length > 0) {
						alert(msg);
					}
					
					location.replace('%s');
				</script>
				""", msg, uri);
	}

	public static String jsHistoryBack(String format) {
		
		if (format == null) {
			format = "";
		}
		
		return String.format("""
				<script>
					const msg = '%s'.trim();
					
					if (msg.length > 0) {
						alert(msg);
					}
					
					history.back();
				</script>
				""", format);
	}

}
