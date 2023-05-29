<!DOCTYPE html>
<head>
</head>
<body>

<h1>Spring Boot - Multiple file upload example - AJAX</h1>

<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <label for="headLine">Head Line</label><input type="text" name="noticeHeader" id="noticeHeader"/><br/><br/>
    <label for="noticeType">Notice Type</label><select name = "noticeType" id="noticeType"><option value="Notice">Notice</option><option value="News">News</option></select><br/><br/>
    <label for="file">Choose File</label><input type="file" name="file"/><br/><br/>
    <input type="submit" value="Submit" id="btnSubmit"/>
</form>

<form method="GET" action="/" id="homePageForm">
    <input type="hidden" name="date" id="date"/><br/><br/>
    <input type="hidden" name="hnoticeHeader" id="hnoticeHeader"/><br/><br/>
    <input type="hidden" name="hfileLocation" id="hfileLocation"/><br/><br/>
    <input type="hidden" name="hnoticeType" id="hnoticeType"/><br/><br/>
</form>

<h1>Ajax Post Result</h1>
<pre>
    <span id="result"></span>
</pre>

<script type="text/javascript"
        src="/wp-content/themes/kec/assets/vendors/jquery/dist/jquery.min.js"></script>

<script type="text/javascript" src="/js/fileupload.js"></script>

</body>
</html>
