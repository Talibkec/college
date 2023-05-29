<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        height:260px  
   	}

   	   .gallery img:hover{


   		transform: scale(1.1);
   		transition: 1s;
   	}



   </style>

<body>

<div class="gallery">
	<center>
		<p><h1>GALLERY</h1></p>
		<c:forEach items="${imageList}" var="image">
	        <a href="/${image.galleryImageId}/galleryImage${image.fileType}" data-lightbox="photo"><img src="/${image.galleryImageId}/galleryImage${image.fileType}" style="max-width:50%;"></a>
            <c:forEach var="item" items="${Role}">
                <c:if test = "${'Admin' eq item}">
                <span class="label label-danger"><a href="<c:url value='/auth/galleryImageDelete/${image.galleryImageId}'/>">Delete</a></span>
                </c:if>
            </c:forEach>
        </c:forEach>
	</center>
</div>


<jsp:include page="/jsp/footer.jsp" />
