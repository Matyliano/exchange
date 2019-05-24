
var $select = $('#currencies');

$.getJSON('currencies.json',function (data) {
    $select.html('');

    for(var i =0; i < data['currencies'].length;i++){
        $select.append('<option id="' + data['currencies'][i]['code'] + '">' + data['currencies'][i]['name'] + '</option>');
    }
});
//
// $(document).ready(function(){
//     function f(id,code) {
//         var html ='';
//         $.getJSON('currencies.json', function(data){
//             html += '<option value=""> Select ' + code + '</option>';
//             $each(data, function (key,value) {
//                 if (id == 'currencies') {
//                     html += '<option value=" ' + value.code + '">' + value.name + '</option>';
//                 }
//
//
//             });
//         });
//     }
// }


//
// $(function(){
//     var currencyOptions;
//     $.getJSON('currencies.json',function(result){
//         $.each(result, function(i,currency) {
//             //<option value='countrycode'>contryname</option>
//             currencyOptions+="<option value='"
//                 +currency.code+
//                 "'>"
//                 +currency.name+
//                 "</option>";
//         });
//         $('#currency').html(currencyOptions);
//     });
// });
