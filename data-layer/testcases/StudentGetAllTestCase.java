import com.thinking.machines.school.dl.exceptions.*;
import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import java.util.*;
import java.text.*;
class StudentGetAllTestCase
{
public static void main(String args[])
{
try
{
List<StudentDTOInterface> students;
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
StudentDTOInterface studentDTOInterface;
int rollNumber;
String name;
char gender;
boolean isIndian;
java.util.Date dateOfBirth;
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String dateOfBirthString;
students=studentDAOInterface.getAll();
int x;
System.out.println("Number of Students: "+students.size());
for(x=0;x<students.size();++x)
{
studentDTOInterface=students.get(x);
rollNumber=studentDTOInterface.getRollNumber();
name=studentDTOInterface.getName();
gender=studentDTOInterface.getGender();
isIndian=studentDTOInterface.getIsIndian();
dateOfBirth=studentDTOInterface.getDateOfBirth();
dateOfBirthString=simpleDateFormat.format(dateOfBirth);
System.out.println("Roll Number: "+rollNumber);
System.out.println("Name: "+name);
System.out.println("Gender: "+gender);
System.out.println("Is Indian: "+isIndian);
System.out.println("Date of Birth: "+dateOfBirthString);
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}