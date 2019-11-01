
# E-commerce App

------
This Ecommerce app was developed using kotlin. To run ensure to install all dependancies.
=======
#Getting Started

This is documentation for Amazin mobile application.This document contains structure and layout of the application.It offers instructions for setting up and developing the E-commerce application.This document contains code snippets from the application project and simple explanations.

To get started you are required to pull the application android files from github repository .To contribute to project the files please contact the admin for permission.

To start developing you have to make sure you have meet the requirements and specification for developing and running the application.

### SDK SETUP {#sdk-setup}

---

This section describes setup and configuration of the Android Development Environment necessary for building Ecommerce mobile applications.

**Java Development Kit \(JDK\)**

The Java Development Kit is a prerequisite for the Android Eclipse IDE \(which comes with the ADT Bundle\) as well as Apache Ant.

**Android Development Tools Bundle**

The Android Development Tools \(ADT\) Bundle includes everything needed to begin developing Java Android

* Android SDK Tools
* Android Platform Tools
* Latest Android Platform
* Latest System Image for Emulator

## Setting up your Android Studio Project {#setting-up-your-android-studio-project}

---

For an existing app:

1. Open the **build.gradle **file inside your application module directory. Android Studio projects contain a top level **build.gradle **file and a **build.gradle **for each module. Make sure to edit the file for your application module.

## Project Structure {#project_structure}

---

Each project in Android Studio contains one or more modules with source code files and resource files. Types of modules include:

* Android app modules
* Library modules
* Network modules

By default, Android Studio displays your project files in the Android project view. This view is organized by modules to provide quick access to your project's key source files.

All the build files are visible at the top level under **Gradle Scripts **and each app module contains the following folders:

* **manifests**
: Contains the
`AndroidManifest.xml`
file.
* **java**
: Contains the Java source code files, including JUnit test code.
* **res**
: Contains all non-code resources, such as XML layouts, UI strings, and bitmap images.


---

### **Java Packages Structure**

**adapter**

It contains adapter which are used in view binding such as listviews and recycler view that data bind to the user interface layout xml.

**components**

This are helper classes that contain multiple reusable methods that are called in the main classes to perform certain functions to the project.

**fragment**

This package contain various packages found in the project.Fragment such as providers fragment and intro fragment.

**model**

This contains database classes object and class objects \(POJO\) used in api respond and requests.

**rest**

This contains the network services for the project.Contains URL links and end points that are called to servers.


---

#### Res Package Structure


**color**

contains custom color such as selector colors and gradient colors defined that are refered in the project.

**xml**

Contain custom xml components such as settings layout.