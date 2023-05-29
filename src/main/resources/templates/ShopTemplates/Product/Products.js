/*
$(document).ready(function() {
    $("#searchbutton").click(function() {
      alert("¡Hola desde jQuery!");
    });
  });
*/

var productArray = [];
var str = "";
var prodid = 0;
var url = "http://localhost:8080";

//loads the information from the backend
document.addEventListener("DOMContentLoaded", function() {
    getArray();
});

//makes fetch with the backend
async function getArray(){
    productArray = 
    await fetch(url+"/Product", {
          method: 'GET',
          mode: 'cors'
    })
    .then(response => response.json())
    .catch(error => { 
        console.log(error);
        alert(error);
    });
}

//when clic call getProducts()
$(document).ready(function() {
    $("#getall").click(function() {
      getProducts();
    });
});

//when clic call findProduct()
$(document).ready(function() {
    $("#searchbutton").click(function() {
      findProduct();
    });
});

//function to print all products on table
function getProducts(){
    str = "";
    for(let i=0;i<productArray.length;i++){
        str +=
            "<tr><td colspan=\"1\">" + productArray[i].prod_id+"</td>"
            + "<td colspan=\"1\">" + productArray[i].name + "</td>" 
            + "<td colspan=\"1\">" + productArray[i].cost + "</td>" 
            + "<td colspan=\"1\">" + productArray[i].price +"</td>"
            + "<td colspan=\"1\">" + productArray[i].description + "</td>" 
            + "<td colspan=\"1\">" + productArray[i].dep_id + "</td>"
            + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtn\" onclick=\"deleteProduct("+productArray[i].prod_id+")\">Delete</button>"
            + "<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"modifyProduct("+productArray[i].prod_id+")\">Modify</button></td></tr>";
        document.getElementById("producttable").innerHTML = str;
    }
}

//function to find one product
function findProduct(){
    try{
        str = "";
        prodid = document.getElementById("searchinput").value;
        if(prodid==0){
            alert("Please introduce product ID");
        }else{
            str = productArray.find((element)=>{
                return element.prod_id==prodid
            });
                str = 
                    "<tr><td colspan=\"1\">" + str.prod_id + "</td>" 
                    + "<td colspan=\"1\">" + str.name + "</td>" 
                    + "<td colspan=\"1\">" + str.cost + "</td>" 
                    + "<td colspan=\"1\">" + str.price +"</td>"
                    + "<td colspan=\"1\">" + str.description + "</td>" 
                    + "<td colspan=\"1\">" + str.dep_id + "</td>"
                    + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtn\" onclick=\"deleteProduct("+str.prod_id+")\">Delete</button>"
                    + "<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"modifyProduct("+str.prod_id+")\">Modify</button></td></tr>";
                document.getElementById("producttable").innerHTML = str;
        }
    }catch(error){
        alert("Data not found.");
    }
}


//function to modify products
function modifyProduct(prodid){
    console.log('test done');
    console.log(prodid)
}

//function to create delete product
async function deleteProduct(prodid){
    await fetch(url+"/Product/"+prodid, {
        method: "DELETE"
    }).then(response => response.json())
    .catch(error => {
        console.error();
    });
    location.reload();
}

//function to call createProduct()
$(document).ready(function() {
    $("#sendbtnform").click(function(){
        createProduct();
    });
});

//function to create new product on DB
function createProduct(){
    var form = document.getElementById("myform");
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("POST",url+"/Product",true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log(xhr.responseText);
        }else{
            console.log("Request error "+xhr.status);
        }
    };
    var jsonData = JSON.stringify(Object.fromEntries(formData.entries()));
    xhr.send(jsonData);
}
