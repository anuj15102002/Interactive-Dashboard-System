package com.thinking.machines.school.bl.manager.interfaces;
import com.thinking.machines.school.bl.enums.*;
import com.thinking.machines.school.bl.beans.*;
import com.thinking.machines.school.bl.beans.interfaces.*;
import com.thinking.machines.school.bl.exceptions.*;
import java.util.*;
public interface StudentManagerInterface
{
public enum ORDER_BY
{
GENDER,ROLL_NUMBER,DATE_OF_BIRTH,AGE,NAME;
}
public void add(StudentBeanInterface studentBeanInterface) throws BLException;
public  void update(StudentBeanInterface studentBeanInterface) throws BLException;
public void delete(int rollNumber)throws BLException;
public StudentBeanInterface getByRollNumber(int rollNumber) throws BLException;
public List<StudentBeanInterface>getAll(ORDER_BY orderBy)throws BLException;
public List<StudentBeanInterface>getByGender(GENDER gender,ORDER_BY orderBy) throws BLException;
public List<StudentBeanInterface>getIndian(ORDER_BY orderBy)throws BLException;
public List<StudentBeanInterface>getForeigner(ORDER_BY orderBy)throws BLException;
public List<StudentBeanInterface>getByDateOfBirth(java.util.Date dateOfBirth,ORDER_BY orderBy) throws BLException;
public List<StudentBeanInterface>getByAge(int age,ORDER_BY orderBy)throws BLException;
}