<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.college.service.UserService" %>
<%@ page import="com.college.core.model.PlacementsDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.http.converter.json.GsonBuilderUtils" %>

<jsp:include page="/jsp/header.jsp"/>
<style>
    td {
        text-align: left;
        padding: 10px !important;
    }
</style>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-sm-8"><h3>Training and Placed Students</h3></div>

        </div>
    </div>
</div>
<div class="container">

    <a href="/"><i class="fa fa-home"></i> Home</a>
F
    <div class="row"  style="margin:30px">

            <aside class="col-sm-3" role="complementary">
                <div class="region region-sidebar-second">
                    <section id="block-menu-block-3" class="block block-menu-block clearfix">
                        <h2 class="block-title">Training & Placement </h2>
                        <div class="menu-block-wrapper menu-block-3 menu-name-main-menu parent-mlid-0 menu-level-2">
                            <ul class="menu nav">
                                <li class="first leaf  menu-mlid-1154"><a href="/trainingplacement/aboutplacement" class="sf-depth-2 active">About Placement</a></li>
                                <li class="leaf menu-mlid-1121"><a href="/trainingplacement/rulesoftrainingplacement" class="sf-depth-2">Rules of Training & Placement</a></li>
                                <li class="leaf menu-mlid-1121"><a href="/trainingplacement/tipsforresume" class="sf-depth-2">Tips for Resume</a></li>
                                <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placedstudent" class="sf-depth-2">Placed Students</a></li>
                                <li class="leaf menu-mlid-1121"><a href="/trainingplacement/placementbrochure" class="sf-depth-2">Placement Brochure</a></li>
                            </ul>
                        </div>
                    </section>
                </div>
            </aside>

            <div class="col-md-8">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;">
                    <style>
                        .accordion-button {
                            font-size: 20px;
                        }
                        .accordion-body {
                            overflow: scroll;
                        }
                    </style>




                    <c:if test="${userType == 1}">
                        <a href="/auth/uploadfile/addPlacements">
                            <button class="btn btn-primary mt-2 p-2"> Add Placement </button>
                        </a>
                    </c:if>



                    <table class="table">
                        <thead>
                        <tr>
                            <th>SI No.</th>
                            <th>Name</th>
                            <th>Session</th>
                            <th>Branch</th>
                            <th>Registration No.</th>
                            <th>Company Name</th>

                        </tr>
                        </thead>


                        <tbody>
                        <c:forEach items="${placementList}" var="placement">
                        <tr>
                            <td>1</td>
                            <td>${placement.studentName}</td>
                            <td>${placement.studentSession}</td>
                            <td>${placement.departmentId}</td>
                            <td>${placement.reigstrationNo}</td>
                            <td>${placement.companyName}</td>
                            <td>

                                <c:if test="${userType == 1}">
                                  <a href="./deletePlacement?placementId=${placement.placementId}"><button class="btn btn-danger">Delete</button></a>
                                </c:if>
                            </td>
                        </tr>
                        </c:forEach>

                        </tbody>


                    </table>
























<%--                    <div class="accordion" id="accordionExample">--%>
<%--                        <div class="accordion-item">--%>
<%--                            <h2 class="accordion-header" id="headingOne">--%>
<%--                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">--%>
<%--                                    (2016-2020)--%>
<%--                                </button>--%>
<%--                            </h2>--%>
<%--                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne">--%>
<%--                                <div class="accordion-body">--%>



<%--                                    <table class="table">--%>
<%--                                        <thead>--%>
<%--                                        <tr>--%>
<%--                                            <th>SI No.</th>--%>
<%--                                            <th>Name</th>--%>
<%--                                            <th>Session</th>--%>
<%--                                            <th>Branch</th>--%>
<%--                                            <th>Registration No.</th>--%>
<%--                                            <th>Company Name</th>--%>
<%--                                        </tr>--%>
<%--                                        </thead>--%>


<%--                                        <tbody>--%>

<%--                                        <tr>--%>
<%--                                            <td>1</td>--%>
<%--                                            <td>Ankit Dutta</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129016</td>--%>
<%--                                            <td>Capgemini Pvt Ltd</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>2</td>--%>
<%--                                            <td>Anshuman PdChoudhary Badal</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129009</td>--%>
<%--                                            <td>Blue Berry e-Services Pvt Ltd</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>3</td>--%>
<%--                                            <td>Suraj Kumar</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129012</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>4</td>--%>
<%--                                            <td>Sakshi Priya</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129902</td>--%>
<%--                                            <td>IBM</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>5</td>--%>
<%--                                            <td>Nishu Singh</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129001</td>--%>
<%--                                            <td>HCL Technology</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>7</td>--%>
<%--                                            <td>Ankit Kumar</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129033</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>8</td>--%>
<%--                                            <td>Aman Kumar Pandi</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129015</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>9</td>--%>
<%--                                            <td>Rishav Kumar</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129005</td>--%>
<%--                                            <td>Blue Berry e-Services Pvt Ltd</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>10</td>--%>
<%--                                            <td>Gaurav Kumar</td>--%>
<%--                                            <td>(2016-2020)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>16105129028</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        </tbody>--%>


<%--                                    </table>--%>




<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="accordion-item">--%>
<%--                            <h2 class="accordion-header" id="headingTwo">--%>
<%--                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">--%>
<%--                                    (2017-2021)--%>
<%--                                </button>--%>
<%--                            </h2>--%>
<%--                            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo">--%>
<%--                                <div class="accordion-body">--%>


<%--                                    <table class="table">--%>
<%--                                        <thead>--%>
<%--                                        <tr>--%>
<%--                                            <th>SI No.</th>--%>
<%--                                            <th>Name</th>--%>
<%--                                            <th>Session</th>--%>
<%--                                            <th>Branch</th>--%>
<%--                                            <th>Registration No.</th>--%>
<%--                                            <th>Company Name</th>--%>
<%--                                        </tr>--%>
<%--                                        </thead>--%>


<%--                                        <tbody>--%>

<%--                                        <tr>--%>
<%--                                            <td>11</td>--%>
<%--                                            <td>Faizan Alam</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129030</td>--%>
<%--                                            <td>SourceFuse Technologies</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>12</td>--%>
<%--                                            <td>Megha Agarwal</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129020</td>--%>
<%--                                            <td>Tata Consultancy Services</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>13</td>--%>
<%--                                            <td>Kumar Saksham</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129031</td>--%>
<%--                                            <td>Wipro</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>14</td>--%>
<%--                                            <td>Nigar Naaz</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129027</td>--%>
<%--                                            <td>Mapmy India</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>15</td>--%>
<%--                                            <td>Manish Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129016</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>16</td>--%>
<%--                                            <td>Prinse Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129901</td>--%>
<%--                                            <td>Mapmy India</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>17</td>--%>
<%--                                            <td>Simran Kumari</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129013</td>--%>
<%--                                            <td>Mapmy India</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>18</td>--%>
<%--                                            <td>Chunnu Yadav</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129025</td>--%>
<%--                                            <td>Mount Blue Technologies</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>19</td>--%>
<%--                                            <td>Sima Kumari</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129003</td>--%>
<%--                                            <td>Cognizant</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>20</td>--%>
<%--                                            <td>Annu Kumari</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129023</td>--%>
<%--                                            <td>Tata Consultancy Services</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>21</td>--%>
<%--                                            <td>Pallavi Kumari</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129001</td>--%>
<%--                                            <td>HCL through Collabera</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>22</td>--%>
<%--                                            <td>Ranjana Bharti</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129026</td>--%>
<%--                                            <td>Savantis Solution</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>23</td>--%>
<%--                                            <td>Sumit Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129028</td>--%>
<%--                                            <td>Tech Mahindra</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>24</td>--%>
<%--                                            <td>Abdul Talib</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129018</td>--%>
<%--                                            <td>BYJU's</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>25</td>--%>
<%--                                            <td>Jyoti Rani</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129004</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>26</td>--%>
<%--                                            <td>Puja Singh</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129024</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>27</td>--%>
<%--                                            <td>Gunjan Bharti</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129005</td>--%>
<%--                                            <td>Atos Syntel</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>28</td>--%>
<%--                                            <td>Amit Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129043</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>29</td>--%>
<%--                                            <td>Sakshi Kumari</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129008</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>30</td>--%>
<%--                                            <td>Dipty Beauty</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129002</td>--%>
<%--                                            <td>Tech Mahindra</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>31</td>--%>
<%--                                            <td>Alok Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129007</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>32</td>--%>
<%--                                            <td>Golu Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129029</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>33</td>--%>
<%--                                            <td>Satyam Singh</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129021</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>34</td>--%>
<%--                                            <td>Akshay Anand</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129035</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>35</td>--%>
<%--                                            <td>Ankit Kumar Rajak</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129036</td>--%>
<%--                                            <td>BYJU's</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>36</td>--%>
<%--                                            <td>Shubham Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129014</td>--%>
<%--                                            <td>Tech Mahindra</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>37</td>--%>
<%--                                            <td>Sharique Zahir</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129017</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>38</td>--%>
<%--                                            <td>Sujeet Kumar</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>17105129044</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>39</td>--%>
<%--                                            <td>Richa Trivedi</td>--%>
<%--                                            <td>(2017-2021)</td>--%>
<%--                                            <td>ME</td>--%>
<%--                                            <td>17102129030</td>--%>
<%--                                            <td>Creta Class</td>--%>
<%--                                        </tr>--%>

<%--                                        </tbody>--%>

<%--                                    </table>--%>


<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="accordion-item">--%>
<%--                            <h2 class="accordion-header" id="headingThree">--%>
<%--                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree">--%>
<%--                                    (2018-2022)--%>
<%--                                </button>--%>
<%--                            </h2>--%>
<%--                            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree">--%>
<%--                                <div class="accordion-body">--%>



<%--                                    <table class="table">--%>
<%--                                        <thead>--%>
<%--                                        <tr>--%>
<%--                                            <th>SI No.</th>--%>
<%--                                            <th>Name</th>--%>
<%--                                            <th>Session</th>--%>
<%--                                            <th>Branch</th>--%>
<%--                                            <th>Registration No.</th>--%>
<%--                                            <th>Company Name</th>--%>
<%--                                        </tr>--%>
<%--                                        </thead>--%>

<%--                                        <tbody>--%>


<%--                                        <tr>--%>
<%--                                            <td>40</td>--%>
<%--                                            <td>Aarush Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129009</td>--%>
<%--                                            <td>MapMyIndia</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>41</td>--%>
<%--                                            <td>Kumar Saurabh Sum</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129001</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>42</td>--%>
<%--                                            <td>Raj Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129002</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>43</td>--%>
<%--                                            <td>Rakhi Kumari</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129034</td>--%>
<%--                                            <td>Atsuya Tech. Pvt. Ltf.</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>44</td>--%>
<%--                                            <td>Tathagat Jee</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129020</td>--%>
<%--                                            <td>Default</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>45</td>--%>
<%--                                            <td>Khushboo Kumari</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129050</td>--%>
<%--                                            <td>Wipro</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>46</td>--%>
<%--                                            <td>Sameer Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129008</td>--%>
<%--                                            <td>Atsuya Tech. Pvt. Ltf.</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>47</td>--%>
<%--                                            <td>Ritu Kumari Mahat</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129033</td>--%>
<%--                                            <td>HCL</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>48</td>--%>
<%--                                            <td>Chandan Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129017</td>--%>
<%--                                            <td>MapMyIndia</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>49</td>--%>
<%--                                            <td>Manisha Singh</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129025</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>50</td>--%>
<%--                                            <td>Sakshi Gosh</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129028</td>--%>
<%--                                            <td>HCL Through Collabera</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>51</td>--%>
<%--                                            <td>Prashan Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129040</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>52</td>--%>
<%--                                            <td>Nikhil Kumar Jha</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129019</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>53</td>--%>
<%--                                            <td>Gourav Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129044</td>--%>
<%--                                            <td>Default</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>54</td>--%>
<%--                                            <td>Navin Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129041</td>--%>
<%--                                            <td>Capgemini</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>55</td>--%>
<%--                                            <td>Divya</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129023</td>--%>
<%--                                            <td>Bolt Metric</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>56</td>--%>
<%--                                            <td>Saurav Suman</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129043</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>57</td>--%>
<%--                                            <td>Purshottam Kumar Jha</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129005</td>--%>
<%--                                            <td>NEPTUNE India Pvt. Ltd. (Noid</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>58</td>--%>
<%--                                            <td>Ayushi Arya</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129022</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>59</td>--%>
<%--                                            <td>Palak</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129031</td>--%>
<%--                                            <td>Wipro ( Project Engineer)</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>60</td>--%>
<%--                                            <td>Deepti jha</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129047</td>--%>
<%--                                            <td>HCL</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>61</td>--%>
<%--                                            <td>Amarjeet Kumar Keshav</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129004</td>--%>
<%--                                            <td>Progressive Infotech, Noida</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>62</td>--%>
<%--                                            <td>Abhilasha Kumari</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129013</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>63</td>--%>
<%--                                            <td>Manisha Singh</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129025</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>64</td>--%>
<%--                                            <td>Satish Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129035</td>--%>
<%--                                            <td>Global InfotechPvt. LTD.</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>65</td>--%>
<%--                                            <td>Anshika Singh Sengar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129021</td>--%>
<%--                                            <td>Infosys, Wipro</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>66</td>--%>
<%--                                            <td>Satyam Kumar Das</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td></td>--%>
<%--                                            <td>Pibythree</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>67</td>--%>
<%--                                            <td>Tanuja Kumari</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129045</td>--%>
<%--                                            <td>Terex</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>68</td>--%>
<%--                                            <td>Sakshi Gosh</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129028</td>--%>
<%--                                            <td>BISAG-N</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>69</td>--%>
<%--                                            <td>Vikram Kumar</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129010</td>--%>
<%--                                            <td>Save ECO Organic PVT. LTD</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>70</td>--%>
<%--                                            <td>Nisha Verma</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129024</td>--%>
<%--                                            <td>Taquino Pvt. LTD.</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>71</td>--%>
<%--                                            <td>Sudarshan Sahu</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>18105129003</td>--%>
<%--                                            <td>GlobalTeckz</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>72</td>--%>
<%--                                            <td>SAURAV KUMAR JHA</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>ME</td>--%>
<%--                                            <td>18102129041</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>73</td>--%>
<%--                                            <td>Akansha</td>--%>
<%--                                            <td>(2018-2022)</td>--%>
<%--                                            <td>ME</td>--%>
<%--                                            <td>18102129008</td>--%>
<%--                                            <td>Infosys</td>--%>
<%--                                        </tr>--%>
<%--                                        </tbody>--%>

<%--                                    </table>--%>



<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>


<%--                        <div class="accordion-item">--%>
<%--                            <h2 class="accordion-header" id="headingFour">--%>
<%--                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">--%>
<%--                                    (2019-2023)--%>
<%--                                </button>--%>
<%--                            </h2>--%>
<%--                            <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour">--%>
<%--                                <div class="accordion-body ">--%>



<%--                                    <table class="table">--%>
<%--                                        <thead>--%>
<%--                                        <tr>--%>
<%--                                            <th>SI No.</th>--%>
<%--                                            <th>Name</th>--%>
<%--                                            <th>Session</th>--%>
<%--                                            <th>Branch</th>--%>
<%--                                            <th>Registration No.</th>--%>
<%--                                            <th>Company Name</th>--%>
<%--                                        </tr>--%>
<%--                                        </thead>--%>

<%--                                        <tbody>--%>

<%--                                        <tr>--%>
<%--                                            <td>74</td>--%>
<%--                                            <td>Mohit Kumar</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>20105129906</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>75</td>--%>
<%--                                            <td>Tannu Singh</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>19105129020</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>76</td>--%>
<%--                                            <td>Vishal Kumar Gupta</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>20105129907</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>77</td>--%>
<%--                                            <td>Shivam Kumar</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>19105129014</td>--%>
<%--                                            <td>TCS</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>78</td>--%>
<%--                                            <td>Chottu Kumar</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>19105129032</td>--%>
<%--                                            <td>HCL</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>79</td>--%>
<%--                                            <td>Hozifa</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>20105129905</td>--%>
<%--                                            <td>Planet Spark</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td>80</td>--%>
<%--                                            <td>Md. Shahid</td>--%>
<%--                                            <td>(2019-2023)</td>--%>
<%--                                            <td>CSE</td>--%>
<%--                                            <td>20105129912</td>--%>
<%--                                            <td>HCL</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td></td>--%>
<%--                                            <td></td>--%>
<%--                                            <td></td>--%>
<%--                                            <td></td>--%>
<%--                                            <td></td>--%>
<%--                                            <td></td>--%>
<%--                                        </tr>--%>
<%--                                        </tbody>--%>

<%--                                    </table>--%>



<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>





<%--                    </div>--%>



















                        </tbody>
                    </table>

                </div>
            </div></div>
                </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
