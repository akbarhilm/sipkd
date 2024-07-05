$(document).ready(function() {
   


});

function ubahlink( ) {
    var idkegiatan = $('#idkegiatan').val();
  // $("#spprincitable :input[type='text']").attr("readonly", false);
    $("#popupakun").attr("href", getbasepath() + "/bast/listpopupakun/" + idkegiatan + "?target='_top");
}
