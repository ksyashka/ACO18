package week1.day1;

import java.util.*;
import java.*;

import week2.day1.*;

/**
 * Created by ksyashka on 22.01.2017.
 */
public class Group {
    private String name;
    private int size;
    private Student[] students;


    public Group() {
    }

    public Group(String name, int size) {
        this.name = name;
        this.students = new Student[size];
    }

    public Group(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }


    public boolean addStudent(String name, String surname, int age) {
        if (size == students.length) {
            System.out.println("Sorry! Group is ful!");
            return false;
        }
        Student student = new Student(name, surname, age);
        if (isDuplicateStudent(student)) {
            System.out.println("A student is already in the group");
            return false;
        }
        students[size++] = new Student(name, surname, age);
        return true;
    }

    public boolean addStudent(String name, String surname, int age, double averageMark) {
        if (size == students.length) {
            System.out.println("Sorry! Group is ful!");
            return false;
        }
        Student student = new Student(name, surname, age, averageMark);
        if (isDuplicateStudent(student)) {
            System.out.println("A student is already in the group");
            return false;
        }
        students[size++] = new Student(name, surname, age, averageMark);
        return true;
    }

    public boolean isDuplicateStudent(Student student) {
        if (student == null) return false;
        for (int i = 0; i < size; i++)
            if (students[i].equals(student)) return true;
        return false;
    }

    public void delStudent(int index) {
        if (index < 0 || index > students.length || students[index] == null) {
            System.out.println("A student doesn't exist!");
            return;
        }
        System.arraycopy(students, index + 1, students, index, students.length - index - 1);
        students[students.length - 1] = null;
        size--;
    }


    public String asString() {
        return String.format("Group name - %s, Students  - %d", name, students.length);
    }

    public void showStudents() {
        System.out.println(name);
        for (int i = 0; i < students.length; i++)
            if (students[i] != null)
                System.out.println(students[i]);
    }

    public void searchStudent(String name) {
        System.out.println("Students with name " + name + " in group " + name);
        for (int i = 0; i < students.length; i++)
            if (students[i] != null && students[i].getName().equals(name)) System.out.println(toString());
    }
/*
    public void searchStudentBinary(String name) {
        System.out.println("Students with name " + name + " in group " + name);
        Student[] searchStudents = new Student[this.size];
        System.arraycopy(this.students, 0, searchStudents, 0, size);
        Group search = new Group("temp", searchStudents);
        search.size = this.size;
        int len = search.students.length;
        int i = len / 2;
        int result;
        while (i >= 0 && i < search.size) {
            result = name.compareTo(search.students[i].getName());
            if (result == 0) {
                System.out.println(search.students[i]);
                search.delStudent(i);
                if (i == search.size) i--;
            }
            if (result > 0) i = (len + i) / 2 + 1;
            if (result < 0) i = i / 2 - 1;

        }

    }*/
   public void searchStudentBinary(String name){
       System.out.println("Students with name " + name + " in group " + name);
       Student[] searchStudents = new Student[this.size];
       System.arraycopy(this.students, 0, searchStudents, 0, size);
       int i = searchBinary (name, searchStudents);
       for(;i!=-1;){
           System.out.println(searchStudents[i]);
           searchStudents = ArrayUtils.remove(searchStudents, i);
           i = searchBinary (name, searchStudents);
       }
   }
    public static int searchBinary (String name, Student[] searchStudents){
        int len = searchStudents.length;
        int i = len/2;
        int result;
        while (i>=0&&i<searchStudents.length){
            System.out.println(i);
            result = name.compareTo(searchStudents[i].getName());
            if (result == 0) return i;
            if (result > 0) i = (len + i)/2+1;
            if (result < 0) i = i/2 -1;
        }
        return -1;
    }


    public void sort() {
        Student[] temp = new Student[size];
        System.arraycopy(students, 0, temp, 0, size);
        Arrays.sort(temp);
        System.arraycopy(temp, 0, students, 0, size);
    }

      /*  public Student[] sortStudents() {
        int len = students.length;
        Student temp;
        for (int i = len - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (students[j + 1] != null && students[j].getName().compareTo(students[j + 1].getName()) > 0) {
                    temp = students[j + 1];
                    students[j + 1] = students[j];
                    students[j] = temp;
                }
        return students;
    }*/

    public void sort(Comparator comparator) {
        int len = students.length;
        Student temp;
        for (int i = len - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if ((comparator.compare(students[j], students[j + 1]) > 0)) {
                    temp = students[j + 1];
                    students[j + 1] = students[j];
                    students[j] = temp;
                }
    }

    @Override
    public boolean equals(Object group) {
        if (group == null || !(group instanceof Group)) return false;
        Group temp = (Group) group;
        if (this == group) return true;
        if (!this.name.equals(temp.name)) return false;
        for (int i = 0; i < size; i++)
            if (!this.students[i].equals(temp.students[i])) return false;
        if (this.size != temp.size) return false;
        return true;
    }

}
