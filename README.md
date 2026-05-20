# Wedding-Planning-and-Vendor-Booking-System

**Module:** SE1020 - Object Oriented Programming
**Project Type:** Individual
**Student:** IT21212222
**Topic:** Wedding Planning and Vendor Booking System

A web-based application that lets users manage wedding service vendors
(photographers, caterers, florists, decorators, etc.) through full CRUD
operations. Data is persisted to a plain-text file using Java file I/O.

## How to Run
Cargo will download Tomcat 10 the first time and run the app in one go:

```bash
mvn package
mvn cargo:run
```

Then open: **http://localhost:8080/wedding-planner**

(Press `Ctrl+C` to stop.)

## Project Structure

```
testwedding/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/wedding/
    │   ├── model/
    │   │   ├── Person.java            (abstract)
    │   │   ├── Vendor.java            (extends Person)
    │   │   ├── StandardVendor.java    (extends Vendor)
    │   │   └── PremiumVendor.java     (extends Vendor)
    │   ├── dao/
    │   │   └── VendorDAO.java         (file read/write)
    │   ├── servlet/
    │   │   ├── AddVendorServlet.java
    │   │   ├── ListVendorServlet.java
    │   │   ├── EditVendorServlet.java
    │   │   └── DeleteVendorServlet.java
    │   └── util/
    │       └── AppContextListener.java
    └── webapp/
        ├── index.jsp
        ├── addVendor.jsp
        ├── listVendors.jsp
        ├── editVendor.jsp
        └── WEB-INF/
            ├── web.xml
            └── data/vendors.txt       (sample seed data)
```
