package com.example.materialx.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "MaterialX.db";
        private static final int DATABASE_VERSION = 10;

        private static final String TABLE_USERS = "users";
        private static final String COLUMN_USER_ID = "id";
        private static final String COLUMN_STUDENT_ID = "student_id";
        private static final String COLUMN_PASSWORD = "password";

        private static final String TABLE_COURSES = "courses";
        private static final String COLUMN_COURSE_CODE = "code";
        private static final String COLUMN_COURSE_NAME = "name";
        private static final String COLUMN_COURSE_DEPARTMENT = "department";
        private static final String COLUMN_COURSE_YEAR = "year";
        private static final String COLUMN_COURSE_SEMESTER = "semester";

        private static final String TABLE_MATERIALS = "materials";
        private static final String COLUMN_MATERIAL_ID = "id";
        private static final String COLUMN_MATERIAL_NAME = "name";
        private static final String COLUMN_MATERIAL_TYPE = "type";
        private static final String COLUMN_MATERIAL_DATE = "date";
        private static final String COLUMN_MATERIAL_FILE_PATH = "file_path";
        private static final String COLUMN_MATERIAL_COURSE_CODE = "course_code";

        public DatabaseHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + TABLE_USERS + "("
                                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + COLUMN_STUDENT_ID + " TEXT UNIQUE,"
                                + COLUMN_PASSWORD + " TEXT" + ")");

                db.execSQL("CREATE TABLE " + TABLE_COURSES + "("
                                + COLUMN_COURSE_CODE + " TEXT PRIMARY KEY,"
                                + COLUMN_COURSE_NAME + " TEXT,"
                                + COLUMN_COURSE_DEPARTMENT + " TEXT,"
                                + COLUMN_COURSE_YEAR + " INTEGER,"
                                + COLUMN_COURSE_SEMESTER + " INTEGER" + ")");

                db.execSQL("CREATE TABLE " + TABLE_MATERIALS + "("
                                + COLUMN_MATERIAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + COLUMN_MATERIAL_NAME + " TEXT,"
                                + COLUMN_MATERIAL_TYPE + " TEXT,"
                                + COLUMN_MATERIAL_DATE + " TEXT,"
                                + COLUMN_MATERIAL_FILE_PATH + " TEXT,"
                                + COLUMN_MATERIAL_COURSE_CODE + " TEXT,"
                                + "FOREIGN KEY(" + COLUMN_MATERIAL_COURSE_CODE + ") REFERENCES " + TABLE_COURSES + "("
                                + COLUMN_COURSE_CODE + "))");

                seedCourses(db);
                seedEECourses(db);
                seedSWEYear2(db);
                seedSWEYear3(db);
                seedSWEYear4(db);
                seedEEMaterials(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIALS);
                onCreate(db);
        }

        private void seedCourses(SQLiteDatabase db) {
                // SWE Year 2
                db.execSQL("INSERT INTO courses VALUES ('SWE211', 'Computer Programming II', 'SWE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE212', 'Discrete Mathematics', 'SWE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE213', 'Database Systems', 'SWE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE214', 'Software Engineering Fundamentals', 'SWE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE215', 'Inclusiveness', 'SWE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE216', 'Introduction to Economics', 'SWE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE221', 'Organization and Architecture', 'SWE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE222', 'Data Structure and Algorithm', 'SWE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE223', 'Fundamental of Networking', 'SWE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE224', 'OO Programming', 'SWE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE225', 'OS and S Programming', 'SWE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE226', 'Probability', 'SWE', 2, 2)");

                // SWE Year 3
                db.execSQL("INSERT INTO courses VALUES ('SWE311', 'Advanced Database Systems', 'SWE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE312', 'Software Security', 'SWE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE313', 'Web Programming', 'SWE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE314', 'Requirements Engineering', 'SWE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE315', 'SE Tools and Practices', 'SWE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE316', 'Microprocessors', 'SWE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE321', 'Distributed Systems', 'SWE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE322', 'Project Management', 'SWE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE323', 'Software Architecture', 'SWE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE324', 'Advanced Programming', 'SWE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE325', 'Machine Learning', 'SWE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('SWE326', 'Cloud Computing & IoT', 'SWE', 3, 2)");

                // SWE Year 4
                db.execSQL("INSERT INTO courses VALUES ('SWE411', 'Mobile App Development', 'SWE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE412', 'Artificial Intelligence', 'SWE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE413', 'Software Quality Assurance', 'SWE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE414', 'Compiler Design', 'SWE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('SWE415', 'Real Time Systems', 'SWE', 4, 1)");
        }

        private void seedEECourses(SQLiteDatabase db) {
                // EE Year 2 - Semester 1
                db.execSQL("INSERT INTO courses VALUES ('EE211', 'Engineering Profession', 'EE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE212', 'Global Trend', 'EE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE213', 'Economics', 'EE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE214', 'Mechanics', 'EE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE215', 'Applied II', 'EE', 2, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE216', 'Probability and Random Process', 'EE', 2, 1)");

                // EE Year 2 - Semester 2
                db.execSQL("INSERT INTO courses VALUES ('EE221', 'Applied III', 'EE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE222', 'Fundamental Lab', 'EE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE223', 'Thermodynamics', 'EE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE224', 'Electrical Workshop', 'EE', 2, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE225', 'Modern Physics', 'EE', 2, 2)");

                // EE Year 3 - Semester 1
                db.execSQL("INSERT INTO courses VALUES ('EE311', 'Applied Electronics I', 'EE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE312', 'Computational Method', 'EE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE313', 'Electromagnetic Field', 'EE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE314', 'EE Method and Technology', 'EE', 3, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE315', 'Object Oriented Programming', 'EE', 3, 1)");

                // EE Year 3 - Semester 2
                db.execSQL("INSERT INTO courses VALUES ('EE321', 'Applied Electronics II', 'EE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE322', 'Network Analysis & Synthesis', 'EE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE323', 'Inclusiveness', 'EE', 3, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE324', 'Work shop II', 'EE', 3, 2)");

                // EE Year 4 - Semester 1
                db.execSQL("INSERT INTO courses VALUES ('EE411', 'Communication', 'EE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE412', 'Instrumentation', 'EE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE413', 'Control System', 'EE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE414', 'Computer Architecture', 'EE', 4, 1)");
                db.execSQL("INSERT INTO courses VALUES ('EE415', 'Digital Signal Processing', 'EE', 4, 1)");

                // EE Year 4 - Semester 2
                db.execSQL("INSERT INTO courses VALUES ('EE421', 'Database System', 'EE', 4, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE422', 'Data Communication', 'EE', 4, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE423', 'Data Structure and Algorithm', 'EE', 4, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE424', 'Microprocessor and Interfacing', 'EE', 4, 2)");
                db.execSQL("INSERT INTO courses VALUES ('EE425', 'Research Method', 'EE', 4, 2)");
        }

        private void seedEEMaterials(SQLiteDatabase db) {
                // Year 2 - Semester 1
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Engineering Profession Assignment', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Engineering profession/Engineering Proefession assignment.pdf', 'EE211')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Profession PPT Ch 1,2,3', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Engineering profession/Engineering Proefession ppt ch 1,2&3.pdf', 'EE211')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Global Trend Ch 1', 'PPT', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Global trend/Ch-1-GT.pptx', 'EE212')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Global Trend Ch 2', 'PPT', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Global trend/Ch-2,.pptx', 'EE212')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Economics Handbook', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Economics/economics.pdf', 'EE213')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Mechanics Material', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Mechanics/electrical engineering.pdf', 'EE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Applied II Handbook', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Applied II/Applied2.pdf', 'EE215')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Probability Class Notes', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 1/Probability and random process/Probability and random class.pdf', 'EE216')");

                // Year 2 - Semester 2
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Applied III Notes', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 2/Applied III/Applied III.pdf', 'EE221')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Electronics Fundamentals Lab', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 2/Fundamental lab/Experiments_in_Electronics_Fundamentals_and_Electric_Circuits_Fundamentals.pdf', 'EE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Thermodynamics Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 2/Thermodynamics/Chapter 1 thermodynamics.pdf', 'EE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Electrical Workshop Scan 14', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 2/Electrical workshop/scan0014.pdf', 'EE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Modern Physics Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/2nd year/Semister 2/Modern Physics/Chapter 1 The Special Theory of Relativity.pdf', 'EE225')");

                // Year 3 - Semester 1
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Applied Electronics I Ch 2', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 1/Applied Electronics I/chapter 2electronics.pdf', 'EE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Applied Electronics I Ch 3', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 1/Applied Electronics I/Chapter 3 electronics.pdf', 'EE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Computationl Method Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 1/Computationl method/CHAPTER 1 Error Analysis and Number Representations.pdf', 'EE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Electromagnetic Field Sadiku', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 1/Electromagnetic field/Matthew_N_O_Sadiku_Elements_of_ElectromagneticBookFi_Copy.pdf', 'EE313')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Conducting Material', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 1/Electrical engineering method and technology/Conducting material.pdf', 'EE314')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('OOP Java Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 1/Object oriented programming/chapter_1.pdf', 'EE315')");

                // Year 3 - Semester 2
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Applied Electronics II Ch 1', 'PPT', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 2/Applied electronics II/ch-1 ppt by biruk.pptx', 'EE321')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Applied Electronics II Ch 2', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 2/Applied electronics II/chapter 2.pdf', 'EE321')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Network Analysis Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 2/Network analysis & synthesis/CHAPTER_1_Introduction_to_Networks.pdf', 'EE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Inclusiveness Ch 1', 'DOC', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 2/Inclusiveness/Chap.1  Inclusiveness.docx', 'EE323')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Workshop II Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/3rd year/Semister 2/Work shop II/chapter 1. Pdf 91958.pdf', 'EE324')");

                // Year 4 - Semester 1
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Communication Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 1/Communication/chapter 1 comm.pdf', 'EE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Instrumentation Lec 1', 'PPT', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 1/instrumentation/Inst _lecture 1.pptx', 'EE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Control System Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 1/Control system/chapter one.pdf', 'EE413')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Computer Architecture Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 1/Computer architecture/CHAPTER 1.pdf', 'EE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('DSP Ch 1', 'PPT', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 1/Digital signal processing/Ch-1-Digital logic and digital systems [Autosaved].ppt', 'EE415')");

                // Year 4 - Semester 2
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Database System Ch 3', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 2/Database system/3 Relational Data Model.pdf', 'EE421')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Data Communication Ch 3', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 2/Data communication/ch3.pdf', 'EE422')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Data Structure Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 2/Data structure and algorithm/ch1.pdf', 'EE423')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Microprocessor Ch 1', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 2/Microprocessor and interfacing/CH-1.pdf', 'EE424')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Research Method AI Project', 'PDF', '2024-01-26', 'Electrical Engineering materials/4th year/Semister 2/research method/Artificial Inteligence project.pdf', 'EE425')");
        }

        private void seedSWEYear2(SQLiteDatabase db) {
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 - Pointers.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Computer Programming II/Chapter 2 - Pointers.pptx', 'SWE211')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1 - Arrays and Strings-1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Computer Programming II/Chapter 1 - Arrays and Strings-1.pptx', 'SWE211')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 - Pointers-1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Computer Programming II/Chapter 2 - Pointers-1.pptx', 'SWE211')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('macroeconomics concept.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Introduction to Economics/macroeconomics concept.pdf', 'SWE216')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter-1.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Introduction to Economics/chapter-1.pdf', 'SWE216')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('theory of production and cost.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Introduction to Economics/theory of production and cost.pdf', 'SWE216')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('theory of consumer behaviour.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Introduction to Economics/theory of consumer behaviour.pdf', 'SWE216')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter-2.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Introduction to Economics/chapter-2.pdf', 'SWE216')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('basics-of-counting.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Discrete Mathematics and Combinatory/basics-of-counting.pdf', 'SWE212')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Discrete math chapter1-4.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Discrete Mathematics and Combinatory/Discrete math chapter1-4.pdf', 'SWE212')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('begashaw (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Discrete Mathematics and Combinatory/begashaw (1).pdf', 'SWE212')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('DB Chapter 1  - Introduction to DB System.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Database Systems/DB Chapter 1  - Introduction to DB System.pptx', 'SWE213')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Fundamental_of_Database_Systems.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Database Systems/Fundamental_of_Database_Systems.pdf', 'SWE213')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('DB Chapter 2 - Relational Data Model.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Database Systems/DB Chapter 2 - Relational Data Model.pptx', 'SWE213')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Database_Systems_A_Practical_A_Thomas_Co.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Database Systems/Database_Systems_A_Practical_A_Thomas_Co.pdf', 'SWE213')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 (1) (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Software Engineering/Chapter 2 (1) (1).pdf', 'SWE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Software Engineering/Chapter 5 (1).pdf', 'SWE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1 (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Software Engineering/Chapter 1 (1).pdf', 'SWE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6 (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Software Engineering/Chapter 6 (1).pdf', 'SWE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3 (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Software Engineering/Chapter 3 (1).pdf', 'SWE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 (1).pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Fundamentals of Software Engineering/Chapter 4 (1).pdf', 'SWE214')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chap.1-PPT inclusiveness..pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Inclusiveness/Chap.1-PPT inclusiveness..pdf', 'SWE215')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Inclu from.ch3-1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Inclusiveness/Inclu from.ch3-1.pptx', 'SWE215')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Inclusiveness module-1.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/first semester/Inclusiveness/Inclusiveness module-1.pdf', 'SWE215')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter_Four of Networking.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/Chapter_Four of Networking.pptx', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Three of Networking final.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/Three of Networking final.pptx', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter_Two of Networking.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/Chapter_Two of Networking.pdf', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter_One of Networking.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/Chapter_One of Networking.pdf', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter_Three of Networking.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/Chapter_Three of Networking.pptx', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('A Chapter_Two of Networking212.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/s A/A Chapter_Two of Networking212.pptx', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('A Chapter_One of Networking.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Fundamental of networking/s A/A Chapter_One of Networking.pptx', 'SWE223')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Updated Chapter 1 - Algorithm Analysis concept.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Updated Chapter 1 - Algorithm Analysis concept.pdf', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 - Simple Sorting & Searching Algorithm.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Chapter 2 - Simple Sorting & Searching Algorithm.pdf', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 - Non-Linear Data Structures.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Chapter 4 - Non-Linear Data Structures.pdf', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter-4-advanced sorting and  alg.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Chapter-4-advanced sorting and  alg.pptx', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 - Advanced sorting and searching algorithms.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Chapter 5 - Advanced sorting and searching algorithms.pdf', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3 - Linked List, Stacks an Queues.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Chapter 3 - Linked List, Stacks an Queues.pdf', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 Hashing - Copy.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/Chapter 5 Hashing - Copy.pptx', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter-4-advanced sorting and  alg.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/New folder/Chapter-4-advanced sorting and  alg.pptx', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 Hashing - Copy.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/data Structure and algorithem/New folder/Chapter 5 Hashing - Copy.pptx', 'SWE222')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('CHAPTER 3- Memory Management.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/CHAPTER 3- Memory Management.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 3.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('CHAPTER 5 file managment.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/CHAPTER 5 file managment.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 1.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 5.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 2.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chap 1 Introduction to Operating System.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/chap 1 Introduction to Operating System.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 4.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter 7   Network Security _ Security and protection.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/chapter 7   Network Security _ Security and protection.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 6.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chap 2 process managment.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chap 2 process managment.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 Device managment(DM) Slide.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Chapter 4 Device managment(DM) Slide.pdf', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Operating system ch-1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OS and S programming/Operating system ch-1.pptx', 'SWE225')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter five to end.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/probability/chapter five to end.pptx', 'SWE226')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter 5 and 6.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/probability/chapter 5 and 6.pdf', 'SWE226')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Probability_and_statistics_for_SE_G_2_chapter_1_8.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/probability/Probability_and_statistics_for_SE_G_2_chapter_1_8.pdf', 'SWE226')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('workshee for assignment For SENG 2025.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/probability/workshee for assignment For SENG 2025.pdf', 'SWE226')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter04 - Packages and Inerfaces (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter04 - Packages and Inerfaces (1).pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter05 - Generics (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter05 - Generics (1).pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter01 Part II (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter01 Part II (1).pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter01 Part I (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter01 Part I (1).pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter03 - Inheritance & Polymorphism.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter03 - Inheritance & Polymorphism.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter06 - Exception Handling.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter06 - Exception Handling.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter02 - Classes & Objects (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter02 - Classes & Objects (1).pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter07 - Files.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/Chapter07 - Files.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 05 - Inheritance.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/Lecture 05 - Inheritance.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 03 - Methods.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/Lecture 03 - Methods.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('abdiladif mohamoud.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/abdiladif mohamoud.pdf', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter - 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/Chapter - 2.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 04 - Class and Object.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/Lecture 04 - Class and Object.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter - 1 Final.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/Chapter - 1 Final.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 05 - Inheritance.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/New folder/Lecture 05 - Inheritance.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 08 - Files & Streams.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/New folder/Lecture 08 - Files & Streams.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 06 - Polymorphism.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/New folder/Lecture 06 - Polymorphism.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lecture 07 - Exception Handling.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/OO programming/new/New folder/Lecture 07 - Exception Handling.pptx', 'SWE224')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chap 5.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Computer Organization and Architecture/Chap 5.pptx', 'SWE221')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 IO Management.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Computer Organization and Architecture/Chapter 4 IO Management.pptx', 'SWE221')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1.ppt', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Computer Organization and Architecture/Chapter 1.ppt', 'SWE221')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Computer Organization and Architecture/Chapter 2 (1).pptx', 'SWE221')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3 (1) (1).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/2nd year/second semester/Computer Organization and Architecture/Chapter 3 (1) (1).pptx', 'SWE221')");
        }

        private void seedSWEYear3(SQLiteDatabase db) {
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter - 4 Final.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Software Engineering Tools and Practices/Chapter - 4 Final.pptx', 'SWE315')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter - 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Software Engineering Tools and Practices/Chapter - 2.pptx', 'SWE315')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter - 1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Software Engineering Tools and Practices/Chapter - 1.pptx', 'SWE315')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter - 3.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Software Engineering Tools and Practices/Chapter - 3.pptx', 'SWE315')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter_Two of Microprocessor.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/microprosser/Chapter_Two of Microprocessor.pptx', 'SWE316')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter_One of Microprocessor.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/microprosser/Chapter_One of Microprocessor.pptx', 'SWE316')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter 1.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Advanced Database Systems/chapter 1.pdf', 'SWE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Advanced Database Systems/Chapter 2.pdf', 'SWE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6-Data Warehouse and Datamining.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Advanced Database Systems/Chapter 6-Data Warehouse and Datamining.pdf', 'SWE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 - Distributed Database System.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Advanced Database Systems/Chapter 4 - Distributed Database System.pdf', 'SWE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter 3- DB security and Integrity.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Advanced Database Systems/chapter 3- DB security and Integrity.pdf', 'SWE311')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter three (web design and programming).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Web Design and Programming/chapter three (web design and programming).pptx', 'SWE313')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter one (web design and programming) (2).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Web Design and Programming/chapter one (web design and programming) (2).pptx', 'SWE313')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter four (web design and programming) (4).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Web Design and Programming/Chapter four (web design and programming) (4).pptx', 'SWE313')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter two (web design and programming).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Web Design and Programming/chapter two (web design and programming).pptx', 'SWE313')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('chapter Five (web design and programming).pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Web Design and Programming/chapter Five (web design and programming).pptx', 'SWE313')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Software Requirements Engineering SLIDE.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Requirements Engineering/Software Requirements Engineering SLIDE.pptx', 'SWE314')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Course Overview.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Fundamental of Software Security/Course Overview.pdf', 'SWE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4_Application and OS Security.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Fundamental of Software Security/Chapter 4_Application and OS Security.pdf', 'SWE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1_Introduction to SW Security.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Fundamental of Software Security/Chapter 1_Introduction to SW Security.pdf', 'SWE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3_Cryptography.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Fundamental of Software Security/Chapter 3_Cryptography.pdf', 'SWE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Group Ass''t on Fund_of_SW Sec. for G3SE_A&B2017E.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Fundamental of Software Security/Group Ass''t on Fund_of_SW Sec. for G3SE_A&B2017E.pdf', 'SWE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2_Secure System Design & Design Principles.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/first semester/Fundamental of Software Security/Chapter 2_Secure System Design & Design Principles.pdf', 'SWE312')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2-Architectures.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Distributed Systems/Chapter 2-Architectures.pdf', 'SWE321')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 7 SPM.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 7 SPM.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Project HRM.ppt', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Project HRM.ppt', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 3 SPM.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 3 SPM.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 4 SPM.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 4 SPM.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 1 of SPM.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 1 of SPM.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Project Communication.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Project Communication.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 6 SPM.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 6 SPM.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 5 SPM.ppt', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 5 SPM.ppt', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Lec 2 SPM.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Lec 2 SPM.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Project Procurement Management.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Project Management/Project Procurement Management.pptx', 'SWE322')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('SE2_01_Introduction.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Architecture and Design/SE2_01_Introduction.pdf', 'SWE323')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('SE2_02_Design Patterns -prt01.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Architecture and Design/SE2_02_Design Patterns -prt01.pdf', 'SWE323')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('SE2_02_Design Patterns-prt02.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Architecture and Design/SE2_02_Design Patterns-prt02.pdf', 'SWE323')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('SE2_03_Envisioning Architecture.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Software Architecture and Design/SE2_03_Envisioning Architecture.pdf', 'SWE323')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Advanced Programming/Chapter 2.pptx', 'SWE324')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 7.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Advanced Programming/Chapter 7.pptx', 'SWE324')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Advanced Programming/Chapter 6.pptx', 'SWE324')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Advanced Programming/Chapter 4.pptx', 'SWE324')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Advanced Programming/Chapter 5.pptx', 'SWE324')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1 - Machine Learning Fundamentals.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Machine Learning/Chapter 1 - Machine Learning Fundamentals.pdf', 'SWE325')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3- Unsupervised learnings.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Machine Learning/Chapter 3- Unsupervised learnings.pdf', 'SWE325')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 - Data Preprocessing and Feature Engineering.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Machine Learning/Chapter 5 - Data Preprocessing and Feature Engineering.pdf', 'SWE325')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2- Supervised Learning.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Machine Learning/Chapter 2- Supervised Learning.pdf', 'SWE325')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 - NN.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Machine Learning/Chapter 4 - NN.pdf', 'SWE325')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6 - Model Selection.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Machine Learning/Chapter 6 - Model Selection.pdf', 'SWE325')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1 Introduction.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Cloud Computing & IoT/Chapter 1 Introduction.pdf', 'SWE326')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 Understanding Cloud Computing.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Cloud Computing & IoT/Chapter 2 Understanding Cloud Computing.pdf', 'SWE326')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3  Fundamental Concepts and Models.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Cloud Computing & IoT/Chapter 3  Fundamental Concepts and Models.pdf', 'SWE326')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 Fundamentals of Cloud-Security.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Cloud Computing & IoT/Chapter 5 Fundamentals of Cloud-Security.pdf', 'SWE326')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4  Cloud-Enabling Technology.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/3rd year/second semester/Fundamental of Cloud Computing & IoT/Chapter 4  Cloud-Enabling Technology.pdf', 'SWE326')");
        }

        private void seedSWEYear4(SQLiteDatabase db) {
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('ChapterEsc 4.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Chapter ES 4.pptx', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter ESC 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Chapter Two  finall.pptx', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Final exam For Real Time and Embedded System 2015.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Final exam For Real Time and Embedded System 2015.pdf', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter ESC 1.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Chapter One.pdf', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter ES 4.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Chapter ES 4.pptx', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter Five.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Chapter Five.pptx', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter Three final.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Real Time and Embedded Systems/Chapter Three final.pptx', 'SWE415')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AI Chapter 3.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Fundamentals of Artificial Intelligence/AI Chapter 3.pptx', 'SWE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AI chap4-updated.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Fundamentals of Artificial Intelligence/AI chap4-updated.pptx', 'SWE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AI chapter6-NLP.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Fundamentals of Artificial Intelligence/AI chapter6-NLP.pptx', 'SWE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AI ch 2 updated.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Fundamentals of Artificial Intelligence/AI ch 2 updated.pptx', 'SWE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AI chapter 5.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Fundamentals of Artificial Intelligence/AI chapter 5.pptx', 'SWE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES (' ch-1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Fundamentals of Artificial Intelligence/ ch-1.pptx', 'SWE412')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 7 Code Optimization and Code Generation.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter 7 Code Optimization and Code Generation.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6 Run Time Environment.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter 6 Run Time Environment.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5 Intermidate code Generation.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter 5 Intermidate code Generation.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2 Lexical Analysis.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter 2 Lexical Analysis.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter  1 Introduction.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter  1 Introduction.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3 Syntax Analysis.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter 3 Syntax Analysis.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4 Semantic Analysis.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Principles of Compiler Design/Chapter 4 Semantic Analysis.pdf', 'SWE414')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Chapter 2.pptx', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 6.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Chapter 6.pptx', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 4.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Chapter 4.pptx', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Send SMS/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Send SMS/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Send SMS/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Network Types/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Network Types/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Network Types/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Telephony Example/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Telephony Example/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 5/Lab 5/Telephony Example/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 3/Lab 3/ExternalStorage Example/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 3/Lab 3/ExternalStorage Example/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 3/Lab 3/SharedPreference Example/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 3/Lab 3/SharedPreference Example/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 3/Lab 3/InternalStorage Example/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('ActivityMain.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 3/Lab 3/InternalStorage Example/ActivityMain.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Chapter 1.pptx', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/RecycleViewAdapter App/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('row_fiction.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/RecycleViewAdapter App/row_fiction.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('FictionAdapter.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/RecycleViewAdapter App/FictionAdapter.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Fiction.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/RecycleViewAdapter App/Fiction.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/RecycleViewAdapter App/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/ArrayAdapter App/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/ArrayAdapter App/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/StoryWithInflation App/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('row_story.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/StoryWithInflation App/row_story.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/StoryWithInflation App/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/StoryWithInflation App/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('themes.xml.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/MaterialDesign Example/themes.xml.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('colors.xml.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/MaterialDesign Example/colors.xml.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/MaterialDesign Example/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/MaterialDesign Example/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 7/Lab 7/MaterialDesign Example/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 3.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Chapter 3.pptx', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Chapter 5.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Chapter 5.pptx', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Alam Two/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Alam Two/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AlarmReceiver.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Alam Two/AlarmReceiver.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Alam Two/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Alarm/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Alarm/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AlarmReceiver.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Alarm/AlarmReceiver.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Alarm/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Notification/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Notification/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('ic_notification.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Notification/ic_notification.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 6/Lab 6/Simple Notification/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Consumer App/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Consumer App/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Consumer App/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('AndroidManifest.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Provider App/AndroidManifest.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('DBHelper.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Provider App/DBHelper.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Provider App/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('StudentProvider.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Provider App/StudentProvider.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/Provider App/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('DBHelper.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/SQLite Example/DBHelper.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('activity_main.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/SQLite Example/activity_main.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('MainActivity.txt', 'DOC', '2024-01-26', 'Software Engineering materials/4th year/Mobile Application Development/Lab 4/SQLite Example/MainActivity.txt', 'SWE411')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('STQA-Chapter 1.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Software Testing and Quality Assurance/STQA-Chapter 1.pptx', 'SWE413')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('STQA-Chapter 3.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Software Testing and Quality Assurance/STQA-Chapter 3.pptx', 'SWE413')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('STQA-Chapter 5.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Software Testing and Quality Assurance/STQA-Chapter 5.pptx', 'SWE413')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('STQA-Chapter 2.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Software Testing and Quality Assurance/STQA-Chapter 2.pptx', 'SWE413')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('Ind & Group Ass.pdf', 'PDF', '2024-01-26', 'Software Engineering materials/4th year/Software Testing and Quality Assurance/Ind & Group Ass.pdf', 'SWE413')");
                db.execSQL("INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('STQA-Chapter 4.pptx', 'PPT', '2024-01-26', 'Software Engineering materials/4th year/Software Testing and Quality Assurance/STQA-Chapter 4.pptx', 'SWE413')");
        }

        public boolean registerUser(String studentId, String password) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COLUMN_STUDENT_ID, studentId);
                values.put(COLUMN_PASSWORD, password);
                long result = db.insert(TABLE_USERS, null, values);
                db.close();
                return result != -1;
        }

        public boolean checkUser(String studentId, String password) {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query(TABLE_USERS, new String[] { COLUMN_USER_ID },
                                COLUMN_STUDENT_ID + "=? AND " + COLUMN_PASSWORD + "=?",
                                new String[] { studentId, password }, null, null, null);
                boolean exists = cursor.getCount() > 0;
                cursor.close();
                db.close();
                return exists;
        }

        public List<Course> getCourses(String department, int year, int semester) {
                List<Course> list = new ArrayList<>();
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query(TABLE_COURSES, null,
                                COLUMN_COURSE_DEPARTMENT + "=? AND " + COLUMN_COURSE_YEAR + "=? AND "
                                                + COLUMN_COURSE_SEMESTER + "=?",
                                new String[] { department, String.valueOf(year), String.valueOf(semester) }, null, null,
                                null);
                if (cursor.moveToFirst()) {
                        do {
                                list.add(new Course(
                                                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_CODE)),
                                                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_NAME)),
                                                cursor.getString(
                                                                cursor.getColumnIndexOrThrow(COLUMN_COURSE_DEPARTMENT)),
                                                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURSE_YEAR)),
                                                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURSE_SEMESTER))));
                        } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                return list;
        }

        public List<Material> getMaterials(String courseCode) {
                List<Material> list = new ArrayList<>();
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query(TABLE_MATERIALS, null, COLUMN_MATERIAL_COURSE_CODE + "=?",
                                new String[] { courseCode }, null, null, null);
                if (cursor.moveToFirst()) {
                        do {
                                list.add(new Material(
                                                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MATERIAL_NAME)),
                                                Material.Type.valueOf(cursor.getString(
                                                                cursor.getColumnIndexOrThrow(COLUMN_MATERIAL_TYPE))),
                                                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MATERIAL_DATE)),
                                                cursor.getString(cursor
                                                                .getColumnIndexOrThrow(COLUMN_MATERIAL_COURSE_CODE)),
                                                cursor.getString(cursor
                                                                .getColumnIndexOrThrow(COLUMN_MATERIAL_FILE_PATH))));
                        } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                return list;
        }
}
