package com.thinking.machines.school.ui.mains;
import com.thinking.machines.school.ui.*;
import com.thinking.machines.utils.*;
public class Main
{
public static void main(String args[])
{
int choice;
StudentUI studentUI=new StudentUI();
while(true)
{
studentUI.drawLine(40);
System.out.println("MENU");
studentUI.drawLine(40);
System.out.println("1.Add Student");
System.out.println("2.Update Student");
System.out.println("3.Delete Student");
System.out.println("4.Search Student");
System.out.println("5.List Students");
System.out.println("6.List Filtered StudentS");
System.out.println("7.Exit");
choice=KeyBoard.getInt("Enter your choice: ");
if(choice==1)studentUI.add();
else if(choice==2)studentUI.update();
else if(choice==3)studentUI.delete();
else if(choice==4)studentUI.getStudents();
else if(choice==5)studentUI.getStudentList();
else if(choice==6)studentUI.getFilteredStudent();
else if(choice==7)break;
}
}
}