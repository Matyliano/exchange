var $select = $('#currencies');

$.getJSON('currencies.json', function(currencies){
    $select.html('');

    for(var i = 0; i < currencies['currencies'].length; i++){
        $select.append('<option id="' + currencies['currencies'][i]['id'] + '">' + '</option>');
    }
});