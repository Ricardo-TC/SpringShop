var productArray = [];
var str = "";
var strCom = "";
var prodid = 0;
var url = "http://localhost:8080";
var deptArray = [];
var discArray = [];
var discid = 0;

//loads the information from the backend when page load
document.addEventListener("DOMContentLoaded", function() {
    getArray();
    getDepartment();
    getDiscount();
});

//makes fetch with discount on backend
async function getDiscount(){
    discArray = 
    await fetch(url+"/Discount",{
        method: 'GET',
        mode: 'cors'
    })
    .then(response => response.json())
    .catch(error => {
        console.log(error);
        alert(error);
    })
}

//makes fetch with product on backend
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

//makes fetch whth department on backend
async function getDepartment(){
    deptArray =
    await fetch(url+"/Department", {
        method: 'GET',
        mode: 'cors'
    })
    .then(response => response.json())
    .catch(error => {
        console.log(error);
        alert(error);
    });
    setDepartments();
}

//validattions
function validateInput(event){
    var key = String.fromCharCode(event.keyCode || event.which);
    var allowedChars = ['x','d'];
    if(!allowedChars.includes(key.toLowerCase())){
        event.preventDefault();
        return false;
    }
}

function validateDate(event){
    var key = String.fromCharCode(event.keyCode || event.which);
    var allowedChars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'];
    if(!allowedChars.includes(key.toLowerCase())){
        event.preventDefault();
        return false;
    }
}



//when clic call getProducts()
$(document).ready(function() {
    $("#getall").click(function() {
        getProducts();
    });
});

//function to print all products on table
function getProducts(){
    str = "";
    strCom = "";
    for(let i=0;i<productArray.length;i++){
        strCom +=
            "<tr><td colspan=\"1\">" + productArray[i].prod_id + "</td>"
            + "<td colspan=\"1\">" + productArray[i].name + "</td>" 
            + "<td colspan=\"1\">" + productArray[i].cost + "</td>" 
            + "<td colspan=\"1\">" + productArray[i].price +"</td>"
            + "<td colspan=\"1\">" + productArray[i].description + "</td>" 
            + "<td colspan=\"1\">" + showDepartment(productArray[i].dep_id) + "</td>"
            + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtn\" onclick=\"deleteProduct("+productArray[i].prod_id+")\">Delete</button>"
            + "<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"txtValue("+productArray[i].prod_id+")\" "
            + "type=\"button\" data-toggle=\"modal\" data-target=\"#mymodal\">Modify</button></td></tr>";
        document.getElementById("producttable").innerHTML = strCom;
    }
}

//function to call getalldiscounts()
$(document).ready(function(){
    $("#getalldiscounts").click(function(){
        getAllDiscounts();
    })
});

//function to print all discounts on table
function getAllDiscounts(){
    if(discArray.length!=0){
        strCom = "";
        for(let i=0;i<discArray.length;i++){
            strCom +=
                "<tr><td colspan=\"1\">" + discArray[i].disc_id + "</td>"
                + "<td colspan=\"1\">" + discArray[i].prod_id + "</td>"
                + "<td colspan=\"1\">" + discArray[i].discount_type + "</td>"
                + "<td colspan=\"1\">" + discArray[i].discount_amount + "</td>"
                + "<td colspan=\"1\">" + discArray[i].date_begin + "</td>"
                + "<td colspan=\"1\">" + discArray[i].date_expire + "</td>"
                + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtndisc\" onclick=\"deleteDiscount("+discArray[i].disc_id+")\">Delete</button>"
                + "<button class=\"actionbtn\" id=\"modifybtndisc\" onclick=\"txtValueDisc("+discArray[i].disc_id+")\" "
                + "type=\"button\" data-toggle=\"modal\" data-target=\"#mymodal2\">Modify</button></td></tr>";
            document.getElementById("discounttable").innerHTML = strCom;
        }
    }
}

//when clic call findProduct()
$(document).ready(function() {
    $("#searchbutton").click(function() {
        findProduct();
    });
});

//function to find one product
function findProduct(){
    try{
        str = "";
        strCom = "";
        prodid = document.getElementById("searchinput").value;
        if(prodid==0){
            alert("Please introduce product ID");
        }else{
            str = productArray.find((element)=>{                return element.prod_id==prodid            });
                strCom = 
                    "<tr><td colspan=\"1\">" + str.prod_id + "</td>" 
                    + "<td colspan=\"1\">" + str.name + "</td>" 
                    + "<td colspan=\"1\">" + str.cost + "</td>" 
                    + "<td colspan=\"1\">" + str.price +"</td>"
                    + "<td colspan=\"1\">" + str.description + "</td>" 
                    + "<td colspan=\"1\">" + showDepartment(str.dep_id) + "</td>"
                    + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtn\" onclick=\"deleteProduct("+str.prod_id+")\">Delete</button>"
                    + "<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"txtValue("+str.prod_id+")\" "
                    + "type=\"button\" data-toggle=\"modal\" data-target=\"#mymodal\">Modify</button></td></tr>";
                document.getElementById("producttable").innerHTML = strCom;
        }
        if(discArray.length!=0){
            str = "";
            strCom = "";
            document.getElementById("discounttable").innerHTML = strCom;
            str = discArray.find((element)=>{return element.prod_id==prodid});
            if(str==undefined){
                return;
            }
            strCom =
                "<tr><td colspan=\"1\">" + str.disc_id + "</td>"
                + "<td colspan=\"1\">" + str.prod_id + "</td>"
                + "<td colspan=\"1\">" + str.discount_type + "</td>"
                + "<td colspan=\"1\">" + str.discount_amount + "</td>"
                + "<td colspan=\"1\">" + str.date_begin + "</td>"
                + "<td colspan=\"1\">" + str.date_expire + "</td>"
                + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtndisc\" onclick=\"deleteDiscount("+str.disc_id+")\">Delete</button>"
                + "<button class=\"actionbtn\" id=\"modifybtndisc\" onclick=\"txtValueDisc("+str.disc_id+")\" "
                + "type=\"button\" data-toggle=\"modal\" data-target=\"#mymodal2\">Modify</button></td></tr>";
            document.getElementById("discounttable").innerHTML = strCom;
        }
    }catch(error){
        alert("Data not found.");
        console.log(error);
    }
}

//function to send data into modify form
function txtValueDisc(discountid){
    strCom = discArray.find((element)=>{return element.disc_id==discountid});
    document.getElementById("modaldiscid").value = strCom.disc_id;
    document.getElementById("modaldisctype").value = strCom.discount_type;
    document.getElementById("modaldiscamount").value = strCom.discount_amount;
    document.getElementById("modaldiscbegin").value = strCom.date_begin;
    document.getElementById("modaldiscexpire").value = strCom.date_expire;
    discid = strCom.disc_id;
}

//function to send data into modify Form
function txtValue(idData){
    strCom = productArray.find((element)=>{return element.prod_id==idData});
    document.getElementById("modalname").value = strCom.name;
    document.getElementById("modalcost").value = strCom.cost;
    document.getElementById("modalprice").value = strCom.price;
    document.getElementById("modaldescription").value = strCom.description;
    prodid = strCom.prod_id;//id of product form modifyProduct() function
}

//function to call modifyProduct()
$(document).ready(function(){
    $("#sendbtnformmodal").click(function(){
        modifyProduct();
    })
});

//function to modify products
async function modifyProduct(){
    strCom = "";
    strCom = productArray.find((element)=>{return element.prod_id==prodid});
    var form = document.getElementById("myformmodal");
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("PATCH",url+"/Product/"+strCom.prod_id, true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log(xhr.responseText);
        }else{
            console.log("Error, status: "+xhr.status);
        }
    };
    var jsonData = JSON.stringify(Object.fromEntries(formData.entries()));
    xhr.send(jsonData);
    location.reload();
}

//function to call modifydiscounts()
$(document).ready(function(){
    $("#sendbtnformmodaldisc").click(function(){
        modifyDiscount();
    })
});

//function to modify discounts
async function modifyDiscount(){
    strCom = "";
    strCom = discArray.find((element)=>{return element.disc_id==discid});
    var form = document.getElementById("discformmodal");
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("PATCH",url+"/Discount/"+strCom.disc_id,true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log(xhr.responseText);
        }else{
            console.log("Error, status: "+xhr.status);
            console.log(xhr.responseText);
        }
    }
    var jsonData = JSON.stringify(Object.fromEntries(formData.entries()));
    xhr.send(jsonData);
    location.reload();
}

//function to create delete product
async function deleteProduct(prodid){
    await fetch(url+"/Product/"+prodid, {
        method: "DELETE"
    })
    .then(response => response.json())
    .catch(error => {
        console.error();
    });
    location.reload();
}

//function to delete a discount
async function deleteDiscount(discid){
    await fetch(url+"/Discount/"+discid,{
        method: "DELETE"
    })
    .then(response => response.json())
    .catch(error => {
        console.error();
    })
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
    var form1 = document.getElementById("myform");
    var formData1 = new FormData(form1);
    var xhr1 = new XMLHttpRequest();
    xhr1.open("POST",url+"/Product/createProduct",true);
    xhr1.setRequestHeader("Content-Type","application/json");
    xhr1.onload = function(){
        if(xhr1.status === 200){
            console.log(xhr1.responseText);
        }else{
            console.log("Error, status: "+xhr1.status);
        }
    };
    var jsonData1 = JSON.stringify(Object.fromEntries(formData1.entries()));
    xhr1.send(jsonData1);
    location.reload();
}

//function to call new discount
$(document).ready(function(){
    $("#sendbtnformdisc").click(function(){
        createDiscount();
    })
});

//function to create new discount
function createDiscount(){
    var form = document.getElementById("discform");
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("POST",url+"/Discount",true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log(xhr.responseText);
        }else{
            console.log("error, status: "+xhr.status);
        }
    };
    var jsonData = JSON.stringify(Object.fromEntries(formData.entries()));
    xhr.send(jsonData);
    location.reload();
}

//function to print the name of the department on the table
function showDepartment(idData){
    var dept = deptArray.find((element)=>{return element.dep_id==idData});
    return dept.dep_name;
}

//function to load all departments on select options into Form (on createProduct() and modifyProduct())
function setDepartments(){
    for(var i=0;i<deptArray.length;i++){
        var option1 = document.createElement("option");
        option1.value = deptArray[i].dep_id;
        option1.text = deptArray[i].dep_name;
        var option2 = document.createElement("option");
        option2.value = deptArray[i].dep_id;
        option2.text = deptArray[i].dep_name;
        document.getElementById("optdepartment").appendChild(option1);
        document.getElementById("optdepartmentmodal").appendChild(option2);
    }
}

