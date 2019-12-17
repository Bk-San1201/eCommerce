package valid;

public class Validator {
	public Validator() {
		
	}
	public boolean isNumberic(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	public boolean validateForm(String name, String email, String phone, String address, String cityRegion, String ccNumber) {
		if (name == null)
			return false;
		if (email == null)
			return false;
		if (phone == null)
			return false;
		if (address == null)
			return false;
		if (cityRegion == null)
			return false;
		if (ccNumber == null)
			return false;

		return true;
	}
}
