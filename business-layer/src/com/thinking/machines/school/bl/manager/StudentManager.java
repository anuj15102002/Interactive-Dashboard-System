package com.thinking.machines.school.bl.manager;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.manager.comparators.*;

import com.thinking.machines.school.dl.exceptions.*;
import com.thinking.machines.school.dl.dao.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import java.util.*;
import java.io.*;
public class StudentManager implements StudentManagerInterface
{
private int getAge(java.util.Date dateOfBirth)
{
java.util.Date today=new java.util.Date();
int todayDD=today.getDate();
int todayMM=today.getMonth()+1;
int todayYYYY=today.getYear()+1990;

int dobDD=dateOfBirth.getDate();
int dobMM=dateOfBirth.getMonth()+1;
int dobYYYY=dateOfBirth.getYear()+1990;
int age=todayYYYY-dobYYYY;

if(todayMM<dobMM)
{
age--;
}
else if(todayMM==dobMM)
{
if(todayDD<dobDD)
{
age--;
}
}
return age;
}
public void add(StudentBeanInterface studentBeanInterface) throws BLException
{
int rollNumber;
String name;
GENDER gender;
java.util.Date dateOfBirth;
boolean isIndian;
rollNumber=studentBeanInterface.getRollNumber();
name=studentBeanInterface.getName();
dateOfBirth=studentBeanInterface.getDateOfBirth();
isIndian=studentBeanInterface.getIsIndian();
gender=studentBeanInterface.getGender();
if(rollNumber<=0)throw new BLException("Invalid rollNumber");
if(dateOfBirth==null)throw new BLException("Date of birth required");
if(getAge(dateOfBirth)<5)throw new BLException("Age cannot be less than 5");

if(name==null)throw new BLException("name required");
name=name.trim();
if(name.length()<=0)throw new BLException("name required");
if(name.length()>50)throw new BLException("Name cannot consist more than 50 words");
StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO();
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
if(gender==GENDER.MALE)studentDTOInterface.setGender('M');
else if(gender==GENDER.FEMALE)studentDTOInterface.setGender('F');
else studentDTOInterface.setGender('T');
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(dateOfBirth);
try
{
studentDAOInterface.add(studentDTOInterface);
}catch(DAOException daoException)
{
throw new BLException(daoException.getMessage());
}	
}
public  void update(StudentBeanInterface studentBeanInterface) throws BLException
{
int rollNumber;
String name;
GENDER gender;
java.util.Date dateOfBirth;
boolean isIndian;
rollNumber=studentBeanInterface.getRollNumber();
name=studentBeanInterface.getName();
gender=studentBeanInterface.getGender();
isIndian=studentBeanInterface.getIsIndian();
dateOfBirth=studentBeanInterface.getDateOfBirth();
if(rollNumber<=0)throw new BLException("Invalid rollNumber");
if(dateOfBirth==null)throw new BLException("Date of birth required");
if(getAge(dateOfBirth)<5)throw new BLException("Age cannot be less than 5");
if(name==null)throw new BLException("name required");
name=name.trim();
if(name.length()<=0)throw new BLException("name required");
if(name.length()>50)throw new BLException("Name cannot consist more than 50 words");
StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO();
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
if(gender==GENDER.MALE)studentDTOInterface.setGender('M');
else if(gender==GENDER.FEMALE)studentDTOInterface.setGender('F');
else studentDTOInterface.setGender('T');
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(dateOfBirth);
try
{
studentDAOInterface.update(studentDTOInterface);
}catch(DAOException daoException)
{
throw new BLException(daoException.getMessage());
}	
}
public void delete(int rollNumber)throws BLException
{
if(rollNumber<=0)throw new BLException("Invalid Roll Number");
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
try
{
studentDAOInterface.delete(rollNumber);
}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
public StudentBeanInterface getByRollNumber(int rollNumber) throws BLException
{
if(rollNumber<=0)throw new BLException("Invalid Roll Number");
StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
try
{
studentDTOInterface=studentDAOInterface.get(rollNumber);
StudentBeanInterface studentBeanInterface;
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(studentDTOInterface.getName());
char gender;
gender=studentDTOInterface.getGender();
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='F')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
return studentBeanInterface;
}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
public List<StudentBeanInterface>getAll(StudentManagerInterface.ORDER_BY orderBy)throws BLException
{
List<StudentBeanInterface> blStudents;
List<StudentDTOInterface> dlStudents;
blStudents=new ArrayList<StudentBeanInterface>();
StudentBeanInterface studentBeanInterface;
StudentDAOInterface studentDAOInterface;
try
{
studentDAOInterface=new StudentDAO();
dlStudents=studentDAOInterface.getAll();
for(StudentDTOInterface studentDTOInterface:dlStudents)
{
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
studentBeanInterface.setName(studentDTOInterface.getName());
char gender=studentDTOInterface.getGender();
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='F')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
blStudents.add(studentBeanInterface);
}
if(orderBy==StudentManagerInterface.ORDER_BY.ROLL_NUMBER)
{
Collections.sort(blStudents,new StudentRollNumberComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.NAME)
{
Collections.sort(blStudents,new StudentNameComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.GENDER)
{
Collections.sort(blStudents,new StudentGenderComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH)
{
Collections.sort(blStudents,new StudentDateOfBirthComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.AGE)
{
Collections.sort(blStudents,new StudentAgeComparator());
}
return blStudents;
}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
public List<StudentBeanInterface>getByGender(GENDER gender,StudentManagerInterface.ORDER_BY orderBy) throws BLException
{
List<StudentBeanInterface> blStudents;
List<StudentDTOInterface> dlStudents;
blStudents=new ArrayList<StudentBeanInterface>();
StudentBeanInterface studentBeanInterface;
StudentDAOInterface studentDAOInterface;
try
{
studentDAOInterface=new StudentDAO();
dlStudents=studentDAOInterface.getAll();
for(StudentDTOInterface studentDTOInterface:dlStudents)
{
char gen=studentDTOInterface.getGender();
GENDER g;
if(gen=='M')g=GENDER.MALE;
else if(gen=='F')g=GENDER.FEMALE;
else g=GENDER.TRANSGENDER;
if(g==gender)
{
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
studentBeanInterface.setName(studentDTOInterface.getName());
char vGender=studentDTOInterface.getGender();
if(vGender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(vGender=='M')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());

blStudents.add(studentBeanInterface);
}
}
if(orderBy==StudentManagerInterface.ORDER_BY.ROLL_NUMBER)
{
Collections.sort(blStudents,new StudentRollNumberComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.NAME)
{
Collections.sort(blStudents,new StudentNameComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.GENDER)
{
Collections.sort(blStudents,new StudentGenderComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH)
{
Collections.sort(blStudents,new StudentDateOfBirthComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.AGE)
{
Collections.sort(blStudents,new StudentAgeComparator());
}
return blStudents;
}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
public List<StudentBeanInterface>getIndian(StudentManagerInterface.ORDER_BY orderBy)throws BLException
{
List<StudentBeanInterface> blStudents;
List<StudentDTOInterface> dlStudents;
blStudents=new ArrayList<StudentBeanInterface>();
StudentBeanInterface studentBeanInterface;
StudentDAOInterface studentDAOInterface;
try
{
studentDAOInterface=new StudentDAO();
dlStudents=studentDAOInterface.getAll();
for(StudentDTOInterface studentDTOInterface:dlStudents)
{
if(studentDTOInterface.getIsIndian())
{
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
studentBeanInterface.setName(studentDTOInterface.getName());
char gender=studentDTOInterface.getGender();
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='M')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
blStudents.add(studentBeanInterface);
}
}
if(orderBy==StudentManagerInterface.ORDER_BY.ROLL_NUMBER)
{
Collections.sort(blStudents,new StudentRollNumberComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.NAME)
{
Collections.sort(blStudents,new StudentNameComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.GENDER)
{
Collections.sort(blStudents,new StudentGenderComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH)
{
Collections.sort(blStudents,new StudentDateOfBirthComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.AGE)
{
Collections.sort(blStudents,new StudentAgeComparator());
}
return blStudents;

}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}

}
public List<StudentBeanInterface>getForeigner(StudentManagerInterface.ORDER_BY orderBy)throws BLException
{
List<StudentBeanInterface> blStudents;
List<StudentDTOInterface> dlStudents;
blStudents=new ArrayList<StudentBeanInterface>();
StudentBeanInterface studentBeanInterface;
StudentDAOInterface studentDAOInterface;
try
{
studentDAOInterface=new StudentDAO();
dlStudents=studentDAOInterface.getAll();
for(StudentDTOInterface studentDTOInterface:dlStudents)
{
if(studentDTOInterface.getIsIndian()==false)
{
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
studentBeanInterface.setName(studentDTOInterface.getName());
char gender=studentDTOInterface.getGender();
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='M')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
blStudents.add(studentBeanInterface);
}
}
if(orderBy==StudentManagerInterface.ORDER_BY.ROLL_NUMBER)
{
Collections.sort(blStudents,new StudentRollNumberComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.NAME)
{
Collections.sort(blStudents,new StudentNameComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.GENDER)
{
Collections.sort(blStudents,new StudentGenderComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH)
{
Collections.sort(blStudents,new StudentDateOfBirthComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.AGE)
{
Collections.sort(blStudents,new StudentAgeComparator());
}
return blStudents;

}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
public List<StudentBeanInterface>getByDateOfBirth(java.util.Date dateOfBirth,StudentManagerInterface.ORDER_BY orderBy) throws BLException
{
List<StudentBeanInterface> blStudents;
List<StudentDTOInterface> dlStudents;
blStudents=new ArrayList<StudentBeanInterface>();
StudentBeanInterface studentBeanInterface;
StudentDAOInterface studentDAOInterface;
int dd,mm,yyyy;
java.util.Date dob;
try
{
studentDAOInterface=new StudentDAO();
dlStudents=studentDAOInterface.getAll();
for(StudentDTOInterface studentDTOInterface:dlStudents)
{
dob=studentDTOInterface.getDateOfBirth();
dd=dob.getDate();
mm=dob.getMonth();
yyyy=dob.getYear();
if(dateOfBirth.getDate()==dd && dateOfBirth.getMonth()==mm && dateOfBirth.getYear()==yyyy)
{
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
studentBeanInterface.setName(studentDTOInterface.getName());
char gender=studentDTOInterface.getGender();
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='M')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
blStudents.add(studentBeanInterface);
}
}
if(orderBy==StudentManagerInterface.ORDER_BY.ROLL_NUMBER)
{
Collections.sort(blStudents,new StudentRollNumberComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.NAME)
{
Collections.sort(blStudents,new StudentNameComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.GENDER)
{
Collections.sort(blStudents,new StudentGenderComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH)
{
Collections.sort(blStudents,new StudentDateOfBirthComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.AGE)
{
Collections.sort(blStudents,new StudentAgeComparator());
}
return blStudents;

}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
public List<StudentBeanInterface>getByAge(int age,StudentManagerInterface.ORDER_BY orderBy)throws BLException
{
List<StudentBeanInterface> blStudents;
List<StudentDTOInterface> dlStudents;
blStudents=new ArrayList<StudentBeanInterface>();
StudentBeanInterface studentBeanInterface;
StudentDAOInterface studentDAOInterface;
try
{
studentDAOInterface=new StudentDAO();
dlStudents=studentDAOInterface.getAll();
for(StudentDTOInterface studentDTOInterface:dlStudents)
{
if(getAge(studentDTOInterface.getDateOfBirth())==age)
{
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(studentDTOInterface.getRollNumber());
studentBeanInterface.setName(studentDTOInterface.getName());
char gender=studentDTOInterface.getGender();
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='M')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
studentBeanInterface.setIsIndian(studentDTOInterface.getIsIndian());
studentBeanInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
blStudents.add(studentBeanInterface);
}
}
if(orderBy==StudentManagerInterface.ORDER_BY.ROLL_NUMBER)
{
Collections.sort(blStudents,new StudentRollNumberComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.NAME)
{
Collections.sort(blStudents,new StudentNameComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.GENDER)
{
Collections.sort(blStudents,new StudentGenderComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH)
{
Collections.sort(blStudents,new StudentDateOfBirthComparator());
}else if(orderBy==StudentManagerInterface.ORDER_BY.AGE)
{
Collections.sort(blStudents,new StudentAgeComparator());
}
return blStudents;

}catch(DAOException daoException)
{
throw new BLException("DAOException: "+daoException);
}
}
}