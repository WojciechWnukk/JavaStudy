package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Main {

    public static void main(String[] args) {
            List<Student> students = new ArrayList<>();
            students.add(new Student("John Doe", 20, "Computer Science"));
            students.add(new Student("Bob Johnson", 22, "Physics"));
            students.add(new Student("Jane Smith", -3, "Mathematics"));


        createXML(students, "students.xml");
            List<Student> parsedStudents = parseXML("students.xml");

            for (Student student : parsedStudents) {
                if (student != null) {
                    System.out.println("Name: " + student.getName());
                    System.out.println("Age: " + student.getAge());
                    System.out.println("Course: " + student.getCourse());
                    System.out.println();
                } else {
                    System.out.println("Nie można utworzyć obiektu Student");
                }
            }
        }

        public static void createXML(List<Student> students, String filename) {
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Students");
                doc.appendChild(rootElement);

                for (Student student : students) {
                    Element studentElement = doc.createElement("Student");
                    rootElement.appendChild(studentElement);

                    Element nameElement = doc.createElement("Name");
                    nameElement.appendChild(doc.createTextNode(student.getName()));
                    studentElement.appendChild(nameElement);

                    Element ageElement = doc.createElement("Age");
                    ageElement.appendChild(doc.createTextNode(String.valueOf(student.getAge())));
                    studentElement.appendChild(ageElement);

                    Element courseElement = doc.createElement("Course");
                    courseElement.appendChild(doc.createTextNode(student.getCourse()));
                    studentElement.appendChild(courseElement);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(filename));
                transformer.transform(source, result);

                System.out.println("XML file saved as " + filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static List<Student> parseXML(String filename) {
            List<Student> students = new ArrayList<>();

            try {
                File xmlFile = new File(filename);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);

                doc.getDocumentElement().normalize();

                NodeList studentNodes = doc.getElementsByTagName("Student");

                for (int i = 0; i < studentNodes.getLength(); i++) {
                    Node studentNode = studentNodes.item(i);
                    if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element studentElement = (Element) studentNode;
                        String name = studentElement.getElementsByTagName("Name").item(0).getTextContent();
                        String ageStr = studentElement.getElementsByTagName("Age").item(0).getTextContent();
                        String course = studentElement.getElementsByTagName("Course").item(0).getTextContent();
                        int age;

                        try {
                            age = Integer.parseInt(ageStr);
                        } catch (NumberFormatException e) {
                            age = -1;
                        }
                        if (!name.isEmpty() && age > 0 && !course.isEmpty()) {
                            students.add(new Student(name, age, course));
                        } else {
                            students.add(null);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return students;
        }
    }