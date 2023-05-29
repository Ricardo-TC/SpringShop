var productArray = new Array();
var strGlobal = "";
var cartArray = [];
var quant=0;
var newQuant=0;
var id=0;
var str="";
var fulldiv ="";
var total=0;
var totalItems=0;
var divtotal="";

//obtain and create data on productArray from products.json
fetch('./products.json')
.then(response => response.json())
.then(data => productArray=data)
.catch(error => console.log(error));

$('#searchbutton').click(function(){
    alert("Text: " + $("#test").text());
  });
  //windows.load

//function to add items to sumary from the modal
function addItemsToSummary(){
    try{
        quant = document.getElementById("modalinput").value;//quantity for product
        if(quant<1){
            alert("Please insert a value higer than 1.");
        }else{
            //adds data to cartArray
            cartArray.push({"Prod_id":strGlobal.prod_id,"Product":strGlobal.name,"Quantity":quant,"Price":strGlobal.price.toFixed(2),"Subtotal":((strGlobal.price*quant).toFixed(2))})
            printOnSummary();//call to function 
            tableReset();//clean table for new search
            document.getElementById('buy').disabled=false;//enable button
        }
    }catch(error){
        alert(error);
    }
}

//function to print product from id
function searchProduct(){
    try{
        id = document.getElementById("prodInput").value;//get the id from the input
        str="";
        strGlobal = productArray.find((element)=>element.prod_id==id);//find the product from the array
        //print the product on table
        str = "<tr><td colspan=\"1\">" + strGlobal.prod_id + "</td></tr>";
        document.getElementById("prod_id").innerHTML = str;
        str = "<tr><td colspan=\"1\">" + strGlobal.name + "</td></tr>";
        document.getElementById("name").innerHTML = str;
        str = "<tr><td colspan=\"1\">" + strGlobal.cost + "</td></tr>";
        document.getElementById("cost").innerHTML = str;
        str = "<tr><td colspan=\"1\">" + strGlobal.price + "</td></tr>";
        document.getElementById("price").innerHTML = str;    
        str = "<tr><td colspan=\"1\">" + strGlobal.description + "</td></tr>";
        document.getElementById("description").innerHTML = str;
        str = "<tr><td colspan=\"1\">" + strGlobal.dep_id + "</td></tr>";
        document.getElementById("department").innerHTML = str;
        document.getElementById("trigger-modalbutton").disabled = false;
        document.getElementById("modal-product").innerHTML = strGlobal.name;
    }catch(error){
        alert("Product not found");
    }
}

//function to print selected product to summary ticket
function printOnSummary(){
    //to clear the summary and concat others elements
    document.getElementById("summary-ul").innerHTML = "";
    document.getElementById("summary-total").innerHTML = "";
    $(".summary-total").text("");
    totalItems=0;
    total=0;
    fulldiv="";
    divtotal="";
    for(i=0;i<cartArray.length;i++){
        //print items to summary
        fulldiv += "<li>Product: <b>"+ cartArray[i].Product + "</b>"
            +", Quantity: <b>"+ cartArray[i].Quantity +"</b>"
            +", Price: <b>$"+ cartArray[i].Price +"</b>"
            +", Subtotal: <b>$" +cartArray[i].Subtotal +"</b>"  
            +"<button class=\"BTNsummary\" id=\"btnremove\" onclick=\"removeFromArray("+cartArray[i].Prod_id+")\">Remove</button>" //add a button for remove items
            +"<button class=\"BTNsummary\" id=\"btnmodify\" onclick=\"modifyList("+cartArray[i].Prod_id+")\">Modify</button>" //add a button to modify items
            +"</li>";
        document.getElementById("summary-ul").innerHTML = fulldiv;
        //print total items on label summary
        totalItems += (cartArray[i].Quantity*1);
        $(".summary-totalitems").text(totalItems);
        //print total the bottom summary
        total = total +(cartArray[i].Price*(cartArray[i].Quantity*1));
        divtotal = "Total: "+"<b>$"+total.toFixed(2)+"</b>";
        document.getElementById("summary-total").innerHTML = divtotal;
    }
}

//function to remove a product from the list
function removeFromArray(prod_id){
    let pos = cartArray.indexOf(cartArray.find((element)=>element.Prod_id==prod_id));//find the index in the array
    cartArray.splice(pos,1);//remove it from the array depending the position
    //if the array is empty clean the screen and block buy button
    if(cartArray.length==0){
        cancelBuy();
    }else{
        printOnSummary();
    }
}

//function to modify quantity of product
function modifyList(prod_id){
    promptQuantity();//ask for new quantity
    let pos = cartArray.indexOf(cartArray.find((element)=>element.Prod_id==prod_id));//find the index in the array
    //modify the array depending of position
    cartArray[pos].Quantity=newQuant;
    cartArray[pos].Subtotal=((newQuant*1)*(cartArray[pos].Price*1)).toFixed(2);
    printOnSummary();
}

//function to ask new quantity when modify product
function promptQuantity(){
    newQuant = prompt("Please enter new quantity of product:");
    if(newQuant==0){
        alert("Please not insert null");
        promptQuantity();
    }else{
        if(!/^[0-9]+$/.test(newQuant)){
            alert("Please only enter numeric characters only. (Allowed input:0-9)");
            promptQuantity();
        }
    }
}

//function to finish the buy and print ticket
function finishBuy(){
    createFileText(cartArray);
    cancelBuy();
    document.getElementById('buy').disabled=true;
}

//function to delete summary & table
function cancelBuy(){
    //clean the list
    document.getElementById("summary-ul").innerHTML = "";
    document.getElementById("summary-total").innerHTML = "";
    $(".summary-totalitems").text("");
    //clean the array
    cartArray.length = 0;
    tableReset();
    //location.reload();
    document.getElementById('buy').disabled=true;
}

//function to format date and create ticket on txt file
function createFileText(arrayToText) {
    let objectDate = new Date();
    let day = objectDate.getDate();
    let month = objectDate.getMonth()+1;
    let year = objectDate.getFullYear();
    let hour = objectDate.getHours();
    let minute = objectDate.getMinutes();
    let seconds = objectDate.getSeconds();
    let format = `${month}-${day}-${year}_${hour}-${minute}-${seconds}`;
    //var filename = "Ticketbox01"+ new Date().toJSON().slice(6,25) + ".txt";
    const title = "***** PURCHASE TICKET *****\n" 
    + "Date: " + format +"\n"
    + "Total of Products: " + totalItems + "\n"
    + "Total: $" + parseInt(total) +"\n"
    + "Products:\n";
    //const changeText = arrayToText.map(object => JSON.stringify(object)).join(", ");
    //const changeText = arrayToText.map(object => Object.values(object).join(", ")).join(", ");
    const changeText = arrayToText.map(object => object.Product + ' Quantity: ' + object.Quantity + ' Price: $' + object.Price + ' Subtotal: $' + object.Subtotal).join(", \n");
    const fileBlob = new Blob([title,changeText], { type: 'text/plain' });
    const urlFile = URL.createObjectURL(fileBlob);
    const downloadFile = document.createElement('a');
    downloadFile.href = urlFile;
    downloadFile.download = format+'-TicketBox01.txt';
    downloadFile.click();
    //downloadFile.textContent = 'Download File';
    //document.body.appendChild(downloadFile);
}

//function to erase table
function tableReset(){
    document.getElementById("prod_id").innerHTML = '';
    document.getElementById("name").innerHTML = '';
    document.getElementById("cost").innerHTML = '';
    document.getElementById("price").innerHTML = ''; 
    document.getElementById("description").innerHTML = ''; 
    document.getElementById("department").innerHTML = '';
    document.getElementById("prodInput").value='';
    document.getElementById("modalinput").value='';
    document.getElementById("trigger-modalbutton").disabled = true;
}