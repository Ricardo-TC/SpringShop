var ticket = 0;
var prodid = 0;
var totalGlobal = 0;
var newQuant = 0;
var mod = 1;
var str = "";
var strCom = "";
const url = "http://localhost:8080";
var productArray = [];
var cartArray = [];

$(document).ready(function(){
    $("#ticketbtn").click(function(){
        newTicket();
    })
});

async function newTicket(){
    try{
        const response = await fetch(url+"/Purchase/NewTicket",{
            method: 'POST',
            mode: 'cors',
            headers: { 'Content-Type': 'application/json' }
        });
        if(response.ok){
            ticket = await response.json();
            $("#ticketbtn").attr('disabled', true);
            $("#ticketspan").text(ticket);
            $("#ticketinput").val(ticket);
            $("#searchbutton").attr('disabled', false);
            $("#datespan").text(formatDate());
        }else{            console.log(response.status);        }
    }catch(error){        console.log(error);    }
}

$(document).ready(function(){
    $("#consultbtn").click(function(){
        consultProducts();
    })
});

async function consultProducts(){
    try{
        const response = await fetch(url+"/Product",{
            method: 'GET',
            mode: 'cors',
            headers: {'Content-Type':'application/json'}
        });
        if(response.ok){
            productArray = await response.json();
            strCom = "";
            productArray = sortArray(productArray);
            for(let i=0;i<productArray.length;i++){
                strCom +=
                    "<tr><td colspan=\"1\">" + productArray[i].prod_id + "</td>"
                    + "<td colspan=\"1\">" + productArray[i].name + "</td>" 
                    + "<td colspan=\"1\">$" + productArray[i].price +"</td>"
                    + "<td conspan=\"1\">N/A</td></tr>";
                $("#producttable").html(strCom);
            }
        }else{            console.log(response.status);        }
    }catch(error){        console.log(error);    }
}

$(document).ready(function(){
    $("#searchbutton").click(function(){
        prodid = $("#searchinput").val();
        if(prodid!=0){
            printOneProduct();
        }else{
            printMoreProducts();
        }
    })
});

async function printOneProduct(){
    try{
        const response = await fetch(url+"/Product/"+prodid,{
            method: 'GET',
            mode: 'cors',
            header: {'Content-Type':'application/json'}
        });
        if(response.ok){
            productArray = await response.json();
            strCom = "";
            strCom +=
                "<tr><td colspan=\"1\">" + productArray.prod_id + "</td>"
                + "<td colspan=\"1\">" + productArray.name + "</td>" 
                + "<td colspan=\"1\">$" + productArray.price +"</td>"
                + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"modalCart("+productArray.prod_id+")\" "
                + "type=\"button\" data-toggle=\"modal\" data-target=\"#cartmodal\">Add to Cart</button></td></tr>";
            $("#producttable").html(strCom);
        }else{            console.log(response.status)        }
    }catch(error){        console.log(error);    }
}

async function printMoreProducts(){
    try{
        const response = await fetch(url+"/Product",{
            method: 'GET',
            mode: 'cors',
            headers: {'Content-Type':'application/json'}
        });
        if(response.ok){
            productArray = await response.json();
            productArray = sortArray(productArray);
            strCom = "";
            for(let i=0;i<productArray.length;i++){
                strCom +=
                    "<tr><td colspan=\"1\">" + productArray[i].prod_id + "</td>"
                    + "<td colspan=\"1\">" + productArray[i].name + "</td>" 
                    + "<td colspan=\"1\">$" + productArray[i].price +"</td>"
                    + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"modifybtn\" onclick=\"modalCart("+productArray[i].prod_id+")\" "
                    + "type=\"button\" data-toggle=\"modal\" data-target=\"#cartmodal\">Add to Cart</button></td></tr>";
                $("#producttable").html(strCom);
            }
        }else{            console.log(response.status);        }
    }catch(error){        console.log(error);    }
}

function modalCart(idProd){
    if(productArray.length>1){
        let tempArray = productArray.find((element)=>{return element.prod_id==idProd});
        $("#spanprodmodal").text(tempArray.name);
    }else{        $("#spanprodmodal").text(productArray.name)    }
    $("#inputcartmodalproduct").val(idProd);
}

$(document).ready(function(){
    $("#sendbtncartmodal").click(function(){
        addToCart();
    })
});

async function addToCart(){
    try{
        let pid = $("#inputcartmodalproduct").val();
        let quant = $("#inputcartmodalquantity").val();
        if(cartArray.length!=0){
            let pos = cartArray.findIndex((element)=>{return element.prod_id==pid});
            if(pos > -1){
                newQuant = cartArray[pos].quantity  + parseInt(quant);
                mod = 2;
                updateProductFromCart(cartArray[pos].id);
            }else{
                const response = await fetch(url+"/Purchase/Cart?prod_id="+pid+"&quantity="+quant,{
                    method: 'POST',
                    mode: 'cors'
                });
                if(response.ok){
                    printCart();
                }
            }
        }else{
            const response = await fetch(url+"/Purchase/Cart?prod_id="+pid+"&quantity="+quant,{
                method: 'POST',
                mode: 'cors'
            });
            if(response.ok){
                printCart();
            }
        }
    }catch(error){        console.log(error);    }
}

async function printCart(){
    try{
        const response = await fetch(url+"/Purchase/"+ticket,{
            method: 'GET',
            mode: 'cors'
        });
        if(response.ok){
            cartArray = await response.json();
            strCom = "";
            for(let i=0;i<cartArray.length;i++){                
                strCom +=
                    "<tr><td colspan=\"1\">" + getNameForCart(cartArray[i].prod_id) + "</td>"
                    + "<td colspan=\"1\">" + cartArray[i].quantity + "</td>" 
                    + "<td colspan=\"1\">$" + getPriceForCart(cartArray[i].prod_id) + "</td>" 
                    + "<td colspan=\"1\">$" + cartArray[i].subtotal +"</td>"
                    + "<td colspan=\"1\">" + "<button class=\"actionbtn\" id=\"deletebtncart\" onclick=\"deleteProductFromCart("+cartArray[i].id+")\">Delete</button>"
                    + "<button class=\"actionbtn\" id=\"modifybtncart\" onclick=\"updateProductFromCart("+cartArray[i].id+")\">Modify</button></td></tr>";
                $("#carttable").html(strCom);
                printTotal();
            }
            $("#finishbtn").attr('disabled',false);
            $("#cancelbtn").attr('disabled',false);
        }
    }catch(error){        console.log(error);    }
}

async function updateProductFromCart(cid){
    try{
        let tempCartArray = [];
        var response = "";
        switch(mod){
            case 1://///modify from cart table
                tempCartArray = cartArray.find((element)=>{return element.id==cid});
                promptQuantity();
                response = await fetch(url+"/Purchase/UpdateProductFromCart?id="+cid+"&prod_id="+tempCartArray.prod_id+"&quantity="+newQuant,{
                    method: 'PATCH',
                    mode: 'cors'
                });
                if(response.ok){
                    console.log("Product updated");
                    printCart();
                }
            break;
            case 2://///modify from duplicated existing product
                tempCartArray = cartArray.find((element)=>{return element.id==cid});
                response = await fetch(url+"/Purchase/UpdateProductFromCart?id="+cid+"&prod_id="+tempCartArray.prod_id+"&quantity="+newQuant,{
                    method: 'PATCH',
                    mode: 'cors'
                });
                if(response.ok){
                    console.log("Product updated");
                    printCart();
                }
                mod = 1;
            break;
            default:console.log("Error when updating cart");
            break;
        }
    }catch(error){        console.log(error);    }
}

function promptQuantity(){
    newQuant = prompt("Please introduce quantity of product:");
    if(newQuant==0){
        alert("Please no insert null");
        promptQuantity();
    }else{
        if(!/^[0-9]+$/.test(newQuant)){
            alert("Please only enter numbers. (Allowed input:0-9)");
            promptQuantity();
        }
    }
}

async function deleteProductFromCart(cid){
    try{
        const response = await fetch(url+"/Purchase/DeleteProductFromCart/"+cid,{
            method: 'DELETE',
            mode: 'cors'
        });
        if(response.ok){
            console.log('Product removed from table');
            printCart();
        }
    }catch(error){        console.log(error);    }
}


function getNameForCart(pid){
    let tempArray = productArray.find((element)=>{return element.prod_id==pid});
    return tempArray.name;
}

function getPriceForCart(pid){
    let tempArray = productArray.find((element)=>{return element.prod_id==pid});
    return tempArray.price;
}

async function printTotal(){
    try{
        const response = await fetch(url+"/Purchase/GetTotal",{
            method: 'GET',
            mode: 'cors'
        });
        if(response.ok){
            let total = await response.json();
            totalGlobal = total.toFixed(2);
            $("#totalspan").html(totalGlobal);
        }
    }catch(error){        console.log(error);    }
}


$(document).ready(function(){
    $("#finishbtn").click(function(){
        finishPurchase();
    })
});

async function finishPurchase(){
    try{
        const response = await fetch(url+"/Purchase/FinishPurchasePrintTicket",{
            method: 'PATCH',
            mode: 'cors'
        });
        if(response.ok){
            cartArray = await response.json();
            cartArray = sortArray(cartArray);
            createFileToText(cartArray);
            $("#ticketbtn").attr('disabled',false);
            $("#searchbutton").attr('disabled',true)
            $("#ticketinput").val(0);
            $("#producttable").html("");
            $("#carttable").html("");
            $("#totalspan").html("");
            $("#ticketspan").html("");
            $("#datespan").html("");
            $("#finishbtn").attr('disabled',true);
            $("#cancelbtn").attr('disabled',true);
            totalGlobal = 0;
            ticket = 0;
            cartArray.length  = 0;
            productArray.length = 0;
            prodid = 0;
            newQuant = 0;
            mod = 1;
            str = "";
            strCom = "";
        }
    }catch(error){        console.log(error);    }
}

$(document).ready(function(){
    $("#cancelbtn").click(function(){
        cancelBuy();
    })
});

function cancelBuy(){
    location.reload();
}

function sortArray(array){
    array.sort(function(a,b){
        if(a.name>b.name){return 1;}
        if(a.name<b.name){return -1;}
        return 0;
    })
    return array;
}

function formatDate(){
    let objectDate = new Date();
    let day = objectDate.getDate();
    let month = objectDate.getMonth()+1;
    let year = objectDate.getFullYear();
    let hour = objectDate.getHours();
    let minute = objectDate.getMinutes();
    let seconds = objectDate.getSeconds();
    let format = `${month}-${day}-${year} ${hour}-${minute}-${seconds}`;
    return format;
}

function createFileToText(arrayToText){
    const title = "***** PURCHASE TICKET *****\n" 
    + "Date: " + formatDate() +"\n"
    + "Total of Products: " + cartArray.length + "\n"
    + "Ticket: " + ticket + "\n"
    + "Total: $" + parseFloat(totalGlobal) +"\n"
    + "Products:\n";
    const changeText = arrayToText.map(object => '-' + getNameForCart(object.prod_id) + ' Quantity: ' + object.quantity + ' Price: $' + getPriceForCart(object.prod_id) + ' Subtotal: $' + object.subtotal).join(", \n");
    const fileBlob = new Blob([title,changeText], { type: 'text/plain' });
    const urlFile = URL.createObjectURL(fileBlob);
    const downloadFile = document.createElement('a');
    downloadFile.href = urlFile;
    downloadFile.download = formatDate()+'-TicketBox01.txt';
    downloadFile.click();
}