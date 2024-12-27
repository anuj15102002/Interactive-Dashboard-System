import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.enums.*;
class StudentGetByRollNumberTestCase
{
public static void main(String args[])
{
try
{
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new java.text.SimpleDateFormat("dd/MM/yyyy");
int rollNumber=Integer.parseInt(args[0]);
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
StudentBeanInterface studentBeanInterface;
studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
String name=studentBeanInterface.getName();
boolean isIndian=studentBeanInterface.getIsIndian();
java.util.Date dateOfBirth=studentBeanInterface.getDateOfBirth();
String dateOfBirthString=simpleDateFormat.format(dateOfBirth);
GENDER gender;
gender=studentBeanInterface.getGender();
char vGender;
if(gender==GENDER.MALE)vGender='M';
else if(gender==GENDER.FEMALE)vGender='F';
else vGender='T';
System.out.println(rollNumber);
System.out.println(name);
System.out.println(isIndian);
System.out.println(vGender);
System.out.println(dateOfBirthString);
}catch(BLException blException)
{
System.out.println(blException);
}
}
}