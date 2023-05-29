/**
 * 
 */
let prodArray = [
  {
    "prod_id": 1,
    "name": "Sabritas saladas",
    "cost": 12,
    "price": 14.5,
    "description": "Papas fritas sabor sal",
    "dep_id": 1
  },
  {
    "prod_id": 2,
    "name": "Sabritas limon",
    "cost": 12,
    "price": 14.5,
    "description": "Papas fritas sabor sal",
    "dep_id": 1
  },
  {
    "prod_id": 3,
    "name": "Coca-Cola 1.5lts",
    "cost": 20,
    "price": 25,
    "description": "Refresco cocacola de 1.5 litros",
    "dep_id": 1
  },
  {
    "prod_id": 4,
    "name": "Coca-Cola 2lts",
    "cost": 25,
    "price": 32,
    "description": "Refresco cocacola de 2 litros",
    "dep_id": 1
  },
  {
    "prod_id": 5,
    "name": "Naranja",
    "cost": 12.5,
    "price": 15.25,
    "description": "Saco de naranjas para jugo",
    "dep_id": 2
  },
  {
    "prod_id": 6,
    "name": "Dona glaceada",
    "cost": 4.5,
    "price": 9.5,
    "description": "Dona glaceado de sabor variado",
    "dep_id": 3
  },
  {
    "prod_id": 7,
    "name": "Atun",
    "cost": 120,
    "price": 155.59,
    "description": "Unidad de pescado",
    "dep_id": 4
  },
  {
    "prod_id": 8,
    "name": "Lomo de res",
    "cost": 122,
    "price": 158.55,
    "description": "Kilo de carne de res",
    "dep_id": 5
  },
  {
    "prod_id": 9,
    "name": "Vitaminas",
    "cost": 34.52,
    "price": 66.78,
    "description": "Bote de pastillas con 50 piezas",
    "dep_id": 6
  },
  {
    "prod_id": 10,
    "name": "Destornillador de cruz",
    "cost": 23.32,
    "price": 31.5,
    "description": "Destornillador de cruz de 5 pulgadas de largo",
    "dep_id": 7
  },
  {
    "prod_id": 11,
    "name": "Pantalla Led 41 plg",
    "cost": 7451.11,
    "price": 8999.99,
    "description": "Pantalla LED de 21 pulgadas marca LG sin marco",
    "dep_id": 8
  },
  {
    "prod_id": 12,
    "name": "Refrigerador 11 pies",
    "cost": 6453.22,
    "price": 7432.99,
    "description": "Refrigerador MABe de 11 pies color gris",
    "dep_id": 9
  },
  {
    "prod_id": 13,
    "name": "Sillon reclinable negro",
    "cost": 1221.12,
    "price": 3215.99,
    "description": "Sillon individual reclinable color negro, palanca lateral",
    "dep_id": 10
  },
  {
    "prod_id": 14,
    "name": "Pantalon mezclilla",
    "cost": 66.55,
    "price": 225.55,
    "description": "Pantalon de mezclilla generica sin marca",
    "dep_id": 11
  },
  {
    "prod_id": 15,
    "name": "Vino español",
    "cost": 82,
    "price": 104.22,
    "description": "Vino tinto español, 750ml",
    "dep_id": 12
  },
  {
    "prod_id": 16,
    "name": "Ramen Tonkotsu",
    "cost": 17,
    "price": 21.6,
    "description": "Sopa de fideos con sabor a cerdo",
    "dep_id": 12
  },
  {
    "prod_id": 17,
    "name": "Product1",
    "cost": 10,
    "price": 20,
    "description": "Description product",
    "dep_id": 1
  },
  {
    "prod_id": 18,
    "name": "Product2",
    "cost": 10,
    "price": 20,
    "description": "Description product",
    "dep_id": 1
  },
  {
    "prod_id": 19,
    "name": "Product2",
    "cost": 10,
    "price": 20,
    "description": "Description product",
    "dep_id": 1
  },
  {
    "prod_id": 20,
    "name": "Product3",
    "cost": 10,
    "price": 20,
    "description": "Description product",
    "dep_id": 1
  },
  {
    "prod_id": 21,
    "name": "Product4",
    "cost": 10,
    "price": 20,
    "description": "Description product",
    "dep_id": 1
  }];

const btn = document.querySelector('#btn1');
btn.setAttribute("type", "submit");
btn.addEventListener("click",printArray(prodArray));

  function printArray(prodArray){
	  console.log(prodArray);
  }
  
  
  
async function getProducts(){
	let url = 'http://localhost:8080/Product.json';
	console.log("test");
	try{
		let res = await fetch(url);
		return await res.json();
	}catch(error){
		console.log(error);
	}
}

let res = fetch("http://localhost:8080/Product",{
			mode: 'no-cors',
			headers: {'Access-Control-Allow-Origin':'*',
			'Accept': 'application/json',
			'Content-Type': 'application/json'},
			method: 'GET'
		});
		//test = res.json();
		//console.log(test);