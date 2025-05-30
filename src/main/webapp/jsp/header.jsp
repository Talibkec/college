<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <html lang="en" dir="ltr"
      prefix="og: http://ogp.me/ns# article: http://ogp.me/ns/article# book: http://ogp.me/ns/book# profile: http://ogp.me/ns/profile# video: http://ogp.me/ns/video# product: http://ogp.me/ns/product# content: http://purl.org/rss/1.0/modules/content/ dc: http://purl.org/dc/terms/ foaf: http://xmlns.com/foaf/0.1/ rdfs: http://www.w3.org/2000/01/rdf-schema# sioc: http://rdfs.org/sioc/ns# sioct: http://rdfs.org/sioc/types# skos: http://www.w3.org/2004/02/skos/core# xsd: http://www.w3.org/2001/XMLSchema#">

    <head>

      <link rel="profile" href="http://www.w3.org/1999/xhtml/vocab" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <link rel="shortcut icon" href="/images/logo1.jpeg" type="image/vnd.microsoft.icon" />
      <meta name="description" content="Katihar Engineering College, Katihar" />
      <meta name="robots" content="follow, index" / <link rel="canonical" href="http://www.keck.ac.in" />
      <link rel="shortlink" href="http://www.keck.ac.in" />
      <meta property="og:site_name" content="KEC Katihar" />
      <meta property="og:type" content="website" />
      <meta property="og:url" content="http://www.keck.ac.in" />
      <meta property="og:title" content="KEC Katihar" />
      <meta itemprop="name" content="About Us" />
      <meta name="dcterms.title" content="KEC Katihar" />
      <meta name="dcterms.type" content="Text" />
      <meta name="dcterms.format" content="text/html" />
      <meta name="dcterms.identifier" content="" />

      <link rel="stylesheet" href="/css/main.css">
      <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
      <title>KATIHAR ENGINEERING COLLEGE, KATIHAR</title>
      <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
      <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
      <link href="https://cdn.jsdelivr.net/npm/flowbite@2.4.1/dist/flowbite.min.css" rel="stylesheet" />
      <script src="https://cdn.jsdelivr.net/npm/flowbite@2.4.1/dist/flowbite.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    </head>
    <style>
      @import url("https://use.fontawesome.com/releases/v5.13.0/css/all.css");

      * {
        box-sizing: border-box;
      }

      .quicknav {
        display: inline;
        padding: 4px;
        font-size: small;
        color: #000;
        text-decoration: none;

      }

      .submenu {
        display: none;
        max-width: fit-content;
        position: absolute;
        z-index: 100000;
        background: #285690;
        padding: 10px;

      }

      .submenu2 {
        display: none;
        max-width: fit-content;
        position: absolute;
        left: 100%;
        top: 20px;


        z-index: 100000;
        background: #285690;
        padding: 10px;

      }

      .submenu li {
        padding: 2px;

      }

      .submenu2 li {
        padding: 2px;

      }

      .main-nav {
        display: inline;


      }

      .main-nav:hover>.submenu {
        display: block;
      }

      .submenubar:hover>.submenu2 {
        display: block;
      }

      .submenubar>.submenu2:hover {
        display: block;
      }

      .btn-danger {
        background-color: red;
        color: white;
        padding: 8px;
        border-radius: 5px;
      }

      .page-content {
        padding: 1rem;
        width: 100%;
        overflow-x: scroll;
      }

      .jumbotron {
        background: #eee;
        padding-left: 200px;
        font-size: x-large;
      }
    </style>

    <body>

      <main>
        <header>
          <div class="w-full p-4 flex flex-col sm:flex-row sm:items-center sm:justify-between space-y-4 sm:space-y-0">
            <div class="">
              <img src="/images/logo.png" class="" />
            </div>
        
            <div class="w-full sm:w-auto flex flex-col sm:items-end space-y-4">
     
              <ul class="flex flex-wrap justify-center sm:justify-end space-x-4">
                <li class="quicknav"><a href="/"> &raquo; Home</a></li>
                <li class="quicknav"><a href="/academic/antiragging"> &raquo; Anti Ragging</a></li>
                <li class="quicknav"><a href="/rti/rtiact"> &raquo; RTI</a></li>
                <li class="quicknav"><a href="/approval/aicteapproval" target="_blank"> &raquo; AICTE</a></li>
                <li class="quicknav"><a href="/category/kecwebteam" target="_blank"> &raquo; KEC Web Team</a></li>
              </ul>
        
              <div class="flex justify-center sm:justify-end space-x-8 pr-4 sm:pr-8">
                <a href="https://www.facebook.com/profile.php?id=61564963783682" target="_blank" class="text-gray-800 hover:text-blue-600">
                  <i class="fab fa-facebook"></i>
                </a>
                <a href="https://x.com/keckatihar" target="_blank" class="text-gray-800 hover:text-blue-400">
                  <i class="fab fa-twitter"></i>
                </a>
                <a href="https://www.instagram.com/keckatiharofficial/" target="_blank" class="text-gray-800 hover:text-pink-600">
                  <i class="fab fa-instagram"></i>
                </a>
                <a href="https://whatsapp.com/channel/0029VaeIPrnFHWptfQRrpo0Q" target="_blank" class="text-gray-800 hover:text-green-500">
                  <i class="fab fa-whatsapp"></i>
                </a>
                <a href="https://www.youtube.com/channel/UCJu8sFs4jAp6sZIPsa8Qs5g" target="_blank" class="text-gray-800 hover:text-red-600">
                  <i class="fab fa-youtube"></i>
                </a>
                <a href="https://www.linkedin.com/in/katihar-engineering-college-dstte-govt-of-bihar-327aaa326/" target="_blank" class="text-gray-800 hover:text-blue-700">
                  <i class="fab fa-linkedin"></i>
                </a>
              </div>

      
              <form class="w-full max-w-md sm:self-end">
                <div class="relative flex items-center border border-gray-300 rounded-lg">
                  <input type="text" placeholder="Search Faculty" name="facultyName" id="facultyNames"
                    class="px-3 py-2 w-full rounded-lg focus:outline-none">
                  <button type="submit" class="p-2 bg-gray-200 rounded-r-lg">
                    <i class="fas fa-search"></i>
                  </button>
                </div>
              </form>
        
            </div>
          </div>
        </header>
        <!-- MobileNav start-->
        <div id="default-sidebar"
          class="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0"
          aria-label="Sidebar">
          <div class="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
            <ul class="space-y-2 font-medium">
              <li>
                <a href="/"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="ms-3">Home</span>
                </a>
              </li>
              <li>
                <a href="/about/history"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">About</span>
                </a>
              </li>
              <li>
                <a href="/academic/admission"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Academic</span>
                </a>
              </li>
              <li>
                <a href="/category/department"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Departments</span>
                </a>
              </li>
              <li>
                <a href="/facilities/csecenter"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Facilities</span>
                </a>
              </li>
              <li>
                <a href="/trainingplacement/placementbrochure"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Training & Placement</span>
                </a>
              </li>
              <li>
                <a href="/approval/aicteapproval"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Approval</span>
                </a>
              </li>
              <li>
                <a href="/rti/rtiact"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">RTI</span>
                </a>
              </li>
              <li>
                <a href="/gallery/mediagallery"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Gallery</span>
                </a>
              </li>
              <li>
                <a href="/facilities/downloads"
                  class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group">
                  <span class="flex-1 ms-3 whitespace-nowrap">Downloads</span>
                </a>
              </li>
              <sec:authorize access="isAuthenticated()">
                <li class="main-nav">
                  <a href="/auth/uploadDashboard" title="" class="sf-depth-1 menuparent">Dashboard</a>
                </li>
              </sec:authorize>
              <li>
                <sec:authorize access="!isAuthenticated()">
              <li class="main-nav">
                <a href="/login" title="" class="sf-depth-1 menuparent">Login</a>
              </li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
                <li class="main-nav">
                  <a href="/logout" title="" class="sf-depth-1 menuparent">Logout</a>
                </li>
              </sec:authorize>
              </li>
            </ul>
          </div>
        </div>


        <!-- MobileNav end-->

        <header class="h-20 bg-white">
          <style>
            body,
            html {
              margin: 0;
              padding: 0;
            }

            .main-nav {
              display: inline;
            }

            @media only screen and (max-width: 600px) {
              .mobile {
                display: none;
              }

              .desktop {
                display: block;
              }

              #default-carousel {
                height: 50vh;
              }

              .main-nav {
                display: block;
                padding: 10px;
              }

              .col-md-8 {
                width: 100%;
              }

              aside {
                width: 80%;
                padding: 20px;
                margin: 30px 20px;
              }
            }

            @media only screen and (min-width: 600px) {
              .desktop {
                display: none;
              }

              #default-sidebar {
                display: none !important;
              }

              .col-md-8 {
                max-width: 600px;
                min-width: 600px;
                padding: 10px;
              }

              aside {
                max-width: 280px;
              }

              .row {
                display: flex;
                justify-content: center;
              }

              .heroCol {
                max-width: 200px;
              }

            }

            li:hover {
              cursor: pointer;
              text-decoration: underline;

            }

            .heroCol {
              border: 1px solid grey;
              padding: 10px;
              margin: 10px;
            }

            #noticeBoardItems li {
              margin: 4px;
              background: #f1f1f1;
              padding: 6px;
            }

            .btn-default {
              color: white;
              background-color: #285690;
              padding: 8px;
              border-radius: 6px;
              width: 100%;
            }

            .btn-default:hover {
              background-color: #062447;
            }

            .headli {
              font-weight: 900;
              font-size: large;


            }

            .headli:hover {
              text-decoration: none;

            }
          </style>
          <div role="navigation" class="mobile">


            <div class="bg-blue">
              <section id="block-superfish-1" class="">
                <ul id="superfish-1" class="p-4 md:flex justify-between text-white" style="background:#285690">
                  <li id="menu-218-1" class="main-nav"><a href="/" class="sf-depth-1 ">Home</a></li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/about/history" class="sf-depth-1 menuparent">About KEC &#9660;</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/about/history" class="sf-depth-2">History</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/about/administration" class="sf-depth-2">Administration</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/about/principal" class="sf-depth-2">Principal's Message</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/about/vision" title="" class="sf-depth-2">Vision &amp; Mission</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/about/affiliation" class="sf-depth-2">Affiliation</a></li>
                      <li id="menu-1321-17" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/about/allotment" class="sf-depth-2">Allotment &amp; Surrender Report</a></li>
                    </ul>
                  </li>

                  <li id="menu-1131-1" class="main-nav">
                    <a href="/academic/admission" class="sf-depth-1 menuparent">Academic &#9660; </a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/admission" class="sf-depth-2">Admission</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/academic/regulations" class="sf-depth-2">Academic Regulations</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/academic/calender" class="sf-depth-2">Academic Calender</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/academic/lab?id=1" class="sf-depth-2">Computer Center Labs</a></li>

                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/academic/holidays" title="" class="sf-depth-2">List of Holidays</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/academic/attendance" class="sf-depth-2">Attendance</a></li>
                      <li id="menu-1321-17" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/syllabus" class="sf-depth-2">Syllabus</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/academic/rules" class="sf-depth-2">Disciplinary Rules</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/academic/paymenttutorial" class="sf-depth-2"> Online Fee Payment Tutorial</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/academic/antiragging" class="sf-depth-2"> Anti Ragging</a></li>
                      <li id="menu-1321-20" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/academiccouncil" class="sf-depth-2">MoM of Academic Council</a></li>
                      <li id="menu-1321-21" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/notice" class="sf-depth-2">Notice from Govt.</a></li>

                    </ul>
                  </li>

                  <li id="menu-1131-1" class="main-nav">
                    <a href="/category/department" class="sf-depth-1 menuparent">Departments &#9660;</a>

                    <div class="submenu">
                      <div class=" flex">




                        <ul class="">

                          <li class="headli">Under Graduates</li>
                          <li id="menu-1321-12" class="submenubar middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                              href="/department/civil/about" class="sf-depth-2">Civil Engineering</a>
                          <li id="menu-1462-13" class="submenubar  first odd sf-item-1 sf-depth-2 sf-no-children"><a
                              href="/department/mechanical/mech" class="sf-depth-2">Mechanical Engineering</a>
                          <li id="menu-1462-13" class="submenubar  first odd sf-item-1 sf-depth-2 sf-no-children"><a
                              href="/department/mechandsmartmanufacturing/about" class="sf-depth-2">Mechanical and
                              Smart Manufacturing</a>


                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/ece/about" class="sf-depth-2">Electronics Engineering (VLSI Design
                              and Technology) </a></li>
                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/cse/about" class="sf-depth-2">Computer Sc. & Engineering</a></li>
                          <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                              href="/department/eee/about" title="" class="sf-depth-2">Electrical & Electronics
                              Engg.</a></li>
                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/ash/about" class="sf-depth-2">Applied Sc. & Humanities</a></li>
                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/fp/about" class="sf-depth-2">Food Processing</a></li>

                        </ul>

                        <ul class="">
                          <li class="headli">Post Graduates</li>
                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/ecevlsi/about" class="sf-depth-2">Electronics Engineering (VLSI
                              Design
                              and Technology) </a></li>

                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/manufacturing/about" class="sf-depth-2">Manufacturing
                              Engineering</a></li>
                        </ul>
                      </div>
                    </div>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/facilities/csecenter" class="sf-depth-1 menuparent">Facilities &#9660; </a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/bank" class="sf-depth-2">Bank</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/facilities/centrallibrary" class="sf-depth-2">Central Library</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/facilities/csecenter" class="sf-depth-2">Computer Centre</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/facilities/club" title="" class="sf-depth-2">Club</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/facilities/guesthouse" class="sf-depth-2">Guest House</a></li>
                      <li id="menu-1321-17" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/gymnasium" class="sf-depth-2">Gymnasium</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/facilities/healthcenter" class="sf-depth-2">Health Centre</a></li>
                      <li id="menu-1401-19" class="last odd sf-item-7 sf-depth-2 sf-no-children"><a
                          href="/facilities/hostels" title="Hostels" class="sf-depth-2">Hostels</a></li>
                      <li id="menu-1321-20" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/medicalfacilities" class="sf-depth-2">Medical Facilities</a></li>
                      <li id="menu-1321-21" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/placementbrochure" class="sf-depth-2">Placement</a></li>
                      <li id="menu-1321-22" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/sportsfacilities" class="sf-depth-2">Sports Facilities</a></li>
                      <li id="menu-1321-23" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/startupcell" class="sf-depth-2">Startup cell</a></li>
                      <li id="menu-1321-24" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/usefulllink" class="sf-depth-2">Useful Link</a></li>
                      <li id="menu-1321-25" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/wifi" class="sf-depth-2">Wi Fi</a></li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/trainingplacement/placementbrochure" class="sf-depth-1 menuparent">Training &
                      Placement &#9660; </a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/aboutplacement" class="sf-depth-2">About Placement</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brouchure</a>
                      </li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of training &
                          Placement</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/tipsforresume" title="" class="sf-depth-2">Tips for Resume</a>
                      </li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/placedstudent" title="" class="sf-depth-2">Placed Students</a>
                      </li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/studentplacementcoordinator" class="sf-depth-2">Student Placement
                          Coordinator</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="https://tpo.keckatihar.in" class="sf-depth-2">Placement Portal</a></li>

                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/approval/akuapproval" class="sf-depth-1 menuparent">Approval &#9660; </a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/approval/aicteapproval" class="sf-depth-2">AICTE Aproval</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/approval/akuapproval" class="sf-depth-2">AKU Approval</a></li>
                      <li class="first leaf  menu-mlid-1178"><a href="/approval/grievance"
                          class="sf-depth-2 active">Grievance</a></li>
                      <li class="first leaf  menu-mlid-1179"><a href="/approval/mandatorydisclosure"
                          class="sf-depth-2 active">Mandatory Disclosure</a></li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/rti/rtiact" class="sf-depth-1 menuparent">RTI &#9660; </a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/rti/rtiact" class="sf-depth-2">RTI Act 2005</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/rti/rtiapplication" class="sf-depth-2">RTI Application Form</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/rti/rtistatus" class="sf-depth-2">RTI Status</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/rti/publicinformationcell" title="" class="sf-depth-2">Public Information Cell</a>
                      </li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/gallery/mediagallery" class="sf-depth-1 menuparent">Gallery &#9660; </a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/gallery/photogallery" class="sf-depth-2">photo Gallery </a></li>
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/gallery/mediagallery" class="sf-depth-2">Media Gallery </a></li>
                    </ul>
                  </li>
                  <li id="menu-218-1" class="main-nav">
                    <a href="/facilities/downloads" class="sf-depth-1 ">
                      Downloads</a>
                  </li>

                  <sec:authorize access="isAuthenticated()">
                    <li id="menu-218-1" class="main-nav">
                      <a href="/auth/uploadDashboard" title="" class="sf-depth-1 menuparent">Dashboard</a>
                    </li>
                  </sec:authorize>
                  <sec:authorize access="!isAuthenticated()">
                    <li class="main-nav">
                      <a href="/login" title="" class="sf-depth-1 menuparent">Login</a>
                    </li>
                  </sec:authorize>
                  <sec:authorize access="isAuthenticated()">
                    <li class="main-nav">
                      <a href="/logout" title="" class="sf-depth-1 menuparent">Logout</a>
                    </li>
                  </sec:authorize>
                </ul>
                <div id="nav_closer" onclick="closeNav()">

                </div>
              </section>
            </div>
          </div>





          <div role="navigation" class="desktop">
            <div class="menubarholder flex justify-end" style="background: #285690">

              <button data-drawer-target="default-sidebar" data-drawer-toggle="default-sidebar"
                aria-controls="default-sidebar" type="button"
                class="text-white inline-flex items-center p-2 mt-2 ms-3 text-sm text-gray-500 rounded-lg sm:hidden focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:focus:ring-gray-600">
                <span class="sr-only">Open sidebar</span>
                <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg">
                  <path clip-rule="evenodd" fill-rule="evenodd"
                    d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z">
                  </path>
                </svg>
              </button>
            </div>

            <div class="mobilenav bg-blue hidden"
              style="position:fixed;height:100vh;overflow-y:scroll;width:100%;top:0; z-index:9999999">
              <section id="block-superfish-1" class="">
                <ul id="superfish-1" class="p-4 md:flex justify-between text-white" style="background:#285690">
                  <li id="menu-218-1" class="w-full flex justify-end"><a onclick="toggleNav()"> X </a></li>

                  <li id="menu-218-1" class="main-nav"><a href="/" class="sf-depth-1 ">Home</a></li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/about/history" class="sf-depth-1 menuparent">About KEC</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/about/history" class="sf-depth-2">History</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/about/administration" class="sf-depth-2">Administration</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/about/principal" class="sf-depth-2">Principal's Message</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/about/vision" title="" class="sf-depth-2">Vision &amp; Mission</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/about/affiliation" class="sf-depth-2">Affiliation</a></li>
                      <li id="menu-1321-17" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/about/allotment" class="sf-depth-2">Allotment &amp; Surrender Report</a></li>
                    </ul>
                  </li>

                  <li id="menu-1131-1" class="main-nav">
                    <a href="/academic/admission" class="sf-depth-1 menuparent">Academic</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/admission" class="sf-depth-2">Admission</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/academic/regulations" class="sf-depth-2">Academic Regulations</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/academic/calender" class="sf-depth-2">Academic Calender</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/academic/holidays" title="" class="sf-depth-2">List of Holidays</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/academic/attendance" class="sf-depth-2">Attendance</a></li>
                      <li id="menu-1321-17" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/syllabus" class="sf-depth-2">Syllabus</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/academic/rules" class="sf-depth-2">Disciplinary Rules</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/academic/paymenttutorial" class="sf-depth-2"> Online Fee Payment Tutorial</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/academic/antiragging" class="sf-depth-2"> Anti Ragging</a></li>
                      <li id="menu-1321-20" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/academiccouncil" class="sf-depth-2">MoM of Academic Council</a></li>
                      <li id="menu-1321-21" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/academic/notice" class="sf-depth-2">Notice from Govt.</a></li>
                      |
                    </ul>
                  </li>

                  <li id="menu-1131-1" class="main-nav">
                    <a href="/category/department" class="sf-depth-1 menuparent">Departments</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" class="submenubar middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/department/civil/about" class="sf-depth-2">Under Graduate</a>

                        <ul class="submenu2">
                          <li id="menu-1321-12" class="submenubar middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                              href="/department/civil/about" class="sf-depth-2">Civil Engineering</a>
                          <li id="menu-1462-13" class="submenubar  first odd sf-item-1 sf-depth-2 sf-no-children"><a
                              href="/department/mechanical/mech" class="sf-depth-2">Mechanical Engineering</a>


                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/ece/about" class="sf-depth-2">Electronics Engineering (VLSI Design
                              and Technology) </a></li>
                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/cse/about" class="sf-depth-2">Computer Sc. & Engineering</a></li>
                          <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                              href="/department/eee/about" title="" class="sf-depth-2">Electrical & Electronics
                              Engg.</a></li>
                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/ash/about" class="sf-depth-2">Applied Sc. & Humanities</a></li>
                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/fp/about" class="sf-depth-2">Food Processing</a></li>

                        </ul>

                      </li>
                      <li id="menu-1462-13" class="submenubar  first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/department/mechanical/mech" class="sf-depth-2">Post Graduate</a>


                        <ul class="submenu2">
                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/ece/about" class="sf-depth-2">Electronics Engineering (VLSI Design
                              and Technology) </a></li>
                          <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                              href="/department/cse/about" class="sf-depth-2">Computer Sc. & Engineering</a></li>
                          <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                              href="/department/eee/about" title="" class="sf-depth-2">Electrical & Electronics
                              Engg.</a></li>
                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/ash/about" class="sf-depth-2">Applied Sc. & Humanities</a></li>
                          <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                              href="/department/fp/about" class="sf-depth-2">Food Processing</a></li>

                        </ul>
                      </li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/facilities/csecenter" class="sf-depth-1 menuparent">Facilities</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/bank" class="sf-depth-2">Bank</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/facilities/centrallibrary" class="sf-depth-2">Central Library</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/facilities/csecenter" class="sf-depth-2">Computer Centre</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/facilities/club" title="" class="sf-depth-2">Club</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/facilities/guesthouse" class="sf-depth-2">Guest House</a></li>
                      <li id="menu-1321-17" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/gymnasium" class="sf-depth-2">Gymnasium</a></li>
                      <li id="menu-1311-18" class="middle even sf-item-6 sf-depth-2 sf-no-children"><a
                          href="/facilities/healthcenter" class="sf-depth-2">Health Centre</a></li>
                      <li id="menu-1401-19" class="last odd sf-item-7 sf-depth-2 sf-no-children"><a
                          href="/facilities/hostels" title="Hostels" class="sf-depth-2">Hostels</a></li>
                      <li id="menu-1321-20" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/medicalfacilities" class="sf-depth-2">Medical Facilities</a></li>
                      <li id="menu-1321-21" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/placementbrochure" class="sf-depth-2">Placement</a></li>
                      <li id="menu-1321-22" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/sportsfacilities" class="sf-depth-2">Sports Facilities</a></li>
                      <li id="menu-1321-23" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/startupcell" class="sf-depth-2">Startup cell</a></li>
                      <li id="menu-1321-24" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/usefulllink" class="sf-depth-2">Useful Link</a></li>
                      <li id="menu-1321-25" class="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/facilities/wifi" class="sf-depth-2">Wi Fi</a></li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/trainingplacement/placementbrochure" class="sf-depth-1 menuparent">Training &
                      Placement</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/placementbrochure" class="sf-depth-2">About Placement</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brouchure</a>
                      </li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of training &
                          Placement</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/tipsforresume" title="" class="sf-depth-2">Tips for Resume</a>
                      </li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/placedstudent" title="" class="sf-depth-2">Placed Students</a>
                      </li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="/trainingplacement/studentplacementcoordinator" class="sf-depth-2">Student Placement
                          Coordinator</a></li>
                      <li id="menu-1133-16" class="middle even sf-item-4 sf-depth-2 sf-no-children"><a
                          href="https://tpo.keckatihar.in" class="sf-depth-2">Placement Portal</a></li>

                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/approval/akuapproval" class="sf-depth-1 menuparent">Approval</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/approval/aicteapproval" class="sf-depth-2">AICTE Aproval</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/approval/akuapproval" class="sf-depth-2">AKU Approval</a></li>
                      <li class="first leaf  menu-mlid-1178"><a href="/approval/grievance"
                          class="sf-depth-2 active">Grievance</a></li>
                      <li class="first leaf  menu-mlid-1179"><a href="/approval/mandatorydisclosure"
                          class="sf-depth-2 active">Mandatory Disclosure</a></li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/rti/rtiact" class="sf-depth-1 menuparent">RTI</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/rti/rtiact" class="sf-depth-2">RTI Act 2005</a></li>
                      <li id="menu-1462-13" class="first odd sf-item-1 sf-depth-2 sf-no-children"><a
                          href="/rti/rtiapplication" class="sf-depth-2">RTI Application Form</a></li>
                      <li id="menu-1282-14" class="middle even sf-item-2 sf-depth-2 sf-no-children"><a
                          href="/rti/rtistatus" class="sf-depth-2">RTI Status</a></li>
                      <li id="menu-1132-15" class="middle odd sf-item-3 sf-depth-2 sf-no-children"><a
                          href="/rti/publicinformationcell" title="" class="sf-depth-2">Public Information Cell</a>
                      </li>
                    </ul>
                  </li>
                  <li id="menu-1131-1" class="main-nav">
                    <a href="/gallery/mediagallery" class="sf-depth-1 menuparent">Gallery</a>
                    <ul class="submenu">
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/gallery/photogallery" class="sf-depth-2">Photo Gallery</a></li>
                      <li id="menu-1321-12" cl ass="middle odd sf-item-5 sf-depth-2 sf-no-children"><a
                          href="/gallery/mediagallery" class="sf-depth-2">Media Gallery</a></li>
                    </ul>
                  </li>
                  <li id="menu-218-1" class="main-nav">
                    <a href="/facilities/downloads" class="sf-depth-1 ">
                      Downloads</a>
                  </li>

                  <c:forEach var="item" items="${Role}">
                    <c:if test="${'Admin' eq item}">
                      <li id="menu-218-1" class="main-nav">
                        <a href="/auth/uploadDashboard" class="sf-depth-1 ">
                          Dashboard
                        </a>
                      </li>
                    </c:if>
                  </c:forEach>
                  <sec:authorize access="!isAuthenticated()">
                    <li class="main-nav">
                      <a href="/login" title="" class="sf-depth-1 menuparent">Login</a>
                    </li>
                  </sec:authorize>
                  <sec:authorize access="isAuthenticated()">
                    <li class="main-nav">
                      <a href="/logout" title="" class="sf-depth-1 menuparent">Logout</a>
                    </li>
                  </sec:authorize>
                </ul>
                <div id="nav_closer" onclick="closeNav()">

                </div>
              </section>
            </div>
          </div>

        </header>
      </main>

      <script>
        const mobile_icon = document.getElementById('mobile-icon');
        const mobile_menu = document.getElementById('mobile-menu');
        const hamburger_icon = document.querySelector("#mobile-icon i");

        function openCloseMenu() {
          mobile_menu.classList.toggle('block');
          mobile_menu.classList.toggle('active');
        }

        function changeIcon(icon) {
          icon.classList.toggle("fa-xmark");
        }

        mobile_icon.addEventListener('click', openCloseMenu);



      </script>