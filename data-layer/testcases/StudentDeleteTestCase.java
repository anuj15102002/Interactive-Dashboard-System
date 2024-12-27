import com.thinking.machines.school.dl.exceptions.*;
import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
class StudentDeleteTestCase
{
public static void main(String args[])
{
try
{
int rollNumber=Integer.parseInt(args[0]);
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
studentDAOInterface.delete(rollNumber);
System.out.println("Student Deleted");
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}