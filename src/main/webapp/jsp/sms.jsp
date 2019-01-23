<jsp:include page ="header.jsp"/>

<div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;"></div><div class="container"> <br><br><div class="row"><div class="col-sm-offset-4 col-sm-4"><div class="panel panel-default"><div class="panel-heading"> </i> Send Message</div>
<div class="panel-body">


<form method="POST" action="/auth/sendMessage" id="sendMessageForm">
    <label for="noticeType" class="control-label col-sm-4">Message Group</label>
	<div class="col-sm-8">
		<select name = "smsGroup" id="smsGroup">
			<option value="KECWebTeam">KEC WEB TEAM</option>
			<option value="allStudents">ALL Students</option>
			<option value="allParents">ALL Parents</option>
			<option value="allFaculties">ALL Faculties</option>
			<option value="individual">Individual</option>
		</select><br/><br/><br/>
	</div>
	<label class="control-label col-sm-4" id="numberLabel" style="display:none;">Number </label>
	<div class="control-label col-sm-8">
        <input type="text" name="numberInput" id="numberInput" style="display:none;"></div><br>
    </div>
    <label for="message" class="control-label col-sm-4" >Message</label>
	<div class="col-sm-8">
		<input type="text" name = "message" id="message" /><br><br>
	</div>
    <input type="submit" class="btn btn-primary" value="Submit" id="btnSubmit"/>
    <a href="/" class="btn btn-success">Home Page</a><div class="col-sm-4"></div>
</form>

</div></div></div></div></div>


<jsp:include page="footer.jsp"/>