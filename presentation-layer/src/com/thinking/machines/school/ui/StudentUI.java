package com.thinking.machines.school.ui;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.manager.*;
import com.thinking.machines.school.bl.manager.interfaces.*;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.utils.*;
import java.text.*;
import java.io.*;
import java.util.*;
public class StudentUI
{
public void drawLine(int size)
{
for(int i=0;i<=size;++i)
{
System.out.print("-");
}
System.out.println();
}
public java.util.Date toDate(String dateString)
{
if(dateString==null || dateString.trim().isEmpty())
{
System.out.println("Null Value has come as an argument");
return null;
}
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
simpleDateFormat.setLenient(false);
try
{
java.util.Date d=simpleDateFormat.parse(dateString);
String pcs1[];
String pcs2[];
pcs1=dateString.split("/");
pcs2=simpleDateFormat.format(d).split("/");
int dd1=Integer.parseInt(pcs1[0]);
int mm1=Integer.parseInt(pcs1[1]);
int yyyy1=Integer.parseInt(pcs1[2]);

int dd2=Integer.parseInt(pcs2[0]);
int mm2=Integer.parseInt(pcs2[1]);
int yyyy2=Integer.parseInt(pcs2[2]);

if(dd1!=dd2 || mm1!=mm2 || yyyy1!=yyyy2)
{
return null;
}
return d;
}catch(ParseException parseException)
{
System.out.println("Unparsable String ");
return null;
}
}
public void add()
{
drawLine(40);
System.out.println("Student (Add Module)");
drawLine(40);
int rollNumber=KeyBoard.getInt("Roll Number:");
if(rollNumber<=0)
{
System.out.println("Invalid Roll Number");
return;
}
String name=KeyBoard.getString("Name: ");
if(name==null || name.length()==0)
{
System.out.println("Name required");
return;
}
char gender=KeyBoard.getCharacter("Gender(M/F/T): ");
if(gender!='M' && gender!='F' && gender!='T')
{
System.out.println("Invalid Gender");
return;
}
char isIndian=KeyBoard.getCharacter("Is Indian(Y/N): ");
if(isIndian!='Y' && isIndian!='N')
{
System.out.println("Invalid input");
return;
}
String dateOfBirthString=KeyBoard.getString("Date Of Birth(dd/mm/yyyy): ");
if(dateOfBirthString==null || dateOfBirthString.length()==0)
{
System.out.println("Date of Birth required");
return;
}
java.util.Date dateOfBirth=toDate(dateOfBirthString);
if(dateOfBirth==null)
{
System.out.println("Invalid Input");
return;
}
char confirm=KeyBoard.getCharacter("Save(Y/N): ");
if(confirm!='Y' && confirm!='N')
{
System.out.println("Invalid Input");
return;
}
if(confirm=='N')
{
System.out.println("Student not saved");
return;
}
try
{
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
StudentBeanInterface studentBeanInterface;
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(name);
if(gender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(gender=='F')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.MALE);
if(isIndian=='Y')studentBeanInterface.setIsIndian(true);
else studentBeanInterface.setIsIndian(false);
studentBeanInterface.setDateOfBirth(dateOfBirth);
studentManagerInterface.add(studentBeanInterface);
System.out.println("Student Saved");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
System.out.println("Student not saved");
}
}
public void delete()
{
try
{
drawLine(40);
System.out.println("Student Delete Module");
drawLine(40);
int rollNumber=KeyBoard.getInt("Roll Number");
if(rollNumber<=0)
{
System.out.println("Invalid Roll Number");
return;
}
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
StudentBeanInterface studentBeanInterface;
studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
System.out.println(rollNumber);
String name=studentBeanInterface.getName();
System.out.println(name);
GENDER gen;
gen=studentBeanInterface.getGender();
if(gen==GENDER.MALE)System.out.println('M');
else if(gen==GENDER.FEMALE)System.out.println('F');
else System.out.println("Gender: "+'T');
boolean isIndian=studentBeanInterface.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else System.out.println("Foreigner");
java.util.Date dob=studentBeanInterface.getDateOfBirth();
System.out.println(dob);
char confirm;
confirm=KeyBoard.getCharacter("Delete(Y/N): ");
if(confirm=='y')confirm='Y';
if(confirm=='n')confirm='N';
if(confirm!='Y' && confirm!='N')
{
System.out.println("Invalid input");
return;
}
if(confirm=='N')
{
System.out.println("Student not deleted");
return;
}
studentManagerInterface.delete(rollNumber);
System.out.println("Student Deleted");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
System.out.println("Student not Deleted");
}
}
public void update()
{
try
{
drawLine(40);
System.out.println("Student Update Module");
drawLine(40);
int rollNumber=KeyBoard.getInt("Roll Number: ");
if(rollNumber<=0)
{
System.out.println("Invalid Roll Number");
return;
}
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
StudentBeanInterface studentBeanInterface;
studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
System.out.println(rollNumber);
String name=studentBeanInterface.getName();
System.out.println(name);
GENDER gen;
gen=studentBeanInterface.getGender();
if(gen==GENDER.MALE)System.out.println('M');
else if(gen==GENDER.FEMALE)System.out.println('F');
else System.out.println("Gender: "+'T');
boolean isIndian=studentBeanInterface.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else System.out.println("Foreigner");
java.util.Date dob=studentBeanInterface.getDateOfBirth();
System.out.println(dob);
char confirm;
confirm=KeyBoard.getCharacter("Save(Y/N): ");
if(confirm=='y')confirm='Y';
if(confirm=='n')confirm='N';
if(confirm!='Y' && confirm!='N')
{
System.out.println("Invalid input");
return;
}
if(confirm=='N')
{
System.out.println("Student not updated");
return;
}
String vName=KeyBoard.getString("Enter nama: ");
if(vName==null ||vName.length()==0)
{
System.out.println("Student not updated");
return;
}
char vGender=KeyBoard.getCharacter("Gender(M/F/T): ");
if(vGender!='M' && vGender!='F' && vGender!='T')
{
System.out.println("Invalid Gender");
return;
}
char vIsIndian=KeyBoard.getCharacter("Is Indian(Y/N): ");
if(vIsIndian!='Y' && vIsIndian!='N')
{
System.out.println("Invalid input");
return;
}
String dateOfBirthString=KeyBoard.getString("Date Of Birth(dd/mm/yyyy): ");
if(dateOfBirthString==null || dateOfBirthString.length()==0)
{
System.out.println("Date of Birth required");
return;
}
java.util.Date dateOfBirth=toDate(dateOfBirthString);
if(dateOfBirth==null)
{
System.out.println("Invalid Input");
return;
}
confirm=KeyBoard.getCharacter("Save(Y/N): ");
if(confirm!='Y' && confirm!='N')
{
System.out.println("Invalid Input");
return;
}
if(confirm=='N')
{
System.out.println("Student not saved");
return;
}
studentBeanInterface=new StudentBean();
studentBeanInterface.setRollNumber(rollNumber);
studentBeanInterface.setName(vName);
if(vGender=='M')studentBeanInterface.setGender(GENDER.MALE);
else if(vGender=='F')studentBeanInterface.setGender(GENDER.FEMALE);
else studentBeanInterface.setGender(GENDER.TRANSGENDER);
if(vIsIndian=='Y')studentBeanInterface.setIsIndian(true);
else studentBeanInterface.setIsIndian(false);
studentBeanInterface.setDateOfBirth(dateOfBirth);
studentManagerInterface.update(studentBeanInterface);
System.out.println("Student Updated");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
System.out.println("Student not Updated");
}
}
public void getStudents()
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
drawLine(40);
System.out.println("Student Get Module");
drawLine(40);
int rollNumber=KeyBoard.getInt("Roll Number: ");
if(rollNumber<=0)
{
System.out.println("Invalid Roll Number");
return;
}
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
StudentBeanInterface studentBeanInterface;
studentBeanInterface=studentManagerInterface.getByRollNumber(rollNumber);
System.out.println(rollNumber);
String name=studentBeanInterface.getName();
System.out.println(name);
GENDER gen;
gen=studentBeanInterface.getGender();
if(gen==GENDER.MALE)System.out.println('M');
else if(gen==GENDER.FEMALE)System.out.println('F');
else System.out.println("Gender: "+'T');
boolean isIndian=studentBeanInterface.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else System.out.println("Foreigner");
java.util.Date dob=studentBeanInterface.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}catch(BLException blException)
{
System.out.println(blException.getMessage());
System.out.println("Invalid roll Number");
}
}
public void getStudentList()
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
drawLine(40);
System.out.println("Student List Module");
drawLine(40);
int choice;
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
System.out.println("1.List");
System.out.println("2.List ordered by Gender");
System.out.println("3.List ordered by Age");
System.out.println("4.List ordered by Date Of Birth");
System.out.println("5.List ordered by name");
choice=KeyBoard.getInt("Enter your choice: ");
if(choice==1)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==2)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.GENDER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==3)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.AGE);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==4)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.DATE_OF_BIRTH);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==5)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getAll(StudentManagerInterface.ORDER_BY.NAME);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
}catch(BLException blException)
{
System.out.println(blException);
}
}
public void getFilteredStudent()
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
drawLine(40);
System.out.println("Student List Module");
drawLine(40);
int choice;
StudentManagerInterface studentManagerInterface;
studentManagerInterface=new StudentManager();
System.out.println("1.Gender");
System.out.println("2.Age");
System.out.println("3.Date Of Birth");
System.out.println("4.Indians");
System.out.println("5.Foreigners");
choice=KeyBoard.getInt("Enter your choice: ");
if(choice==1)
{
char g=KeyBoard.getCharacter("Enter the Gender(M/F/T): ");
if(g=='M' && g=='F' && g=='T')
{
System.out.println("Invalid Gender");
return;
}
GENDER gen;
if(g=='M')gen=GENDER.MALE;
else if(g=='F')gen=GENDER.FEMALE;
else gen=GENDER.TRANSGENDER;
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getByGender(gen,StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==2)
{
int age=KeyBoard.getInt("Enter the age");
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getByAge(age,StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==3)
{
String dateOfBirthString=KeyBoard.getString("Enter the date(dd/mm/yyyy): ");
String split[];
split=dateOfBirthString.split("/");
int dd=Integer.parseInt(split[0]);
int mm=Integer.parseInt(split[1]);
int yyyy=Integer.parseInt(split[2]);
java.util.Date dateOfBirth=new java.util.Date(yyyy-1900,mm-1,dd);
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getByDateOfBirth(dateOfBirth,StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==4)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getIndian(StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
else if(choice==5)
{
List<StudentBeanInterface> blStudents;
blStudents=studentManagerInterface.getForeigner(StudentManagerInterface.ORDER_BY.ROLL_NUMBER);
for(StudentBeanInterface sbi:blStudents)
{
System.out.println(sbi.getRollNumber());
System.out.println(sbi.getName());
GENDER gender;
gender=sbi.getGender();
if(gender==GENDER.MALE)System.out.println('M');
else if(gender==GENDER.FEMALE)System.out.println('F');
else System.out.println('T');
boolean isIndian=sbi.getIsIndian();
if(isIndian==true)System.out.println("Indian");
else if(isIndian==false)System.out.println("Foreigner");
java.util.Date dob=sbi.getDateOfBirth();
String dobString=simpleDateFormat.format(dob);
System.out.println(dobString);
}
}
}catch(BLException blException)
{
System.out.println(blException);
}
}
}