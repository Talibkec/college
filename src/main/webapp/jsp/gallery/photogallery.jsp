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
   <div class="bg-gray-200 py-4 mt-4">
       <div class="container mx-auto px-4">
           <div class="flex items-center justify-between">
            <div class="flex space-x-4">
                   <a href="/"> <i class="fa fa-home"></i> Home</a>
                                      </div>
               <!-- Social Media Icons Section -->
               <div class="flex-grow text-center">
                   <h3 class="text-2xl font-semibold">Photo Gallery</h3>
               </div>

           </div>
       </div>
   </div>

    <div class="container mx-auto mt-4 px-4">
        <div class="mb-4 flex items-center gap-5">
            <label for="gallery-select" class="text-lg font-medium">Select Image Gallery:</label>
            <select id="gallery-select" class="form-select block w-1/2 sm:w-1/3 bg-gray-100 border border-gray-300 rounded-md py-2 px-3 text-gray-900" onchange="location.href = this.options[this.selectedIndex].value">
                <option value="">Latest Images</option>
                <option value="">Campus of KEC Katihar</option>
                <option value="">Event at KEC Katihar</option>
                <option value="">Hostel of KEC</option>
                <option value="">Library of KEC Katihar</option>
            </select>
        </div>

        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
            <!-- Gallery Item 1 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img1.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img1.JPG" alt="Cinque Terre" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 2 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img2.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img2.JPG" alt="Forest" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 3 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img3.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img3.JPG" alt="Northern Lights" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 4 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img4.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img4.JPG" alt="Mountains" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 5 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img5.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img5.JPG" alt="Cinque Terre" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 6 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img6.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img6.JPG" alt="Forest" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 7 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img7.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img7.JPG" alt="Northern Lights" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 8 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img8.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img8.JPG" alt="Forest" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 9 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img9.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img9.JPG" alt="Northern Lights" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 10 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img10.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img10.JPG" alt="Mountains" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 11 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img11.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img11.JPG" alt="Cinque Terre" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 12 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img12.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img12.JPG" alt="Forest" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>

            <!-- Gallery Item 13 -->
            <div class="bg-white border border-gray-300 rounded-lg shadow-md overflow-hidden transition-transform transform hover:scale-105">
                <a href="/wp-content/uploads/2018/img13.JPG" target="_blank">
                    <img src="/wp-content/uploads/2018/img13.JPG" alt="Northern Lights" class="w-full h-48 object-cover">
                </a>
                <div class="p-4 text-center text-gray-700">
                    <!-- Add caption if needed -->
                </div>
            </div>
        </div>
    </div>

<jsp:include page="/jsp/footer.jsp" />
