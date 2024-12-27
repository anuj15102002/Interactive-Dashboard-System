package com.thinking.machines.school.dl.dao;
import java.io.*;
import com.thinking.machines.school.dl.dao.interfaces.*;
import com.thinking.machines.school.dl.dto.interfaces.*;
import com.thinking.machines.school.dl.dto.*;
import com.thinking.machines.school.dl.exceptions.*;
import java.util.*;
import java.text.*;
public class StudentDAO implements StudentDAOInterface
{
private final static String dataFile="student.data";
public void add(StudentDTOInterface studentDTOInterface)throws DAOException
{
try
{
String dateOfBirthString;
int rollNumber;
int vRollNumber;
String name;
char gender;
boolean isIndian;
java.util.Date dateOfBirth;
rollNumber=studentDTOInterface.getRollNumber();
name=studentDTOInterface.getName();
gender=studentDTOInterface.getGender();
isIndian=studentDTOInterface.getIsIndian();
dateOfBirth=studentDTOInterface.getDateOfBirth();
RandomAccessFile randomAccessFile;
File file=new File(dataFile);
randomAccessFile=new RandomAccessFile(file,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
if(vRollNumber==rollNumber)
{
randomAccessFile.close();
throw new DAOException(rollNumber+" exists.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.writeBytes(rollNumber+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
dateOfBirthString=dateOfBirth.getDate()+"/"+(dateOfBirth.getMonth()+1)+"/"+(dateOfBirth.getYear()+1900);
randomAccessFile.writeBytes(dateOfBirthString+"\n");
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException("IOException: "+ioException);
}
catch(Exception exception)
{
throw new DAOException("Exception: "+exception);
}
}
public void update(StudentDTOInterface studentDTOInterface)throws DAOException
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int vRollNumber;
String rollNumberString;
String name;
String genderString;
String isIndianString;
String dateOfBirthString;
java.util.Date dateOfBirth;
try
{
File file=new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid Roll Number");
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Roll Number");
}
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
vRollNumber=Integer.parseInt(rollNumberString);
if(vRollNumber!=studentDTOInterface.getRollNumber())
{
found=true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invlaid Roll Number");
}
randomAccessFile.seek(0);
File tmpFile=new File("faltu.data");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
name=randomAccessFile.readLine();
genderString=randomAccessFile.readLine();
isIndianString=randomAccessFile.readLine();
dateOfBirthString=randomAccessFile.readLine();
//successfully readed the first record and many one by one
vRollNumber=Integer.parseInt(rollNumberString);
if(vRollNumber!=studentDTOInterface.getRollNumber())
{
tmpRandomAccessFile.writeBytes(rollNumberString+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(genderString+"\n");
tmpRandomAccessFile.writeBytes(isIndianString+"\n");
tmpRandomAccessFile.writeBytes(dateOfBirthString+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(studentDTOInterface.getRollNumber()+"\n");
tmpRandomAccessFile.writeBytes(studentDTOInterface.getName()+"\n");
tmpRandomAccessFile.writeBytes(studentDTOInterface.getGender()+"\n");
tmpRandomAccessFile.writeBytes(studentDTOInterface.getIsIndian()+"\n");
dateOfBirthString=simpleDateFormat.format(studentDTOInterface.getDateOfBirth());
tmpRandomAccessFile.writeBytes(dateOfBirthString+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
//never ever delete the temporary file
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException("IOException: "+ioException.getMessage());
}
}
public void delete(int rollNumber)throws DAOException
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
int vRollNumber;
String rollNumberString;
String name;
String genderString;
String isIndianString;
String dateOfBirthString;
java.util.Date dateOfBirth;
try
{
File file=new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid Roll Number");
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Roll Number");
}
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
vRollNumber=Integer.parseInt(rollNumberString);
if(vRollNumber!=rollNumber)
{
found=true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invlaid Roll Number");
}
randomAccessFile.seek(0);
File tmpFile=new File("faltu.data");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
name=randomAccessFile.readLine();
genderString=randomAccessFile.readLine();
isIndianString=randomAccessFile.readLine();
dateOfBirthString=randomAccessFile.readLine();
//successfully readed the first record and many one by one
vRollNumber=Integer.parseInt(rollNumberString);
if(vRollNumber!=rollNumber)
{
tmpRandomAccessFile.writeBytes(rollNumberString+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(genderString+"\n");
tmpRandomAccessFile.writeBytes(isIndianString+"\n");
tmpRandomAccessFile.writeBytes(dateOfBirthString+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
//never ever delete the temporary file
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException("IOException: "+ioException.getMessage());
}
}
public StudentDTOInterface get(int rollNumber)throws DAOException
{
StudentDTOInterface studentDTOInterface;
String rollNumberString;
String name;
String genderString;
String isIndianString;
String dateOfBirthString;
int vRollNumber;
char vGender;
boolean vIsIndian;
java.util.Date vDateOfBirth;
int dd,mm,yyyy;
String pcs[];
try
{
File file=new File(dataFile);
if(file.exists()==false)
{
throw new DAOException(rollNumber+" not exists.");
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid Roll Number");
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
name=randomAccessFile.readLine();
genderString=randomAccessFile.readLine();
isIndianString=randomAccessFile.readLine();
dateOfBirthString=randomAccessFile.readLine();
vRollNumber=Integer.parseInt(rollNumberString);
if(rollNumber==vRollNumber)
{
vGender=genderString.charAt(0);
vIsIndian=Boolean.parseBoolean(isIndianString);
pcs=dateOfBirthString.split("/");
dd=Integer.parseInt(pcs[0]);
mm=Integer.parseInt(pcs[1]);
yyyy=Integer.parseInt(pcs[2]);
vDateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
studentDTOInterface=new StudentDTO();
studentDTOInterface.setRollNumber(vRollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(vGender);
studentDTOInterface.setIsIndian(vIsIndian);
studentDTOInterface.setDateOfBirth(vDateOfBirth);
randomAccessFile.close();
return studentDTOInterface;
}
}
randomAccessFile.close();
throw new DAOException("Invalid rollnumber: "+rollNumber);
}catch(IOException ioException)
{
throw new DAOException("IOException: "+ioException.getMessage());
}
catch(Exception exception)
{
throw new DAOException("Exception: "+exception.getMessage());
}
}
public List<StudentDTOInterface>getAll()throws DAOException
{
List<StudentDTOInterface> students;
students=new ArrayList<StudentDTOInterface>();
StudentDTOInterface studentDTOInterface;
String rollNumberString;
String name;
String genderString;
String isIndianString;
String dateOfBirthString;
int vRollNumber;
char gender;
boolean isIndian;
java.util.Date dateOfBirth;
int dd,mm,yyyy;
String pcs[];
try
{
File file=new File(dataFile);
if(file.exists()==false)
{
return students;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return students;
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
rollNumberString=randomAccessFile.readLine();
name=randomAccessFile.readLine();
genderString=randomAccessFile.readLine();
isIndianString=randomAccessFile.readLine();
dateOfBirthString=randomAccessFile.readLine();
vRollNumber=Integer.parseInt(rollNumberString);
gender=genderString.charAt(0);
isIndian=Boolean.parseBoolean(isIndianString);
pcs=dateOfBirthString.split("/");
dd=Integer.parseInt(pcs[0]);
mm=Integer.parseInt(pcs[1]);
yyyy=Integer.parseInt(pcs[2]);
dateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
studentDTOInterface=new StudentDTO();
studentDTOInterface.setRollNumber(vRollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);
studentDTOInterface.setIsIndian(isIndian);
studentDTOInterface.setDateOfBirth(dateOfBirth);
students.add(studentDTOInterface);
}
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException("IOException: "+ioException);
}
return students;
}
}