var deptArray = [];
var url = "http://localhost:8080";
var str = "";
var strCom = "";
var deptid = 0;

document.addEventListener("DOMContentLoaded", function(){
    loadDeptArray();
});

async function loadDeptArray(){
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
}

$(document).ready(function(){
    $("#btngetall").click(function(){
        getDepartments();
    });
});

function getDepartments(){
    str = "";
    strCom = "";
    for(let i=0;i<deptArray.length;i++){
        strCom +=
            "<tr><td colspan=\"1\">" + deptArray[i].dep_id + "</td>"
            +"<td colspan=\"1\">"+ deptArray[i].dep_name +"</td>"
            +"<td colspan=\"1\">"+ "<button class=\"actionbtn\" id=\"deletebtn\" onclick=\"deleteDepartment("+ deptArray[i].dep_id +")\">Delete</button>" 
            +"<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"txtValue("+ deptArray[i].dep_id +")\" "
            +"type=\"button\" data-toggle=\"modal\" data-target=\"#mymodal\">Modify</button> </td></tr>";
        document.getElementById("departmenttable").innerHTML = strCom;
    }
}

$(document).ready(function(){
    $("#sendbtnform").click(function(){
        createDepartment();
    })
});

function createDepartment(){
    var form = document.getElementById("mydeptform");
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("POST",url+"/Department/createDepartment",true);
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
    //location.reload();
}

function txtValue(idData){
    strCom = deptArray.find((element)=>{return element.dep_id==idData});
    document.getElementById("modalname").value = strCom.dep_name;
    deptid = strCom.dep_id;
}

$(document).ready(function(){
    $("#sendbtnformmodal").click(function(){
        modifyDepartment();
    })
});

function modifyDepartment(){
    strCom = "";
    strCom = deptArray.find((element)=>{return element.dep_id==deptid});
    var form = document.getElementById("myformmodal");
    var formData =  new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open("PATCH",url+"/Department/"+strCom.dep_id,true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log(xhr.responseText);
        }else{
            console.log("Error, request: "+xhr.status);
        }
    };
    var jsonData = JSON.stringify(Object.fromEntries(formData.entries()));
    xhr.send(jsonData);
    location.reload();
}

async function deleteDepartment(deptid){
    await fetch(url+"/Department/"+deptid, {
        method: "DELETE"
    })
    .then(response => response.json())
    .catch(error => {
        console.error();
    });
    location.reload();
}