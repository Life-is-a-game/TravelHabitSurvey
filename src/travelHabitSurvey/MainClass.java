/**
 * 
 */
package travelHabitSurvey;

import java.io.*;
import java.util.*;
/**
 * @author NathanClarke
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	private static Scanner scnr = new Scanner(System.in);
	private static List<String> domQuestions = new ArrayList<String>();
	private static List<String> bizQuestions = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		questionsInit();
		
		try {
			File f = new File("src/log.txt");
			if(!f.exists()) {
				f.createNewFile();
				
				FileWriter fWriter = new FileWriter("src/log.txt");
				BufferedWriter buffWrite = new BufferedWriter(fWriter);
				
				surveyInit(buffWrite);
				
				buffWrite.close();
			}
			else {
				
				FileWriter fW = new FileWriter("src/log.txt", true);
				BufferedWriter buff = new BufferedWriter(fW);
				
				surveyInit(buff);
				
				buff.close();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	private static void questionsInit() {
		// Domestic questions list initialisation
		domQuestions.add("How often do you travel domestically");
		domQuestions.add("What is the foremost reason for you to travel domestically? E.G. Studying, Travelling, Meeting friends.");
		domQuestions.add("What is your most frequently used method of transport for domestic travel? E.G. Bus");
		domQuestions.add("Do you have a free bus pass? Please input Yes/No");
		domQuestions.add("On Average, how long do you typically stay for when domestically travelling?");
		
		// Business questions list initialisation.
		bizQuestions.add("How often do you travel for business?");
		bizQuestions.add("Do you typically travel with anyone for business related purposes. If so, what is your typical party size?");
		bizQuestions.add("Do you travel abroad for work?");
		bizQuestions.add("If the answer to the previous question was Yes, which country do you travel to? If the answer was no, input N/A");
		bizQuestions.add("On Average, how long do you typically stay for when travelling for business purposes?");
	}
	
	private static void surveyInit(BufferedWriter bW) {
		boolean flag1 = true;
		boolean flag2 = true;
		int count = 0;
		System.out.println("Please enter your name");
		String name = "";
		
		try {
			name = scnr.nextLine();
			bW.write("Participant Name: " + name);
			bW.newLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("Do you travel domestically? \nThis means travelling within your country's borders for reasons other than business. E.G. College, holiday, etc.\nPlease enter Y or N");
			while(flag1) {
				
				String ans = "";
				if(scnr.hasNextLine()) {
					ans = scnr.nextLine();
				}
				ans = ans.toUpperCase();
				switch(ans) {
					default:
						System.out.println("That wasn't a valid answer, try again.");
						break;
					case "Y":
						domesticQuestions(bW);
						count++;
						flag1 = false;
						break;
					case "N":
						flag1 = false;
						break;
				}
			}
			System.out.println("\nDo you travel business-wise? \nPlease enter Y or N");
			while(flag2) {
				
				String ans = "";
				if(scnr.hasNextLine()) {
					ans = scnr.nextLine();
				}
				ans = ans.toUpperCase();
				switch(ans) {
					default:
						System.out.println("That wasn't a valid answer, try again.");
						break;
					case "Y":
						businessQuestions(bW);
						count++;
						flag2 = false;
						break;
					case "N":
						flag2 = false;
						break;
				}
			}
			
			bW.newLine();
			bW.write("--------------------------------------------------");
			bW.newLine();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		switch(count) {
			default:
				System.out.println("Thankyou for taking this survey.");
				break;
			case 0:
				System.out.println("Unfortunately, this survey concerns domestic and business travel; as such, you aren't eligible to complete it.");
				break;
		}
	}
	
	private static void domesticQuestions(BufferedWriter bW) {
		try {
			bW.newLine();
			bW.write("-- Domestic Travel Questions --\n");
			for(String s : domQuestions) {
				System.out.println(s);
				String str = "";
				if(scnr.hasNextLine()) {
					str = scnr.nextLine();
				}
				bW.write("Q: " + s);
				bW.newLine();
				bW.write("A: " + str);
				bW.newLine();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private static void businessQuestions(BufferedWriter bW) {
		try {
			bW.newLine();
			bW.write("-- Business Travel Questions --\n");
			for(String s : bizQuestions) {
				System.out.println(s);
				String str = "";
				if(scnr.hasNextLine()) {
					str = scnr.nextLine();
				}
				bW.write("Q: " + s);
				bW.newLine();
				bW.write("A: " + str);
				bW.newLine();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
