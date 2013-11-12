package samples.quickstart.service;

import java.util.HashMap;
import java.util.Map;

import samples.quickstart.service.pojos.HealthProfile;
import samples.quickstart.service.pojos.Person;


public class HealthProfileService {
	
	public static Map<String,Person> database = new HashMap<String,Person>();
	
	static
    {
    	Person pallino = new Person();
		Person pallo = new Person("Pinco","Pallo");
		HealthProfile hp = new HealthProfile(68.0,1.72);
		Person john = new Person("John","Doe",hp);
		
		database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
		database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
		database.put(john.getFirstname()+" "+john.getLastname(), john);
    }

	public String getHealthProfile(String fname, String lname) {
			// read the person from the DB
			Person p= database.get(fname+" "+lname);
			if (p!=null) { 
				return (fname+" "+lname+"'s health profile is: "+p.gethProfile().toString());
			} else {
				return (fname+" "+lname+" is not in the database");
			}
	}

	public String getParameterValue(String fname, String lname, String parameter) {
                        // read the person from the DB
                        Person p= database.get(fname+" "+lname);
		if(p!=null){
			if (parameter.equals("Height")){
				return Double.toString(p.gethProfile().getHeight());
			}
			else if (parameter.equals("Weight")){
				return Double.toString(p.gethProfile().getWeight());
			}
			else if (parameter.equals("BMI")){
				return Double.toString(p.gethProfile().getBMI());
			}
			return "Invalid info requested";
		}
		return ("Invalid information requested from the healthprofile..");
	}
}
