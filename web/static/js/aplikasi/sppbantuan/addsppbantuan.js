


$(document).ready(function () {
    var nospd = $('#noSpd').val();
    if (nospd == "") {
        $('#popupspd').show();
    } else {
        $('#popupspd').hide();
    }
    try {
        hitungnilaisppsisa();
    } catch (e) {

    }
});

function hitungnilaisppsisa(objval) {
    /*var searchIDs = $("#nilaiSppSisa")(function () {
     return $(this).val();
     }).get();
     var sisa = 0;
     $.each(searchIDs, function (idx, data) {
     sisa += parseFloat(accounting.unformat($("#nilaiSpd" - "#nilaiSpp").val(), ","));
     });*/

    var nilaispd = parseFloat(accounting.unformat($("#nilaiSpd").val(), ","));
    var nilaispp = parseFloat(accounting.unformat(objval, ","));
    if (nilaispp == 0)
        nilaispp = parseFloat(accounting.unformat($("#nilaiSpp").val(), ","));
    var sisa = accounting.formatNumber((nilaispd - nilaispp), 2, '.', ",");
    console.log(sisa);
    $("#nilaiSppSisaAkhir").text(sisa);
    $("#nilaiSppSisa").val(sisa);

}

function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/sppbantuan/indexsppbantuan/");
    } else {
        window.location.replace(getbasepath() + "/sppbantuan/indexsppbantuan");
    }

}

