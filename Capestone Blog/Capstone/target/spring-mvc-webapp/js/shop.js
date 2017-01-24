
$(document).ready(function () {
    addCart();   
    sendAjax();
});

var QuantityArray = [
    [1, 0],
    [2, 0],
    [3, 0],
    [4, 0],
    [5, 0],
    [6, 0],
    [7, 0],
    [8, 0]
];

var productsArray = [
    [1, "Colombian", 6.75],
    [2, "Espresso", 6.50],
    [3, "French Roast", 6.00],
    [4, "Gourmet", 6.75],
    [5, "Hazelnut", 6.50],
    [6, "House Blend", 6.00],
    [7, "Original", 6.00],
    [8, "Vienna", 6.75]
];

function prepareJSONArray() {
    var jsonarray = [];

    for (var i = 0; i < QuantityArray.length; i++) {
        if (QuantityArray[i][1] > 0) {
            jsonarray.push({productId: QuantityArray[i][0], quantity: QuantityArray[i][1]});
        }
    }
    return jsonarray;
}

function sendAjax() {

    $('#placeOrder').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'order',
            data: JSON.stringify({

                name: $('#pickupName').val(),
                total: total(),
                orderDetails: prepareJSONArray()
//                        [{productId: 2, quantity: 2.00}]
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            alert("Successfully placed order");
        });
    });
}

function addCart() {

    $('.coffeeButtons').click(function () {
        var productId = ($(this).attr('id'));

        var getQuantity = "#quantity" + productId;
        var name = productsArray[productId][1];
        var price = productsArray[productId][2];
        var quantity = QuantityArray[productId][1];


        if (!isNaN(parseInt($(getQuantity).val()))) {

            if (Exists(name)) {
                //ADD QUANTITY
                quantity = parseInt($(getQuantity).val())
                        + parseInt(($('#ShoppingCart').find("tr:contains('" + name + "') td:eq(1)").text()));
                //REMOVE ROW

                $("table#ShoppingCart tr:contains('" + name + "')").remove();
                // CREATE NEW ROW
                $('#contentRows').append('<tr><td>' + name + '</td>' + '<td>' + quantity + '</td>'
                        + '<td>' + '$ ' + (quantity * price) + '</td></tr>');

            } else {
                // CREATE NEW ROW
                quantity = $(getQuantity).val();
                $('#contentRows').append('<tr><td>' + name + '</td>' + '<td>' + quantity + '</td>'
                        + '<td>' + '$ ' + (quantity * price) + '</td></tr>');
                
            }
           QuantityArray[productId][1] = quantity;
           total();
        }
         
        $('#quantity' + productId).val('');
    });
 
}

 
 function total(){
     var total = 0;
//     var table = $('#ShoppingCart tr');           
//     table.each(function () {  
//          $('td:eq(2)', this).each(function () {
//               total = ($(this).text()).replace(/\D/g,'');
//          });     
//    });
         for (var i = 0; i < 8; i++) {             
              if (QuantityArray[i][1] > 0) {                  
                 total = productsArray[i][2] * QuantityArray[i][1] + total;               
             }
         }
     
    $('#total').empty();
    $('#total').append('<p id="totalStyle">Total: $' + total + '</p>');
 }

function Exists(beanName) {
    var beanName;

    var table = $('#ShoppingCart tr');
    var boolean = false;

    table.each(function () {

        $('td', this).each(function () {
            if (beanName === $(this).text()) {
                boolean = true;
            }
        });
    });

    return boolean;
}

function setQuantity(name, quantity) {

    for (var i = 0; i < 8; i++) {
        if (name === productsArray[i][1]) {
        }
    }
}
function productArray() {//INCOMPLETE
    //  ID &&  QUANTITY
    products = [];
    for (var i = 0; i < 8; i++) {

    }
}