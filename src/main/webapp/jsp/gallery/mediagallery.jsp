<jsp:include page="/jsp/header.jsp" />


<head>
	<title>gallery demo</title>

	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/css/lightbox.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.11.1/js/lightbox.js"></script>

</head>
   <style>
   	.gallery img{
   		width: 260px;
   		padding: 5px;

   	}

   	   .gallery img:hover{


   		transform: scale(1.1);
   		transition: 1s;
   	}



   </style>

<body>

<div class="gallery" style="margin-bottom: 10px;">
	<center>
		<p><h1>MEDIA GALLERY</h1></p>
	<a href="http://localhost/wp-content/uploads/2018/img1.JPG" data-lightbox="photo"><img src="http://localhost/wp-content/uploads/2018/img1.JPG" style="max-width:50%;"></a>
	<a href="http://localhost/sites/default/files/img8.JPG" data-lightbox="photo"><img src="http://localhost/sites/default/files/img8.JPG" style="max-width:50%;"></a>
	<a href="http://localhost/wp-content/uploads/2018/img3.JPG" data-lightbox="photo"><img src="http://localhost/wp-content/uploads/2018/img3.JPG"  style="max-width:50%;"></a>
	<a href="http://localhost/sites/default/files/sportkec.jpeg" data-lightbox="photo"><img src="http://localhost/sites/default/files/sportkec.jpeg"  style="max-width:50%;"></a>
	<a href="http://localhost/wp-content/uploads/2018/img7.JPG" data-lightbox="photo"><img src="http://localhost/wp-content/uploads/2018/img7.JPG"></a>
    <a href="http://localhost/sites/default/files/sportkec1.jpeg" data-lightbox="photo"><img src="http://localhost/sites/default/files/sportkec1.jpeg"></a>
    <a href="http://localhost/sites/default/files/img13.JPG" data-lightbox="photo"><img src="http://localhost/sites/default/files/img13.JPG"></a>
    <a href="http://localhost/sites/default/files/img11.JPG" data-lightbox="photo"><img src="http://localhost/sites/default/files/img11.JPG"></a>
    <a href="http://localhost/wp-content/uploads/2018/img12.JPG" data-lightbox="photo"><img src="http://localhost/wp-content/uploads/2018/img12.JPG"></a>
     <a href="http://localhost/sites/default/files/DSC_0448.JPG" data-lightbox="photo"><img src="http://localhost/sites/default/files/DSC_0448.JPG"></a>
	</center>
</div>


<jsp:include page="/jsp/footer.jsp" />