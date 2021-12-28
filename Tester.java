import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class Tester {

	public static void main(String[] args) {
		DatabaseHelper dbHelper=new DatabaseHelper();
		RandomHelper randomHelper = new RandomHelper();


		//INSERTING PERSONS
		System.out.println("Inserting Persons...");
		ArrayList<Integer> social_insurance_id_list = new ArrayList<>();
		for(int i=100000; i < 101200; ++i) {
			social_insurance_id_list.add(i);
			String name = randomHelper.getRandomFirstName() + " " + randomHelper.getRandomLastName();
			dbHelper.insertIntoPerson(i, name, randomHelper.getRandomBirthdate());
		}
		int num_of_inserted_persons = dbHelper.selectcountFromPerson();
		System.out.println("Number of inserted Persons: " + num_of_inserted_persons);


		//INSERTING DATES
		System.out.println("Inserting Dates...");
		String line_date;
		try (BufferedReader br = new BufferedReader(new FileReader("./src/dates.txt"))) {
			while ((line_date = br.readLine()) != null) {
				dbHelper.insertIntoDate(line_date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int num_of_inserted_dates = dbHelper.selectcountFromDate();
		System.out.println("Number of inserted Dates: " + num_of_inserted_dates);


		//INSERTING TEAMS
		System.out.println("Inserting Teams...");
		String line_team;
		try (BufferedReader br = new BufferedReader(new FileReader("./src/teams.csv"))) {
			while ((line_team = br.readLine()) != null) {
				dbHelper.insertIntoTeam(line_team, randomHelper.getRandomInteger(30, 40));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int num_of_inserted_teams = dbHelper.selectcountFromTeam();
		System.out.println("Number of inserted Teams: " + num_of_inserted_teams);


		//INSERTING SPORT FACILITYS
		System.out.println("Inserting Sport Facilitys...");
		ArrayList<String> sport_faclity_names = new ArrayList<>();
		String line_city;
		try (BufferedReader br = new BufferedReader(new FileReader("./src/city.csv"))) {
			while ((line_city = br.readLine()) != null) {
				String sf_name = randomHelper.getRandomSportFacility() + " " + line_city;
				dbHelper.insertIntoSportFacility(sf_name, randomHelper.getRandomStreet(), line_city,
												 randomHelper.getRandomInteger(10000, 90000));
				sport_faclity_names.add(sf_name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int num_of_inserted_sportfacilitys = dbHelper.selectcountFromSportFacility();
		System.out.println("Number of inserted Sportfacilitys: " + num_of_inserted_sportfacilitys);


		//INSERTING FIELD
		System.out.println("Inserting Fields...");
		for(int i = 1; i <= 110; ++i){
			dbHelper.insertIntoField(sport_faclity_names.get(i), randomHelper.getRandomSportTypes(),
									 randomHelper.getRandomGround());
		}
		int num_of_inserted_fields = dbHelper.selectcountFromField();
		System.out.println("Number of inserted Fields: " + num_of_inserted_fields);


		//INSERTING PLAYS IN
		System.out.println("Inserting Plays in...");
		for(int i = 0; i < 120; ++i){
			dbHelper.insertIntoPlaysIn(randomHelper.getRandomInteger(1, dbHelper.selectcountFromTeam()),
					randomHelper.getRandomDate(),
					sport_faclity_names.get(randomHelper.getRandomInteger(0, sport_faclity_names.size()-1)));
		}
		int num_of_inserted_playsin = dbHelper.selectcountFromPlaysIn();
		System.out.println("Number of inserted Plays In: " + num_of_inserted_playsin);


		//INSERTING COACH
		System.out.println("Inserting Coaches...");
		for(int i = 0; i < 120; ++i){
			dbHelper.insertIntoCoach(
					social_insurance_id_list.get(i),
					randomHelper.getRandomInteger(10000, 1000000),
					randomHelper.getRandomInteger(1, num_of_inserted_teams), randomHelper.getRandomCoachingsytle());
		}
		int num_of_inserted_coach = dbHelper.selectcountFromCoach();
		System.out.println("Number of inserted Coaches: " + num_of_inserted_coach);


		//INSERTING PLAYER
		System.out.println("Inserting Players...");
		for(int i = 120; i < 240; ++i){
			dbHelper.insertIntoPlayer(social_insurance_id_list.get(i), randomHelper.getRandomPosition(),
					randomHelper.getRandomInteger(10000, 1000000),
					randomHelper.getRandomInteger(1, num_of_inserted_teams));
		}
		int num_of_inserted_player = dbHelper.selectcountFromPlayer();
		System.out.println("Number of inserted Players: " + num_of_inserted_player);


		//INSERTING MANAGER
		System.out.println("Inserting Manager...");
		for(int i = 240; i < 360; ++i){
			dbHelper.insertIntoManager(social_insurance_id_list.get(i),
					i-239,
					randomHelper.getRandomInteger(10000, 1000000));
		}
		int num_of_inserted_manager = dbHelper.selectcountFromManager();
		System.out.println("Number of inserted Managers: " + num_of_inserted_manager);


		//INSERTING IS FRIEND
		System.out.println("Inserting Friendships...");
		for(int i = 0; i < 60; ++i){
			dbHelper.insertIntoIsFriend(i+1, i+2);
			dbHelper.insertIntoIsFriend(i+1, i+3);
		}
		int num_of_inserted_friends = dbHelper.selectcountFromIsFriend();
		System.out.println("Number of inserted Friends: " + num_of_inserted_friends);


		dbHelper.close();
	}

}
