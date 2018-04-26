package bd;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; 
import java.util.Iterator;

public class EmployeesDAL 
{
	private SQLException ex;

	public SQLException getEx() {
		return ex;
	}

	EmployeesDAL(){}

	public ArrayList <Employee> getEmployees()
	{
		
		System.out.println("weszlo do getEmployees");
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		try (Statement statement = OraConn.getConnection().createStatement(); )
		{
			String query = "SELECT * FROM EMP";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				employees.add(rs2Employee(resultSet));
			}
		}
		catch(SQLException ex)
		{
			this.ex = ex;
			System.out.println(ex);
		}
		catch(NullPointerException ex)
		{
			System.out.println("B³¹d createStatement");
		}
		
		return employees;
	}
	
	public Employee getEmployeeByEmployeeID(Employee emp) 
	{
		
		System.out.println("weszlo do getEmployeeByEmployeeID");
		System.out.println(emp.getEmployee_ID());
		
		int id = emp.getEmployee_ID();
		
		Employee searchedEmp = new Employee();
		
		try (Statement statement = OraConn.getConnection().createStatement())
		{
			String query = "SELECT * FROM HR.EMPLOYEES "
					+ "WHERE EMPLOYEE_ID = " + id;
			
			ResultSet resultSet = statement.executeQuery(query);
			searchedEmp.printEmployee1();
			
			if(resultSet != null)
			{
				searchedEmp = rs2Employee(resultSet);
			}
			else
			{
				System.out.println("W bazie nie ma pracownikow o takim ID");
			}
			
			System.out.println("przeszlo print");
		}
		catch(SQLException ex)
		{
			this.ex = ex;
			System.out.println(ex);
		}
		catch(NullPointerException ex)
		{
			System.out.println("B³¹d createStatement");
		}
		
		
		return searchedEmp; // w wypadku wywolania któregoœ z wyj¹tków Null;
	}
	
	
	private Employee rs2Employee(ResultSet resultSet)
	{
		Employee emp = null;
		
		try
		{
			int counter = 1;
			emp = new Employee(resultSet.getInt(counter++));
			
			
			emp.setFirst_Name(resultSet.getNString(counter++));
			emp.setLast_Name(resultSet.getNString(counter++));
			emp.setEmployee_ID(resultSet.getInt(counter++));
			emp.setDepartment_ID(resultSet.getInt(counter++));
			emp.setManager_ID(resultSet.getInt(counter++));
			emp.setSalary(resultSet.getDouble(counter++));
			emp.setHire_Date(resultSet.getDate(counter++).toLocalDate());
		
		}
		catch (SQLException ex) 
		{
			this.ex = ex;
		}
		return emp;
	}
	
	public int insertEmployee(Employee emp)
	{
		
		Statement statement = null;
		Connection connection = null;
		System.out.println("weszlo do insertEmployee");
		
		try
		{
			connection = OraConn.getConnection();
			statement = connection.createStatement();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			
			String HireDate = dtf.format(emp.getHire_Date());
			
			System.out.println(HireDate);
			
			String query = "INSERT INTO HR.EMPLOYEES(FIRST_NAME, LAST_NAME, EMPLOYEE_ID, DEPARTMENT_ID, MANAGER_ID, SALARY, HIRE_DATE)"
		    				+"VALUES ('"+ emp.getFirst_Name()+ "'," +emp.getLast_Name()+ "'," +emp.getEmployee_ID()+ "'," + emp.getDepartment_ID() +"'," +emp.getManager_ID()+"'," + emp.getSalary() + "'," + HireDate+"')" ;
			
			//String query = "INSERT INTO EMP(EMPNO, ENAME, MGR, JOB, HIREDATE, SAL, COMM, DEPTNO)"
     			//	+"VALUES ('"+ emp.getEMPNO()+ "'," +emp.getENAME()+ "'," +emp.getMGR()+ "'," + emp.getJOB() +"'," +HIREDATE+"'," + emp.getSAL() + "'," + emp.getCOMM()+ "',"+emp.getDEPTNO()+ "')";
			
			
			int affectedRows = statement.executeUpdate(query);
			
			System.out.println(affectedRows);
			
			OraConn.getConnection().commit();
			return affectedRows;
			
		}
		catch(SQLException ex)
		{
			this.ex = ex;
			return 0;
		}
		catch(NullPointerException ex)
		{
			System.out.println("B³¹d createStatement");
			return 0;
		}
	}
	
	
	public int updateEmployee(Employee emp1, Employee emp2) // emp1 - pracownik modyfikowany, emp2 - pracownik, którym modyfikujemy emp1
	{
		
		try(Statement statement = OraConn.getConnection().createStatement() )
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			String HireDate = dtf.format(emp2.getHire_Date());
			
			
			String query = "UPDATE EMPLOYEES SET " + " LAST_NAME = '" + emp2.getLast_Name() + "'," + "FIRST_NAME  = '" +emp2.getFirst_Name() + "',"
		     				+ "EMPLOYEE_ID ='" + emp2.getEmployee_ID() + "'," + "MANAGER_ID ='" +emp2.getManager_ID() + "',"
		     				+ "DEPARTMENT_ID = '" +emp2.getDepartment_ID() + "'," + "SALARY = '" +emp2.getSalary() + "',"
		     				+ "HIRE_DATE = toDate('" + HireDate + "', 'yyyyMMdd'), "
		     				+ "WHERE"
		     				+ "EMPLOYEE_ID = " + emp1.getEmployee_ID();
		     				
			int affectedRows = statement.executeUpdate(query);
			OraConn.getConnection().commit();
			return affectedRows;
		}
		catch(SQLException ex)
		{
			this.ex = ex;
			return 0;
		}
		catch(NullPointerException ex)
		{
			System.out.println("b³¹d createStatement");
			return 0;
		}
		
	}
	
	public int deleteEmployee(Employee emp)
	{
		
		System.out.println("weszlo do delete");
		
		try(Statement statement = OraConn.getConnection().createStatement() )
		{
			String query = "DELETE FROM HR.EMPLOYEES " 
		     				+ "WHERE"
		     				+ "EMPLOYEE_ID = " + emp.getEmployee_ID();
		
			int affectedRows = statement.executeUpdate(query);
			OraConn.getConnection().commit();
			return affectedRows;
		}
		catch(SQLException ex)
		{
			this.ex = ex;
			return 0;
		}
		catch(NullPointerException ex)
		{
			System.out.println("b³¹d createStatement");
			return 0;
		}
		
	}
}

