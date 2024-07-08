<jsp:include page="header.jsp" />
<style>
      .blink {
        animation: blinker 0.8s linear infinite;
        color: #1c87c9;
        font-size: 30px;
        font-weight: bold;
        font-family: sans-serif;
      }
      @keyframes blinker {
        50% {
          opacity: 0;
        }
      }
        
      }
    </style>
<center>
<h2 class="blink"style = "font-family:courier,arial,helvetica;color:red;font-weight:bold">Access denied</h2>

<h3 style = "font-family:courier,arial,helvetica;">You don't have a permission to this page.</h3>
<a href="/" style="font-size:20px">Go to Home page</a> 
</center>


<jsp:include page="footer.jsp" />
