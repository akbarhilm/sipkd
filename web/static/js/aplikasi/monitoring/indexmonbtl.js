$(document).ready(function() {
    //document.getElementById("labelakunbelanja").style.display = "none";
    //setSaldoAwal();
    //document.getElementById("labelwilayah").style.display = "none";
    //setWilayah();
    //setDisplayWilayah();
    //setDate();
    //setPanelAwal(0);
    getBulan();
});

function getBulan(){
    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();

    $.getJSON(getbasepath() + "/monitoringbtl/json/getBulan", {tahun: tahun, idskpd: idskpd},
    function(result) {
        var banyak, kode, ket, opt;
        banyak = result.aData.length;
        

        if (banyak > 0) {
            opt = '<option value="00" selected>-- Pilih Bulan --</option>';
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodeBulan'];
                ket = result.aData[i]['bulanPosting'];
                opt += '<option value="' + kode + '" >' + ket + '</option>';
            }
        }
        else{
            opt = '<option value="XX" selected>-- Pilih Bulan --</option>';
        }
        $("#bulan").html(opt);
    });
}

function cetak() {
    var idskpd = $('#idskpd').val();
    //var kodegabung = $('#checkinduk').is(":checked")?1:0;
    //var tgl = formattanggal($('#tglPosting').val());
    var tahun = $("#tahun").val();
    //var jenislaporan = $("#jenislap").val();
    var bulan = $("#bulan").val();

    if (bulan == null || bulan == "00") {
        bootbox.alert("Bulan harus dipilih");
    } else if (bulan != null && bulan == "XX") {
        bootbox.alert("Data SIMPEG belum diproses");
    } else {
        var tahunbulan = tahun + "" + bulan;
        console.debug(tahunbulan);
        window.location.href = getbasepath() + "/monitoringbtl/json/prosescetak?tahun=" + tahun + "&idskpd=" + idskpd + "&bulan=" + tahunbulan;
    }
}
/*

function setDate() {
    var dd = new Date();
    var tahunSekarang = dd.getFullYear();
    var tahunAnggaran = $('#tahun').val();
    //console.log("masuk setDate()");
    if (tahunAnggaran == tahunSekarang) {
        setCurrentDate();
    } else {
        //console.log("masuk default");
        setDefaultDateDiffYear();
    }
}

function setDefaultDateDiffYear() {
    //console.log("masuk setDefaultDateDiffYear()");
    var tahunAnggaran = $('#tahun').val();
    var tanggal = "31/12/" + tahunAnggaran;
    //console.log("tanggal == "+tanggal);
    $("#tglPosting").val(tanggal);
}

function setCurrentDate() {
    var dd = new Date();
    var m = dd.getMonth() + 1;
    var y = dd.getFullYear();
    var d = dd.getUTCDate();

    if (d < 10) {
        d = '0' + d;
    }
    if (m < 10) {
        m = '0' + m;
    }

    var tanggal = d + "/" + m + "/" + y;
    $('#tglPosting').val(tanggal);
    // console.log(" tglPosting == " + tanggal);

}

function validasiTanggal() {
    var tanggal = $('#tglPosting').val();
    var yyyy = tanggal.substring(6);
    var tahunAnggaran = $('#tahun').val();
    if (yyyy != tahunAnggaran) {
        $("#tglPosting").val("31/12/" + tahunAnggaran);
    }
}

function setPanelAwal(jenis) {
    document.getElementById("panelskpdinduk").style.display = "none";
    if (jenis == 0) {
        document.getElementById("panelskpd").style.display = "none";
        document.getElementById("paneltgl").style.display = "none";
        document.getElementById("panelcetak").style.display = "none";
        $('#idskpdpop').val("");
    } else if (jenis == 1) {
        document.getElementById("panelskpd").style.display = "none";
        document.getElementById("paneltgl").style.display = "block";
        document.getElementById("panelcetak").style.display = "block";
        $('#idskpdpop').val("");
    } else if (jenis == 2) {
        document.getElementById("panelskpd").style.display = "block";
        document.getElementById("paneltgl").style.display = "block";
        document.getElementById("panelcetak").style.display = "block";
    }
    setDate();
}

function formattanggal(tgl) {
    var yy = tgl.substring(6);
    var mm = tgl.substr(3, 2);
    var dd = tgl.substr(0, 2);

    var tglgabung = yy + mm + dd;

    return tglgabung;
}


function sePanelSkpdInduk() {
    if ($("#skpdindukpop").val() == 1) {
        document.getElementById("panelskpdinduk").style.display = "block";
    } else {
        document.getElementById("panelskpdinduk").style.display = "none";
        document.getElementById("checkinduk").checked = false;
    }
}


 function setIdskpd(id) {
 //console.log("idskpd = "+id);
 $('#idskpd').val(id);
 setSaldoAwal();
 }
 
 function setSaldoAwal() {
 var bulan = $('#bulan').val();
 var idskpd = $('#idskpd').val();
 var jenis = $('#jenislap').val();
 
 //console.log("bulan = "+ bulan);
 //console.log("jenis = "+ jenis);
 
 if (jenis == "7" || jenis == "8" || jenis == "9") {
 $.getJSON(getbasepath() + "/formbku/json/getSaldoAwal", {jenis: jenis, bulan: bulan, idskpd: idskpd},
 function(result) {
 
 var saldo = result.aData[0]['saldoawal'];
 console.log("saldo = " + saldo);
 $("#saldo").val(saldo);
 
 });
 } else {
 $("#saldo").val("0");
 }
 
 }
 
 function setAkunBelanja() {
 var tahun = $('#tahun').val();
 var idskpd = $('#idskpd').val();
 
 $.getJSON(getbasepath() + "/formbku/json/getAkunBelanja", {tahun: tahun, idskpd: idskpd},
 function(result) {
 var banyak, kode, ket;
 var opt = "";
 banyak = result.aData.length;
 
 if (banyak > 0) {
 for (var i = 0; i < banyak; i++) {
 kode = result.aData[i]['kodeakun'];
 ket = result.aData[i]['namaakun'];
 
 opt += '<option value="' + kode + '" >' + ket + '</option>';
 
 }
 $("#akunbelanja").html(opt);
 
 }
 
 });
 }
 
 function setPanelAkun(){
 
 if($('#jenislap').val() == 27){
 document.getElementById("labelakunbelanja").style.display = "block";
 setAkunBelanja();
 } else {
 document.getElementById("labelakunbelanja").style.display = "none";
 }
 
 }
 
 function setWilayah() {
 $.getJSON(getbasepath() + "/formbku/json/getWilayah", {},
 function(result) {
 var banyak, kode, ket;
 var opt = "";
 banyak = result.aData.length;
 
 for (var i = 0; i < banyak; i++) {
 kode = result.aData[i]['kodeWilayah'];
 ket = result.aData[i]['ketWilayah'];
 
 opt += '<option value="' + kode + '" >' + ket + '</option>';
 
 }
 $("#wilayah").html(opt);
 });
 }
 */