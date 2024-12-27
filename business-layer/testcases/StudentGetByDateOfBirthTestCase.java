import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.school.bl.manager.comparators.*;
import com.thinking.machines.school.bl.exceptions.*;
import java.util.*;
import java.text.*;
import java.io.*;
class StudentGetByDateOfBirthTestCase
{
public static void main(String args[])
{
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new java.text.SimpleDateFormat("dd/MM/yyyy");
int dd=Integer.parseInt(args[0]);
int mm=Integer.parseInt(args[1]);
int yyyy=Integer.parseInt(args[2]);
java.util.Date dob=new java.util.Date(yyyy-1900,mm-1,dd);
String dobString=simpleDateFormat.format(dob);
List<StudentBeanInterface>blStudents;
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
try
{
blStudents=studentManagerInterface.getByDateOfBirth(dob,StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface studentBeanInterface:blStudents)
{
java.util.Date dateOfBirth=studentBeanInterface.getDateOfBirth();
String dateOfBirthString;
dateOfBirthString=simpleDateFormat.format(dateOfBirth);
System.out.println(studentBeanInterface.getRollNumber());
System.out.println(studentBeanInterface.getName());
GENDER gender;
gender=studentBeanInterface.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
System.out.println(studentBeanInterface.getIsIndian());
System.out.println(dateOfBirthString);
}
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}