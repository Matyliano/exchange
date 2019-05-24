
var $select = $('#currencies-dropdown');

$.getJSON('currencies.json',function (data) {
    $select.html('');

    for(var i =0; i < data['currencies'].length;i++){
        $select.append('<option id="' + data['currencies'][i]['abbreviation'] + '">' + data['currencies'][i]['name'] + '</option>');
    }

});

