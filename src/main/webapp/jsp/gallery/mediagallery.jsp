<jsp:include page="/jsp/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<c:forEach items="${imageList}" var="image">
   <div class="gallery">
     <a target="_blank" href="http://localhost/${image.galleryImageId}/galleryImage${image.fileType}">
       <img src="http://localhost/${image.galleryImageId}/galleryImage${image.fileType}" alt="Cinque Terre" width="600" height="400">
     </a>
     <div class="desc"></div>
   </div>
   </c:forEach>


<jsp:include page="/jsp/footer.jsp" />
