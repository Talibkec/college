<jsp:include page="/jsp/header.jsp" />

<style>
div.gallery {
  margin: 15px;
  border: 1px solid #ccc;
  float: left;
  width: 300px;
}

div.gallery:hover {
  border: 1px solid #777;
}

div.gallery img {
  width: 150%;
  height: 200px;
}

div.desc {
  padding: 15px;
  text-align: center;
}
</style>
    <div class="jumbotron" style="padding-top: 24px; padding-bottom: 24px;"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-sm-8"><h3>Image Gallery</h3></div>
                </div>
        </div>
    </div>
    <div class="container">

           <a href="/"> <i class="fa fa-home"></i> Home</a>

        <div class="text-left" style="font-size: 18px;"> Select Image Gallery: <select class="form-control"
                                                                                       style="display: inline; width: auto;"
                                                                                       onchange="location.href = this.options[this.selectedIndex].value">
            <option value="">Latest Images</option>
            <option value="">Campus of KEC Katihar</option>
            <option value="">Event at KEC Katihar</option>
            <option value="">Hostel of KEC</option>
            <option value="">Library of KEC Katihar</option>

        </select></div>
        <br/>
<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img1.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img1.JPG" alt="Cinque Terre" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img2.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img2.JPG" alt="Forest" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img3.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img3.JPG" alt="Northern Lights" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">Northern Lights
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img4.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img4.JPG" alt="Mountains" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>
<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img5.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img5.JPG" alt="Cinque Terre" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img6.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img6.JPG" alt="Forest" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img7.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img7.JPG" alt="Northern Lights" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>
<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img8.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img8.JPG" alt="Forest" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img9.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img9.JPG" alt="Northern Lights" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img10.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img10.JPG" alt="Mountains" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>
<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img11.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img11.JPG" alt="Cinque Terre" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img12.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img12.JPG" alt="Forest" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>

<div class="gallery">
  <a target="_blank" href="http://localhost/wp-content/uploads/2018/img13.JPG">
    <img src="http://localhost/wp-content/uploads/2018/img13.JPG" alt="Northern Lights" width="600" height="400">
  </a>
  <div class="desc"></div>
</div>
    </div>

<jsp:include page="/jsp/footer.jsp" />