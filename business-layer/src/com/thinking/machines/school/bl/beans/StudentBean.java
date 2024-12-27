package com.thinking.machines.school.bl.beans;
import com.thinking.machines.school.bl.exceptions.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.enums.*;
import java.util.*;
public class StudentBean implements StudentBeanInterface
{
private int rollNumber;
private String name;
private boolean isIndian;
private char gender;
private java.util.Date dateOfBirth;

public void setRollNumber(int rollNumber)
{
this.rollNumber=rollNumber;
}
public int getRollNumber()
{
return this.rollNumber;
}

public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name=name;
}
public void setGender(GENDER gender)
{
if(gender==GENDER.MALE)this.gender='M';
else if(gender==GENDER.FEMALE)this.gender='F';
else this.gender='T';
}
public GENDER getGender()
{
if(this.gender=='M')return GENDER.MALE;
else if(this.gender=='F')return GENDER.FEMALE;
else return GENDER.TRANSGENDER;
}
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public int getAge()
{
java.util.Date today=new java.util.Date();
int todayDD=today.getDate();
int todayMM=today.getMonth()+1;
int todayYYYY=today.getYear()+1900;

int dobDD=this.dateOfBirth.getDate();
int dobMM=this.dateOfBirth.getMonth()+1;
int dobYYYY=this.dateOfBirth.getYear()+1990;
int age=todayYYYY-dobYYYY;
if(todayMM<dobMM)
{
age--;
}
else if(todayMM==dobMM)
{
if(todayDD<dobDD)age--;
}
return age;
}
}