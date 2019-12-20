package valid;

public class Validator {
	public Validator() {

	}

	public boolean isNumberic(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean validateForm(String address, String cityRegion) {

		if (address == null)
			return false;
		if (cityRegion == null)
			return false;

		return true;
	}
}
