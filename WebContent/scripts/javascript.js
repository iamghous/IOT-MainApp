// this method will update ALL the homePage boxes
function callAllMethods(){
       
        getData("ShowDatabase","getDatabase");
        getData("ShowLastRow","getLastRow");
        getDataTextarea("ShowJson","getJson");
        getDataTextarea("ShowXml","getXml");
}


// Scan's the data from servelet and 
function scanData(address, id){
	
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     var returnedData = this.responseText;
            console.log(this.responseText);
            // split the responses text by line and assign it to array
            var arrayOfWords = returnedData.split("\n");
            var data = "";
            data += "<table> <thead> <tr> <th>Sensor Name </th> <th>Sensor Value</th> </tr> </thead> ";
            data +=  "<tbody> <tr> <td>"+ arrayOfWords[0] + "</td> <td>" + arrayOfWords[1] + "</td></tr> </tbody> </table>";
            document.getElementById(id).innerHTML = data;
	    }
	  };
	  xhttp.open("GET", address, true);
	  xhttp.send();
	
}

// A function which sends the Ajax request to web servlet and puts the response form the servlet to index html 
function getData(address, id){
	
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById(id).innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", address, true);
	  xhttp.send();
	
}
//A function which sends the Ajax request to web servlet and puts the response form the servlet to index html, the only difference in this 
// function is that it puts the response in the textarea rather than the div
function getDataTextarea(address, id){
	
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById(id).value = this.responseText;
	    }
	  };
	  xhttp.open("GET", address, true);
	  xhttp.send();
	
}

// this method is used to send request to the motor servlet to move the motor
function getDataMotor(address){
	  
    var selectBox = document.getElementById("selectBox");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("AfterMotorMoved").innerHTML = this.responseText;
	    }
	  };
	  xhttp.open("GET", address + "?position="+selectedValue, true);
	  xhttp.send();
	
}