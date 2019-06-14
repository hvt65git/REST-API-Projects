package enum_practice;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

 enum SLocale {

    EN_US(Locale.US, "www.abc.com", "www.edc.com", "www.vvv.com",
            "www.earn.com");

    List<String> domains;
    Locale loc;

    private SLocale(Locale loc, String... domains) {
        this.domains = Arrays.asList(domains);
        this.loc = loc;
    }

    public List<String> getDomains() {
        return domains;
    }

    public Locale getLoc() {
        return loc;
    }

}

public class Example1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
