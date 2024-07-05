$(document).ready(function() {
    setcurrent();

});

function setcurrent() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }

    if (mm < 10) {
        mm = '0' + mm;
    }

    today = dd + '/' + mm + '/' + yyyy;

    $("#tanggal").val(today);

}

function downloadrekon() {
    var tgl = $("#tanggal").val();

    window.location.href = getbasepath() + "/rekonbankdki/json/getrekon?tanggal=" + tgl;

}
