<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- Include Swiper CSS & Tailwind CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

    <style>
        .container1 {
            position: relative;
        }

        .swiper-container {
            width: 100%;
            height: auto;
            overflow: hidden;
        }

        .mobile-swiper1 {
            display: none;
        }

        .mobile-swiper1 img {
            width: 100%;
            height: 250px;
            object-fit: cover;
            
        }


        /* Hide desktop Swiper on mobile */
        @media (max-width: 768px) {
            .desktop-swiper1 {
                display: none;
            }

            .mobile-swiper1 {
                display: block;
            }
        }

        .heading-animated1 {
            position: relative;
            display: inline-block;
            background: linear-gradient(90deg, #ff6b6b, #f06595, #cc5de8);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            animation: gradientText 3s ease infinite;
            text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        }

        @keyframes gradientText {
            0% {
                background-position: 0% 50%;
            }

            50% {
                background-position: 100% 50%;
            }

            100% {
                background-position: 0% 50%;
            }
        }

        /* Ensure a tag does not overflow */
        .swiper-slide {
            position: relative;
            overflow: visible !important;
        }

        .swiper-slide img {
            transition: none;
        }

        .swiper-slide img:hover {
            transform: none;
            opacity: 1;
        }

        /* Navigation button styles */
        .swiper-button-next,
        .swiper-button-prev {
            color: #ff6b6b;
        }

        .swiper-pagination-bullet-active {
            background: #ff6b6b !important;
        }
    </style>

    <div class="container1 mx-auto p-4">
        <h2 class="text-3xl block-title font-bold w-full text-gray-800 heading-animated1">
            Achievements
        </h2>

        <!-- Desktop Swiper -->
        <!-- Desktop Swiper -->
        <div class="swiper-container desktop-swiper1">
            <div class="swiper-wrapper">
                <c:forEach items="${achievementImageList}" var="achievementImage">
                    <c:if test="${achievementImage.displayFlag == 0}">
                    <div class="swiper-slide bg-white shadow-lg overflow-hidden p-4">
                        <div class="relative">
                            <a href="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                target="_blank">
                                <img class="w-full h-96 object-cover rounded-lg shadow-md"
                                    src="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                    alt="${achievementImage.achievementCaption}"
                                    title="${achievementImage.achievementCaption}" />
                            </a>
                        </div>
                        <span class="block text-center text-lg font-semibold mt-3 text-gray-700">
                            ${achievementImage.achievementCaption}
                        </span>
                        <c:forEach var="item" items="${Role}">
                            <c:if test="${'Admin' eq item}">
                                <div class="text-center mt-4">
                                    <a class="inline-block bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition duration-300"
                                        href="<c:url value='/auth/deleteAchievementImage/${achievementImage.achievementSlideId}' />">
                                        Delete
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
                </c:forEach>
            </div>
            <div class="swiper-pagination desktop-pagination"></div>
            <div class="swiper-button-next desktop-next"></div>
            <div class="swiper-button-prev desktop-prev"></div>
        </div>

        <!-- Mobile Swiper -->
        <div class="swiper-container mobile-swiper1">
            <div class="swiper-wrapper">
                <c:forEach items="${achievementImageList}" var="achievementImage">
                    <c:if test="${achievementImage.displayFlag == 1}">
                    <div class="swiper-slide bg-white shadow-lg overflow-hidden p-2">
                        <div class="relative">
                            <a href="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                target="_blank">
                                <img class=" rounded-lg shadow-md"
                                    src="/${achievementImage.achievementSlideId}/achievementImage${achievementImage.fileType}"
                                    alt="${achievementImage.achievementCaption}"
                                    title="${achievementImage.achievementCaption}" />
                            </a>
                        </div>
                        <span class="block text-center text-sm font-semibold mt-3 text-gray-700">
                            ${achievementImage.achievementCaption}
                        </span>
                        <c:forEach var="item" items="${Role}">
                            <c:if test="${'Admin' eq item}">
                                <div class="text-center mt-4">
                                    <a class="inline-block bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition duration-300"
                                        href="<c:url value='/auth/deleteAchievementImage/${achievementImage.achievementSlideId}' />">
                                        Delete
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
                </c:forEach>
            </div>
            <div class="swiper-pagination mobile-pagination"></div>
            <div class="swiper-button-next mobile-next"></div>
            <div class="swiper-button-prev mobile-prev"></div>
        </div>
    </div>

    <!-- Include Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>

    <script>
        // Desktop Swiper
        var desktopSwiper = new Swiper('.desktop-swiper1', {
            spaceBetween: 20, // Space between slides
            pagination: {
                el: '.desktop-pagination',
                clickable: true,
            },
            loop: true,
            autoplay: {
                delay: 2000,
            },
            navigation: {
                nextEl: '.desktop-next',
                prevEl: '.desktop-prev',
            },
            breakpoints: {
                // Adjust slidesPerView for smaller screens
                768: {
                    slidesPerView: 2, // 2 slides for tablets
                },
                0: {
                    slidesPerView: 1,
                }
            }
        });

        // Mobile Swiper
        var mobileSwiper = new Swiper('.mobile-swiper1', {
            slidesPerView: 1, // Display 1 slide at a time
            spaceBetween: 10,
            pagination: {
                el: '.mobile-pagination',
                clickable: true,
            },
            loop: true,
            autoplay: {
                delay: 2000,
            },// Disable loop to show all images
            navigation: {
                nextEl: '.mobile-next',
                prevEl: '.mobile-prev',
            },
        });
    </script>