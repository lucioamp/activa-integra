<%@ page contentType="text/html"%>
<%@ page pageEncoding="ISO-8859-1"%>
<head>
<script type="text/javascript">
			function go(){
                var url = "http://localhost:8080/activa/AplicacaoExternaCometServlet";
                var request =  new XMLHttpRequest();
                request.open("GET", url, true);
                request.setRequestHeader("Content-Type","application/x-javascript;");
                request.onreadystatechange = function() {
                    if (request.readyState == 4) {
                        if (request.status == 200){
                            if (request.responseText) {
                                document.getElementById("forecasts").innerHTML = request.responseText;
                            }
                        }
                        go();
                    }
                };
                request.send(null);
            }
		</script>
</head>
<body>
<input type="button" onclick="go()" value="Go!"></input>
<div id="forecasts"></div>
</body>