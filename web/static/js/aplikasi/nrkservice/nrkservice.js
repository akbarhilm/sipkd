$(document).ready(function() {


});

function fun() 
{
   var data="hello";
   $.get("soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD?nrk=181909", function(result) {
        data = result;
        
        console.log("result = "+result); 
   }).error(function(xhr){
       console.error("NRK = "+xhr.NRK);
  alert(xhr);
});

}

function tes() {
    console.log("nrk : " + $('#nrk').val());

    $.getJSON("http://soadev.jakarta.go.id/rest/gov/dki/simpeg/ws/getPegawaiSIPKD?nrk=181909", // {nrk: "181909"},
            function(result) {
                console.log("result : " + result);
            });
}

function getDataNrk() {
    var varurl =  getbasepath() + "/nrkservice/json/getnrkAsli";

    var dataac = [];

    var datajour = {
        nrk: "181909" //$('#nrk').val()
    };
    dataac = datajour;

    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: varurl,
       data: JSON.stringify(dataac),
        success: function(data) {
            console.log(data);

        },
        error: function(xhr) {
            console.error(xhr);
        }
    }).always(function(data) {
        
    });
}

function getDataNrkGet() {
    var varurl = getbasepath() + "/nrkservice/json/getnrk";

    var dataac = [];

    var datajour = {
        nrk: "181909" //$('#nrk').val()
    };
    dataac = datajour;

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: varurl,
        data: JSON.stringify(dataac),
        success: function(data) {
            console.log(data);

            //$('#dkinamabank').val(data.namabank);
           
        },
        error: function(xhr) {
            console.error(xhr);
        }
    }).always(function(data) {
        
    });
}
