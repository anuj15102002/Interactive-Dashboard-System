import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
class StudentDeleteTestCase
{
public static void main(String args[])
{
int rollNumber=Integer.parseInt(args[0]);
try
{
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
studentManagerInterface.delete(rollNumber);
System.out.println("Student Deleted");
}catch(BLException blException)
{
System.out.println(blException);
}
}
}