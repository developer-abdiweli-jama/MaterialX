import os

courses = {
    # 2nd Year
    "Computer Programming II": "SWE211",
    "Discrete Mathematics": "SWE212",
    "Fundamentals of Database Systems": "SWE213",
    "Fundamentals of Software Engineering": "SWE214",
    "Inclusiveness": "SWE215",
    "Introduction to Economics": "SWE216",
    "Computer Organization and Architecture": "SWE221",
    "data Structure and algorithem": "SWE222",
    "Fundamental of networking": "SWE223",
    "OO programming": "SWE224",
    "OS and S programming": "SWE225",
    "probability": "SWE226",
    # 3rd Year
    "Advanced Database Systems": "SWE311",
    "Fundamental of Software Security": "SWE312",
    "Web Design and Programming": "SWE313",
    "Requirements Engineering": "SWE314",
    "Software Engineering Tools and Practices": "SWE315",
    "microprosser": "SWE316",
    "Fundamental of Distributed Systems": "SWE321",
    "Software Project Management": "SWE322",
    "Software Architecture and Design": "SWE323",
    "Advanced Programming": "SWE324",
    "Fundamental of Machine Learning": "SWE325",
    "Fundamental of Cloud Computing & IoT": "SWE326",
    # 4th Year
    "Mobile Application Development": "SWE411",
    "Fundamentals of Artificial Intelligence": "SWE412",
    "Software Testing and Quality Assurance": "SWE413",
    "Principles of Compiler Design": "SWE414",
    "Real Time and Embedded Systems": "SWE415"
}

with open("exhaustive_assets_v2.txt", "r") as f:
    lines = f.readlines()

sql_lines = []
for path in lines:
    path = path.strip()
    if not path or path.endswith(".lnk") or "/~$" in path:
        continue
    
    course_found = None
    # Flexible matching for data Structure and algorithem etc.
    lower_path = path.lower()
    for folder, code in courses.items():
        if folder.lower() in lower_path:
            course_found = code
            break
    
    if course_found:
        filename = os.path.basename(path)
        ext = filename.split('.')[-1].lower()
        if ext in ['pptx', 'ppt']:
            type_str = 'PPT'
        elif ext == 'pdf':
            type_str = 'PDF'
        else:
            type_str = 'DOC'
        
        # Escape single quotes for SQL
        safe_filename = filename.replace("'", "''")
        safe_path = path.replace("'", "''")
        
        sql = f"db.execSQL(\"INSERT INTO materials (name, type, date, file_path, course_code) VALUES ('{safe_filename}', '{type_str}', '2024-01-26', '{safe_path}', '{course_found}')\");"
        sql_lines.append(sql)

with open("seed_output_v2.txt", "w") as out:
    for sql in sql_lines:
        out.write(sql + "\n")
