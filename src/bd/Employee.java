package bd;

import java.time.LocalDate;

public class Employee {
	
	private String First_Name;
	private String Last_Name;
	private int Employee_ID;
	private double Salary;
	private int Department_ID;
	private int Manager_ID;
	private LocalDate Hire_Date;
	
	
	public int getDepartment_ID() {
		return Department_ID;
	}

	public void setDepartment_ID(int department_ID) {
		Department_ID = department_ID;
	}

	public int getManager_ID() {
		return Manager_ID;
	}

	public void setManager_ID(int manager_ID) {
		Manager_ID = manager_ID;
	}

	public LocalDate getHire_Date() {
		return Hire_Date;
	}

	public void setHire_Date(LocalDate localDate) {
		Hire_Date = localDate;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}

	
	public String getFirst_Name() {
		return First_Name;
	}
	
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	
	public String getLast_Name() {
		return Last_Name;
	}
	
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	
	public int getEmployee_ID() {
		return Employee_ID;
	}
	
	public void setEmployee_ID(int employee_ID) {
		Employee_ID = employee_ID;
	}
	
	public Employee(int Employee_ID) {
		this.Employee_ID = Employee_ID;
		this.First_Name = null;
		this.Last_Name = null;
		this.Employee_ID = 0;
		this.Department_ID = 0;
		this.Manager_ID = 0;
		this.Hire_Date = null;
	}
	
	Employee(String First_Name, String Last_Name, int Employee_ID,  int Department_ID, int Manager_ID, double Salary, LocalDate HireDate){
		
		this.First_Name = First_Name;
		this.Last_Name = Last_Name; 
		this.Employee_ID = Employee_ID;
		this.Department_ID = Department_ID;
		this.Manager_ID = Manager_ID;
		this.Salary = Salary;
		this.Hire_Date = HireDate;
	}
	
	public Employee(Employee emp)
	{
		this.First_Name = emp.First_Name;
		this.Last_Name = emp.Last_Name; 
		this.Employee_ID = emp.Employee_ID;
		this.Department_ID = emp.Department_ID;
		this.Manager_ID = emp.Manager_ID;
		this.Salary = emp.Salary;
		this.Hire_Date = emp.Hire_Date;
	}

	
	public void printEmployee1() {
		System.out.println(getFirst_Name());
		System.out.println(getLast_Name());
		System.out.println(getEmployee_ID());
		System.out.println(getDepartment_ID());
		System.out.println(getManager_ID());
		System.out.println(getSalary());
		System.out.println(getHire_Date());
	
	}
	
	
		public Employee() {}

}
