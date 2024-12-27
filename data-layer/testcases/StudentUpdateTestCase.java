import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.exceptions.*;
import java.io.*;
class StudentUpdateTestCase
{
public static void main(String args[])
{
int rollNumber=Integer.parseInt(args[0]);
String name=args[1];
char gender=args[2].charAt(0);
boolean isIndian=Boolean.parseBoolean(args[3]);
int dd=Integer.parseInt(args[4]);
int mm=Integer.parseInt(args[5]);
int yyyy=Integer.parseInt(args[6]);
java.util.Date dateOfBirth;
dateOfBirth=new java.util.Date(yyyy-1990,mm-1,dd);
StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO();
studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(dateOfBirth);
try
{
StudentDAOInterface students;
students=new StudentDAO();
students.update(studentDTOInterface);
System.out.println("Student Updated");
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}