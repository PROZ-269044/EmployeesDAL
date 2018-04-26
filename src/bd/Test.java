package bd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		
		int error = -1;
		
		
		// TODO Auto-generated method stub
		
		// tworzenie obiektow do testów
		
		//utworzenie formatowania
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		//ustalenie polaczenia
		
		// utworzenie obiektów dat
		LocalDate localDate1 = LocalDate.parse("1994/01/14", dtf);
		LocalDate localDate2 = LocalDate.parse("2015/09/29", dtf);
		LocalDate localDate3 = LocalDate.parse("2009/03/03", dtf);
		LocalDate localDate4 = LocalDate.parse("2001/02/09", dtf);
		
		//utworzenie obiektów klasy pracownika
		Employee Employee1 = new Employee("Pawe³", "Radzimirski", 10, 200, 20, 4000.0, localDate1);
		Employee Employee2 = new Employee("£ukasz", "Baran", 30, 100, 20, 3500.0, localDate2);
		Employee Employee3 = new Employee("£ukasz", "Baran", 40, 80, 20, 5500.0, localDate3);
		Employee Employee4 = new Employee("Anna", "Zawadzka", 45, 100, 20, 6500.0, localDate4);
		
		
		// na rzecz tabeli EMP
		//Employee Employee1 = new Employee(7000, "Radzimirski", "PROGRAMMER", 7777, localDate1 , 4000.0, 0, 10);
		//Employee Employee2 = new Employee(7100, "Baran", "ENGINEER", 7789, localDate2 , 4000.0, 0, 10);
		//Employee Employee3 = new Employee(7050, "Zalewska", "PROGRAMMER", 7734, localDate3 , 5000.0, 0, 10);
		//Employee Employee4 = new Employee(7020, "Brodzik", "CLERK", 7789, localDate4 , 3000.0, 0, 10);
		
	
		//Employee CopyEmployee = new Employee();
		
		
		//utworzenie obiektu klasy dostêpowej
		EmployeesDAL dataBase = new EmployeesDAL();
		
		// rozpoczecie programu
		OraConn.register();
		OraConn.open();
		
		error = dataBase.insertEmployee(Employee1);
		System.out.println(error);
		
		
		dataBase.insertEmployee(Employee3);
		dataBase.insertEmployee(Employee2);
		
		dataBase.getEmployeeByEmployeeID(Employee1);
		dataBase.getEmployees();
		
		dataBase.deleteEmployee(Employee1);
		
		dataBase.insertEmployee(Employee4);
		dataBase.updateEmployee(Employee2, Employee1);
		
		OraConn.close();
	}

}
