$(document).ready(function() {
    //document.getElementById("labelakunbelanja").style.display = "none";
    //setSaldoAwal();
    //document.getElementById("labelwilayah").style.display = "none";
    //setWilayah();
    //setDisplayWilayah();
    document.getElementById("paneljenislap").style.display = "none";
    setDate();
    //setCurrentDate();
    //setPanelAwal(0);
    //console.log("tanggalset == "+$('#tglPosting').val());
});

function setDate(){
    var dd = new Date();
    var tahunSekarang = dd.getFullYear();
    var tahunAnggaran = $('#tahun').val();
    //console.log("masuk setDate()");
    if(tahunAnggaran == tahunSekarang){
        setCurrentDate();
    } else {
        //console.log("masuk default");
        setDefaultDateDiffYear();
    }
}

function setDefaultDateDiffYear(){
    //console.log("masuk setDefaultDateDiffYear()");
    var tahunAnggaran = $('#tahun').val();
    var tanggal = "31/12/" + tahunAnggaran;
    //console.log("tanggal == "+tanggal);
    $("#tglPosting").val("31/12/2016");
}

function setCurrentDate() {
    var dd = new Date();
    var m = dd.getMonth() + 1;
    var y = dd.getFullYear();
    var d = dd.getUTCDate();
    var tanggal;

    if (d < 10) {
        d = '0' + d;
    }
    if (m < 10) {
        m = '0' + m;
    }

    tanggal = d + "/" + m + "/" + y;
    $('#tglPosting').val(tanggal);
    // console.log(" tglPosting == " + tanggal);

}

function validasiTanggal(){
    var tanggal = $('#tglPosting').val();
    var yyyy = tanggal.substring(6);
    var tahunAnggaran = $('#tahun').val();
    if(yyyy != tahunAnggaran){
        $("#tglPosting").val("31/12/" + tahunAnggaran);
    }
}

function setPanelAwal(jenis){
    if(jenis == 0){
        document.getElementById("panelskpd").style.display = "none";
        document.getElementById("paneltgl").style.display = "none";
        document.getElementById("panelcetak").style.display = "none";
        $('#idskpdpop').val("");
    } else if (jenis == 1){
        document.getElementById("panelskpd").style.display = "none";
        document.getElementById("paneltgl").style.display = "block";
        document.getElementById("panelcetak").style.display = "block";
        $('#idskpdpop').val("");
    } else if (jenis == 2){
        document.getElementById("panelskpd").style.display = "block";
        document.getElementById("paneltgl").style.display = "block";
        document.getElementById("panelcetak").style.display = "block";
    }
    setCurrentDate();
}

function formattanggal(tgl){
    var yy = tgl.substring(6);
    var mm = tgl.substr(3, 2);
    var dd = tgl.substr(0, 2);
    
    var tglgabung = yy+mm+dd;
    
    return tglgabung;
}

/*
function setDisplayWilayah(){
    if ($("#idskpd").val() == 761) {
        document.getElementById("labelwilayah").style.display = "block";
    } else {
        document.getElementById("labelwilayah").style.display = "none";
    }
}
*/
function cetak() {
    var idskpd = $('#idskpd').val();
    var tgl = formattanggal($('#tglPosting').val());
    var tahun = $("#tahun").val();
    var jenislaporan = $("#jenislap").val();
    
    window.location.href = getbasepath() + "/lapbelanja/json/prosescetak?tahun=" + tahun + "&idskpd=" + idskpd + "&tanggal=" + tgl + "&jenislaporan=" + jenislaporan ;
}
/*
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