<%@ taglib prefix="kec" uri="/WEB-INF/kec.tld" %><jsp:include page="/jsp/header.jsp"/>
<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Labs & Infrastructure</h3></div>

            </div>
        </div>
    </div>
    <div class="row">

        <jsp:include page="sidebar.jsp"/>

            <div class="col-md-8">
                <div class="page-content" style="line-height: 175%; font-size: 125%; font-weight: 300;"><kec:section key="department.cse.labs/main" title="CSE Labs: laboratory list"><p><span
                        style="color: #0000ff;"><strong>Departmental Laboratories</strong></span></p>
                    <ol>

                        <li>Programming Problems</li>
                        <li>Object Oriented Programming</li>
                        <li>Data Structure</li>
                        <li>Systems Programming</li>
						<li>Introduction to Java Programming Language</li>
						<li>DataBase Management Systems</li>
						<li>Operating Systems</li>
                    </ol></kec:section>
                </div>
            </div>
        </div>
    </div>
<div class="row">
    <iframe src="${pageContext.request.contextPath}/academic/lab?dept_id=1" width="600" height="500"></iframe>
</div>

<kec:blocks/><jsp:include page="/jsp/footer.jsp"/>
