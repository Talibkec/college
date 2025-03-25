<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    width: 100%;
    height: 200px;
    object-fit: cover;
  }

  div.desc {
    padding: 15px;
    text-align: center;
  }
</style>

  
  
  
<div class="bg-gray-200 py-4 mt-4">
  <div class="container mx-auto px-4">
    <div class="flex items-center justify-between">
      <div class="flex space-x-4">
        <a href="/"> <i class="fa fa-home"></i> Home</a>
      </div>
      <div class="flex-grow text-center">
        <h3 class="text-2xl font-semibold">Photo Gallery</h3>
      </div>
    </div>
  </div>
</div>
<div class="flex justify-center items-center px-4 mt-5">
    <div class="bg-white w-full max-w-md p-6 rounded-lg shadow-lg border border-gray-300">
      <h2 class="text-xl font-semibold text-gray-800 text-center mb-4">Photo Gallery</h2>
      
      <div class="flex flex-col sm:flex-row items-center justify-center gap-4">
        <a href="/gallery/photogallery"
          class="w-full sm:w-auto px-6 py-3 bg-gray-100 text-gray-700 font-medium text-lg rounded-lg shadow hover:bg-blue-500 hover:text-white transition duration-300 text-center">
         Photo Gallery
        </a>
        <a href="/gallery/mediagallery"
          class="w-full sm:w-auto px-6 py-3 bg-gray-100 text-gray-700 font-medium text-lg rounded-lg shadow hover:bg-blue-500 hover:text-white transition duration-300 text-center">
       Media Gallery
        </a>
      </div>
    </div>
  </div>
<div class="container mx-auto mt-4 px-4">
  <div class="mb-4 flex items-center gap-5">
    <label for="gallery-select" class="text-lg font-medium">Select Image Gallery:</label>
    <select id="gallery-select"
      class="form-select block w-1/2 sm:w-1/3 bg-gray-100 border border-gray-300 rounded-md py-2 px-3 text-gray-900"
      onchange="location.href = this.options[this.selectedIndex].value">
      <option value="">Latest Images</option>
      <option value="">Campus of KEC Katihar</option>
      <option value="">Event at KEC Katihar</option>
      <option value="">Hostel of KEC</option>
      <option value="">Library of KEC Katihar</option>
    </select>
  </div>

  <!-- Search Bar -->
  <div class="mb-4">
    <input type="text" id="search-input" placeholder="Search images..."
      class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
  </div>

  <div id="gallery-container" class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
    <% 
      String[] images = {
        "img1.JPG", "img2.JPG", "img3.JPG", "img4.JPG",
        "img5.JPG", "img6.JPG", "img7.JPG", "img8.JPG",
        "img9.JPG", "img10.JPG", "img11.JPG", "img12.JPG",
        "img13.JPG"
      };
      
      String[] captions = {
        "Campus View", "Library", "Event Hall", "Hostel Building",
        "Lab", "Workshop", "Sports Ground", "Conference Hall",
        "Canteen", "Placement Cell", "Auditorium", "Garden Area",
        "Main Gate"
      };

      for (int i = 0; i < images.length; i++) {
    %>
    <div class="gallery-item bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105"
      data-title="<%= captions[i] %>">
      <a href="/wp-content/uploads/2018/<%= images[i] %>" target="_blank">
        <img src="/wp-content/uploads/2018/<%= images[i] %>" alt="<%= captions[i] %>" class="w-full h-48 object-cover">
      </a>
      <div class="p-4 text-center text-gray-700">
        <%= captions[i] %>
      </div>
    </div>
    <% } %>
  </div>
</div>

<script>
 
  document.getElementById("search-input").addEventListener("input", function () {
    let query = this.value.toLowerCase();
    let items = document.querySelectorAll(".gallery-item");

    items.forEach(item => {
      let title = item.getAttribute("data-title").toLowerCase();
      if (title.includes(query)) {
        item.style.display = "block";
      } else {
        item.style.display = "none";
      }
    });
  });
</script>

<jsp:include page="/jsp/footer.jsp" />
