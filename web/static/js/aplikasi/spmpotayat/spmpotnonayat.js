
$(document).ready(function () {

    $("#btlspdtablebody").hide();
    gettotalspm($("#nospm").val());
});

function gettotalspm(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/totspmjson/" + idspm,
            function (result) {
                $('#totspm').val(accounting.formatNumber(result.aData[0]['totSpm'], 2, '.', ","));
                $('#totspmhide').val(result.aData[0]['totSpm']);
                getValTabel(idspm);
                hitung();
            });

}

function getValTabel(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/valtabel/" + idspm,
            function (result) {
                for (i = 0; i <= 10; i++) {
                    c = i + 1;
                    totspm = $('#totspmhide').val();
                    //alert(totspm);
                    if (c == 1) {

                    }
                    else if (c == 2) {
                    } else {
                        $('#vpot' + c).val(accounting.formatNumber(result.aData[i]['nilaiPot'], 2, '.', ","));
                    }
                    $("#tampildataholder" + c).append(result.aData[i]['pot']);
                    $('#cpot' + c).val(result.aData[i]['cPot']);
                    $('#status' + c).val(result.aData[i]['status']);
                }

                // $('#vpot1').val(accounting.formatNumber(result.aData[0]['nilaiPot'], 2, '.', ","));               

            });

    $("#btlspdtablebody").show();

}

function hitung() {

    totspm = $('#totspmhide').val();
    dati = totspm * 10 / 100;
    $('#vpot1').val(accounting.formatNumber(dati, 2, '.', ","));
    dati = totspm * 1.5 / 100;
    $('#vpot2').val(accounting.formatNumber(dati, 2, '.', ","));

}

function hitung6() {


    totspm = $('#totspmhide').val();
    persen = $("#persen6").val();
    dati = totspm * persen / 100;
    $('#vpot' + 6).val(accounting.formatNumber(dati));

}

function cekmoney() {
   // $(".edit2").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
}

function ceksubmitnilai( ) {

    submitnilai( );

}

function submitnilai( ) {
    var varurl = getbasepath() + "/spmpotnonayat/json/prosespindahsimpan";
    var dataac = [];
    var datapeg = {
        cpot1: $("#cpot1").val(),
        cpot2: $("#cpot2").val(),
        cpot3: $("#cpot3").val(),
        cpot4: $("#cpot4").val(),
        cpot5: $("#cpot5").val(),
        cpot6: $("#cpot6").val(),
        cpot7: $("#cpot7").val(),
        cpot8: $("#cpot8").val(),
        cpot9: $("#cpot9").val(),
        cpot10: $("#cpot10").val(),
        vpot1: $("#vpot1").val(),
        vpot2: $("#vpot2").val(),
        vpot3: $("#vpot3").val(),
        vpot4: $("#vpot4").val(),
        vpot5: $("#vpot5").val(),
        vpot6: $("#vpot6").val(),
        vpot7: $("#vpot7").val(),
        vpot8: $("#vpot8").val(),
        vpot9: $("#vpot9").val(),
        vpot10: $("#vpot10").val(),
        status1: $("#status1").val(),
        status2: $("#status2").val(),
        status3: $("#status3").val(),
        status4: $("#status4").val(),
        status5: $("#status5").val(),
        status6: $("#status6").val(),
        status7: $("#status7").val(),
        status8: $("#status8").val(),
        status9: $("#status9").val(),
        status10: $("#status10").val(),
        idspm: $("#nospm").val()
    }
    dataac = datapeg;

    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function (data) {
        //grid();
        bootbox.alert("Data SPM Potongan Ayat berhasil disimpan");
    });
}



function settgl() {
    var isi = $("#ssspm").val();
    var tahun = isi.substr(0, 4);
    var bulan = isi.substr(5, 2);
    var hari = isi.substr(8, 2);
    var hasil = hari + "/" + bulan + "/" + tahun;

    $("#sispm").val(hasil);
    // alert("#sspm");
}
