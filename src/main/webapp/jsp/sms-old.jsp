<!DOCTYPE html>
<head>
</head>
<body>

<h1>Send SMS</h1>

<form method="POST" action="/auth/sendMessage" id="sendMessageForm">
    <label for="noticeType">Notice Type</label><select name = "smsGroup" id="smsGroup"><option value="KECWebTeam">KEC WEB TEAM</option><option value="CSE5thsem">CSE 5tm Sem</option></select><br/><br/>
    <label for="message">Message<input type="text" name = "message" id="message" />
    <input type="submit" value="Submit" id="btnSubmit"/>
    <a href="/">Home Page</a>
</form>

<h1>Ajax Post Result</h1>
<pre>
    <span id="result"></span>
</pre>

<script type="text/javascript"
        src="http://localhost/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>

<script type="text/javascript" src="/js/sendsms.js"></script>

</body>
</html>