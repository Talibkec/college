<jsp:include page="/jsp/header.jsp"/>
<div class=" py-6">
     <div class="flex items-center justify-between bg-gray-100 py-4">

                 <div class="flex space-x-4">
                                        <a href="/" class="text-blue-600 flex items-center"><i class="fa fa-home mr-2"></i> Home</a>
                 </div>
               <div class="flex-grow text-center">
                   <h3 class="text-2xl font-semibold">Contact Us</h3>
               </div>

     </div>

<div class="container mx-auto mt-8">


    <div class="flex justify-center mt-8">
        <div class="w-full max-w-md">
            <div class="bg-white shadow-md rounded-lg">
                <div class="bg-gray-200 px-4 py-3 border-b border-gray-300">
                    <i class="fa fa-sign-in mr-2"></i>Contact us
                </div>
                <div class="p-4">
                    <form method="post" class="space-y-4" action="/contactus">
                        <div>
                            <input type="text" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" name="username" placeholder="Email" required autofocus>
                        </div>
                        <div>
                            <input type="text" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" name="name" placeholder="Name" required autofocus>
                        </div>
                        <div>
                            <input type="number" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" name="phone" placeholder="Phone" required autofocus>
                        </div>
                        <div>
                            <input type="number" class="form-input mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" name="registrationNo" placeholder="Registration No" required>
                        </div>
                        <div>
                            <textarea rows="6" class="form-textarea mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm" name="message" placeholder="Type your message here" required></textarea>
                        </div>
                        <div class="flex justify-center mt-6">
                            <button type="submit" class="btn btnHover bg-blue-400 text-white px-8 py-2 rounded-lg hover:bg-blue-500 transition duration-150 ease-in-out"><i class="fa fa-check mr-2"></i>Send Message</button>
                        </div>
                        <div class="mt-6 text-center">
                            <p>Contact mail: <a href="mailto:principalranjanakri84@gmail.com" class="text-blue-600">principalranjanakri84@gmail.com</a></p>
                            <p>Contact phone: <a href="tel:06452-239122" class="text-blue-600">06452-239122</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalInfo">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header"><h4 class="modal-title">Information!</h4></div>
                <div class="modal-body">
                    Please contact site admin to reset and get a new password for your account.
                    <hr>
                    <div class="text-right">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-remove"></i> Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>
