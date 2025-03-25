<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp" />

<!-- Tailwind CSS and Google Fonts -->
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&family=Roboto:wght@300&display=swap" rel="stylesheet">

<style>
  body {
    font-family: 'Roboto', sans-serif;
    background-color: #f6f6f6;
  }

  .gallery-item {
    border: 1px solid #e2e2e2;
    border-radius: 0.5rem;
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .gallery-item:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }

  .gallery-item img {
    object-fit: cover;
    height: 200px;
  }

  .gallery-item .desc {
    padding: 1rem;
    text-align: center;
    font-size: 0.875rem;
    color: #333;
  }
</style>

<!-- Page Header -->
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

<!-- Menu Section -->
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

<!-- Image Gallery Section -->
<main class="container mx-auto px-4 py-6">
  <!-- Dropdown Selector -->
  <div class="mb-6 text-lg text-center">
    <label for="gallery-select" class="block mb-2 font-semibold">Select Image Gallery:</label>
    <select id="gallery-select"
      class="form-select border border-gray-300 rounded-md py-2 px-3 w-full sm:w-auto"
      onchange="location.href = this.options[this.selectedIndex].value">
      <option value="">Latest Images</option>
      <option value="">Campus of KEC Katihar</option>
      <option value="">Event at KEC Katihar</option>
      <option value="">Hostel of KEC</option>
      <option value="">Library of KEC Katihar</option>
    </select>
  </div>

  <!-- Gallery Grid -->
  <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
    <c:forEach items="${imageList}" var="image">
      <div class="gallery-item">
        <a href="/${image.galleryImageId}/galleryImage${image.fileType}" target="_blank">
          <img src="/${image.galleryImageId}/galleryImage${image.fileType}" alt="${image.caption}" class="w-full">
        </a>
        <div class="desc">
          <h2 class="text-xl font-semibold mb-2">
            <a href="/${image.galleryImageId}/galleryImage${image.fileType}" class="text-blue-600 hover:underline">${image.caption}</a>
          </h2>
          <c:forEach var="item" items="${Role}">
            <c:if test="${'Admin' eq item}">
              <a href="<c:url value='/auth/galleryImageDelete/${image.galleryImageId}'/>"
                class="hover:underline border px-4 py-2 bg-red-500 text-white rounded-lg">
                Delete
              </a>
            </c:if>
          </c:forEach>
        </div>
      </div>
    </c:forEach>
  </div>
</main>

<jsp:include page="/jsp/footer.jsp" />
