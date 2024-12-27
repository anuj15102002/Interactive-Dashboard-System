import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.school.bl.manager.comparators.*;
import com.thinking.machines.school.bl.exceptions.*;
import java.util.*;
class StudentGetByAgeTestCase
{
public static void main(String args[])
{
int age=Integer.parseInt(args[0]);
List<StudentBeanInterface>blStudents;
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new java.text.SimpleDateFormat("dd/MM/yyyy");
try
{
blStudents=studentManagerInterface.getByAge(age,StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface studentBeanInterface:blStudents)
{
java.util.Date dateOfBirth=studentBeanInterface.getDateOfBirth();
System.out.println(studentBeanInterface.getRollNumber());
System.out.println(studentBeanInterface.getName());
GENDER gender;
gender=studentBeanInterface.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
System.out.println(studentBeanInterface.getIsIndian());
String dateOfBirthString;
dateOfBirthString=simpleDateFormat.format(dateOfBirth);
System.out.println(dateOfBirthString);
}
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}