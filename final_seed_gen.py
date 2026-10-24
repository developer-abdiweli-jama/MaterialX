import os

# Map folder fragments to course codes
courses = {
    # 2nd Year
    "Computer Programming II": "SWE211",
    "Discrete Mathematics": "SWE212",
    "Database Systems": "SWE213",
    "Software Engineering Fundamentals": "SWE214",
    "Inclusiveness": "SWE215",
    "Introduction to Economics": "SWE216",
    "Organization and Architecture": "SWE221",
    "Data Structure": "SWE222",
    "networking": "SWE223",
    "OO programming": "SWE224",
    "OS and S programming": "SWE225",
    "probability": "SWE226",
    # 3rd Year
    "Advanced Database Systems": "SWE311",
    "Software Security": "SWE312",
    "Web Design and Programming": "SWE313",
    "Requirements Engineering": "SWE314",
    "Software Engineering Tools": "SWE315",
    "microprosser": "SWE316",
    "Distributed Systems": "SWE321",
    "Project Management": "SWE322",
    "Software Architecture": "SWE323",
    "Advanced Programming": "SWE324",
    "Machine Learning": "SWE325",
    "Cloud Computing": "SWE326",
    # 4th Year
    "Mobile Application": "SWE411",
    "Artificial Intelligence": "SWE412",
    "Software Testing": "SWE413",
    "Compiler Design": "SWE414",
    "Real Time": "SWE415"
}

with open("exhaustive_swe_materials.txt", "r") as f:
    lines = f.readlines()

sql_lines = []
for path in lines:
    path = path.strip()
    if not path or path.endswith(".lnk") or "/~$" in path:
        continue
    
    course_found = None
    lower_path = path.lower()
    
    # Sort courses by length descending to match most specific folder first
    sorted_courses = sorted(courses.keys(), key=len, reverse=True)
    for folder in sorted_courses:
        if folder.lower() in lower_path:
            course_found = courses[folder]
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

with open("final_swe_seed.txt", "w") as out:
    for sql in sql_lines:
        out.write(sql + "\n")
