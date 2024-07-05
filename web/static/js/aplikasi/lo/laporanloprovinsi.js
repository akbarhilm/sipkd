$(document).ready(function() {
    jenislaporan(0);

    if ($("#idskpdpop").val() !== "") {
        setComboBulan();
    }
});


function cetakjurnal() {
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpdpop").val();
    var bulanvar = tahun + $("#bulan").val();
    var jenislaporan = $("#jenislap").val();

    //console.log("bulan == " + bulanvar);
    
    if (idskpd == "" || $("#bulan").val() == ""|| $("#bulan").val() == null) {
        bootbox.alert("Data Harus Lengkap");
    } if (jenislaporan == 1 || jenislaporan == 3 || jenislaporan == 5){
         bootbox.alert("Belum Ada Laporan");
    } else {
        window.location.href = getbasepath() + "/lo/json/prosescetak?tahun=" + tahun + "&idskpd=" + idskpd + "&bulan=" + bulanvar + "&jenislaporan=" + jenislaporan;
    } 
}

function setComboBulan() {
    var tahun = $("#tahun").val();
    var idskpd = $('#idskpdpop').val();


    $.getJSON(getbasepath() + "/lo/json/setBulan", {tahun: tahun, idskpd: idskpd},
    function(result) {
        //console.log(result);

        var banyak, kode, ket;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeBulan'];
                ket = result.aData[i]['ketBulan'];

                opt += '<option value="' + kode + '" >' + ket + '</option>';
            }
        }

        $("#bulan").html(opt);

    });
}

function jenislaporan(id) {
    $("#jenislap").html("");

    var idskpd = $('#idskpdpop').val();

    //console.log("id skpd -- "+idskpd);
    var opt = '<option value="1">1 - LO Permendagri 13/2006 Provinsi</option>';
    opt += '<option value="2">2 - LO PP 71/2010 Provinsi</option>';

    if (idskpd !== "") {
        opt += '<option value="3">3 - LO Permendagri 13/2006</option>';
        opt += '<option value="4">4 - LO PP 71/2010</option>';

        if (id == 1) {
            opt += '<option value="5">5 - LO Permendagri 13/2006 (Gabungan)</option>';
            opt += '<option value="6">6 - LO PP 71/2010 (Gabungan)</option>';
        }
    }

    $("#jenislap").html(opt);

}

function getIdInduk() {
    var idskpd = $('#idskpdpop').val();

    $.getJSON(getbasepath() + "/lo/json/getIdInduk", {idskpd: idskpd},
    function(result) {
        //console.log("id induk -- "+result);
        //idinduk = result;

        jenislaporan(result);

        setComboBulan();
    });
}

