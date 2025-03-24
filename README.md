# File Handling and Streams Utilities (Java)

This repository contains multiple Java utilities that demonstrate advanced file and stream manipulation techniques.  
It was developed as part of the **Advanced Programming** course at Reichman University.

---

## 📚 Project Overview

The project focuses on handling binary files, streams, and data manipulation using Java.  
It includes:
- In-place file sorting using `RandomAccessFile`
- Stream reading and writing using `InputStream`, `OutputStream`, and `Reader`
- A decoder interface for handling clue-based map traversal logic

---

## 🛠️ What I Developed

I implemented the following Java classes:
- `RandomAccess.java`:  
  Implements in-place sorting algorithms (byte-level and 24-bit "tri-byte") using `RandomAccessFile`.
- `Streams.java`:  
  Implements stream utilities including:
  - Extracting quoted content from an `InputStream`
  - Reading content until an `endMark`
  - Filtering specific bytes from an `InputStream` to an `OutputStream`
  - Reading 40-bit numbers from an `InputStream`

---

## 📝 Provided by the Instructor

- The project instructions and interface definitions were provided by the course staff.  

---

## 🚀 How to Run

1. Clone the repository
2. Compile the Java files:
   ```bash
   javac files/*.java
